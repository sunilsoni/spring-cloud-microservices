package com.jci.supplier.apis;

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

import com.jci.supplier.dto.SegmentedDetailReq;
import com.jci.supplier.dto.SegmentedDetailRes;
import com.jci.supplier.service.SupplierService;
import com.jci.supplier.utils.AzureUtils;
import com.jci.supplier.utils.Constants;
import com.microsoft.azure.storage.StorageException;

/**
 * <p>
 * <strong>REST layer for managing Supplier data.</strong>
 * <p>
 *
 * @author csonisk
 */

@RestController 
public class SupplierController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SupplierController.class);
	
	@Autowired
	private SupplierService  poService;	
	
	@RequestMapping(value = "/getSegmentedSupplierDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getSupplierDetails(@RequestBody SegmentedDetailReq request){
		LOG.info("### Starting PoController.getSupplierDetails ###"+request );
		request.setTableName(Constants.TABLE_SUPPLIER);
		request.setPartition(AzureUtils.getPartitionKey(request.getErpName().toUpperCase()));
		request.setFirstRequest(false);
		SegmentedDetailRes response = new SegmentedDetailRes();
		
		try {
			response = poService.getSupplierResultSet(request);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
		LOG.info("### Ending PoController.getSupplierDetails ###" +response);
		return response;
	}	

}
