/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.item.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.dto.SegmentedDetailReq;
import com.jci.dto.SegmentedDetailRes;
import com.jci.item.service.ItemService;
import com.jci.utils.Constants;


/**
 * <p>
 * <strong>REST layer for managing Item data.</strong>
 * <p>
 * 
 * @author csonisk
 */

@RestController 
public class ItemController {// NO_UCD (unused code)
	
	/** The Constant LOG. */
	//private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);
	
	/** The po service. */
	@Autowired
	private ItemService  poService;	
	
	/**
	 * Gets the segmented item details.
	 *
	 * @param request the request
	 * @return the segmented item details
	 */
	@RequestMapping(value = "/getSegmentedItemDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getSegmentedItemDetails(@RequestBody SegmentedDetailReq request){
		request.setTableName(Constants.TABLE_ITEM);
		request.setPartition(request.getErpName().toUpperCase());
		request.setFirstRequest(false);
		return  poService.getItemResultSet(request);
	}
	
}
