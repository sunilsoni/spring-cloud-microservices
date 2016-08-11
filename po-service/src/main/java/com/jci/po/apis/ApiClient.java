package com.jci.po.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "e2open", url = "${e2open.url.staging}")
public interface ApiClient {

	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String>  processFlatFile();
	
}
