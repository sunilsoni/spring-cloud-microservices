/**
 * 
 */
package com.jci.job.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.job.api.req.BatchInsertReq;
import com.jci.job.api.req.BatchUpdateReq;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.ItemSuccessRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.PoSuccessRes;
import com.jci.job.api.res.SuppDetailsRes;
import com.jci.job.api.res.SuppSuccessRes;
import com.jci.job.azure.FlatFile;
import com.jci.job.entity.PoEntity;
import com.jci.job.repo.JobRepo;
import com.jci.job.utils.CommonUtils;
import com.jci.job.utils.Constants;
import com.jci.job.utils.PrepareBatchInsertReq;
import com.jci.job.utils.PrepareFlatFile;
import com.microsoft.azure.storage.StorageException;

/**
 * @author cdevdat
 *
 */
@Service
@RefreshScope
public class ApiClientServiceImpl implements ApiClientService { // NO_UCD (unused code)

	private static final Logger LOG = LoggerFactory.getLogger(ApiClientServiceImpl.class);
	@Autowired
	ApiClientImpl apigeeClient;
	
	@Autowired
	private JobRepo repo;
	
    @Value("${all.erp.names}")
    private String allErps;
    
	@Autowired
	private FlatFile config;

	String erpName;
	String region;
	String plant;
	
	@Override
	public String getPoDetails()  throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting ApigeeClientService.getPoDetails ####");
		
		PoDetailsRes responseBody=null;
		CommonUtils utils = new CommonUtils();
		
		/**
		 * Get Region mapping file
		 */
		HashMap<String,HashMap<String,String>>  regionsMapping = utils.getRegionMapping(config.getRegionUrl());
		LOG.info("regionsMapping--->"+regionsMapping);
		
		ResponseEntity<PoDetailsRes> apigeeResponse =null;
		
