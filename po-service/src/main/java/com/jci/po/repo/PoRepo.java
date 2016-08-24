package com.jci.po.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.req.BatchInsertReq;
import com.jci.po.dto.req.BatchUpdateReq;
import com.jci.po.dto.req.PoItemDetailReq;
import com.jci.po.dto.res.BatchInsertResp;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.entity.PoEntity;
import com.jci.po.entity.PoItemsEntity;
import com.microsoft.azure.storage.StorageException;


public interface PoRepo {
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException;
	
	BatchInsertResp batchInsert(BatchInsertReq request);
	BatchUpdateRes batchUpdate(BatchUpdateReq request);
	HashMap<String, ArrayList<Integer>> getGraphData() throws InvalidKeyException, URISyntaxException, StorageException ;
	
	Map<String,List<HashMap<String, Object>>> getErrorPos(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException;
	List<PoEntity> getPoDetails(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException ;
	
	ResultSet getPoItemDetail(ScrollingParam param,DataHelper request)throws InvalidKeyException, URISyntaxException, StorageException;
}
