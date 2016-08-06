package com.jci.job.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SupplierDetailsRes;

@FeignClient(value = "ApigeeServer", url = "http://10.109.218.150:8080")//URL provided by anil 
public interface ApiClient {

	
	@RequestMapping(value = "/poAzureData", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	//public ResponseEntity<PoDetailsResponse>  getPoDetails(@RequestBody PoRequest request);
	public ResponseEntity<PoDetailsRes>  getPoDetails();

	
	@RequestMapping(value = "/itemAzureData", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ItemDetailsRes>  getItemDetails();
	
	
	@RequestMapping(value = "/supplierAzureData", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SupplierDetailsRes>  getSupplierDetails();
	
	/*
	
	@RequestMapping(value = "/getItem", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ItemEntity> getItemEntity();

	@RequestMapping(value = "/getAsn", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AsnEntity> getAsn();

	@RequestMapping(value = "/getPo", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PoEntity> getPo();

	@RequestMapping(value = "/getPoItem", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PoItemsEntity> getPoItem();

	@RequestMapping(value = "/getSupplier", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SupplierEntity> getSupplier();

	@RequestMapping(value = "/setAsn", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String setAsn(@RequestBody AsnEntity asnEntity);

	@RequestMapping(value = "/setPoItem", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String setPoItem(@RequestBody PoItemsEntity poItemsEntity);*/

}
