package com.jci.po.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.jci.po.dto.request.PoDetailsRequest;
import com.jci.po.dto.request.SegmentedDetailRequest;
import com.jci.po.dto.response.PoDetailsResponse;
import com.jci.po.dto.response.SegmentedDetailResponse;
import com.jci.po.entity.PoEntity;
import com.microsoft.azure.storage.StorageException;

public interface PoService {

	 void addPo(PoEntity poEntity) throws InvalidKeyException, URISyntaxException, StorageException;

	PoDetailsResponse getPos(PoDetailsRequest request) throws InvalidKeyException, URISyntaxException, StorageException;

	int getPoSize() throws InvalidKeyException, URISyntaxException, StorageException;
	  
	String getLastPo() throws InvalidKeyException, URISyntaxException, StorageException ;
	  
	PoEntity getPoDetailsByPoNo(String partitionKey, String rowKey) throws InvalidKeyException, URISyntaxException, StorageException;
	  
	List<PoEntity> getPos(int size) throws InvalidKeyException, URISyntaxException, StorageException;

	SegmentedDetailResponse getSegmentedResultSet(SegmentedDetailRequest request) throws InvalidKeyException, URISyntaxException, StorageException;
	
	void insertDummyData() throws InvalidKeyException, URISyntaxException, StorageException;
}
