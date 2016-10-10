/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jci.po.dto.req.ProcessErrorReq;
import com.jci.po.dto.res.ProcessErrorRes;



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
	 * Processing error flat files.
	 *
	 * @param req the req
	 * @return the response entity
	 */
	@RequestMapping(value = "/processErrorPosFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProcessErrorRes> processErrorPosFlatFiles(@RequestBody ProcessErrorReq req);


	/**
	 * Process error supp flat files.
	 *
	 * @param req the req
	 * @return the response entity
	 */
	@RequestMapping(value = "/processErrorSuppFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProcessErrorRes> processErrorSuppFlatFiles(@RequestBody ProcessErrorReq req);

	
	/**
	 * Process error item flat files.
	 *
	 * @param req the req
	 * @return the response entity
	 */
	@RequestMapping(value = "/processErrorItemFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProcessErrorRes> processErrorItemFlatFiles(@RequestBody ProcessErrorReq req);
	

	/**
	 * Process error gr flat files.
	 *
	 * @param req the req
	 * @return the response entity
	 */
	@RequestMapping(value = "/processErrorGrFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProcessErrorRes> processErrorGrFlatFiles(@RequestBody ProcessErrorReq req);
    
	
}
