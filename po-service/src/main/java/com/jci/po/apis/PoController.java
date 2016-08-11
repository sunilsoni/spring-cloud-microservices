package com.jci.po.apis;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.po.dto.req.PoDetailsReq;
import com.jci.po.dto.req.SegmentedDetailReq;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.dto.res.PoDetailsRes;
import com.jci.po.dto.res.SegmentedDetailRes;
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
	
	
	@RequestMapping(value = "/getSegmentedPoDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getSegmentedPoDetails(@RequestBody SegmentedDetailReq request){
		LOG.info("### Starting PoController.getSegmentedPoDetails ###"+request );
		
		SegmentedDetailRes response = new SegmentedDetailRes();
		request.setPartition(AzureUtils.getPartitionKey(request.getErpName().toUpperCase()));
		request.setTableName(Constants.TABLE_PO_DETAILS);
		
		try {
			response = poService.getSegmentedResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("### Ending PoController.getSegmentedPoDetails ###" );
		return response;
	}
	
	@RequestMapping(value = "/getSegmentedErrorDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getErrorDetails(@RequestBody SegmentedDetailReq request){
		LOG.info("### Starting PoController.getSegmentedErrorDetails ###"+request );
		
		SegmentedDetailRes response = new SegmentedDetailRes();
		request.setPartition(AzureUtils.getPartitionKey(request.getErpName().toUpperCase()));
		request.setTableName(Constants.TABLE_PO_DETAILS);
		
		try {
			response = poService.getErrorResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("### Ending PoController.getSegmentedErrorDetails ###" );
		return response;
	}
	
	
	@RequestMapping(value = "/processPos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  PoDetailsRes processPoData(@RequestBody final PoDetailsReq request){
		
		LOG.info("### Starting PoController.processPoDetails ###" +request);
		PoDetailsRes response = new PoDetailsRes();
		
		/*try {
			response = poService.getPos(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			e.printStackTrace();
		}*/  
		LOG.info("### Ending PoController.processPoDetails ###" );
		return null;
	}
	
	@RequestMapping(value = "/processErrorPos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  BatchUpdateRes processErrorPos(@RequestBody final PoDetailsReq request){
		
		LOG.info("### Starting PoController.processErrorPos ###" +request);
		BatchUpdateRes response = new BatchUpdateRes();
		
		try {
			response = poService.processErrorPos(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			e.printStackTrace();
		}
		LOG.info("### Ending PoController.processErrorPos ###"+response );
		return response;
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
