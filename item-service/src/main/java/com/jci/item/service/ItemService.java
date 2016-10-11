/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.item.service;

import com.jci.dto.SegmentedDetailReq;
import com.jci.dto.SegmentedDetailRes;


/**
 * <p>
 * <strong>The Interface ItemService.</strong>
 * <p>
 *
 * @author csonisk
 */
public interface ItemService {
	
	SegmentedDetailRes getItemResultSet(SegmentedDetailReq request);
}
