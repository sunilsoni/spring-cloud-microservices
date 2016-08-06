package com.jci.job.service;

import com.jci.job.azure.BatchInsertReq;
import com.jci.job.azure.BatchInsertRes;

public interface PoJobService {
	BatchInsertRes batchInsert(BatchInsertReq request);
}
