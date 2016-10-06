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
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SuppDetailsRes;

/**
 * <p>
 * <strong>Feign Client for fetching Purchase Orders, Suppliers and Items detail data from Apigee(APIs).</strong>
 * <p>
 *
 * @author cdevdat, csonisk
 */
@FeignClient(value = "apigee", url = "${apigee.staging.url}")
public interface ApiClient {

	/**
	 * Gets the po details.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the po details
	 */
	@RequestMapping(value = "/purchaseorders", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PoDetailsRes> getPoDetails(@RequestParam("apikey") String apikey,@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region);

	/**
	 * Gets the po details res.
	 *
	 * @param poList the po list
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the po details res
	 */
	@RequestMapping(value = "/poreadconfirm", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPoDetailsRes(@RequestBody SuccessReq poList,@RequestParam("apikey") String apikey,@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region);
	
	/**
	 * Gets the items.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the items
	 */
	@RequestMapping(value = "/parts", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ItemDetailsRes> getItems(@RequestParam("apikey") String apikey,@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region);

	/**
	 * Gets the items res.
	 *
	 * @param itemList the item list
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the items res
	 */
	@RequestMapping(value = "/itemreadconfirm", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getItemsRes(@RequestBody SuccessReq itemList,@RequestParam("apikey") String apikey,@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region);

	
	/**
	 * Gets the supp.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the supp
	 */
	@RequestMapping(value = "/suppliers", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SuppDetailsRes> getSupp(@RequestParam("apikey") String apikey,@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region);

	/**
	 * Gets the supp res.
	 *
	 * @param supplierList the supplier list
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the supp res
	 */
	@RequestMapping(value = "/supplierreadconfirm", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getSuppRes(@RequestBody SuccessReq supplierList,@RequestParam("apikey") String apikey,@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region);

	
	/**
	 * Gets the gr details.
	 *
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the gr details
	 */
	@RequestMapping(value = "/receiptcon", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GrDetailsRes> getGrDetails(@RequestParam("apikey") String apikey,@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region);

	/**
	 * Gets the gr details res.
	 *
	 * @param poList the po list
	 * @param apikey the apikey
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the gr details res
	 */
	@RequestMapping(value = "/receiptconreadconfirm", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getGrDetailsRes(@RequestBody SuccessReq poList,@RequestParam("apikey") String apikey,@RequestParam("plant") String plant, @RequestParam("erp") String erp, @RequestParam("region") String region);
	
}
