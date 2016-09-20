/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.job.api.req.SuccessReq;
import com.jci.job.api.res.GrDetailsRes;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.ItemSuccessRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.PoSuccessRes;
import com.jci.job.api.res.SuppDetailsRes;
import com.jci.job.api.res.SuppSuccessRes;
import com.jci.job.apis.ApiClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * The Class ApiClientImpl.
 */
@Service class ApiClientImpl {
	
	/** The apigee client. */
	@Autowired
	private ApiClient apigeeClient;
	
	
	/**
	 * Gets the po details.
	 *
	 * @param erpName the erp name
	 * @param region the region
	 * @param plant the plant
	 * @param ordernumber the ordernumber
	 * @param ordercreationdate the ordercreationdate
	 * @return the po details
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getPoDetailsFallback") ResponseEntity<PoDetailsRes> getPoDetails(String erpName, String region, String plant, String ordernumber,
			String ordercreationdate) {
		return  apigeeClient.getPoDetails(erpName,region,plant,ordernumber,ordercreationdate);
	}

	
	/**
	 * Gets the po details fallback.
	 *
	 * @return the po details fallback
	 */
	public ResponseEntity<PoDetailsRes> getPoDetailsFallback(String erpName, String region, String plant, String ordernumber,
			String ordercreationdate) {		
		return null;
	}

	
	/**
	 * Gets the po details res.
	 *
	 * @param poList the po list
	 * @param erpName the erp name
	 * @param region the region
	 * @param plant the plant
	 * @return the po details res
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getPoDetailsResFallback")
	String getPoDetailsRes(SuccessReq req) {
		return apigeeClient.getPoDetailsRes(req);
	}
	
	/**
	 * Gets the po details res fallback.
	 *
	 * @param poList the po list
	 * @param erpName the erp name
	 * @param region the region
	 * @param plant the plant
	 * @return the po details res fallback
	 */
	public String getPoDetailsResFallback(SuccessReq req) { // NO_UCD (unused code)
		return "Fallback";
	}

	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getGrDetailsFallback") 
	ResponseEntity<GrDetailsRes> getGrDetails(String erpName, String region, String plant, String ordernumber,
			String ordercreationdate) {
		return  apigeeClient.getGrDetails(erpName,region,plant,ordernumber,ordercreationdate);
	}

	
	public ResponseEntity<GrDetailsRes> getGrDetailsFallback(String erpName, String region, String plant, String ordernumber,
			String ordercreationdate) {		
		return null;
	}
	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getGrDetailsResFallback")
	String getGrDetailsRes(SuccessReq req) {
		return apigeeClient.getGrDetailsRes(req);
	}
	
	/**
	 * Gets the po details res fallback.
	 *
	 * @param poList the po list
	 * @param erpName the erp name
	 * @param region the region
	 * @param plant the plant
	 * @return the po details res fallback
	 */
	public String getGrDetailsResFallback(SuccessReq req) { // NO_UCD (unused code)
		return "Fallback";
	}
	
	/**
	 * Gets the items.
	 *
	 * @param erp the erp
	 * @param region the region
	 * @param plant the plant
	 * @param itemnumber the itemnumber
	 * @return the items
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getItemsFallback") ResponseEntity<ItemDetailsRes> getItems(String erp, String region, String plant, String itemnumber) {
		return apigeeClient.getItems(erp, region, plant, itemnumber);
	}

	/**
	 * Gets the items fallback.
	 *
	 * @param erp the erp
	 * @param region the region
	 * @param plant the plant
	 * @param itemnumber the itemnumber
	 * @return the items fallback
	 */
	ResponseEntity<ItemDetailsRes> getItemsFallback(String erp, String region, String plant, String itemnumber) { // NO_UCD (unused code)
		return null;
	}
	
	/**
	 * Gets the items res.
	 *
	 * @param poList the po list
	 * @param erp the erp
	 * @param region the region
	 * @param plant the plant
	 * @return the items res
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getItemsResFallback")
	public String getItemsRes(SuccessReq poList) { // NO_UCD (use default)
		return apigeeClient.getItemsRes(poList);
	}
	
	/**
	 * Gets the items res fallback.
	 *
	 * @param poList the po list
	 * @param erp the erp
	 * @param region the region
	 * @param plant the plant
	 * @return the items res fallback
	 */
	public String getItemsResFallback(SuccessReq poList) { // NO_UCD (unused code)
		return "Fallback";
	}

	/**
	 * Gets the supp.
	 *
	 * @param erp the erp
	 * @param region the region
	 * @param plant the plant
	 * @param suppname the suppname
	 * @return the supp
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getSuppFallback") 
		ResponseEntity<SuppDetailsRes> getSupp(String erp, String region, String plant,String suppname) {
			return apigeeClient.getSupp(erp, region, plant, suppname);
		}
	
	/**
	 * Gets the supp fallback.
	 *
	 * @param erp the erp
	 * @param region the region
	 * @param plant the plant
	 * @param suppname the suppname
	 * @return the supp fallback
	 */
	public ResponseEntity<SuppDetailsRes> getSuppFallback(String erp, String region, String plant,String suppname) { // NO_UCD (unused code)
		return null;
	}

	/**
	 * Gets the supp res.
	 *
	 * @param poList the po list
	 * @param erp the erp
	 * @param region the region
	 * @param plant the plant
	 * @return the supp res
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getSuppResFallback") 
	String getSuppRes(SuccessReq poList) {
		return apigeeClient.getSuppRes(poList);
	}

	/**
	 * Gets the supp res fallback.
	 *
	 * @param poList the po list
	 * @param erp the erp
	 * @param region the region
	 * @param plant the plant
	 * @return the supp res fallback
	 */
	public String getSuppResFallback(SuccessReq poList) { // NO_UCD (unused code)
		return "Fallback";
	}
}
