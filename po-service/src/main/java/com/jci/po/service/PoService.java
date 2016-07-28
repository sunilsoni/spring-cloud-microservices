package com.jci.po.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.po.dto.request.SegmentedDetailRequest;
import com.jci.po.dto.response.SegmentedDetailResponse;
import com.microsoft.azure.storage.StorageException;

public interface PoService {

	String getLastPo() throws InvalidKeyException, URISyntaxException, StorageException ;
	SegmentedDetailResponse getSegmentedResultSet(SegmentedDetailRequest request) throws InvalidKeyException, URISyntaxException, StorageException;
	void insertDummyData() throws InvalidKeyException, URISyntaxException, StorageException;
}
