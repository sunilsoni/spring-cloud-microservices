package com.jci.po.apis;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.po.dto.req.PoDetailsReq;
import com.jci.po.dto.req.PoItemDetailReq;
import com.jci.po.dto.req.SegmentedDetailReq;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.dto.res.PoDetailsRes;
import com.jci.po.dto.res.PoItemDetailRes;
import com.jci.po.dto.res.SegmentedDetailRes;
import com.jci.po.service.PoService;
import com.jci.po.utils.AzureUtils;
import com.jci.po.utils.Constants;
import com.microsoft.azure.storage.StorageException;

/**
 * <p>
 * <strong>REST layer for managing PO,  graph and Error data.</strong>
 * <p>
 *
 * @author csonisk
 */      

@RestController 
@RefreshScope
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
	
	@RequestMapping(value = "/processErrorPos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  BatchUpdateRes processErrorPos(@RequestBody final PoDetailsReq request){
		long startTime = System.nanoTime();
		LOG.info("### Starting PoController.processErrorPos ###" +request);
		BatchUpdateRes response = new BatchUpdateRes();
		
		try {
			response = poService.processErrorPos(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		
		//Start time calculation
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		//End time calculation		
		
		LOG.info("### Ending PoController.processErrorPos ###"+response );
		return response;
	}	
	
	@RequestMapping(value = "/getPoItemDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  PoItemDetailRes getPoItemDetail(@RequestBody PoItemDetailReq request){
		LOG.info("### Starting PoController.getPoItemDetail ###" +request);
		PoItemDetailRes res = new PoItemDetailRes();
		try {
			res = poService.getPoItemDetail(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			res.setError(true);
			res.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("### Ending PoController.getPoItemDetail ###"+res );
		return res;
	}
	
	@RequestMapping(value = "/processPos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  PoDetailsRes processPoData(@RequestBody final PoDetailsReq request){
		
		LOG.info("### Starting PoController.processPoDetails ###" +request);
		//PoDetailsRes response = new PoDetailsRes();
		
		/*try {
			response = poService.getPos(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			e.printStackTrace();
		}*/  
		LOG.info("### Ending PoController.processPoDetails ###" );
		return null;
	}

	
}
