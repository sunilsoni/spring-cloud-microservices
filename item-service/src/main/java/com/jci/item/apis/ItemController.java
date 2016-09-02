package com.jci.item.apis;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.item.dto.SegmentedDetailReq;
import com.jci.item.dto.SegmentedDetailRes;
import com.jci.item.service.ItemService;
import com.jci.item.utils.AzureUtils;
import com.jci.item.utils.Constants;
import com.microsoft.azure.storage.StorageException;

/**
 * <p>
 * <strong>REST layer for managing Item data.</strong>
 * <p>
 *
 * @author csonisk
 */

@RestController 
public class ItemController {// NO_UCD (unused code)
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService  poService;	
	
	@RequestMapping(value = "/getSegmentedItemDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getSegmentedItemDetails(@RequestBody SegmentedDetailReq request){
		LOG.info("### Starting PoController.getSegmentedItemDetails ###"+request );
		request.setTableName(Constants.TABLE_ITEM);
		
		request.setPartition(AzureUtils.getPartitionKey(request.getErpName().toUpperCase()));//setting hardcoded in repo
		request.setFirstRequest(false);
		SegmentedDetailRes response = new SegmentedDetailRes();
		try {
			response = poService.getItemResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage(e.getMessage());
			LOG.error("### Exception in PoController.getSegmentedItemDetails ###",e);
			e.printStackTrace();
		}
		LOG.info("### Ending PoController.getSegmentedItemDetails ###" +response);
		return response;
	}
	
}
