/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.utils;

import java.util.List;

import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.Operators;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;


/**
 * The Class QueryBuilder.
 */
public class QueryBuilder {
	
	/**
	 * Po query.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @return the string
	 */
	public static String poItemQuery(String partitionKey, List<String> poList){
		// Create filters to limit the data
	   String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	   String combinedFilter = null;
	   String rowKeyFilter = null;
	   
	   StringBuilder builder =  new StringBuilder();
	   for(int i=0;i<poList.size();i++){  
		 builder.append(" ( ");
		 rowKeyFilter = TableQuery.generateFilterCondition(Constants.ORDER_NUMBER, QueryComparisons.EQUAL, poList.get(i));//Wrong code correct this
	     combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter); 
	     builder.append(combinedFilter);
	     builder.append(" ) ");
	     
	     if(i!=(poList.size()-1)){
			   builder.append(" or ");
		  }
	   }
	   
	   rowKeyFilter = builder.toString();
	   combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter);
		return rowKeyFilter;
	}
	
	/**
	 * Po query.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @return the string
	 */
	public static String poQuery(String partitionKey, List<String> poList){
		// Create filters to limit the data
	   String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	   String combinedFilter = null;
	   String rowKeyFilter = null;
	   
	   StringBuilder builder =  new StringBuilder();
	   for(int i=0;i<poList.size();i++){  
		 builder.append(" ( ");
		 rowKeyFilter = TableQuery.generateFilterCondition(Constants.ROWKEY, QueryComparisons.EQUAL, poList.get(i));//Wrong code correct this
	     combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter); 
	     builder.append(combinedFilter);
	     builder.append(" ) ");
	     
	     if(i!=(poList.size()-1)){
			   builder.append(" or ");
		  }
	   }
	   
	   rowKeyFilter = builder.toString();
	   combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter);
		return rowKeyFilter;
	}
	
	/**
	 * Process pos query.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @return the string
	 */
	public static String processPosQuery(String partitionKey, List<String> poList){
		// Create filters to limit the data
	   String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	      
	   String combinedFilter = null;
	   String rowKeyFilter = null;
	   
	   StringBuilder builder =  new StringBuilder();
	   for(int i=0;i<poList.size();i++){  
		 builder.append(" ( ");
		 rowKeyFilter = TableQuery.generateFilterCondition(Constants.ROWKEY, QueryComparisons.EQUAL, poList.get(i).split("\\.")[0]);
	     combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter); 
	     builder.append(combinedFilter);
	     builder.append(" ) ");
	     
	     if(i!=(poList.size()-1)){
			   builder.append(" or ");
		  }
	   }
	   
	   rowKeyFilter = builder.toString();
	   combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter);
		return rowKeyFilter;
	}
	
	/**
	 * Ff query.
	 *
	 * @param partitionKey the partition key
	 * @return the string
	 */
	public static String ffQuery(String partitionKey){
		// Create filters to limit the data
	   String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	   
	   String rowKeyFilter = TableQuery.generateFilterCondition(Constants.STATUS_KEY, QueryComparisons.EQUAL, Constants.STATUS_IN_TRANSIT);
	   String   combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter); 
	 return combinedFilter;
	}
}
