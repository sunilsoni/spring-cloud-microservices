/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
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
import com.jci.po.dto.req.BatchUpdateReq;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.entity.PoEntity;
import com.microsoft.azure.storage.StorageException;



/**
 * <p>
 * <strong> The interface PoRepo.</strong>
 * <p>
 *
 * @author csonisk
 */
public interface PoRepo {
	
	/**
	 * Gets the segmented result set.
	 *
	 * @param param the param
	 * @param request the request
	 * @return the segmented result set
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * Batch update.
	 *
	 * @param request the request
	 * @return the batch update res
	 */
	BatchUpdateRes batchUpdate(BatchUpdateReq request);
	
	/**
	 * Gets the graph data.
	 *
	 * @return the graph data
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	HashMap<String, ArrayList<Integer>> getGraphData() throws InvalidKeyException, URISyntaxException, StorageException ;
	
	/**
	 * Gets the error pos.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @return the error pos
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	Map<String,List<HashMap<String, Object>>> getErrorPos(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * Gets the po details.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @return the po details
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	List<PoEntity> getPoDetails(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException ;
	
	/**
	 * Gets the po item detail.
	 *
	 * @param param the param
	 * @param request the request
	 * @return the po item detail
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	ResultSet getPoItemDetail(ScrollingParam param,DataHelper request)throws InvalidKeyException, URISyntaxException, StorageException;
}
