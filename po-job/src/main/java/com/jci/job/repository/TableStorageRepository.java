package com.jci.job.repository;

import com.jci.job.azure.BatchInsertRequest;
import com.jci.job.azure.BatchInsertResponse;


public interface TableStorageRepository {
	BatchInsertResponse batchInsert(BatchInsertRequest request);
}
