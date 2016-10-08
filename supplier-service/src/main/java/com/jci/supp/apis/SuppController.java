/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.supp.apis;

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

import com.jci.dto.SegmentedDetailReq;
import com.jci.dto.SegmentedDetailRes;
import com.jci.supp.service.SuppService;
import com.jci.utils.Constants;
import com.microsoft.azure.storage.StorageException;


/**
 * <p>
 * <strong>REST layer for managing Supplier data.</strong>
 * <p>
 *
 * @author csonisk
 */

@RestController 
public class SuppController { // NO_UCD (unused code)
	
	/** The Constant LOG. */
 private static final Logger LOG = LoggerFactory.getLogger(SuppController.class);
	
	/** The po service. */
	@Autowired
	private SuppService  poService;	
	
	/**
	 * Gets the supplier details.
	 *
	 * @param request the request
	 * @return the supplier details
	 */
	@RequestMapping(value = "/getSegmentedSupplierDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getSupplierDetails(@RequestBody SegmentedDetailReq request){
		request.setTableName(Constants.TABLE_SUPPLIER);
		request.setPartition(request.getErpName().toUpperCase());
		request.setFirstRequest(false);
		SegmentedDetailRes response = new SegmentedDetailRes();
		
		try {
			response = poService.getSupplierResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage(e.getMessage());
			
			LOG.error("### Exception in   ####",e);
		}
		return response;
	}	

}