		//Asumming only one erps for now with 1 file only
		String responseStatus = null;
		 for (Map.Entry<String,HashMap<String,String>> regions : regionsMapping.entrySet()){
			 
			 HashMap<String,String> val = regions.getValue();
			 erpName =  val.get("erp");//Define in property file
			 region =  val.get("region");
			 plant =  val.get("plant");
			  
			 /**
			  * Starting apigee call
			 */
			 LOG.info("===========Starting apigee call===>");
			 apigeeResponse =  apigeeClient.getPoDetails(erpName,region,plant,"**","**");
			 LOG.info("<===========Ending apigee call====");
			 
			 
			 responseBody = apigeeResponse.getBody();
			LOG.info("responseBody-1->"+responseBody);
				
			//Make it dynamic
			String lineID="lineID";
			String requestID="requestID";
			
			BatchInsertReq  req = PrepareBatchInsertReq.prepareReq(responseBody,erpName,region,plant,lineID,requestID);
			LOG.info("req-->"+req);
			 
			/**
			 * Start Storing data in Azure tables
			 */
			List<String> successList = repo.batchInsert(req);
			LOG.info("successList-->"+successList);
			
			 //End Storing data in Azure tables 
			
			/**
			 * Starting apigee call for successList of POs
			 */
			PoSuccessRes PoSuccessRes = new PoSuccessRes();
			List<HashMap<String,String>> poList = new ArrayList<HashMap<String,String>>();
			for (String po : successList) {
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("orderNumber", po);
				poList.add(map);
			}
			PoSuccessRes.setPoList(poList);
			
			LOG.info("===========Starting apigee call for status update===>"+PoSuccessRes);
			responseStatus =  apigeeClient.getPoDetailsRes(PoSuccessRes,erpName,region,plant);
			LOG.info("responseStatus-->"+responseStatus);
			LOG.info("<===========Enduing apigee call for status update===");
			 
		 }
		LOG.info("### Ending ApigeeClientService.getPoDetails ####");
		return responseStatus;
	}
	
	@Override
	public String getItemDetails() throws InvalidKeyException, URISyntaxException, StorageException {
		
		LOG.info("### Starting ApigeeClientService.getItemDetails ####");
		/**
		 * Get Region mapping file
		 */
		CommonUtils utils = new CommonUtils();
		HashMap<String,HashMap<String,String>>  regionsMapping = utils.getRegionMapping(config.getRegionUrl());
		LOG.info("regionsMapping--->"+regionsMapping);
		
		
		//Make it dynamic
		String customerItemID="customerItemID";
		String suppID="supplierID";
		String responseStatus=null;
		for (Map.Entry<String,HashMap<String,String>> regions : regionsMapping.entrySet()){
			 
			 HashMap<String,String> val = regions.getValue();
			 erpName =  val.get("erp");//Define in property file
			 region =  val.get("region");
			 plant =  val.get("plant");
			 
			 /**
			  * Starting apigee call
			 */
			 LOG.info("====Starting Apigee Item Call===>");
			 ResponseEntity<ItemDetailsRes> response = apigeeClient.getItems(erpName,region,plant,"**");
			LOG.info("<====Ending Apigee Item Call===");
			
			ItemDetailsRes responseBody = response.getBody();
			 
			BatchInsertReq  req = PrepareBatchInsertReq.prepareItemReq(responseBody,erpName,region, plant,customerItemID,suppID);
			
			List<String> successList = repo.batchInsert(req);
			
			/**
			 * Starting apigee call for successList of Items
			 */
			ItemSuccessRes itemSuccessRes = new ItemSuccessRes();
			List<HashMap<String,String>> itemList = new ArrayList<HashMap<String,String>>();
			for (String itemKey : successList) {
				String[] arr = itemKey.split("_");
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("customerItemID", arr[0]);
				map.put("supplierID", arr[1]);
				itemList.add(map);
			}
			itemSuccessRes.setItemList(itemList);
			responseStatus =  apigeeClient.getItemsRes(itemSuccessRes, erpName,region, plant);
			LOG.info("responseStatus-->"+responseStatus);
		}
		
		LOG.info("### Ending ApigeeClientService.getItemDetails ####");
		return responseStatus;
	}

	@Override
	public String getSuppDetails() throws InvalidKeyException, URISyntaxException, StorageException {
		/**
		 * Get Region mapping file
		 */
		CommonUtils utils = new CommonUtils();
		HashMap<String,HashMap<String,String>>  regionsMapping = utils.getRegionMapping(config.getRegionUrl());
		
		//Make it dynamic
		String suppID="supplierID";
		String responseStatus=null;
		for (Map.Entry<String,HashMap<String,String>> regions : regionsMapping.entrySet()){			 
			HashMap<String,String> val = regions.getValue();
			erpName =  val.get("erp");//Define in property file
			region =  val.get("region");
			plant =  val.get("plant");

			/**
			  * Starting apigee call
			 */
			LOG.info("====Starting Apigee Supplier Call===>");
			ResponseEntity<SuppDetailsRes> response = apigeeClient.getSupp(erpName,region,plant,"**");
			LOG.info("<====Ending Apigee Supplier Call===");
			
			SuppDetailsRes responseBody = response.getBody();
			
			BatchInsertReq  req = PrepareBatchInsertReq.prepareSuppReq(responseBody,erpName,region, plant,suppID);
			
			List<String> res = repo.batchInsert(req);
			
			/**
			 * Starting apigee call for successList of Suppliers
			 */
			SuppSuccessRes itemSuccessRes = new SuppSuccessRes();
			List<HashMap<String,String>> suppList = new ArrayList<HashMap<String,String>>();
			for (String id : res) {
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("customerItemID",id);
				suppList.add(map);
			}
			itemSuccessRes.setSuppList(suppList);
			
			responseStatus =  apigeeClient.getSuppRes(itemSuccessRes, erpName,region, plant);
			LOG.info("responseStatus--->"+responseStatus);
			
		}
		return responseStatus;
	}
	
