package com.jci.po.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jci.po.dto.req.TempRequest;
import com.jci.po.dto.res.TempResponse;


@FeignClient(value = "e2open", url = "${e2open.url.staging}")
public interface ApiClient {

	
	@RequestMapping(value = "/removeThis", method = RequestMethod.POST)
	public ResponseEntity<String>  processFlatFile();
	
	//Below method is created for node.js poc purpose
	@RequestMapping(value = "/po", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TempResponse> postSupplierData(@RequestBody TempRequest req);
	
}
