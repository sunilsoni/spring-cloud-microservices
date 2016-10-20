/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.flatfile.apis.GitClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;



/**
 * The Class GitClientImpl.
 */
@Service
class GitClientImpl {

	/** The github client. */
	@Autowired
	private GitClient gitClient;
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GitClientImpl.class);

	/**
	 * Gets the po json.
	 *
	 * @return the po json
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000") })
	public ResponseEntity<String>  getPoJson() {
		LOG.info("### Starting  GitClientImpl.getPoJson ####");
		return  gitClient.getPoJson();
	}

	/**
	 * Gets the gr json.
	 *
	 * @return the gr json
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000") })
	ResponseEntity<String> getGrJson() {
		return gitClient.getGrJson();
	}

	/**
	 * Gets the item json.
	 *
	 * @return the item json
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000") })
	ResponseEntity<String> getItemJson() {
		return gitClient.getItemJson();
	}


	/**
	 * Gets the supp json.
	 *
	 * @return the supp json
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000") })
	ResponseEntity<String> getSuppJson() {
		return gitClient.getSuppJson();
	}

}
