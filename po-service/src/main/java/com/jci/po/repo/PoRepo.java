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
	
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) ;
	
	HashMap<String, ArrayList<Integer>> getGraphData()  ;
	
	Map<String,List<HashMap<String, Object>>> getErrorPos(String partitionKey, List<String> poList) ; // NO_UCD (unused code)
	
	List<PoEntity> getPoDetails(String partitionKey, List<String> poList)  ; // NO_UCD (unused code)
	
	ResultSet getPoItemDetail(ScrollingParam param,DataHelper request);
}
