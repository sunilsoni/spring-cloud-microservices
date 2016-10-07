/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.job.apis.FlatFileClient;
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
	 * Process po flat files.
	 *
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "processPoFlatFilesFallback")
	ResponseEntity<String> processPoFlatFiles() {
		return  client.processPoFlatFiles();
	}

	
	/**
	 * Process po flat files fallback.
	 *
	 * @return the response entity
	 */
	public ResponseEntity<String> processPoFlatFilesFallback() {	 // NO_UCD (unused code)
	    LOG.info("#####  processPoFlatFilesFallback ####");
	    
		return null;
	}

	
	/**
	 * Process supp flat files.
	 *
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "processSuppFlatFilesFallback")
	ResponseEntity<String> processSuppFlatFiles() {
		return client.processSuppFlatFiles();
	}
	
	/**
	 * Process supp flat files fallback.
	 *
	 * @return the response entity
	 */
	public ResponseEntity<String> processSuppFlatFilesFallback() { // NO_UCD (unused code)
		return null;
	}

	
	/**
	 * Process item flat files.
	 *
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "processItemFlatFilesFallback") 
	ResponseEntity<String> processItemFlatFiles() {
		return  client.processItemFlatFiles();
	}

	
	/**
	 * Process item flat files fallback.
	 *
	 * @return the response entity
	 */
	public ResponseEntity<String> processItemFlatFilesFallback() {		 // NO_UCD (unused code)
		return null;
	}
	
	/**
	 * Process gr flat files.
	 *
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "processGrFlatFilesFallback")
	ResponseEntity<String> processGrFlatFiles() {
		return client.processGrFlatFiles();
	}
	
	/**
	 * Process gr flat files fallback.
	 *
	 * @return the response entity
	 */
	public ResponseEntity<String> processGrFlatFilesFallback() { // NO_UCD (unused code)
	    return null;
	}
}
