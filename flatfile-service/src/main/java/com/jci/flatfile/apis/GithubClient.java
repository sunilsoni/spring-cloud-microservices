/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.apis;

import java.io.File;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * <p>
 * <strong>Feign Client for fetching Purchase Orders, Suppliers and Items details mapping file from Github.</strong>
 * <p>
 *
 * @author csonisk
 */
@FeignClient(value = "github", url = "https://raw.githubusercontent.com/sunilsoni/supplier-collaboration-config-dev/master")
public interface GithubClient {
	
	@RequestMapping(value = "/po/E2Open.json", method = RequestMethod.GET , produces = { MediaType.APPLICATION_JSON_VALUE })
	public File getPoJson();

	
	@RequestMapping(value = "/gr/e2open.json", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> getGrJson();


	@RequestMapping(value = "/item/e2open.json", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> getItemJson();

	
	@RequestMapping(value = "/supplier/e2open.json", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> getSuppJson();

	
}
