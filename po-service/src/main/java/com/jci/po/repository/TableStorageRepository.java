package com.jci.po.repository;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.request.BatchInsertRequest;
import com.jci.po.dto.response.BatchInsertResponse;
import com.microsoft.azure.storage.StorageException;


public interface TableStorageRepository {
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException;
	BatchInsertResponse batchInsert(BatchInsertRequest request);
}
