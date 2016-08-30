package com.jci.job.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jci.job.apis.SupplierClient;
import com.jci.job.dto.UploadInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class SupplierClientServiceImpl implements  SupplierClientService{
	private static final Logger LOG = LoggerFactory.getLogger(SupplierClientServiceImpl.class);
	
	@Autowired
	SupplierClient client;
	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900000"),
			@HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
			@HystrixProperty(name = "fallback.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			@HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "sendFlatFileFallback")
	@Override
	public String sendFlatFile(String filename, MultipartFile file) {
		LOG.info("### Starting SupplierClientServiceImpl.sendFlatFile ####");
		
		UploadInfo response = null;//client.sendFlatFile(filename, file);
		
		
		LOG.info("response--->"+response);

		LOG.info("### Ending SupplierClientServiceImpl.sendFlatFile ####");
		return null;
	}
	
	public String sendFlatFileFallback(String filename, MultipartFile file) {
		LOG.info("### Starting SupplierClientServiceImpl.sendFlatFileFallback ####");
		


		
		
		
		LOG.info("### Ending SupplierClientServiceImpl.sendFlatFileFallback ####");
		return null;
	}

	
}
