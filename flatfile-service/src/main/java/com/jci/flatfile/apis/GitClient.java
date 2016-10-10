/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * <p>
 * <strong>Feign Client for fetching Purchase Orders, Suppliers and Items details mapping file from Git Location.</strong>
 * <p>
 *
 * @author csonisk
 */
 
@FeignClient(value = "git", url = "${git.repo.config.url}")
public interface GitClient {
	
	/**
	 * Gets the po json.
	 *
	 * @return the po json
	 */
	@RequestMapping(value = "/po/e2open.json", method = RequestMethod.GET)
	public ResponseEntity<String>  getPoJson();

	
	/**
	 * Gets the gr json.
	 *
	 * @return the gr json
	 */
	@RequestMapping(value = "/gr/e2open.json", method = RequestMethod.GET)
	public ResponseEntity<String> getGrJson();


	/**
	 * Gets the item json.
	 *
	 * @return the item json
	 */
	@RequestMapping(value = "/item/e2open.json", method = RequestMethod.GET)
	public ResponseEntity<String> getItemJson();

	
	/**
	 * Gets the supp json.
	 *
	 * @return the supp json
	 */
	@RequestMapping(value = "/supplier/e2open.json", method = RequestMethod.GET)
	public ResponseEntity<String> getSuppJson();

	
}
