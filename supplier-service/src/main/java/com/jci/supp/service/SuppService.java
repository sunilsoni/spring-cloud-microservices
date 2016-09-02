package com.jci.supp.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.supp.dto.SegmentedDetailReq;
import com.jci.supp.dto.SegmentedDetailRes;
import com.microsoft.azure.storage.StorageException;

public interface SuppService {
	SegmentedDetailRes getSupplierResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException;
}
