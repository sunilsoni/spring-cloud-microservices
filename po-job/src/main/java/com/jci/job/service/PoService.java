package com.jci.job.service;

import com.jci.job.azure.BatchInsertRequest;
import com.jci.job.azure.BatchInsertResponse;

public interface PoService {
	BatchInsertResponse batchInsert(BatchInsertRequest request);
}
