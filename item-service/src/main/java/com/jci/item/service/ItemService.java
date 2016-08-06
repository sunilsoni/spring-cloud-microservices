package com.jci.item.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.item.dto.SegmentedDetailReq;
import com.jci.item.dto.SegmentedDetailRes;
import com.microsoft.azure.storage.StorageException;

public interface ItemService {
	SegmentedDetailRes getItemResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException;
}
