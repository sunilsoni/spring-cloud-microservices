/**
 * 
 */
package com.jci.job.apis;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SupplierDetailsRes;
import com.jci.job.azure.BatchInsertReq;
import com.jci.job.entity.PoEntity;
import com.jci.job.service.ApiClientService;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableEntity;

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

	@RequestMapping(value = "/getPoDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPoDetails() {
		LOG.info("### Starting ApigeeClientController.getPoDetails ####");
		//PoRequest request = new PoRequest();
		String response=null;
		try {
			response = service.getPoDetails();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("response-->"+response);
		LOG.info("### Ending ApigeeClientController.getPoDetails ####");
		return response;
	}
	
	@RequestMapping(value = "/getSupplierDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getSupplierDetails() {
		LOG.info("### Starting ApigeeClientController.getSupplierDetails ####");
		//PoRequest request = new PoRequest();
		String response = service.getSupplierDetails();
		LOG.info("response-->"+response);
		
		LOG.info("### Ending ApigeeClientController.getSupplierDetails ####");
		return response;
	}
	
	@RequestMapping(value = "/getItemDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getItemDetails() {
		LOG.info("### Starting ApigeeClientController.getItemDetails ####");
		//PoRequest request = new PoRequest();
		String response = service.getItemDetails();
		LOG.info("response-->"+response);
		
		LOG.info("### Ending ApigeeClientController.getItemDetails ####");
		return response;
	}
	
	
	@RequestMapping(value = "/processFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processFlatFile() {
		LOG.info("### Starting ApigeeClientController.processFlatFile ####");
			
		String response=null;
		try {
			response = service.processFlatFile();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			e.printStackTrace();
		}
			
		LOG.info("response-->"+response);
		LOG.info("### Ending ApigeeClientController.processFlatFile ####");
		return response;
	}
	
}
