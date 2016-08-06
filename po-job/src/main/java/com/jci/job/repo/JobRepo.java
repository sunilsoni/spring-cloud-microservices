package com.jci.job.repo;

import com.jci.job.azure.BatchInsertReq;
import com.jci.job.azure.BatchInsertRes;


public interface JobRepo {
	BatchInsertRes batchInsert(BatchInsertReq request);
}
