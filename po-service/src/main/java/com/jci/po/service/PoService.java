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
	
	SegmentedDetailRes getSegmentedResultSet(SegmentedDetailReq request) ;
	
	SegmentedDetailRes getErrorResultSet(SegmentedDetailReq request) ;
	
	BatchUpdateRes processErrorPos(PoDetailsReq request);
	
	PoItemDetailRes getPoItemDetail(PoItemDetailReq request);
}
