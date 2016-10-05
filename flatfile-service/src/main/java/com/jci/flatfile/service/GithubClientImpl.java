/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.flatfile.apis.GithubClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * The Class ApiClientImpl.
 */
@Service
class GithubClientImpl {

	/** The apigee client. */
	@Autowired
	private GithubClient githubClient;
	private static final Logger LOG = LoggerFactory.getLogger(GithubClientImpl.class);

	/**
	 * Gets the po details.
	 *
	 * @param apikey
	 *            the apikey
	 * @param erp
	 *            the erp
	 * @return the po details
	 */
	@HystrixCommand(fallbackMethod = "getPoJsonFall", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000") })
	public File getPoJson() {
		LOG.info("### Starting  GithubClientImpl.getPoJson ####");
		return  githubClient.getPoJson();
	}

	/**
	 * Gets the po details fallback.
	 *
	 * @param apikey
	 *            the apikey
	 * @param erp
	 *            the erp
	 * @return the po details fallback
	 */
	public File getPoJsonFall() {
		LOG.info("### Starting  GithubClientImpl.getPoJson ####");
		return githubClient.getPoJson();
	}

	/**
	 * Gets the po details res.
	 *
	 * @param req
	 *            the req
	 * @param apikey
	 *            the apikey
	 * @param erp
	 *            the erp
	 * @return the po details res
	 */
	@HystrixCommand(commandProperties = { @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getGrJsonFallback")
	ResponseEntity<String> getPoDetailsRes() {
		return githubClient.getGrJson();
	}

	/**
	 * Gets the po details res fallback.
	 *
	 * @param req
	 *            the req
	 * @param apikey
	 *            the apikey
	 * @param erp
	 *            the erp
	 * @return the po details res fallback
	 */
	public ResponseEntity<String> getGrJsonFallback() { // NO_UCD (unused code)
		return null;
	}

	/**
	 * Gets the gr details.
	 *
	 * @param apikey
	 *            the apikey
	 * @param erp
	 *            the erp
	 * @return the gr details
	 */
	@HystrixCommand(commandProperties = { @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getItemJsonFallback")
	ResponseEntity<String> getItemJson() {
		return githubClient.getItemJson();
	}

	/**
	 * Gets the gr details fallback.
	 *
	 * @param apikey
	 *            the apikey
	 * @param erp
	 *            the erp
	 * @return the gr details fallback
	 */
	public ResponseEntity<String> getItemJsonFallback() { // NO_UCD (unused
															// code)
		return null;
	}

	/**
	 * Gets the gr details res.
	 *
	 * @param req
	 *            the req
	 * @param apikey
	 *            the apikey
	 * @param erp
	 *            the erp
	 * @return the gr details res
	 */
	@HystrixCommand(commandProperties = { @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getSuppJsonFallback")
	ResponseEntity<String> getSuppJson() {
		return githubClient.getSuppJson();
	}

	public ResponseEntity<String> getSuppJsonFallback() { // NO_UCD (unused
															// code)
		return null;
	}

}
