package com.jci.po.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.po.dto.req.PoDetailsReq;
import com.jci.po.dto.req.SegmentedDetailReq;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.dto.res.SegmentedDetailRes;
import com.microsoft.azure.storage.StorageException;

public interface PoService {

	String getLastPo() throws InvalidKeyException, URISyntaxException, StorageException ;
	SegmentedDetailRes getSegmentedResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException;
	SegmentedDetailRes getErrorResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException;
	
	
	BatchUpdateRes processErrorPos(PoDetailsReq request)throws InvalidKeyException, URISyntaxException, StorageException;
	
	//Need to delete this 
	void insertDummyData() throws InvalidKeyException, URISyntaxException, StorageException;
}
