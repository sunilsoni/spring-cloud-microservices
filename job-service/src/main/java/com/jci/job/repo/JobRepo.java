/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.jci.job.api.req.BatchInsertReq;
import com.jci.job.api.req.BatchUpdateReq;
import com.jci.job.api.res.BatchUpdateRes;
import com.jci.job.entity.PoEntity;
import com.microsoft.azure.storage.StorageException;

/**
 * <p>
 * <strong> The Interface JobRepo..</strong>
 * <p>
 *
 * @author csonisk
 */
public interface JobRepo {
	
	/**
	 * Creates the table.
	 *
	 * @param tableName the table name
	 * @return true, if successful
	 * @throws InvalidKeyException the invalid key exception
	 * @throws StorageException the storage exception
	 * @throws URISyntaxException the URI syntax exception
	 */
	boolean createTable(String tableName) throws InvalidKeyException, StorageException, URISyntaxException ; // NO_UCD (unused code)
	
	/**
	 * Batch insert.
	 *
	 * @param request the request
	 * @return the list
	 */
	//List<String> batchInsert(BatchInsertReq request);
	
	List<Object> batchInsert(BatchInsertReq request);
	
	/**
	 * Gets the po details.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @return the po details
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	List<PoEntity> getPoDetails(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException; // NO_UCD (unused code)
	
	BatchUpdateRes batchUpdate(BatchUpdateReq request);
	
}
