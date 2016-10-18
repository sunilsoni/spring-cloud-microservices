/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.repo;

import java.util.List;
import java.util.Map;

import com.jci.dto.BatchUpdateRes;
import com.jci.dto.GrDetails;
import com.jci.entity.PoEntity;
import com.jci.job.api.req.BatchInsertReq;
import com.jci.job.api.req.BatchUpdateReq;

/**
 * <p>
 * <strong> The Interface JobRepo..</strong>
 * <p>
 *
 * @author csonisk
 */
public interface JobRepo {
	
	boolean createTable(String tableName) ;  
	
	List<Object> batchInsert(BatchInsertReq request);
	
	List<PoEntity> getPoDetails(String partitionKey, List<String> poList) ; 
	
	BatchUpdateRes batchUpdate(BatchUpdateReq request);
	
	Map<String,Integer>  getGrQtyMap(List<GrDetails> grList);
	
}
