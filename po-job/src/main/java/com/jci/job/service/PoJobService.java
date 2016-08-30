package com.jci.job.service;

import java.util.List;

import com.jci.job.azure.BatchInsertReq;
import com.jci.job.azure.BatchInsertRes;

public interface PoJobService {
	List<String> batchInsert(BatchInsertReq request);
}
