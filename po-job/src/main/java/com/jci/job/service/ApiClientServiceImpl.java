/**
 * 
 */
package com.jci.job.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.ItemSuccessRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.PoSuccessRes;
import com.jci.job.api.res.SupplierDetailsRes;
import com.jci.job.api.res.SupplierSuccessRes;
import com.jci.job.apis.ApiClient;
import com.jci.job.apis.SupplierClient;
import com.jci.job.azure.BatchInsertReq;
import com.jci.job.azure.BatchInsertRes;
import com.jci.job.azure.FlatFile;
import com.jci.job.repo.JobRepo;
import com.jci.job.utils.CommonUtils;
import com.jci.job.utils.PrepareBatchInsertReq;
import com.microsoft.azure.storage.StorageException;
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
	ApiClient apigeeClient;
	
	@Autowired
	SupplierClient suppClient;

	@Autowired
	private JobRepo repo;
	
	
	@Autowired
	private FlatFile config;

	String erpName;
	String region;
	String plant;
	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getPoDetailsFallback")
	@Override
	public String getPoDetails()  throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting ApigeeClientService.getPoDetails ####");
		
		PoDetailsRes responseBody=null;
		
		
		//Sunil: Dummy values, make it dynamic by reading config json file
		
		//String ordernumber="4370024";
//		String ordercreationdate="2016-08-28";

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
			 LOG.info("<===========Ending apigee call===="+apigeeResponse);
			 
			 
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
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getItemDetailsFallback")
	public String getItemDetails() {
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
			//Object response = apigeeClient.getItems(erpName,region,plant,"**");
			System.out.println("response===>"+response);
			LOG.info("<====Ending Apigee Item Call==="+response);
			
			ItemDetailsRes responseBody = null;//response.getBody();
			LOG.info("responseBody-->"+responseBody);
			 
			BatchInsertReq  req = PrepareBatchInsertReq.prepareItemReq(responseBody,erpName,customerItemID,supplierID);
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

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getSupplierDetailsFallback")
	@Override
	public String getSupplierDetails() {
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
			LOG.info("<====Ending Apigee Supplier Call===");
			
			SupplierDetailsRes responseBody = response.getBody();
			LOG.info("responseBody-->"+responseBody);
			
			BatchInsertReq  req = PrepareBatchInsertReq.prepareSupplierReq(responseBody,erpName,supplierID);
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
	public String processFlatFile() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting ApigeeClientService.processFlatFile ####");
		
		CommonUtils utils = new CommonUtils();
		HashMap<String,HashMap<String,String>>  regionsMapping = utils.getRegionMapping(config.getRegionUrl());
		LOG.info("regionsMapping--->"+regionsMapping);
		
		
		
		for (Map.Entry<String,HashMap<String,String>> regions : regionsMapping.entrySet()){			 
			HashMap<String,String> val = regions.getValue();
			String partitionKey = CommonUtils.getPartitionKey(val.get("erp"));
			
			List<Map<String,List<HashMap<String, Object>>>>  list = repo.getFlatFileData(partitionKey);
			LOG.info("list-->"+list);
			
			
		}
		
		
		
		
		
		
		ResponseEntity<String> response =  null;//suppClient.sendFlatFile();
		
		
		
		 LOG.info("### Ending ApigeeClientService.processFlatFile ####");
		return null;
	}
	

	public String getPoDetailsFallback() {		
		return "PoDetailsFallback";
	}
	
	public String getSupplierDetailsFallback() {
		return "SupplierDetailsFallback";
	}
	
	public String getItemDetailsFallback() {
		
		/*ResponseEntity<ItemDetailsRes> response = apigeeClient.getItems(erpName,region,plant,"**");
		
		 LOG.info("response--->"+response);*/
		 
		
		return "ItemDetailsFallback";
	}
	
	public String processFlatFileFallback() {
		return "FlatFileFallback";
	}
	

}
