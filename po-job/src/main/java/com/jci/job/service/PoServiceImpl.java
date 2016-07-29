package com.jci.job.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.job.azure.BatchInsertRequest;
import com.jci.job.azure.BatchInsertResponse;
import com.jci.job.repository.TableStorageRepository;

@Service
public class PoServiceImpl implements PoService{

	private static final Logger LOG = LoggerFactory.getLogger(PoServiceImpl.class);
	
	@Autowired
	private TableStorageRepository repo;

	@Override
	public BatchInsertResponse batchInsert(BatchInsertRequest request) {
		
		
		
		BatchInsertResponse response = repo.batchInsert(request);
		return response;
	}

}
