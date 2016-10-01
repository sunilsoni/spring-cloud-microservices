/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
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
	 * Processing intransit flat files
	 */
	@RequestMapping(value = "/processPoFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> processPoFlatFiles();

	@RequestMapping(value = "/processSuppFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> processSuppFlatFiles();
	
	@RequestMapping(value = "/processItemFlatFiles", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> processItemFlatFiles();

	@RequestMapping(value = "/processGrFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> processGrFlatFiles();
}
