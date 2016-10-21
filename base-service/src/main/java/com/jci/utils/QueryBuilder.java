/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jci.azure.DataHelper;
import com.jci.dto.GrDetails;
import com.microsoft.azure.storage.table.TableEntity;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.Operators;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;


/**
 * <p>
 * <strong>The Class QueryBuilder for Azure Tables.</strong>
 * <p>
 *
 * @author csonisk
 */
public class QueryBuilder {
	
	/**
	 * Po item query.
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
	public static String poQuery(String partitionKey, List<String> rowKeyList){
		// Create filters to limit the data
	   String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	   String combinedFilter = null;
	   String rowKeyFilter = null;
	   
	   StringBuilder builder =  new StringBuilder();
	   for(int i=0;i<rowKeyList.size();i++){  
		 builder.append(" ( ");
		 rowKeyFilter = TableQuery.generateFilterCondition(Constants.ROWKEY, QueryComparisons.EQUAL, rowKeyList.get(i));//Wrong code correct this
	     combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter); 
	     builder.append(combinedFilter);
	     builder.append(" ) ");
	     
	     if(i!=(rowKeyList.size()-1)){
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
		 rowKeyFilter = TableQuery.generateFilterCondition(Constants.ROWKEY, QueryComparisons.EQUAL, poList.get(i)); 
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
	 * Partition where condition.
	 *
	 * @param partitionKey the partition key
	 * @return the string
	 */
	public static String partitionWhereCondition(String partitionKey){
		return String.format("(PartitionKey eq '%s')",partitionKey);
	} 
	 
	
	/**
	 * Error data query.
	 *
	 * @param partitionKey the partition key
	 * @return the string
	 */
	public static String errorDataQuery(String partitionKey){
		 String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	     String statusFilter = TableQuery.generateFilterCondition(Constants.STATUS_KEY,QueryComparisons.EQUAL, Constants.STATUS_ERROR);
	     return  TableQuery.combineFilters(partitionFilter, Operators.AND, statusFilter);
	}
	
	/**
	 * Gets the error pos query.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @return the error pos query
	 */
	public static String getErrorPosQuery(String partitionKey, List<String> poList){
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
	 * Graph query.
	 *
	 * @param partitionKey the partition key
	 * @param allErps the all erps
	 * @return the string
	 */
	public static String graphQuery(String partitionKey, String allErps){
		String[] rowKey = allErps.split(",");
		
		// Create filters to limit the data
	   String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	      
	   String combinedFilter = null;
	   String rowKeyFilter = null;
	   
	   StringBuilder builder =  new StringBuilder();
	   for(int i=0;i<rowKey.length;i++){  
		 builder.append(" ( ");
		 rowKeyFilter = TableQuery.generateFilterCondition(Constants.ROWKEY, QueryComparisons.EQUAL, rowKey[i]);
	     combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter); 
	     builder.append(combinedFilter);
	     builder.append(" ) ");
	     
	     if(i!=(rowKey.length-1)){
			   builder.append(" or ");
		  }
	   }
	   
	   rowKeyFilter = builder.toString();
	   combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter);
		return rowKeyFilter;
	}
	
	/**
	 * Error query.
	 *
	 * @param partitionKey the partition key
	 * @param allErps the all erps
	 * @return the string
	 */
	public static String errorQuery(String partitionKey, String allErps){
		String[] rowKey = allErps.split(",");
		
		// Create filters to limit the data
	   String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	   String statusFilter = TableQuery.generateFilterCondition(Constants.STATUS_KEY,QueryComparisons.EQUAL, Constants.STATUS_ERROR);
	   
	  String combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, statusFilter); 
	   
	   String rowKeyFilter = null;
	   
	   StringBuilder builder =  new StringBuilder();
	   for(int i=0;i<rowKey.length;i++){  
		 builder.append(" ( ");
		 rowKeyFilter = TableQuery.generateFilterCondition(Constants.ROWKEY, QueryComparisons.EQUAL, rowKey[i]);
	     combinedFilter = TableQuery.combineFilters(combinedFilter, Operators.AND, rowKeyFilter); 
	     builder.append(combinedFilter);
	     builder.append(" ) ");
	     
	     if(i!=(rowKey.length-1)){
			   builder.append(" or ");
		  }
	   }
	   
	   rowKeyFilter = builder.toString();
	   combinedFilter = TableQuery.combineFilters(combinedFilter, Operators.AND, rowKeyFilter);
		return rowKeyFilter;
	}
	
	/**
	 * Po item detail query.
	 *
	 * @param request the request
	 * @return the string
	 */
	public static String poItemDetailQuery(DataHelper request){
	   String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, request.getPartitionValue());
	   String orderNo = TableQuery.generateFilterCondition(Constants.ORDER_NUMBER,QueryComparisons.EQUAL,request.getPoNum());
	   String combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, orderNo); 
		return combinedFilter;
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
	
	public static String getGrQtyQuery(List<GrDetails> grList){
	      
	   String combinedFilter = null;
	   String rowKeyFilter = null;
	   
	   StringBuilder builder =  new StringBuilder();
	   for(int i=0;i<grList.size();i++){  
		   GrDetails obj =    grList.get(i);
		   
		 String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, obj.getErp().toUpperCase());
		 builder.append(" ( ");
		 rowKeyFilter = TableQuery.generateFilterCondition(Constants.ROWKEY, QueryComparisons.EQUAL, obj.getReceiptID()); 
	     combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter); 
	     builder.append(combinedFilter);
	     builder.append(" ) ");
	     
	     if(i!=(grList.size()-1)){
			   builder.append(" or ");
		  }
	   }
		return builder.toString();
	}
	
	
	public static String getCombinedDataQuery(Map<String, String> rowKeyToPkMap){
	      
	   String combinedFilter = null;
	   String rowKeyFilter = null;
	   int size = rowKeyToPkMap==null ? 0 : rowKeyToPkMap.size();
	   
	   StringBuilder builder =  new StringBuilder();
	   int count=0;
	   
	   Iterator<Map.Entry<String, String>> iter = rowKeyToPkMap.entrySet().iterator();
		while (iter.hasNext()) {
			 count=count+1;
			 Map.Entry<String, String> entry = iter.next();
			 
			 String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, entry.getValue());
			 builder.append(" ( ");
			 rowKeyFilter = TableQuery.generateFilterCondition(Constants.ROWKEY, QueryComparisons.EQUAL, entry.getKey()); 
		     combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, rowKeyFilter); 
		     builder.append(combinedFilter);
		     builder.append(" ) ");
		     
		     if(count!=size){
				   builder.append(" or ");
			  }
		     iter.remove();
		}
	   
		return builder.toString();
	}
}
