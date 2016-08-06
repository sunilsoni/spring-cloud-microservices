package com.jci.job.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.job.azure.BatchInsertReq;
import com.jci.job.azure.BatchInsertRes;
import com.jci.job.repo.JobRepo;

@Service
public class PoJobServiceImpl implements PoJobService{

	private static final Logger LOG = LoggerFactory.getLogger(PoJobServiceImpl.class);
	
	@Autowired
	private JobRepo repo;

	@Override
	public BatchInsertRes batchInsert(BatchInsertReq request) {
		BatchInsertRes response = repo.batchInsert(request);
		return response;
	}

}
