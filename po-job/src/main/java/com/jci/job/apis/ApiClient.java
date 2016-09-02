package com.jci.job.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.ItemSuccessRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.PoSuccessRes;
import com.jci.job.api.res.SuppDetailsRes;
import com.jci.job.api.res.SuppSuccessRes;

/**
 * <p>
 * <strong>Rest Client for fetching Purchase Orders, Suppliers and Items detail data from Apigee(APIs).</strong>
 * <p>
 *
 * @author cdevdat, csonisk
 */
@FeignClient(value = "apigee", url = "${apigee.staging.url}")
public interface ApiClient {

	/**
	 * Get PO Details
	 * @param erp
	 * @param region
	 * @param plant
	 * @param ordernumber
	 * @param ordercreationdate
	 * @return PoDetailsRes
	 */
	@RequestMapping(value = "/purchaseorders", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PoDetailsRes> getPoDetails(@RequestParam("erp") String erp,@RequestParam("region") String region,@RequestParam("plant") String plant,@RequestParam("ordernumber") String ordernumber,@RequestParam("ordercreationdate") String ordercreationdate);

	@RequestMapping(value = "/pologupdate", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPoDetailsRes(@RequestBody PoSuccessRes poList,@RequestParam("erp") String erp,@RequestParam("region") String region,@RequestParam("plant") String plant);
	
	/**
	 * Get Item master Data
	 * @param erp
	 * @param region
	 * @param plant
	 * @param itemnumber
	 * @return ItemDetailsRes
	 */
	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ItemDetailsRes> getItems(@RequestParam("erp") String erp,@RequestParam("region") String region, @RequestParam("plant") String plant,@RequestParam("itemnumber") String itemnumber);

	@RequestMapping(value = "/itemsUpdate", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getItemsRes(@RequestBody ItemSuccessRes poList,@RequestParam("erp") String erp,@RequestParam("region") String region,@RequestParam("plant") String plant);

	
	/**
	 * Get Supplier master Data
	 * @param erp
	 * @param region
	 * @param plant
	 * @param suppliername
	 * @return SupplierDetailsRes
	 */
	@RequestMapping(value = "/suppliers", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SuppDetailsRes> getSupp(@RequestParam("erp") String erp,@RequestParam("region") String region,@RequestParam("plant") String plant,@RequestParam("suppname") String suppname);


	@RequestMapping(value = "/suppliersUpdate", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getSuppRes(@RequestBody SuppSuccessRes poList,@RequestParam("erp") String erp,@RequestParam("region") String region,@RequestParam("plant") String plant);

	
}
