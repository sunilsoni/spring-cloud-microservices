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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.job.api.req.BatchUpdateReq;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.ItemSuccessRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.PoSuccessRes;
import com.jci.job.api.res.SupplierDetailsRes;
import com.jci.job.api.res.SupplierSuccessRes;
import com.jci.job.apis.ApiClient;
import com.jci.job.apis.SupplierClient;
import com.jci.job.azure.BatchInsertReq;
import com.jci.job.azure.FlatFile;
import com.jci.job.entity.PoEntity;
import com.jci.job.repo.JobRepo;
import com.jci.job.utils.CommonUtils;
import com.jci.job.utils.Constants;
import com.jci.job.utils.PrepareBatchInsertReq;
import com.jci.job.utils.PrepareFlatFile;
import com.microsoft.azure.storage.StorageException;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author cdevdat
 *
 */
@Service
public class ApiClientServiceImpl implements ApiClientService {

	private static final Logger LOG = LoggerFactory.getLogger(ApiClientServiceImpl.class);
	@Autowired
	ApiClientImpl apigeeClient;
	
	@Autowired
	SupplierClient suppClient;

	@Autowired
	private JobRepo repo;
	
	
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
			 LOG.info("erpName-->"+erpName);
			 LOG.info("region-->"+region);
			 LOG.info("plant-->"+plant);
			  
			 /**
			  * Starting apigee call
			 */
			 LOG.info("===========Starting apigee call===>");
			 apigeeResponse =  apigeeClient.getPoDetails(erpName,region,plant,"**","**");
			 LOG.info("<===========Ending apigee call====");
			 
			 
			/* String str =  apigeeClient.getPoDetails(erpName,region,plant,"**","**");
			 System.out.println("str===>"+str);*/
			 
			 responseBody = apigeeResponse.getBody();
			LOG.info("responseBody-1->"+responseBody);
			//System.out.println("apigeeResponse===>"+apigeeResponse);
				
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
		 System.out.println("===========Ending apigee call===>");
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
		String supplierID="supplierID";
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
			System.out.println("response===>"+response);
			LOG.info("<====Ending Apigee Item Call==="+response);
			
			ItemDetailsRes responseBody = response.getBody();
			LOG.info("responseBody-->"+responseBody);
			 
			BatchInsertReq  req = PrepareBatchInsertReq.prepareItemReq(responseBody,erpName,region, plant,customerItemID,supplierID);
			LOG.info("req-->"+req);
			
			List<String> successList = repo.batchInsert(req);
			LOG.info("successList--->"+successList);
			
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
			
			LOG.info("===========Starting apigee call for status update===>"+itemSuccessRes);
			
			responseStatus =  apigeeClient.getItemsRes(itemSuccessRes, erpName,region, plant);
			
