/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.po.dto.req.PoDetailsReq;
import com.jci.po.dto.req.PoItemDetailReq;
import com.jci.po.dto.req.SegmentedDetailReq;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.dto.res.PoItemDetailRes;
import com.jci.po.dto.res.SegmentedDetailRes;
import com.microsoft.azure.storage.StorageException;


/**
 * The Interface PoService.
 */
public interface PoService {
	
	/**
	 * Gets the segmented result set.
	 *
	 * @param request the request
	 * @return the segmented result set
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	SegmentedDetailRes getSegmentedResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * Gets the error result set.
	 *
	 * @param request the request
	 * @return the error result set
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	SegmentedDetailRes getErrorResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * Process error pos.
	 *
	 * @param request the request
	 * @return the batch update res
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	BatchUpdateRes processErrorPos(PoDetailsReq request)throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * Gets the po item detail.
	 *
	 * @param request the request
	 * @return the po item detail
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	PoItemDetailRes getPoItemDetail(PoItemDetailReq request)throws InvalidKeyException, URISyntaxException, StorageException;
}
