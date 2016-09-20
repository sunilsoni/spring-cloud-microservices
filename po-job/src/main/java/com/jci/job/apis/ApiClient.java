/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.apis;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jci.job.api.req.SuccessReq;
import com.jci.job.api.res.GrDetailsRes;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.ItemSuccessRes;
import com.jci.job.api.res.PoDetailsRes;
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

	@RequestMapping(value = "/purchaseorders", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPoDetailsRes(@RequestBody SuccessReq poList);
	
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

	@RequestMapping(value = "/items", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getItemsRes(@RequestBody SuccessReq itemList);

	
	/**
	 * Get Supplier master Data
	 * @param erp
	 * @param region
	 * @param plant
	 * @param suppliername
	 * @return SupplierDetailsRes
	 */
	@RequestMapping(value = "/suppliers", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SuppDetailsRes> getSupp(@RequestParam("erp") String erp,@RequestParam("region") String region,@RequestParam("plant") String plant,@RequestParam("suppliername") String suppname);

	@RequestMapping(value = "/suppliers", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getSuppRes(@RequestBody SuccessReq supplierList);

	
	@RequestMapping(value = "/receiptcon", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GrDetailsRes> getGrDetails(@RequestParam("erp") String erp,@RequestParam("region") String region,@RequestParam("plant") String plant,@RequestParam("ordernumber") String ordernumber,@RequestParam("ordercreationdate") String ordercreationdate);
	

	@RequestMapping(value = "/receiptcon", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getGrDetailsRes(@RequestBody SuccessReq poList);
	
}
