package com.jci.po.repository;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.jci.po.azure.data.AzureRequest;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.response.ResultSet;
import com.jci.po.entity.PoEntity;
import com.microsoft.azure.storage.StorageException;


public interface TableStorageRepository {
	
	void insertPo(PoEntity poEntity) throws InvalidKeyException, URISyntaxException, StorageException;
	
	void insertPo(List<PoEntity> poEntityList) throws InvalidKeyException, URISyntaxException, StorageException;
	  
	List<PoEntity> selectPos() throws InvalidKeyException, URISyntaxException, StorageException;
	
	PoEntity getPoDetailsByPoNo(String partitionKey, String rowKey) throws InvalidKeyException, URISyntaxException, StorageException;
	
	List<PoEntity> getPoItemsByPoNo(String orderNumber) throws InvalidKeyException, URISyntaxException, StorageException;

	ResultSet getSegmentedResultSet(ScrollingParam param, AzureRequest request) throws InvalidKeyException, URISyntaxException, StorageException;

}
