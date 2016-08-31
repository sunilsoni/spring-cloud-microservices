package com.jci.job.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jci.job.api.req.BatchUpdateReq;
import com.jci.job.api.res.BatchUpdateRes;
import com.jci.job.azure.BatchInsertReq;
import com.jci.job.entity.PoEntity;
import com.microsoft.azure.storage.StorageException;


public interface JobRepo {
	List<String> batchInsert(BatchInsertReq request);
	//Map<String,List<HashMap<String, Object>>> getPos(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException;
	List<PoEntity> getPoDetails(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException;
	BatchUpdateRes batchUpdate(BatchUpdateReq request) throws InvalidKeyException, URISyntaxException, StorageException;
	List<Map<String,List<HashMap<String, Object>>>> getFlatFileData(String partitionKey) throws InvalidKeyException, URISyntaxException, StorageException;
	List<HashMap<String, Object>> getFlatFileData(String partitionKey,String tableName) throws InvalidKeyException, URISyntaxException, StorageException;
}
