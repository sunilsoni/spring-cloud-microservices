package com.jci.po.apis;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.po.dto.request.PoDetailsRequest;
import com.jci.po.dto.request.SegmentedDetailRequest;
import com.jci.po.dto.response.PoDetailsResponse;
import com.jci.po.dto.response.SegmentedDetailResponse;
import com.jci.po.entity.PoEntity;
import com.jci.po.service.PoService;
import com.jci.po.utils.AzureUtils;
import com.jci.po.utils.Constants;
import com.microsoft.azure.storage.StorageException;

/**
 * <p>
 * <strong>REST layer for managing PO, Supplier and Item data.</strong>
 * <p>
 *
 * @author csonisk
 */

@RestController 
public class PoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PoController.class);
	
	@Autowired
	private PoService  poService;	
	
	@RequestMapping(value = "/getSegmentedSupplierDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailResponse getSegmentedSupplierDetails(@RequestBody SegmentedDetailRequest request){
		LOG.info("### Starting PoController.getSegmentedSupplierDetails ###"+request );
		request.setTableName(Constants.TABLE_SUPPLIER);
		request.setPartition(AzureUtils.getPartitionKey(request.getErpName().toUpperCase()));
		SegmentedDetailResponse response = new SegmentedDetailResponse();
		try {
			response = poService.getSegmentedResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("### Ending PoController.getSegmentedSupplierDetails ###" +response);
		return response;
	}
	
	@RequestMapping(value = "/getSegmentedItemDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailResponse getSegmentedItemDetails(@RequestBody SegmentedDetailRequest request){
		LOG.info("### Starting PoController.getSegmentedItemDetails ###"+request );
		request.setTableName(Constants.TABLE_ITEM);
		
		request.setPartition(AzureUtils.getPartitionKey(request.getErpName().toUpperCase()));
		
		SegmentedDetailResponse response = new SegmentedDetailResponse();
		try {
			response = poService.getSegmentedResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("### Ending PoController.getSegmentedItemDetails ###" +response);
		return response;
	}
	
	
	
	@RequestMapping(value = "/getSegmentedPoDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailResponse getSegmentedPoDetails(@RequestBody SegmentedDetailRequest request){
		LOG.info("### Starting PoController.getSegmentedPoDetails ###"+request );
		
		SegmentedDetailResponse response = new SegmentedDetailResponse();
		request.setPartition(AzureUtils.getPartitionKey(request.getErpName().toUpperCase()));
		request.setTableName(Constants.TABLE_PO_DETAILS);
		
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
		
		/*try {
			response = poService.getPos(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			e.printStackTrace();
		}*/
		LOG.info("### Ending PoController.processPoDetails ###" );
		return null;
	}
	
	
	
	//@Scheduled(fixedRate = 100000)
	@RequestMapping(value = "/pullData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  void pullData(){
		LOG.info(" ### Starting PoController.pullData ###");
		
		
		String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SYMIX);
		PoEntity poEntity = new PoEntity(partitionKey, "");
		poEntity.setDescription("(GE45RC375060)3/8x3/8x6  OAL: RH .060");
		poEntity.setOrderCreationDate(new Date());
		poEntity.setOrderNumber("");
		
		poEntity.setSourceErpName(1);;
		poEntity.setStatus(1);
		/*try {
			poService.addPo(poEntity);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.warn(" ### Exception PoController.pullData ###");
			e.printStackTrace();
		}*/
		
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
	
	@RequestMapping(value = "/insertDummyData", method = RequestMethod.GET)
	public  void insertDummyData(){
		LOG.info("### Starting PoController.insertDummyData ###" );
		
		try {
			poService.insertDummyData();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
		}
		
		LOG.info("### Ending PoController.insertDummyData ###" );
	}

}
