/**
 * 
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
public class ApiClientController {

	@Autowired
	ApiClientService service;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(ApiClientController.class);
	
	/**
	 * Get PO Details scheduler
	 * @return
	 */
	@RequestMapping(value = "/getPoDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPoDetails() {
		String response=null;
		try {
			response = service.getPoDetails();
			
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.getPoDetails ####");
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Get Supplier Details scheduler
	 * @return
	 */
	@RequestMapping(value = "/getSupplierDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getSupplierDetails() {
		String response=null;
		try {
			response = service.getSupplierDetails();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.getSupplierDetails ####");
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Get Item Details scheduler
	 * @return
	 */
	@RequestMapping(value = "/getItemDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getItemDetails() {
		String response =null;
		try {
			response = service.getItemDetails();
			
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.getPoDetails ####");
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Process Flat File scheduler for PO details
	 * @return
	 */
	@RequestMapping(value = "/processPoFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processPoFlatFile() {
		String response=null;
		try {
			response = service.processPoFlatFile();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.processFlatFile ####");
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Process Flat File scheduler for Suppliers
	 * @return
	 */
	@RequestMapping(value = "/processSupplierFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processSupplierFlatFile() {
		String response=null;
		try {
			response = service.processSupplierFlatFile();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.processFlatFile ####");
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * Process Flat File scheduler for Items
	 * @return
	 */
	@RequestMapping(value = "/processItemFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processItemFlatFile() {
		String response=null;
		try {
			response = service.processItemFlatFile();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.info("### Exception in ApigeeClientController.processFlatFile ####");
			e.printStackTrace();
		}
		return response;
	}
	
}
