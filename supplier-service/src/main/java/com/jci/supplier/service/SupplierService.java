package com.jci.supplier.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.supplier.dto.SegmentedDetailReq;
import com.jci.supplier.dto.SegmentedDetailRes;
import com.microsoft.azure.storage.StorageException;

public interface SupplierService {
	SegmentedDetailRes getSupplierResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException;
}
