/**
 * 
 */
package com.jci.po.service;

import java.io.File;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.po.apis.ApiClient;
import com.jci.po.dto.req.FlatFileReq;
import com.jci.po.dto.res.FlatFileRes;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ApiClientServiceImpl implements ApiClientService {

	private static final Logger LOG = LoggerFactory.getLogger(ApiClientServiceImpl.class);
	
	@Autowired
	ApiClient client;

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "processFlatFileFallback")
	@Override
	public FlatFileRes processFlatFile(FlatFileReq req) {
		LOG.info("### Starting ApigeeClientService.processFlatFile ####"+req);
		
		ResponseEntity<String> entity = client.processFlatFile();
		
		HashMap<String,File> nameToFileMap = req.getNameToFileMap();
		LOG.info("nameToFileMap--->"+nameToFileMap);
		
		FlatFileRes res = new FlatFileRes();
		res.setBody(entity.getBody());
		
		res.setContentType(String.valueOf(entity.getHeaders().getContentType()));
		res.setStatusCode(String.valueOf(entity.getStatusCode()));
		
		LOG.info("### Ending ApigeeClientService.processFlatFile ####"+res);
		
		return res;
		//return new ResponseEntity<PoDetailsResponse>(responseBody, HttpStatus.OK);
	}
	

	public FlatFileRes processFlatFileFallback(FlatFileReq req) {		
		LOG.info("### Starting ApigeeClientService.processFlatFileFallback ####"+req);
		
		FlatFileRes res = new FlatFileRes();
		res.setMessage("Process Po Details Fallback call, seems PO API service is down");
		
		LOG.info("### Ending ApigeeClientService.processFlatFileFallback ####"+res);
		return res;
	}


}
