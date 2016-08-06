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
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
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
		//return new ResponseEntity<PoDetailsResponse>(responseBody, HttpStatus.OK);
	}
	

	public PoDetailsRes getPoDetailsFallback() {		
		LOG.info("### Starting ApigeeClientService.getPoDetailsFallback ####");
		ResponseEntity<PoDetailsRes> response = client.getPoDetails();
		PoDetailsRes responseBody = response.getBody();
		responseBody.setErrorMsg("Get Po Details Fallback call, seems PO API service is down");
		LOG.info("### Ending ApigeeClientService.getPoDetailsFallback ####"+responseBody);
		return responseBody;
	}


	@Override
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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getItemDetailsFallback")
	public ItemDetailsRes getItemDetails() {

		ResponseEntity<ItemDetailsRes> response = client.getItemDetails();
		ItemDetailsRes responseBody = response.getBody();
		LOG.info("responseBody-->"+responseBody);
		
		return null;
	}
	
	public SupplierDetailsRes getItemDetailsFallback() {
		
		ResponseEntity<SupplierDetailsRes> response = client.getSupplierDetails();
		SupplierDetailsRes responseBody = response.getBody();
		LOG.info("responseBody-->"+responseBody);
		
		return null;
	}

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
			@HystrixProperty(name = "circuitBreaker.forceClosed", value = "false") }, fallbackMethod = "getSupplierDetailsFallback")
	@Override
	public SupplierDetailsRes getSupplierDetails() {


		return null;
	}
	
	public SupplierDetailsRes getSupplierDetailsFallback() {


		return null;
	}
	
	
	/*
	@HystrixCommand(fallbackMethod = "batchInsertFallback")
	public BatchInsertRes batchInsert(BatchInsertReq request) {
		LOG.info("### Starting ApigeeClientService.batchInsert ###"+request);
		BatchInsertRes response = repo.batchInsert(request);
		
		LOG.info("### Ending ApigeeClientService.batchInsert ###"+response);
		
		return response;
	}

	public BatchInsertRes batchInsertFallback(BatchInsertReq request) {
		LOG.info("### Starting ApigeeClientService.batchInsertFallback ###"+request);
		BatchInsertRes response = repo.batchInsert(request);
		
		LOG.info("### Ending ApigeeClientService.batchInsertFallback ###"+response);
		return response;
	}*/

	
	/*	@HystrixCommand(fallbackMethod = "getFail")
	public ResponseEntity<AsnEntity> getAsnData() {
		ResponseEntity<AsnEntity> entity = client.getAsn();
		asnEntity.setAction(entity.getBody().getAction());
		System.out.println(asnEntity.getAction());

		return entity;

	}

	public ResponseEntity<AsnEntity> getFail() {
		ResponseEntity<AsnEntity> entity = client.getAsn();
		System.out.println("FallBack of asn called");

		return entity;

	}

	@HystrixCommand(fallbackMethod = "getItemFail")
	public ResponseEntity<ItemEntity> getItem() {

		ResponseEntity<ItemEntity> itemResponse = client.getItemEntity();
		return itemResponse;

	}

	public ResponseEntity<ItemEntity> getItemFail() {

		ResponseEntity<ItemEntity> itemResponseFail = client.getItemEntity();
		System.out.println("Item Entity fallback method called");
		return itemResponseFail;

	}

	@HystrixCommand(fallbackMethod = "getPoFail")
	public ResponseEntity<PoEntity> getPo() {

		ResponseEntity<PoEntity> poResponse = client.getPo();
		return poResponse;

	}

	public ResponseEntity<PoEntity> getPoFail() {

		ResponseEntity<PoEntity> poResponse = client.getPo();
		System.out.println("Fail to fetch poEntity " + poResponse.getStatusCode());
		return poResponse;

	}

	@HystrixCommand(fallbackMethod = "getPoItemFail")
	public ResponseEntity<PoItemsEntity> getPoItem() {

		ResponseEntity<PoItemsEntity> poResponse = client.getPoItem();
		System.out.println(poResponse.getStatusCode());
		return poResponse;

	}

	public ResponseEntity<PoItemsEntity> getPoItemFail() {

		ResponseEntity<PoItemsEntity> poResponse = client.getPoItem();
		System.out.println("Fail to fetch poItemEntity " + poResponse.getStatusCode());

		return poResponse;

	}

	@HystrixCommand(fallbackMethod = "getSupplierFail")
	public ResponseEntity<SupplierEntity> getSupplierData() {
		ResponseEntity<SupplierEntity> supplierResponse = client.getSupplier();

		return supplierResponse;
	}

	public ResponseEntity<SupplierEntity> getSupplierFail() {
		ResponseEntity<SupplierEntity> supplierResponse = client.getSupplier();

		return supplierResponse;
	}

	@HystrixCommand(fallbackMethod = "setAsnFail")
	public String setAsn(@RequestBody AsnEntity asnEntity) {
		return "successfully added !!!!!";
	}

	public String setAsnFail() {
		return "failled to send , ASN";
	}

	@HystrixCommand(fallbackMethod = "setPoItemFail")
	public String setPoItem(@RequestBody PoItemsEntity poItemsEntity) {
		return "successfully sent data !!!!!! ";
	}

	public String setPoItemFail() {
		return "Oop!!!! Failed to send Po Item";
	}*/
	



}
