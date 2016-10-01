
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
		long startTime = System.nanoTime();
		String response=null;
		try {
			response = service.getPoDetails();
			LOG.info("response-->"+response);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in ApigeeClientController.getPoDetails ####",e);
		}
		//processPoFlatFile();
		/*Thread t = new Thread(new Runnable() {
            public void run() {
                processPoFlatFile();
            }
        });
        //t.setDaemon(true);
        t.start();*/
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		
		double seconds = (double)duration / 1000000000.0;
		LOG.info("getPoDetails seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Get Supplier Details scheduler.
	 *
	 * @return the supp details
	 */
	@RequestMapping(value = "/getSuppDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getSuppDetails() {
		long startTime = System.nanoTime();
		String response=null;
		try {
			response = service.getSuppDetails();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in ApigeeClientController.getSuppDetails ####",e);
		}
	    Thread t = new Thread(new Runnable() {
	            public void run() {
	                processSuppFlatFile();
	            }
	    });
	    t.setDaemon(true);
	    t.start();
	        
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Get Item Details scheduler.
	 *
	 * @return the item details
	 */
	@RequestMapping(value = "/getItemDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getItemDetails() {
		long startTime = System.nanoTime();
		String response =null;
		try {
			response = service.getItemDetails();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in ApigeeClientController.getPoDetails ####",e);
		}
        Thread t = new Thread(new Runnable() {
            public void run() {
                processItemFlatFile();
            }
        });
        t.setDaemon(true);
        t.start();
        
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Gets the gr details.
	 *
	 * @return the gr details
	 */
	@RequestMapping(value = "/getGrDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getGrDetails() {
		long startTime = System.nanoTime();
		String response =null;
		try {
			response = service.getGrDetails();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in ApigeeClientController.getPoDetails ####",e);
		}
        Thread t = new Thread(new Runnable() {
            public void run() {
                processGrFlatFile();
            }
        });
        t.setDaemon(true);
        t.start();
        
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Process Flat File scheduler for PO details.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/processPoFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processPoFlatFile() {
		long startTime = System.nanoTime();
		String response=null;
		 service.processPoFlatFile();
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	
	   @RequestMapping(value = "/processGrFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public String processGrFlatFile() {
        long startTime = System.nanoTime();
        String response=null;
         service.processGrFlatFile();
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        double seconds = (double)duration / 1000000000.0;
        LOG.info("processGrFlatFile seconds--->"+seconds);
        return response;
    }
	
	/**
	 * Process Flat File scheduler for Suppliers.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/processSuppFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processSuppFlatFile() {
		long startTime = System.nanoTime();
		String response=null;
		
			 service.processSuppFlatFile();
		

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
	/**
	 * Process Flat File scheduler for Items.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/processItemFlatFile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processItemFlatFile() {
		long startTime = System.nanoTime();
		String response=null;
		 service.processItemFlatFile();		

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1000000000.0;
		LOG.info("seconds--->"+seconds);
		return response;
	}
	
}
