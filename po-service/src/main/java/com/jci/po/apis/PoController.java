package com.jci.po.apis;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.po.dto.request.PoDetailsRequest;
import com.jci.po.dto.request.SegmentedPoDetailRequest;
import com.jci.po.dto.response.PoDetailsResponse;
import com.jci.po.dto.response.SegmentedPoDetailResponse;
import com.jci.po.entity.PoEntity;
import com.jci.po.service.PoService;
import com.jci.po.utils.AzureUtils;
import com.jci.po.utils.Constants;
import com.microsoft.azure.storage.StorageException;

/**
 * REST endpoint for the Purchase Order functionality
 * 
 * @author csonisk
 *
 */
@RestController
public class PoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PoController.class);
	
	@Value("${scheduler.time.in.milliseconds}")//
	private String time;//900000-15minutes
	
	
	@Autowired
	private PoService  poService;
	
	@RequestMapping(value = "/getPoDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  PoDetailsResponse pullPoData(@RequestBody final PoDetailsRequest request){
		LOG.info("### Starting PoController.getPoDetails ###"+request );
		
		PoDetailsResponse response = new PoDetailsResponse();
		try {
			response = poService.getPos(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			e.printStackTrace();
		}
		
		
		LOG.info("### Ending PoController.getPoDetails ###" +response);
		return response;
	}
	
	
	@RequestMapping(value = "/getSegmentedPoDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedPoDetailResponse getSegmentedPoDetails(@RequestBody final SegmentedPoDetailRequest request){
		LOG.info("### Starting PoController.getSegmentedPoDetails ###"+request );
		
		SegmentedPoDetailResponse response = new SegmentedPoDetailResponse();
		try {
			response = poService.getSegmentedResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("### Ending PoController.getSegmentedPoDetails ###" +response);
		return response;
	}
	
	
	@RequestMapping(value = "/processPoDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  PoDetailsResponse processPoData(@RequestBody final PoDetailsRequest request){
		
		LOG.info("### Starting PoController.processPoDetails ###" +request);
		PoDetailsResponse response = new PoDetailsResponse();
		try {
			response = poService.getPos(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			e.printStackTrace();
		}
		LOG.info("### Ending PoController.processPoDetails ###" );
		return null;
	}
	
	
	
	//@Scheduled(fixedRate = 100000)
	@RequestMapping(value = "/pullData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  void pullData(){
		LOG.info(" ### Starting PoController.pullData ###");
		
		/*String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SYMIX);
		
		//Inserting dummy data
		PoEntity poEntity = null;
		for (int i=3711982;1<3712000;i++){
			PoItemsEntity  poItemsEntity = new PoItemsEntity(partitionKey, i+"");
			poItemsEntity = ModelData.getDummyData	(poItemsEntity)	;
			
			poItemsEntity.setOrderNumber(i+"");
			
			//need to save above data
			
			
			poEntity = new PoEntity(partitionKey, i+"");
			poEntity.setDescription("(GE45RC375060)3/8x3/8x6  OAL: RH .060");
			poEntity.setOrderCreationDate(new Date());
			poEntity.setOrderNumber(i+"");
			
			poEntity.setSourceErpName(1);;
			poEntity.setStatus(1);
			
			try {
				poService.addPo(poEntity);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				LOG.warn(" ### Exception PoController.pullData ###");
				e.printStackTrace();
			}
		}*/

		/*try {
			poService.addPo(poEntity);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.warn(" ### Exception PoController.pullData ###");
			e.printStackTrace();
		}*/
		String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SYMIX);
		PoEntity poEntity = new PoEntity(partitionKey, "");
		poEntity.setDescription("(GE45RC375060)3/8x3/8x6  OAL: RH .060");
		poEntity.setOrderCreationDate(new Date());
		poEntity.setOrderNumber("");
		
		poEntity.setSourceErpName(1);;
		poEntity.setStatus(1);
		try {
			poService.addPo(poEntity);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.warn(" ### Exception PoController.pullData ###");
			e.printStackTrace();
		}
		
		//Gett All Po details
		List<PoEntity> poEntityList =  null;
		
		/*try {
			poEntityList = poService.getPos();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.warn(" ### Exception PoController.pullData ###");
			e.printStackTrace();
		}*/
		
		LOG.info("poEntityList--->"+poEntityList);
		
		//Get last po
		String lastPo = null;
		try {
			lastPo = poService.getLastPo();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.warn(" ### Exception PoController.pullData ###");
			e.printStackTrace();
		}
		
		
		LOG.info("lastPo--->"+lastPo);
		
		
		LOG.info(" ### Ending PoController.pullData ###");
    }
	
	/**
	 * Get pos for specific taskid that is passed in the path.
	 * 
	 * @param taskId
	 * @return
	 */
/*	@RequestMapping(value = "/{taskId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<PoDTO> getCommentsByTaskId(@PathVariable("taskId") String taskId) {
		List<PoDTO> commentListToReturn = new ArrayList<PoDTO>();
		for (PoDTO po : pos) {
			if (po.getTaskId().equalsIgnoreCase(taskId)) {
				commentListToReturn.add(po);
			}
		}

		return commentListToReturn;
	}*/
	
/*	
	private int getAndSaveSymixData(String dateStr){
	  	System.out.println("### Starting  MultiDatasource.getAndSaveSymixData ###");
    	Date date = CommonUtils.stringToDate(dateStr);
        Object[] parameters = new Object[] {date};
        
        List<Map<String,Object>> rows = null;
        
        try{
        	
        	System.out.println("=======Latest query--->"+parameters.toString());
        	
        	System.out.println("SIMUX_QUERY--->"+Constants.SYMIX_QUERY);
        	
        	rows =    jdbcOpenedgeTemplate.queryForList("select * from \"po\"");
        	
            System.out.println("rows size--->"+rows.size());
            
        }catch(Exception e){
     	   System.out.println("### Exception in  MultiDatasource.getAndSaveSymixData ###"+e.getMessage());
     	   e.printStackTrace();
        }
        System.out.println("### Ending  MultiDatasource.getAndSaveSymixData ###");
    	return rows.size();
    }*/
	
	
	
	
	
}
