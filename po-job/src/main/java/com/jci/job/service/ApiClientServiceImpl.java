/**
 * 
 */
package com.jci.job.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SupplierDetailsRes;
import com.jci.job.apis.ApiClient;
import com.jci.job.azure.BatchInsertReq;
import com.jci.job.azure.BatchInsertRes;
import com.jci.job.repo.JobRepo;
import com.jci.job.utils.PrepareBatchInsertReq;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author cdevdat
 *
 */
@Service
public class ApiClientServiceImpl implements ApiClientService {

	private static final Logger LOG = LoggerFactory.getLogger(ApiClientServiceImpl.class);
	@Autowired
	ApiClient client;

	@Autowired
	private JobRepo repo;

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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getPoDetailsFallback")
	@Override
	public PoDetailsRes getPoDetails() {
		LOG.info("### Starting ApigeeClientService.getPoDetails ####");
		
		ResponseEntity<PoDetailsRes> response = client.getPoDetails();
		PoDetailsRes responseBody = response.getBody();
		
		List<BatchInsertReq>  req = PrepareBatchInsertReq.prepareReq(responseBody);
		LOG.info("req-->"+req);
		
		for (BatchInsertReq request : req) {
			BatchInsertRes res = repo.batchInsert(request);
			LOG.info("res--->"+res);
		}
		LOG.info("### Ending ApigeeClientService.getPoDetails ####");
		return responseBody;
	}
	
	@Override
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getItemDetailsFallback")
	public ItemDetailsRes getItemDetails() {
		ResponseEntity<ItemDetailsRes> response = client.getItemDetails();
		ItemDetailsRes responseBody = response.getBody();
		LOG.info("responseBody-->"+responseBody);
		
		List<BatchInsertReq>  req = PrepareBatchInsertReq.prepareItemReq(responseBody);
		LOG.info("req-->"+req);
		
		for (BatchInsertReq request : req) {
			BatchInsertRes res = repo.batchInsert(request);
			LOG.info("res--->"+res);
		}
		return responseBody;
	}

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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getSupplierDetailsFallback")
	@Override
	public SupplierDetailsRes getSupplierDetails() {

		ResponseEntity<SupplierDetailsRes> response = client.getSupplierDetails();
		SupplierDetailsRes responseBody = response.getBody();
		LOG.info("responseBody-->"+responseBody);
		
		List<BatchInsertReq>  req = PrepareBatchInsertReq.prepareSupplierReq(responseBody);
		LOG.info("req-->"+req);
		
		for (BatchInsertReq request : req) {
			BatchInsertRes res = repo.batchInsert(request);
			LOG.info("res--->"+res);
		}
		return responseBody;
	}
	
	

	public PoDetailsRes getPoDetailsFallback() {		
		LOG.info("### Starting ApigeeClientService.getPoDetailsFallback ####");
		ResponseEntity<PoDetailsRes> response = client.getPoDetails();
		PoDetailsRes responseBody = response.getBody();
		responseBody.setErrorMsg("Get Po Details Fallback call, seems PO API service is down");
		LOG.info("### Ending ApigeeClientService.getPoDetailsFallback ####"+responseBody);
		return responseBody;
	}
	
	public SupplierDetailsRes getSupplierDetailsFallback() {
		LOG.info("### Starting ApigeeClientService.getSupplierDetailsFallback ####");
		ResponseEntity<SupplierDetailsRes> response = client.getSupplierDetails();
		SupplierDetailsRes responseBody = response.getBody();
		responseBody.setErrorMsg("Get Supplier Details Fallback call, seems Supplier API service is down");
		
		LOG.info("### Ending ApigeeClientService.getSupplierDetailsFallback ####");

		return responseBody;
	}
	
	public ItemDetailsRes getItemDetailsFallback() {
		LOG.info("### Starting ApigeeClientService.getItemDetailsFallback ####");
		ResponseEntity<ItemDetailsRes> response = client.getItemDetails();
		ItemDetailsRes responseBody = response.getBody();
		responseBody.setErrorMsg("Get Item Details Fallback call, seems Item API service is down");
		
		LOG.info("### Ending ApigeeClientService.getItemDetailsFallback ####");
		return responseBody;
	}
	
}
