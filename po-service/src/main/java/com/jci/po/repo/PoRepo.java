package com.jci.po.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;

import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.req.BatchInsertReq;
import com.jci.po.dto.res.BatchInsertResp;
import com.microsoft.azure.storage.StorageException;


public interface PoRepo {
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException;
	BatchInsertResp batchInsert(BatchInsertReq request);
	public HashMap<String, ArrayList<Integer>> getGraphData() throws InvalidKeyException, URISyntaxException, StorageException ;
}
