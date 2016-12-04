/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.dto.BatchUpdateRes;
import com.jci.dto.SegmentedDetailReq;
import com.jci.dto.SegmentedDetailRes;
import com.jci.exception.BaseController;
import com.jci.po.dto.req.PoDetailsReq;
import com.jci.po.dto.req.PoItemDetailReq;
import com.jci.po.dto.res.PoItemDetailRes;
import com.jci.po.service.PoService;
import com.jci.utils.Constants;



/**
 * <p>
 * <strong>REST layer for managing PO,  graph and Error data.</strong>
 * <p>
 *
 * @author csonisk
 */      

@RestController  
@RefreshScope
public class PoController extends BaseController { // NO_UCD (unused code)
	
	/** The Constant LOG. */
 //private static final Logger LOG = LoggerFactory.getLogger(PoController.class);
	
	/** The po service. */
	@Autowired  
	private PoService  poService;
	
	/**
	 * Gets the segmented po details.
	 *
	 * @param request the request
	 * @return the segmented po details
	 */
	@RequestMapping(value = "/getSegmentedPoDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getSegmentedPoDetails(@RequestBody SegmentedDetailReq request)  throws Exception {
		SegmentedDetailRes response = new SegmentedDetailRes();
		request.setPartition(request.getErpName().toUpperCase());
		request.setTableName(Constants.TABLE_PO_DETAILS);
		response = poService.getSegmentedResultSet(request);
		return response;
	}
	
	/**
	 * Gets the error details.
	 *
	 * @param request the request
	 * @return the error details
	 */
	@RequestMapping(value = "/getSegmentedErrorDetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  SegmentedDetailRes getErrorDetails(@RequestBody SegmentedDetailReq request)  throws Exception {
		SegmentedDetailRes response = new SegmentedDetailRes();
		request.setPartition(request.getErpName().toUpperCase());
		request.setTableName(Constants.TABLE_PO_DETAILS);
		response = poService.getErrorResultSet(request);

		return response;
	}
	
	/**
	 * Process error pos.
	 *
	 * @param request the request
	 * @return the batch update res
	 */
	@RequestMapping(value = "/processErrorPos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  BatchUpdateRes processErrorPos(@RequestBody final PoDetailsReq request)  throws Exception {
		return poService.processErrorPos(request);
	}	
	
	/**
	 * Gets the po item detail.
	 *
	 * @param request the request
	 * @return the po item detail
	 */
	@RequestMapping(value = "/getPoItemDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  PoItemDetailRes getPoItemDetail(@RequestBody PoItemDetailReq request)  throws Exception {
		return   poService.getPoItemDetail(request);
	}
}
