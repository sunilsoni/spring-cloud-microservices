
/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */ 
package com.jci.job.apis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jci.job.service.ApiClientService;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * <p>
 * <strong>Scheduler layer for fetching Purchase Orders, Suppliers and Items from Apigee APIs.</strong>
 * <p>
 *
 * @author cdevdat, csonisk
 */

@RestController
@MonitoredWithSpring
public class ApiClientController { // NO_UCD (unused code)

	/** The service. */
 @Autowired
	ApiClientService service;
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ApiClientController.class);
	
	/**
	 * Get PO Details scheduler.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the po details
	 */
	@RequestMapping(value = "/getPoDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPoDetails(@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region) {
		String	response = service.getPoDetails(plant, erp, region);
		LOG.info("response-->"+response);
		return response;
	}
	
	/**
	 * Gets the gr details.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the gr details
	 */
	@RequestMapping(value = "/getGrDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getGrDetails(@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region) {
		String	response = service.getGrDetails(plant, erp, region);
		return response;
	}
		
	/**
	 * Get Supplier Details scheduler.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the supp details
	 */
	@RequestMapping(value = "/getSuppDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getSuppDetails(@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region) {
		String	response = service.getSuppDetails(plant, erp, region);
		return response;
	}
	
	/**
	 * Get Item Details scheduler.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the item details
	 */
	@RequestMapping(value = "/getItemDetails", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getItemDetails(@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region) {
		String	response = service.getItemDetails(plant, erp, region);
		return response;
	}
	
	/**
	 * Gets the databy plant name.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the databy plant name
	 */
	@RequestMapping(value = "/getDatabyPlantName", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getDatabyPlantName(@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region) {
		String response="SUCCESS";
		
		if(StringUtils.isBlank(plant) || StringUtils.isBlank(erp)){
			response="Plant or Erp can not be blank !";
			return response;
		}
		
		getSuppDetails(plant,erp,region);
		getItemDetails(plant,erp,region);
		getPoDetails(plant,erp,region);
		getGrDetails(plant,erp,region);
		
		return response;
	}
	
	/**
	 * Databy plant name.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the string
	 */
	@RequestMapping(value = "/databyPlantName", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String databyPlantName(@RequestHeader(value="plant") String plant, @RequestHeader(value="erp") String erp, @RequestHeader(value="region") String region) {
		String response="SUCCESS";
		
		if(StringUtils.isBlank(plant) || StringUtils.isBlank(erp)){
			response="Plant or Erp can not be blank !";
			return response;
		}
		
		String msg = getSuppDetails(plant,erp,region);
		LOG.error("getSuppDetails--->"+msg);
		
		msg = getItemDetails(plant,erp,region);
		LOG.error("getItemDetails--->"+msg);
		
		msg = getPoDetails(plant,erp,region);
		LOG.error("getPoDetails--->"+msg);
		
		msg = getGrDetails(plant,erp,region);
		LOG.error("getGrDetails--->"+msg);
		
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
