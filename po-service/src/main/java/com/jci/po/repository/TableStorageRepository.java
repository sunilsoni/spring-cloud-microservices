package com.jci.po.repository;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.request.BatchInsertRequest;
import com.jci.po.dto.response.BatchInsertResponse;
import com.jci.po.entity.PoEntity;
import com.microsoft.azure.storage.StorageException;


public interface TableStorageRepository {
	
	void insertPo(PoEntity poEntity) throws InvalidKeyException, URISyntaxException, StorageException;
	
	void insertPo(List<PoEntity> poEntityList) throws InvalidKeyException, URISyntaxException, StorageException;
	  
	List<PoEntity> selectPos() throws InvalidKeyException, URISyntaxException, StorageException;
	
	PoEntity getPoDetailsByPoNo(String partitionKey, String rowKey) throws InvalidKeyException, URISyntaxException, StorageException;
	
	List<PoEntity> getPoItemsByPoNo(String orderNumber) throws InvalidKeyException, URISyntaxException, StorageException;

	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException;
	BatchInsertResponse batchInsert(BatchInsertRequest request);
}
