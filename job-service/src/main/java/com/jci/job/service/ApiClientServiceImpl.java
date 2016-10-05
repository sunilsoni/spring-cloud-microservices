/**
 * 
 */
package com.jci.job.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.job.api.req.BatchInsertReq;
import com.jci.job.api.req.SuccessReq;
import com.jci.job.api.res.GrDetailsRes;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SuppDetailsRes;
import com.jci.job.azure.ApiKeys;
import com.jci.job.repo.JobRepo;
import com.jci.job.utils.PrepareBatchInsertReq;
import com.microsoft.azure.storage.StorageException;


/**
 * The Class ApiClientServiceImpl.
 *
 * @author cdevdat
 */
@Service
@RefreshScope
public class ApiClientServiceImpl implements ApiClientService { // NO_UCD (unused code)

	/** The Constant LOG. */
 private static final Logger LOG = LoggerFactory.getLogger(ApiClientServiceImpl.class);
	
	/** The apigee client. */
	@Autowired
	ApiClientImpl apigeeClient;
	
	/** The flatfile client. */
	@Autowired
	FlatFileClientImpl flatfileClient;
	
	/** The repo. */
	@Autowired
	private JobRepo repo;
	
    /** The all erps. */
    @Value("${all.erp.names}")
    private String allErps;
    
    /** The config. */
    @Autowired
    private ApiKeys config;	
	
	
	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#getPoDetails()
	 */
	@Override
	public String getPoDetails()  throws InvalidKeyException, URISyntaxException, StorageException {
		PoDetailsRes responseBody=null;
		ResponseEntity<PoDetailsRes> apigeeResponse =null;
		
		//Asumming only one erps for now with 1 file only
		String responseStatus = null;
			 
		 /**
		  * Starting apigee call
		 */
		String[] erpArr  = allErps.split(",");
        for (int i=0;i<erpArr.length;i++){//Sunil: No of count issue here
             
           
            apigeeResponse =  apigeeClient.getPoDetails(config.getPoKey(), erpArr[i]);
            
            if(apigeeResponse==null || apigeeResponse.getBody()==null ){
                continue;
            }
            
            responseBody = apigeeResponse.getBody();
               
            if(responseBody.getPoList()==null || responseBody.getPoList().size()==0){
                continue;
            }
            BatchInsertReq  req = PrepareBatchInsertReq.preparePoReq(responseBody);
             
            /**
             * Start Storing data in Azure tables
             */
            List<Object> successList = repo.batchInsert(req);
            
             //End Storing data in Azure tables 
            
            SuccessReq req1 = new SuccessReq();
            req1.setPoList(successList);
            
          responseStatus =  apigeeClient.getPoDetailsRes(req1,config.getPoKey(), erpArr[i]);//Sunil: method body is wrong
             
        }
        
        processPoFlatFile() ;
		return responseStatus;
	}
	
	
	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#getGrDetails()
	 */
	@Override
	public String getGrDetails() throws InvalidKeyException, URISyntaxException, StorageException {
		GrDetailsRes responseBody=null;
		ResponseEntity<GrDetailsRes> apigeeResponse =null;
		
		//Asumming only one erps for now with 1 file only
		String responseStatus = null;
			 
		 /**
		  * Starting apigee call
		 */
		String[] erpArr  = allErps.split(",");
        for (int i=0;i<erpArr.length;i++){
             
           
    		apigeeResponse =  apigeeClient.getGrDetails(config.getGrKey(), erpArr[i]);
    		 
    		if(apigeeResponse==null ||  apigeeResponse.getBody()==null){
                continue;
            }	
    		
    		responseBody = apigeeResponse.getBody();
    		if(responseBody.getGrList()==null || responseBody.getGrList().size()==0){
                continue;
            }
    		
    		BatchInsertReq  req = PrepareBatchInsertReq.prepareGrReq(responseBody);
    		 
    		/**
    		 * Start Storing data in Azure tables
    		 */
    		List<Object> successList = repo.batchInsert(req);
    		
    		 //End Storing data in Azure tables 
    		
    		SuccessReq req1 = new SuccessReq();
    		req1.setGrList(successList);
    		
    		responseStatus =  apigeeClient.getGrDetailsRes(req1, config.getGrKey(), erpArr[i]);
        }
        
        processGrFlatFile() ;
		return responseStatus;
	} 
	
	
	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#getItemDetails()
	 */
	@Override
	public String getItemDetails() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting in ApigeeClientServiceImpl.getItemDetails ####");
		String responseStatus=null;
			 
