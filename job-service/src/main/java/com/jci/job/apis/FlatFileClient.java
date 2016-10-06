/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * <strong>Feign Client for Generating Flat Files.</strong>
 * <p>
 *
 * @author csonisk
 */
@FeignClient("flatfile-service")
public interface FlatFileClient {

	/**
	 * Processing intransit flat files.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/processPoFlatFiles",method = RequestMethod.GET)
	public ResponseEntity<String> processPoFlatFiles();

	/**
	 * Process supp flat files.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/processSuppFlatFiles",method = RequestMethod.GET)
	public ResponseEntity<String> processSuppFlatFiles();
	
	/**
	 * Process item flat files.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/processItemFlatFiles",method = RequestMethod.GET)
	public ResponseEntity<String> processItemFlatFiles();

	/**
	 * Process gr flat files.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/processGrFlatFiles",method = RequestMethod.GET)
	public ResponseEntity<String> processGrFlatFiles();
}
