/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.po.apis.FlatFileClient;
import com.jci.po.dto.req.ProcessErrorReq;
import com.jci.po.dto.res.ProcessErrorRes;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;



/**
 * <p>
 * <strong>Feign Client Implementation for Generating Flat Files.</strong>
 * <p>
 *
 * @author csonisk
 */
@Service class FlatFileClientImpl {
    
    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(FlatFileClientImpl.class);
	
	/** The client. */
	@Autowired
	private FlatFileClient client;
	
	
	/**
	 * Process error pos flat files.
	 *
	 * @param req the req
	 * @return the response entity
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
            @HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "processErrorPosFlatFilesFallback")
	ResponseEntity<ProcessErrorRes> processErrorPosFlatFiles(ProcessErrorReq req) { // NO_UCD (unused code)
		return client.processErrorPosFlatFiles(req);
	}

	/**
	 * Process error pos flat files fallback.
	 *
	 * @param req the req
	 * @return the response entity
	 */
	ResponseEntity<ProcessErrorRes> processErrorPosFlatFilesFallback(ProcessErrorReq req) { // NO_UCD (unused code)
	    LOG.info(" processErrorPosFlatFilesFallback######");
	    return client.processErrorPosFlatFiles(req);
	}
	
	/**
	 * Process error supp flat files.
	 *
	 * @param req the req
	 * @return the response entity
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "processErrorSuppFlatFilesFallback")
	public ResponseEntity<ProcessErrorRes> processErrorSuppFlatFiles(ProcessErrorReq req) { // NO_UCD (use default)
		return client.processErrorSuppFlatFiles(req);
	}
	
	/**
	 * Process error supp flat files fallback.
	 *
	 * @param req the req
	 * @return the response entity
	 */
	public ResponseEntity<ProcessErrorRes> processErrorSuppFlatFilesFallback(ProcessErrorReq req) { // NO_UCD (unused code)
	    return null;
	}

	/**
	 * Process error item flat files.
	 *
	 * @param req the req
	 * @return the response entity
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "processErrorItemFlatFilesFallback") 
	ResponseEntity<ProcessErrorRes> processErrorItemFlatFiles(ProcessErrorReq req) { // NO_UCD (unused code)
			return client.processErrorItemFlatFiles(req);
		}
	
	/**
	 * Process error item flat files fallback.
	 *
	 * @param req the req
	 * @return the response entity
	 */
	public ResponseEntity<ProcessErrorRes> processErrorItemFlatFilesFallback(ProcessErrorReq req) { // NO_UCD (unused code)
		return null;
	}

	/**
	 * Process error gr flat files.
	 *
	 * @param req the req
	 * @return the response entity
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "processErrorGrFlatFilesFallback") 
	ResponseEntity<ProcessErrorRes> processErrorGrFlatFiles(ProcessErrorReq req) { // NO_UCD (unused code)
		return client.processErrorGrFlatFiles(req);
	}

	/**
	 * Process error gr flat files fallback.
	 *
	 * @param req the req
	 * @return the response entity
	 */
	public ResponseEntity<ProcessErrorRes> processErrorGrFlatFilesFallback(ProcessErrorReq req) { // NO_UCD (unused code)
	    return null;
	}
}
