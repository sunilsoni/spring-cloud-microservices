package com.jci.job.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SupplierDetailsRes;

@FeignClient(value = "ApigeeServer", url = "${e2open.url.staging}")
public interface ApiClient {

	@RequestMapping(value = "/poAzureData", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	//public ResponseEntity<PoDetailsResponse>  getPoDetails(@RequestBody PoRequest request);
	public ResponseEntity<PoDetailsRes>  getPoDetails();

	
	@RequestMapping(value = "/itemAzureData", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ItemDetailsRes>  getItemDetails();
	
	
	@RequestMapping(value = "/supplierAzureData", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SupplierDetailsRes>  getSupplierDetails();
	

}
