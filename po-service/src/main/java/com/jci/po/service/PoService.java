/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.service;

import com.jci.dto.BatchUpdateRes;
import com.jci.dto.SegmentedDetailReq;
import com.jci.dto.SegmentedDetailRes;
import com.jci.po.dto.req.PoDetailsReq;
import com.jci.po.dto.req.PoItemDetailReq;
import com.jci.po.dto.res.PoItemDetailRes;



/**
 * <p>
 * <strong> The interface PoService.</strong>
 * <p>
 *
 * @author csonisk
 */
public interface PoService {
	
	/**
	 * Gets the segmented result set.
	 *
	 * @param request the request
	 * @return the segmented result set
	 */
	SegmentedDetailRes getSegmentedResultSet(SegmentedDetailReq request) ;
	
	/**
	 * Gets the error result set.
	 *
	 * @param request the request
	 * @return the error result set
	 */
	SegmentedDetailRes getErrorResultSet(SegmentedDetailReq request) ;
	
	/**
	 * Process error pos.
	 *
	 * @param request the request
	 * @return the batch update res
	 */
	BatchUpdateRes processErrorPos(PoDetailsReq request);
	
	/**
	 * Gets the po item detail.
	 *
	 * @param request the request
	 * @return the po item detail
	 */
	PoItemDetailRes getPoItemDetail(PoItemDetailReq request);
}
