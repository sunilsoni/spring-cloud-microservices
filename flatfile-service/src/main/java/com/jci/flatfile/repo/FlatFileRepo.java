/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.repo;

import java.util.List;

import com.jci.flatfile.utils.BatchUpdateReq;
import com.jci.flatfile.utils.FlatFileRes;


/**
 * The Interface FlatFileRepo.
 */
public interface FlatFileRepo {
	
    /**
     * Gets the po flat file data.
     *
     * @param partitionKey the partition key
     * @param poList the po list
     * @return the po flat file data
     */
    FlatFileRes getPoFlatFileData(String partitionKey, List<String> poList) ;
    
    /**
     * Gets the flat file data.
     *
     * @param partitionKey the partition key
     * @param tableName the table name
     * @return the flat file data
     */
    FlatFileRes getFlatFileData(String partitionKey,String tableName) ;
    
    /**
     * Batch update.
     *
     * @param request the request
     */
    void batchUpdate(BatchUpdateReq request);
    
    /**
     * Gets the po details.
     *
     * @param partitionKey the partition key
     * @param poList the po list
     * @param tableName the table name
     * @return the po details
     */
    List<Object> getPoDetails(String partitionKey, List<String> poList,String tableName) ;
    
    FlatFileRes  getCombinedData(String partitionKey,String tableName, List<String> rowKeyList);
    
}
