/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
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
public class PoController { // NO_UCD (unused code)
	
	/** The Constant LOG. */
 private static final Logger LOG = LoggerFactory.getLogger(PoController.class);
	
	/** The po service. */
	@Autowired  
	private PoService  poService;
	
	/**
	 * Gets the segmented po details.
	 *
	 * @param request the request
	 * @return the segmented po details
	 */
	@RequestMapping(value = "/getSegmentedPoDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getSegmentedPoDetails(@RequestBody SegmentedDetailReq request){
		long startTime = System.nanoTime();
		SegmentedDetailRes response = new SegmentedDetailRes();
		request.setPartition(AzureUtils.getPoPartitionKey(request.getErpName().toUpperCase()));
		request.setTableName(Constants.TABLE_PO_DETAILS);
		
		try {
			response = poService.getSegmentedResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in   ####",e);
			response.setError(true);
			response.setMessage(e.getMessage());
			
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Gets the error details.
	 *
	 * @param request the request
	 * @return the error details
	 */
	@RequestMapping(value = "/getSegmentedErrorDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getErrorDetails(@RequestBody SegmentedDetailReq request){
		long startTime = System.nanoTime();
		SegmentedDetailRes response = new SegmentedDetailRes();
		request.setPartition(AzureUtils.getPoPartitionKey(request.getErpName().toUpperCase()));
		request.setTableName(Constants.TABLE_PO_DETAILS);
		
		try {
			response = poService.getErrorResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in   ####",e);
			response.setError(true);
			response.setMessage(e.getMessage());
			
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Process error pos.
	 *
	 * @param request the request
	 * @return the batch update res
	 */
	@RequestMapping(value = "/processErrorPos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  BatchUpdateRes processErrorPos(@RequestBody final PoDetailsReq request){
		long startTime = System.nanoTime();
		BatchUpdateRes response = new BatchUpdateRes();
		
		try {
			response = poService.processErrorPos(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in   ####",e);
			response.setError(true);
			response.setMessage(e.getMessage());
			
		}
		
		//Start time calculation
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}	
	
	/**
	 * Gets the po item detail.
	 *
	 * @param request the request
	 * @return the po item detail
	 */
	@RequestMapping(value = "/getPoItemDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  PoItemDetailRes getPoItemDetail(@RequestBody PoItemDetailReq request){
		long startTime = System.nanoTime();
		PoItemDetailRes res = new PoItemDetailRes();
		try {
			res = poService.getPoItemDetail(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			res.setError(true);
			res.setMessage(e.getMessage());
			
			LOG.error("### Exception in   ####",e);
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return res;
	}
}