			LOG.info("responseStatus-->"+responseStatus);
		}
		
		LOG.info("### Ending ApigeeClientService.getItemDetails ####");
		return responseStatus;
	}

	@Override
	public String getSupplierDetails() throws InvalidKeyException, URISyntaxException, StorageException {
		/**
		 * Get Region mapping file
		 */
		CommonUtils utils = new CommonUtils();
		HashMap<String,HashMap<String,String>>  regionsMapping = utils.getRegionMapping(config.getRegionUrl());
		LOG.info("regionsMapping--->"+regionsMapping);
		
		//Make it dynamic
		String supplierID="supplierID";
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
			ResponseEntity<SupplierDetailsRes> response = apigeeClient.getSuppliers(erpName,region,plant,"**");
			LOG.info("<====Ending Apigee Supplier Call==="+response);
			
			SupplierDetailsRes responseBody = response.getBody();
			LOG.info("responseBody-->"+responseBody);
			
			BatchInsertReq  req = PrepareBatchInsertReq.prepareSupplierReq(responseBody,erpName,region, plant,supplierID);
			LOG.info("req-->"+req);
			
			List<String> res = repo.batchInsert(req);
			LOG.info("res--->"+res);
			
			/**
			 * Starting apigee call for successList of Suppliers
			 */
			SupplierSuccessRes itemSuccessRes = new SupplierSuccessRes();
			List<HashMap<String,String>> suppList = new ArrayList<HashMap<String,String>>();
			for (String suppID : res) {
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("customerItemID",suppID);
				suppList.add(map);
			}
			itemSuccessRes.setSupplierList(suppList);
			LOG.info("===========Starting apigee call for status update===>"+itemSuccessRes);
			
			responseStatus =  apigeeClient.getSuppliersRes(itemSuccessRes, erpName,region, plant);
			LOG.info("responseStatus--->"+responseStatus);
			
		}
		return responseStatus;
	}
	

	@Override
	public String processPoFlatFile() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting ApigeeClientService.processFlatFile ####");
		
		CommonUtils utils = new CommonUtils();
		HashMap<String,HashMap<String,String>>  regionsMapping = utils.getRegionMapping(config.getRegionUrl());
		HashMap<String,HashMap<Integer,String>>  supplierNameToMapping = utils.getDestMapping(config.getPoUrl());
		
		PrepareFlatFile file = new PrepareFlatFile();
		Map<String,List<String>> fileNameToRowsMap = null;
		
		List<String> successList = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		
		Map<String,List<String>> pkToSuccessList = new HashMap<String,List<String>>();
		Map<String,List<String>> pkToErrorList = new HashMap<String,List<String>>();
		
		
		for (Map.Entry<String,HashMap<String,String>> regions : regionsMapping.entrySet()){			 
			HashMap<String,String> val = regions.getValue();
			String partitionKey = CommonUtils.getPartitionKey(val.get("erp"));
			
			List<Map<String,List<HashMap<String, Object>>>>  list = repo.getFlatFileData(partitionKey);
			
			for (Map<String,List<HashMap<String, Object>>> poNumToItemListMap : list) {
				 
				 /**
				  * Loop: No of  Supplier mapping files present in directory  
				  */
					//String supplierName=null;
					for (Map.Entry<String,HashMap<Integer,String>> mapping : supplierNameToMapping.entrySet()){
						//supplierName = mapping.getKey();						
						fileNameToRowsMap = file.prepareSupplierData(mapping.getValue(),poNumToItemListMap,config);
						
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
									//finalRes.setError(true);
									//return finalRes;
								}
							// nameToFileMap.put(entry.getKey(), toFile);
							 
							boolean isSuccess =  PrepareFlatFile.processFile(toFile,config.getE2openUrl());
							LOG.info(" isSuccess--->"+ isSuccess);
							
							LOG.info(" entry.getKey()--->"+ entry.getKey());
							if(isSuccess){
								successList.add(entry.getKey().split("\\.")[0]);
							}else{
								errorList.add(entry.getKey().split("\\.")[0]);
							}
							
							
							 //end
						}
						
					}
			}//end poNumToItemListMap
			
			if(successList.size()>0){
				pkToSuccessList.put(val.get("erp"), successList);
			}
			if(errorList.size()>0){
				pkToErrorList.put(val.get("erp"), errorList);
			}
		}
		
		
		//Update status in DB
		updateStatus(pkToSuccessList,pkToErrorList);		
		//END
		
		LOG.info("### Ending ApigeeClientService.processFlatFile ####");
		return null;
	}
	

	
	@Override
	public String processSupplierFlatFile() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting ApigeeClientService.processSupplierFlatFile ####");
		
		CommonUtils utils = new CommonUtils();
		HashMap<String,HashMap<String,String>>  regionsMapping = utils.getRegionMapping(config.getRegionUrl());
		HashMap<String,HashMap<Integer,String>>  supplierNameToMapping = utils.getDestMapping(config.getSupplierUrl());
		
		PrepareFlatFile file = new PrepareFlatFile();
		Map<String,List<String>> fileNameToRowsMap = null;
		
		List<String> successList = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		
		Map<String,List<String>> pkToSuccessList = new HashMap<String,List<String>>();
		Map<String,List<String>> pkToErrorList = new HashMap<String,List<String>>();
		 
		
		for (Map.Entry<String,HashMap<String,String>> regions : regionsMapping.entrySet()){			 
			HashMap<String,String> val = regions.getValue();
			String partitionKey = CommonUtils.getPartitionKey(val.get("erp"));
			
			List<HashMap<String, Object>>  list = repo.getFlatFileData(partitionKey,Constants.TABLE_SUPPLIER);
			
			
			
			Map<String,List<HashMap<String, Object>>> map = new HashMap<String,List<HashMap<String, Object>>>();
			map.put("SupplierFile", list);
			
				 
				 /**
				  * Loop: No of  Supplier mapping files present in directory  
				  */
				for (Map.Entry<String,HashMap<Integer,String>> mapping : supplierNameToMapping.entrySet()){
						//supplierName = mapping.getKey();						
						fileNameToRowsMap = file.prepareSupplierData(mapping.getValue(),map,config);
						
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
									//finalRes.setError(true);
									//return finalRes;
								}
							// nameToFileMap.put(entry.getKey(), toFile);
							 
							boolean isSuccess =  PrepareFlatFile.processFile(toFile,config.getE2openUrl());
							LOG.info(" isSuccess--->"+ isSuccess);
							
							LOG.info(" entry.getKey()--->"+ entry.getKey());
							if(isSuccess){
								successList.add(entry.getKey().split("\\.")[0]);
							}else{
								errorList.add(entry.getKey().split("\\.")[0]);
							}
							
							
							 //end
						}
						
					}
			
			if(successList.size()>0){
				pkToSuccessList.put(val.get("erp"), successList);
			}
			if(errorList.size()>0){
				pkToErrorList.put(val.get("erp"), errorList);
			}
		}
		
		
		//Update status in DB
		updateStatus(pkToSuccessList,pkToErrorList);		
		//END
		
		LOG.info("### Ending ApigeeClientService.processSupplierFlatFile ####");
		return null;
	}

	@Override
	public String processItemFlatFile() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting ApigeeClientService.processItemFlatFile ####");
		
		CommonUtils utils = new CommonUtils();
		HashMap<String,HashMap<String,String>>  regionsMapping = utils.getRegionMapping(config.getRegionUrl());
		HashMap<String,HashMap<Integer,String>>  supplierNameToMapping = utils.getDestMapping(config.getItemUrl());
		
		PrepareFlatFile file = new PrepareFlatFile();
		Map<String,List<String>> fileNameToRowsMap = null;
		
		List<String> successList = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		
		Map<String,List<String>> pkToSuccessList = new HashMap<String,List<String>>();
		Map<String,List<String>> pkToErrorList = new HashMap<String,List<String>>();
		 
		
		for (Map.Entry<String,HashMap<String,String>> regions : regionsMapping.entrySet()){			 
			HashMap<String,String> val = regions.getValue();
			String partitionKey = CommonUtils.getPartitionKey(val.get("erp"));
			
			List<HashMap<String, Object>>  list = repo.getFlatFileData(partitionKey,Constants.TABLE_ITEM);
			LOG.info(" list--->"+ list);
			
			Map<String,List<HashMap<String, Object>>> map = new HashMap<String,List<HashMap<String, Object>>>();
			map.put("ItemFile", list);
			
				 
				 /**
				  * Loop: No of  Supplier mapping files present in directory  
				  */
				for (Map.Entry<String,HashMap<Integer,String>> mapping : supplierNameToMapping.entrySet()){
						//supplierName = mapping.getKey();						
						fileNameToRowsMap = file.prepareSupplierData(mapping.getValue(),map,config);
						LOG.info(" fileNameToRowsMap--->"+ fileNameToRowsMap);
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
									//finalRes.setError(true);
									//return finalRes;
								}
							// nameToFileMap.put(entry.getKey(), toFile);
							 
							boolean isSuccess =  PrepareFlatFile.processFile(toFile,config.getE2openUrl());
							LOG.info(" isSuccess--->"+ isSuccess);
							
							LOG.info(" entry.getKey()--->"+ entry.getKey());
							if(isSuccess){
								successList.add(entry.getKey().split("\\.")[0]);
							}else{
								errorList.add(entry.getKey().split("\\.")[0]);
							}
							
							
							 //end
						}
						
					}
			
			if(successList.size()>0){
				pkToSuccessList.put(val.get("erp"), successList);
			}
			if(errorList.size()>0){
				pkToErrorList.put(val.get("erp"), errorList);
			}
		}
		
		
		//Update status in DB
		updateStatus(pkToSuccessList,pkToErrorList);		
		//END
		
		LOG.info("### Ending ApigeeClientService.processItemFlatFile ####");
		return null;
	
	}
	
	public void updateStatus(Map<String,List<String>> pkToSuccessList,Map<String,List<String>> pkToErrorList) {
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
				e.printStackTrace();
			}
		}
	}
	
}
