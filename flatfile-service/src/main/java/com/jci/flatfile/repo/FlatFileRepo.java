/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.jci.flatfile.utils.BatchUpdateReq;
import com.jci.flatfile.utils.FlatFileRes;
import com.microsoft.azure.storage.StorageException;



public interface FlatFileRepo {
	
    FlatFileRes getPoFlatFileData(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException;
    FlatFileRes getFlatFileData(String partitionKey,String tableName) throws InvalidKeyException, URISyntaxException, StorageException ;
    void batchUpdate(BatchUpdateReq request);
    List<Object> getPoDetails(String partitionKey, List<String> poList,String tableName) throws InvalidKeyException, URISyntaxException, StorageException ;
    
}
