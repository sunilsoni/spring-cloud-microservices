
/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */ 
package com.jci.job.apis;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.job.service.ApiClientService;
import com.microsoft.azure.storage.StorageException;


/**
 * <p>
 * <strong>Scheduler layer for fetching Purchase Orders, Suppliers and Items from Apigee APIs.</strong>
 * <p>
 *
 * @author cdevdat, csonisk
 */

@RestController
public class ApiClientController { // NO_UCD (unused code)

	/** The service. */
 @Autowired
	ApiClientService service;
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ApiClientController.class);
	
	/**
	 * Get PO Details scheduler.
	 *
	 * @return the po details
	 */
	@RequestMapping(value = "/getPoDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPoDetails() {
		String response=null;
		try {
			response = service.getPoDetails();
			LOG.info("response-->"+response);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in ApigeeClientController.getPoDetails ####",e);
		}
		return response;
	}
	
	/**
	 * Gets the gr details.
	 *
	 * @return the gr details
	 */
	@RequestMapping(value = "/getGrDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getGrDetails() {
		String response="SUCCESS";
		try {
			response = service.getGrDetails();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in ApigeeClientController.getPoDetails ####",e);
		}
		return response;
	}
		
	/**
	 * Get Supplier Details scheduler.
	 *
	 * @return the supp details
	 */
	@RequestMapping(value = "/getSuppDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getSuppDetails() {
		String response=null;
		try {
			response = service.getSuppDetails();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in ApigeeClientController.getSuppDetails ####",e);
		}
		return response;
	}
	
	/**
	 * Get Item Details scheduler.
	 *
	 * @return the item details
	 */
	@RequestMapping(value = "/getItemDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getItemDetails() {
		String response =null;
		try {
			response = service.getItemDetails();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in ApigeeClientController.getPoDetails ####",e);
		}
		return response;
	}
	

	/**
	 * Process Flat File scheduler for PO details.
	 *
	 * @return the string
	 *//*
	@RequestMapping(value = "/processPoFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processPoFlatFile() {
		String response="SUCCESS";
		service.processPoFlatFile();
		return response;
	}
	
	
	@RequestMapping(value = "/processGrFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public String processGrFlatFile() {
		String response="SUCCESS";
        service.processGrFlatFile();
        return response;
    }
	
	*//**
	 * Process Flat File scheduler for Suppliers.
	 *
	 * @return the string
	 *//*
	@RequestMapping(value = "/processSuppFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processSuppFlatFile() {
		String response="SUCCESS";
		service.processSuppFlatFile();
		return response;
	}
	
	*//**
	 * Process Flat File scheduler for Items.
	 *
	 * @return the string
	 *//*
	@RequestMapping(value = "/processItemFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processItemFlatFile() {
		String response="SUCCESS";
		service.processItemFlatFile();		
		return response;
	}*/
	
}