		 /**
		  * Starting apigee call
		 */
		String[] erpArr  = allErps.split(",");
        for (int i=0;i<erpArr.length;i++){
           
    		ResponseEntity<ItemDetailsRes> response = apigeeClient.getItems(config.getItemKey(), erpArr[i]);
    		if(response==null || response.getBody()==null){
                continue;
            }   
    		
    		ItemDetailsRes responseBody = response.getBody();
    		if(responseBody.getItemList()==null || responseBody.getItemList().size()==0){
                continue;
            }  
    		
    		BatchInsertReq  req = PrepareBatchInsertReq.prepareItemReq(responseBody);
    		if(req==null){
    			 continue;
    		}
    		
    		List<Object> successList = repo.batchInsert(req);
    		
    		SuccessReq req1 = new SuccessReq();
    		req1.setItemList(successList);
    		
    		responseStatus =  apigeeClient.getItemsRes(req1,config.getItemKey(), erpArr[i]);//Need to change
        }
        processItemFlatFile() ;
    	LOG.info("### Ending in ApigeeClientServiceImpl.getItemDetails ####");
		return responseStatus;
	}

	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#getSuppDetails()
	 */
	@Override
	public String getSuppDetails() throws InvalidKeyException, URISyntaxException, StorageException {
		
		String responseStatus=null;

		/**
		  * Starting Apigee call
		 */
		String[] erpArr  = allErps.split(",");
        for (int i=0;i<erpArr.length;i++){
    		ResponseEntity<SuppDetailsRes> response = apigeeClient.getSupp(config.getSuppKey(), erpArr[i]);
    		
    		if(response==null || response.getBody()==null){
                continue;
            } 
    		
    		SuppDetailsRes responseBody = response.getBody();
    		if(responseBody.getSupplierList()==null || responseBody.getSupplierList().size()==0){
                continue;
            } 
    		
    		BatchInsertReq  req = PrepareBatchInsertReq.prepareSuppReq(responseBody);
    		if(req==null){
    			return null;
    		}
    		
    		List<Object> successList = repo.batchInsert(req);
    		
    		SuccessReq req1 = new SuccessReq();
    		req1.setPoList(successList);
    		
    		req1.setSupplierList(successList);
    		responseStatus =  apigeeClient.getSuppRes(req1,config.getSuppKey(), erpArr[i]);//Need to change
        }
        
        processSuppFlatFile() ;
		return responseStatus;
	}
	
	@Override
	public void processPoFlatFile()  {
		LOG.info("### Starting in ApigeeClientServiceImpl.processPoFlatFile ####");
	    ResponseEntity<String> ffResponse =  flatfileClient.processPoFlatFiles();
	    String ffPos = null;
	    if(ffResponse!=null && ffResponse.getBody()!=null){
	    	ffPos = ffResponse.getBody();
	    }
	    LOG.info("ffPos--->"+ffPos);
	    LOG.info("### Ending in ApigeeClientServiceImpl.processPoFlatFile ####");    
	}
	

	@Override
	public void processSuppFlatFile(){
		LOG.info("### Starting in ApigeeClientServiceImpl.processSuppFlatFile ####");
	    ResponseEntity<String> ffResponse =  flatfileClient.processSuppFlatFiles();
	    String ffPos = ffResponse.getBody();
	    if(ffResponse!=null && ffResponse.getBody()!=null){
	    	ffPos = ffResponse.getBody();
	    }
	    LOG.info("ffPos Supp--->"+ffPos);
	    LOG.info("### Ending in ApigeeClientServiceImpl.processSuppFlatFile ####");  
	}

	@Override
	public void processItemFlatFile(){
		LOG.info("### Starting in ApigeeClientServiceImpl.processItemFlatFile ####");
	    ResponseEntity<String> ffResponse =  flatfileClient.processItemFlatFiles();
	    String ffPos = ffResponse.getBody();
	    if(ffResponse!=null && ffResponse.getBody()!=null){
	    	ffPos = ffResponse.getBody();
	    }
	    LOG.info("ffPos Item--->"+ffPos);
	    LOG.info("### Ending in ApigeeClientServiceImpl.processItemFlatFile ####");  
	}

	@Override
    public void processGrFlatFile() {
    	LOG.info("### Starting in ApigeeClientServiceImpl.processGrFlatFile ####");
        ResponseEntity<String> ffResponse =  flatfileClient.processGrFlatFiles();
        String ffPos = ffResponse.getBody();
        if(ffResponse!=null && ffResponse.getBody()!=null){
	    	ffPos = ffResponse.getBody();
	    }
	    LOG.info("ffPos GR--->"+ffPos);
	    LOG.info("### Ending in ApigeeClientServiceImpl.processGrFlatFile ####");  
    }
    
	
}
