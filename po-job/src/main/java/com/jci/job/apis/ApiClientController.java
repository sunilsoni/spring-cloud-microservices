
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

	@Autowired
	ApiClientService service;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(ApiClientController.class);
	
	/**
	 * Get PO Details scheduler
	 * @return
	 */
	@RequestMapping(value = "/getPoDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPoDetails() {
		long startTime = System.nanoTime();
		String response=null;
		try {
			response = service.getPoDetails();
			
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.getPoDetails ####");
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		
		return response;
	}
	
	/**
	 * Get Supplier Details scheduler
	 * @return
	 */
	@RequestMapping(value = "/getSupplierDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getSupplierDetails() {
		long startTime = System.nanoTime();
		String response=null;
		try {
			response = service.getSupplierDetails();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.getSupplierDetails ####");
			e.printStackTrace();
		}
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Get Item Details scheduler
	 * @return
	 */
	@RequestMapping(value = "/getItemDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getItemDetails() {
		long startTime = System.nanoTime();
		String response =null;
		try {
			response = service.getItemDetails();
			
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.getPoDetails ####");
			e.printStackTrace();
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Process Flat File scheduler for PO details
	 * @return
	 */
	@RequestMapping(value = "/processPoFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processPoFlatFile() {
		long startTime = System.nanoTime();
		String response=null;
		try {
			response = service.processPoFlatFile();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.processFlatFile ####");
			e.printStackTrace();
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Process Flat File scheduler for Suppliers
	 * @return
	 */
	@RequestMapping(value = "/processSupplierFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processSupplierFlatFile() {
		long startTime = System.nanoTime();
		String response=null;
		try {
			response = service.processSupplierFlatFile();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.processFlatFile ####");
			e.printStackTrace();
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Process Flat File scheduler for Items
	 * @return
	 */
	@RequestMapping(value = "/processItemFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processItemFlatFile() {
		long startTime = System.nanoTime();
		String response=null;
		try {
			response = service.processItemFlatFile();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.processFlatFile ####");
			e.printStackTrace();
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
}
