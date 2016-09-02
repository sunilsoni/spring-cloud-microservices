package com.jci.po.utils;

import java.util.List;

import com.jci.po.azure.data.DataHelper;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.Operators;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

public class QueryBuilder {
	
	//private static final Logger LOG = LoggerFactory.getLogger(QueryBuilder.class);

	public static String partitionWhereCondition(String partitionKey){
		return String.format("(PartitionKey eq '%s')",partitionKey);//(PartitionKey eq 'SYMIX_PO_2016');
	} 
	 
	
	public static String errorDataQuery(String partitionKey){
		 String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	     String statusFilter = TableQuery.generateFilterCondition(Constants.STATUS_KEY,QueryComparisons.EQUAL, Constants.STATUS_ERROR);
	     return  TableQuery.combineFilters(partitionFilter, Operators.AND, statusFilter);
	}
	
	//(PartitionKey  eq 'SAP_PO' or PartitionKey eq 'SYMIX_PO') and (RowKey eq '4713010' or  RowKey eq '3714011')
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
	
	public static String processPosQuery(String partitionKey, List<String> poList){
		
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
	
	public static String poItemDetailQuery(DataHelper request){
	   String partitionFilter = TableQuery.generateFilterCondition(Constants.PARTITION_KEY, QueryComparisons.EQUAL, request.getPartitionValue());
	   String orderNo = TableQuery.generateFilterCondition(Constants.ORDER_NUMBER,QueryComparisons.EQUAL,request.getPoNum());
	   String combinedFilter = TableQuery.combineFilters(partitionFilter, Operators.AND, orderNo); 
		return combinedFilter;
	}
	
}
