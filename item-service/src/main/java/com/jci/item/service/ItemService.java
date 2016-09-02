/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.item.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.item.dto.SegmentedDetailReq;
import com.jci.item.dto.SegmentedDetailRes;
import com.microsoft.azure.storage.StorageException;

/**
 * The Interface ItemService.
 */
public interface ItemService {
	
	/**
	 * Gets the item result set.
	 *
	 * @param request the request
	 * @return the item result set
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	SegmentedDetailRes getItemResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException;
}