/*
 * Processing flat files for all erps which have po number status as in transit
 */
	@Override
	public String processPoFlatFile() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting ApigeeClientService.processFlatFile ####");
		
		CommonUtils utils = new CommonUtils();
		//HashMap<String,HashMap<String,String>>  regionsMapping = utils.getRegionMapping(config.getRegionUrl());
		
		//Flat file format should be in this format. Destination(e.g. e2open ) flat file format 
		HashMap<String,HashMap<Integer,String>>  suppNameToMapping = utils.getDestMapping(config.getPoUrl());
		
		PrepareFlatFile file = new PrepareFlatFile();
		Map<String,List<String>> fileNameToRowsMap = null;
		
		List<String> successList = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		
		Map<String,List<String>> pkToSuccessList = new HashMap<String,List<String>>();
		Map<String,List<String>> pkToErrorList = new HashMap<String,List<String>>();
		
		String[] erpArr  = allErps.split(",");
		for (int i=0;i<erpArr.length;i++){
			//HashMap<String,String> val = regions.getValue();
			String partitionKey = CommonUtils.getPartitionKey(erpArr[i]);
			
			List<Map<String,List<HashMap<String, Object>>>>  list = repo.getFlatFileData(partitionKey);
			
			for (Map<String,List<HashMap<String, Object>>> poNumToItemListMap : list) {
				 
				 /**
				  * Loop: No of  Supplier mapping files present in directory  
				  */
					//String suppName=null;
					for (Map.Entry<String,HashMap<Integer,String>> mapping : suppNameToMapping.entrySet()){
						//suppName = mapping.getKey();						
						fileNameToRowsMap = file.prepareSuppData(mapping.getValue(),poNumToItemListMap,config);
						
						/**
						 * Code to process flat files for supplier(e.g.e2open):
						 */
						for (Map.Entry<String,List<String>> entry : fileNameToRowsMap.entrySet()){
							File toFile = new File(entry.getKey());
							 try {
							    	FileUtils.writeLines(toFile,"UTF-8", entry.getValue(),false);
								} catch (IOException e) {
									e.printStackTrace();
								}
							boolean isSuccess =  PrepareFlatFile.processFile(toFile,config.getE2openUrl());
							LOG.info(" isSuccess--->"+ isSuccess);
							
							LOG.info(" entry.getKey()--->"+ entry.getKey());
							if(isSuccess){
								successList.add(entry.getKey().split("\\.")[0]);
							}else{
								errorList.add(entry.getKey().split("\\.")[0]);
							}
						}
						
					}
			}//end poNumToItemListMap
			
			if(successList.size()>0){
				pkToSuccessList.put(erpArr[i], successList);
			}
			if(errorList.size()>0){
				pkToErrorList.put(erpArr[i], errorList);
			}
		}
		//Update status in DB
		updateStatus(pkToSuccessList,pkToErrorList);		
		LOG.info("### Ending ApigeeClientService.processFlatFile ####");
		return null;
	}
	

	/*
	 * Processing flat files for all erps which have supplier status as in transit
	 */
	@Override
	public String processSuppFlatFile() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting ApigeeClientService.processSuppFlatFile ####");
		
		CommonUtils utils = new CommonUtils();
		HashMap<String,HashMap<Integer,String>>  suppNameToMapping = utils.getDestMapping(config.getSuppUrl());
		
		PrepareFlatFile file = new PrepareFlatFile();
		Map<String,List<String>> fileNameToRowsMap = null;
		
		List<String> successList = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		
		Map<String,List<String>> pkToSuccessList = new HashMap<String,List<String>>();
		Map<String,List<String>> pkToErrorList = new HashMap<String,List<String>>();
		 
		
		String[] erpArr  = allErps.split(",");
		for (int i=0;i<erpArr.length;i++){		 
			String partitionKey = CommonUtils.getPartitionKey(erpArr[i]);
			
			List<HashMap<String, Object>>  list = repo.getFlatFileData(partitionKey,Constants.TABLE_SUPPLIER);
			Map<String,List<HashMap<String, Object>>> map = new HashMap<String,List<HashMap<String, Object>>>();
			map.put("SupplierFile", list);
			
				 
				 /**
				  * Loop: No of  Supplier mapping files present in directory  
				  */
				for (Map.Entry<String,HashMap<Integer,String>> mapping : suppNameToMapping.entrySet()){
						//supplierName = mapping.getKey();						
						fileNameToRowsMap = file.prepareSuppData(mapping.getValue(),map,config);
						
						/**
						 * Code to process flat files for supplier(e.g.e2open):
						 */
						for (Map.Entry<String,List<String>> entry : fileNameToRowsMap.entrySet()){
							//File toFile = new File(entry.getKey());
							File toFile=null;
							try {
								toFile = File.createTempFile(entry.getKey(), ".txt");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							 
							 try {
							    	FileUtils.writeLines(toFile,"UTF-8", entry.getValue(),false);
								} catch (IOException e) {
									e.printStackTrace();
								}
							boolean isSuccess =  PrepareFlatFile.processFile(toFile,config.getE2openUrl());
							LOG.info(" isSuccess--->"+ isSuccess);
							
							if(isSuccess){
								successList.add(entry.getKey().split("\\.")[0]);
							}else{
								errorList.add(entry.getKey().split("\\.")[0]);
							}
						}
					}
			if(successList.size()>0){
				pkToSuccessList.put(erpArr[i], successList);
			}
			if(errorList.size()>0){
				pkToErrorList.put(erpArr[i], errorList);
			}
		}
		
		//Update status in DB
		updateStatus(pkToSuccessList,pkToErrorList);		
		LOG.info("### Ending ApigeeClientService.processSuppFlatFile ####");
		return null;
	}

	/*
	 * Processing flat files for all erps which have Items status as in transit
	 */
	@Override
	public String processItemFlatFile() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting ApigeeClientService.processItemFlatFile ####");
		
		CommonUtils utils = new CommonUtils();
		HashMap<String,HashMap<Integer,String>>  suppNameToMapping = utils.getDestMapping(config.getItemUrl());
		
		PrepareFlatFile file = new PrepareFlatFile();
		Map<String,List<String>> fileNameToRowsMap = null;
		
		List<String> successList = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		
		Map<String,List<String>> pkToSuccessList = new HashMap<String,List<String>>();
		Map<String,List<String>> pkToErrorList = new HashMap<String,List<String>>();
		 
		
		String[] erpArr  = allErps.split(",");
		for (int i=0;i<erpArr.length;i++){		 
			String partitionKey = CommonUtils.getPartitionKey(erpArr[i]);
			
			List<HashMap<String, Object>>  list = repo.getFlatFileData(partitionKey,Constants.TABLE_ITEM);
			LOG.info(" list--->"+ list);
			
			Map<String,List<HashMap<String, Object>>> map = new HashMap<String,List<HashMap<String, Object>>>();
			map.put("ItemFile", list);
			
				 
				 /**
				  * Loop: No of  Supplier mapping files present in directory  
				  */
				for (Map.Entry<String,HashMap<Integer,String>> mapping : suppNameToMapping.entrySet()){
						//supplierName = mapping.getKey();						
						fileNameToRowsMap = file.prepareSuppData(mapping.getValue(),map,config);
						LOG.info(" fileNameToRowsMap--->"+ fileNameToRowsMap);
						/**
						 * Code to process flat files for supplier(e.g.e2open):
						 */
						for (Map.Entry<String,List<String>> entry : fileNameToRowsMap.entrySet()){
							File toFile = new File(entry.getKey());
							//File toFile=null;
							/*try {
								toFile = File.createTempFile(entry.getKey(), ".txt");
							} catch (IOException e1) {
								e1.printStackTrace();
							}*/
							 
							 try {
							    	FileUtils.writeLines(toFile,"UTF-8", entry.getValue(),false);
								} catch (IOException e) {
									e.printStackTrace();
								}
							boolean isSuccess =  PrepareFlatFile.processFile(toFile,config.getE2openUrl());
							LOG.info(" isSuccess--->"+ isSuccess);
							
							LOG.info(" entry.getKey()--->"+ entry.getKey());
							if(isSuccess){
								successList.add(entry.getKey().split("\\.")[0]);
							}else{
								errorList.add(entry.getKey().split("\\.")[0]);
							}
						}
					}
			if(successList.size()>0){
				pkToSuccessList.put(erpArr[i], successList);
			}
			if(errorList.size()>0){
				pkToErrorList.put(erpArr[i], errorList);
			}
		}
		
		
		//Update status in DB
		updateStatus(pkToSuccessList,pkToErrorList);		
		//END
		
		LOG.info("### Ending ApigeeClientService.processItemFlatFile ####");
		return null;
	
	}
	
	private void updateStatus(Map<String,List<String>> pkToSuccessList,Map<String,List<String>> pkToErrorList) {
		BatchUpdateReq updateReq =null;
		for (Map.Entry<String,List<String>> entry : pkToSuccessList.entrySet()){
			updateReq = new  BatchUpdateReq ();
			updateReq.setSuccess(true);
			updateReq.setErpName(entry.getKey().toUpperCase());
			 
			String partitionKey = CommonUtils.getPartitionKey(entry.getKey().toUpperCase());
			try {
				List<PoEntity> poEntity = repo.getPoDetails(partitionKey,entry.getValue());
				HashMap<String,List<PoEntity>> tableNameToEntityMap = new HashMap<String,List<PoEntity>>();
				tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poEntity);
				updateReq.setTableNameToEntityMap(tableNameToEntityMap);		
				repo.batchUpdate(updateReq);	
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				LOG.error("### Exception in   ####",e);
				e.printStackTrace();
			}
			
		}
		
		for (Map.Entry<String,List<String>> entry : pkToErrorList.entrySet()){
			updateReq = new  BatchUpdateReq ();
			updateReq.setSuccess(false);
			updateReq.setErpName(entry.getKey().toUpperCase());
			String partitionKey = CommonUtils.getPartitionKey(entry.getKey().toUpperCase());
			try {
				List<PoEntity>  poEntity = repo.getPoDetails(partitionKey,entry.getValue());
				HashMap<String,List<PoEntity>> tableNameToEntityMap = new HashMap<String,List<PoEntity>>();
				tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poEntity);
				updateReq.setTableNameToEntityMap(tableNameToEntityMap);
				repo.batchUpdate(updateReq);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				LOG.error("### Exception in   ####",e);
				e.printStackTrace();
			}
		}
	} 
	
}
