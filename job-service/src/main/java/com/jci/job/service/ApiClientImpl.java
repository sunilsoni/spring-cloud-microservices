/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.job.api.req.SuccessReq;
import com.jci.job.api.res.GrDetailsRes;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SuppDetailsRes;
import com.jci.job.apis.ApiClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * <p>
 * <strong> The Api Client Implementation.</strong>
 * <p>
 *
 * @author csonisk
 */
@Service class ApiClientImpl {
	
	/** The apigee client. */
	@Autowired
	private ApiClient apigeeClient;
	
	
	/**
	 * Gets the po details.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getPoDetailsFallback") 
	ResponseEntity<PoDetailsRes> getPoDetails(String apikey,String plant, String erp, String region) {
		return  apigeeClient.getPoDetails(apikey,plant,erp,region);
	}

	
	/**
	 * Gets the po details fallback.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the po details fallback
	 */
	public ResponseEntity<PoDetailsRes> getPoDetailsFallback(String apikey,String plant, String erp, String region) {		 // NO_UCD (unused code)
		return null;
	}

	
	/**
	 * Gets the po details res.
	 *
	 * @param req the req
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
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
	String getPoDetailsRes(SuccessReq req,String apikey,String plant, String erp, String region) {
		return apigeeClient.getPoDetailsRes(req,apikey,plant,erp,region);
	}
	
	/**
	 * Gets the po details res fallback.
	 *
	 * @param req the req
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the po details res fallback
	 */
	public String getPoDetailsResFallback(SuccessReq req,String apikey,String plant, String erp, String region) { // NO_UCD (unused code)
		return "Fallback";
	}

	
	/**
	 * Gets the gr details.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the gr details
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getGrDetailsFallback") 
	ResponseEntity<GrDetailsRes> getGrDetails(String apikey,String plant, String erp, String region) {
		return  apigeeClient.getGrDetails(apikey,plant,erp,region);
	}

	
	/**
	 * Gets the gr details fallback.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the gr details fallback
	 */
	public ResponseEntity<GrDetailsRes> getGrDetailsFallback(String apikey,String plant, String erp, String region) {		 // NO_UCD (unused code)
		return null;
	}
	
	/**
	 * Gets the gr details res.
	 *
	 * @param req the req
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the gr details res
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getGrDetailsResFallback")
	String getGrDetailsRes(SuccessReq req,String apikey,String plant, String erp, String region) {
		return apigeeClient.getGrDetailsRes(req,apikey,plant,erp,region);
	}
	
	/**
	 * Gets the po details res fallback.
	 *
	 * @param req the req
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the po details res fallback
	 */
	public String getGrDetailsResFallback(SuccessReq req,String apikey,String plant, String erp, String region) { // NO_UCD (unused code)
		return "Fallback";
	}
	
	/**
	 * Gets the items.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getItemsFallback") ResponseEntity<ItemDetailsRes> getItems(String apikey,String plant, String erp, String region) {
		return apigeeClient.getItems(apikey,plant,erp,region);
	}

	/**
	 * Gets the items fallback.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the items fallback
	 */
	ResponseEntity<ItemDetailsRes> getItemsFallback(String apikey,String plant, String erp, String region) { // NO_UCD (unused code)
		return null;
	}
	
	/**
	 * Gets the items res.
	 *
	 * @param poList the po list
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
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
	public String getItemsRes(SuccessReq poList,String apikey,String plant, String erp, String region) { // NO_UCD (use default)
		return apigeeClient.getItemsRes(poList,apikey,plant,erp,region);
	}
	
	/**
	 * Gets the items res fallback.
	 *
	 * @param poList the po list
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the items res fallback
	 */
	public String getItemsResFallback(SuccessReq poList,String apikey,String plant, String erp, String region) { // NO_UCD (unused code)
		return "Fallback";
	}

	/**
	 * Gets the supp.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
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
		ResponseEntity<SuppDetailsRes> getSupp(String apikey,String plant, String erp, String region) {
			return apigeeClient.getSupp(apikey,plant,erp,region);
		}
	
	/**
	 * Gets the supp fallback.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the supp fallback
	 */
	public ResponseEntity<SuppDetailsRes> getSuppFallback(String apikey,String plant, String erp, String region) { // NO_UCD (unused code)
		return null;
	}

	/**
	 * Gets the supp res.
	 *
	 * @param poList the po list
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
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
	String getSuppRes(SuccessReq poList,String apikey,String plant, String erp, String region) {
		return apigeeClient.getSuppRes(poList,apikey,plant,erp,region);
	}

	/**
	 * Gets the supp res fallback.
	 *
	 * @param poList the po list
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the supp res fallback
	 */
	public String getSuppResFallback(SuccessReq poList,String apikey,String plant, String erp, String region) { // NO_UCD (unused code)
		return "Fallback";
	}
}
