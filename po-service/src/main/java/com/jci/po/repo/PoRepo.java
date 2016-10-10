/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jci.azure.DataHelper;
import com.jci.azure.ResultSet;
import com.jci.azure.ScrollingParam;
import com.jci.entity.PoEntity;


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
	 */
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) ;
	
	/**
	 * Gets the graph data.
	 *
	 * @return the graph data
	 */
	HashMap<String, ArrayList<Integer>> getGraphData()  ;
	
	/**
	 * Gets the error pos.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @return the error pos
	 */
	Map<String,List<HashMap<String, Object>>> getErrorPos(String partitionKey, List<String> poList) ; // NO_UCD (unused code)
	
	/**
	 * Gets the po details.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @return the po details
	 */
	List<PoEntity> getPoDetails(String partitionKey, List<String> poList)  ; // NO_UCD (unused code)
	
	/**
	 * Gets the po item detail.
	 *
	 * @param param the param
	 * @param request the request
	 * @return the po item detail
	 */
	ResultSet getPoItemDetail(ScrollingParam param,DataHelper request);
}
