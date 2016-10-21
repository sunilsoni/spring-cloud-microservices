/**
 * 
 */
package com.jci.job.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.config.ApiKeys;
import com.jci.enums.ErrorEnum;
import com.jci.exception.ErrorService;
import com.jci.job.api.req.BatchInsertReq;
import com.jci.job.api.req.PrepareBatchInsertReq;
import com.jci.job.api.req.SuccessReq;
import com.jci.job.api.res.GrDetailsRes;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SuppDetailsRes;
import com.jci.job.exception.JobException;
import com.jci.job.repo.JobRepo;
/**
 * The Class ApiClientServiceImpl.
 *
 * @author cdevdat
 */
@Service
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
	
    @Autowired
    private ErrorService errorService;
	
    /** The config. */
    @Autowired
    private ApiKeys config;	
    
	
	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#getPoDetails()
	 */
	@Override
	public String getPoDetails(String plant, String erp, String region) {
		PoDetailsRes responseBody=null;
		ResponseEntity<PoDetailsRes> apigeeResponse =null;
		
		String responseStatus = null;
			 
		 /**
		  * Starting apigee call
		 */
		try{
		 	apigeeResponse =  apigeeClient.getPoDetails(config.getPoKey(), plant,erp,region);
            if(apigeeResponse==null || apigeeResponse.getBody()==null ){
                return "Failure";
            }
            responseBody = apigeeResponse.getBody();
            
            if(responseBody.getPoList()==null || responseBody.getPoList().size()==0){
            	 return "Failure";
            }
		}catch(Exception e){
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_APIGEE_PO_GET);
		}
		
		
        BatchInsertReq  req = PrepareBatchInsertReq.preparePoReq(responseBody);
         
        /**
         * Start Storing data in Azure tables
         */
        List<Object> successList = repo.batchInsert(req);
        
         //End Storing data in Azure tables 
        
        SuccessReq req1 = new SuccessReq();
        req1.setPoList(successList);
        
        try{
        	responseStatus =  apigeeClient.getPoDetailsRes(req1,config.getPoKey(), plant,erp,region);
		}catch(Exception e){
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_APIGEE_PO_PUT);
		}
             
         processPoFlatFile() ; 
		return responseStatus;
	}
	
	
	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#getGrDetails()
	 */
	@Override
	public String getGrDetails(String plant, String erp, String region)  {
		GrDetailsRes responseBody=null;
		ResponseEntity<GrDetailsRes> apigeeResponse =null;
		
		//Asumming only one erps for now with 1 file only
		String responseStatus = null;
			 
		 /**
		  * Starting apigee call
		 */
			try{
				apigeeResponse =  apigeeClient.getGrDetails(config.getGrKey(), plant,erp,region);
				responseBody = apigeeResponse.getBody();
				if(apigeeResponse==null ||  apigeeResponse.getBody()==null){
		   			 return "Failure";
		         }	
			}catch(Exception e){
				throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_APIGEE_GR_GET);
			}
			
			if(responseBody.getGrList()==null || responseBody.getGrList().size()==0){
	   			 return "Failure";
	         }	
			
			Map<String,Integer> poMap =  repo.getGrQtyMap(responseBody.getGrList());
    		BatchInsertReq  req = PrepareBatchInsertReq.prepareGrReq(responseBody,poMap);
    		 
    		/**
    		 * Start Storing data in Azure tables
    		 */
    		List<Object> successList = repo.batchInsert(req);
    		
    		 //End Storing data in Azure tables 
    		
    		SuccessReq req1 = new SuccessReq();
    		req1.setGrList(successList);
    		
    		try{
    			responseStatus =  apigeeClient.getGrDetailsRes(req1, config.getGrKey(), plant,erp,region);
			}catch(Exception e){
				throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_APIGEE_GR_PUT);
			}
    		
    		processGrFlatFile() ;
		return responseStatus;
	} 
	
	
	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#getItemDetails()
	 */
	@Override
	public String getItemDetails(String plant, String erp, String region)  {
		LOG.info("### Starting in ApigeeClientServiceImpl.getItemDetails ####");
		String responseStatus=null;
			 
		 /**
		  * Starting apigee call
		 */
		ItemDetailsRes responseBody =null;
    		try{
    			ResponseEntity<ItemDetailsRes> response = apigeeClient.getItems(config.getItemKey(), plant,erp,region);
    			responseBody = response.getBody();
    			if(response==null || response.getBody()==null ){
        			 return "Failure";
                }   
			}catch(Exception e){
				throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_APIGEE_ITEM_GET);
			}
    		
    		if(responseBody.getItemList()==null || responseBody.getItemList().size()==0){
   			 return "Failure";
            } 
    		
    		BatchInsertReq  req = PrepareBatchInsertReq.prepareItemReq(responseBody);
    		if(req==null){
    			 return "Failure";
    		}
    		
    		List<Object> successList = repo.batchInsert(req);
    		
    		SuccessReq req1 = new SuccessReq();
    		req1.setItemList(successList);
    		
    		
    		try{
    			responseStatus =  apigeeClient.getItemsRes(req1,config.getItemKey(), plant,erp,region);
			}catch(Exception e){
				throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_APIGEE_ITEM_PUT);
			}
    		
    		processItemFlatFile() ;
    	LOG.info("### Ending in ApigeeClientServiceImpl.getItemDetails ####");
		return responseStatus;
	}

	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#getSuppDetails()
	 */
	@Override
	public String getSuppDetails(String plant, String erp, String region)  {
		
		String responseStatus=null;
		SuppDetailsRes responseBody =null;
		
		/**
		  * Starting Apigee call
		 */
			try{
				ResponseEntity<SuppDetailsRes> response = apigeeClient.getSupp(config.getSuppKey(), plant,erp,region);
	    		if(response==null || response.getBody()==null){
	    			 return "Failure";
	            } 
	    		responseBody = response.getBody();
			}catch(Exception e){
				throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_APIGEE_SUPP_GET);
			}

    		
    		if(responseBody.getSupplierList()==null || responseBody.getSupplierList().size()==0){
    			 return "Failure";
            }
    		BatchInsertReq  req = PrepareBatchInsertReq.prepareSuppReq(responseBody);
    		if(req==null){
    			return null;
    		}
    		
    		List<Object> successList = repo.batchInsert(req);
    		
    		SuccessReq req1 = new SuccessReq();
    		req1.setPoList(successList);
    		
    		req1.setSupplierList(successList);
    		try{
    			responseStatus =  apigeeClient.getSuppRes(req1,config.getSuppKey(), plant,erp,region); 
			}catch(Exception e){
				throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_APIGEE_SUPP_PUT);
			}
    		
    		processSuppFlatFile() ;
		return responseStatus;
	}
	
	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#processPoFlatFile()
	 */
	@Override
	public void processPoFlatFile()  {
		LOG.info("### Starting in ApigeeClientServiceImpl.processPoFlatFile ####");
	    
		ResponseEntity<String> ffResponse = null;
	    try{
	    	ffResponse =  flatfileClient.processPoFlatFiles();
		}catch(Exception e){
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_FLATFILE_PO_SERVICE_DOWN);
		}
	    
	    String ffPos = null;
	    if(ffResponse!=null && ffResponse.getBody()!=null){
	    	ffPos = ffResponse.getBody();
	    }
	    LOG.info("ffPos--->"+ffPos);
	    LOG.info("### Ending in ApigeeClientServiceImpl.processPoFlatFile ####");    
	}
	

	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#processSuppFlatFile()
	 */
	@Override
	public void processSuppFlatFile(){
		LOG.info("### Starting in ApigeeClientServiceImpl.processSuppFlatFile ####");
		
		ResponseEntity<String> ffResponse = null;
	    try{
	    	ffResponse =  flatfileClient.processSuppFlatFiles();
		}catch(Exception e){
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_FLATFILE_SUPP_SERVICE_DOWN);
		}
	    
	    String ffPos = ffResponse.getBody();
	    if(ffResponse!=null && ffResponse.getBody()!=null){
	    	ffPos = ffResponse.getBody();
	    }
	    LOG.info("ffPos Supp--->"+ffPos);
	    LOG.info("### Ending in ApigeeClientServiceImpl.processSuppFlatFile ####");  
	}

	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#processItemFlatFile()
	 */
	@Override
	public void processItemFlatFile(){
		LOG.info("### Starting in ApigeeClientServiceImpl.processItemFlatFile ####");
		ResponseEntity<String> ffResponse = null;
	    try{
	    	ffResponse =  flatfileClient.processItemFlatFiles();
		}catch(Exception e){
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_FLATFILE_ITEM_SERVICE_DOWN);
		}
	    String ffPos = ffResponse.getBody();
	    if(ffResponse!=null && ffResponse.getBody()!=null){
	    	ffPos = ffResponse.getBody();
	    }
	    LOG.info("ffPos Item--->"+ffPos);
	    LOG.info("### Ending in ApigeeClientServiceImpl.processItemFlatFile ####");  
	}

	/* (non-Javadoc)
	 * @see com.jci.job.service.ApiClientService#processGrFlatFile()
	 */
	@Override
    public void processGrFlatFile() {
    	LOG.info("### Starting in ApigeeClientServiceImpl.processGrFlatFile ####");
    	ResponseEntity<String> ffResponse =null;
    	 try{
 	    	ffResponse =  flatfileClient.processGrFlatFiles();
 		}catch(Exception e){
 			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_FLATFILE_GR_SERVICE_DOWN);
 		}
        String ffPos = ffResponse.getBody();
        if(ffResponse!=null && ffResponse.getBody()!=null){
	    	ffPos = ffResponse.getBody();
	    }
	    LOG.info("ffPos GR--->"+ffPos);
	    LOG.info("### Ending in ApigeeClientServiceImpl.processGrFlatFile ####");  
    }
    
	
}
