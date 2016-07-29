package com.jci.po.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jci.po.azure.data.DataHelper;
import com.jci.po.repository.TableStorageRepositoryImpl;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.Operators;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

public class QueryBuilder {
	
	private static final Logger LOG = LoggerFactory.getLogger(QueryBuilder.class);
	private static final String PARTITION_KEY = "PartitionKey";
	private static final String STATUS_KEY = "Status";
	private static final String TIMESTAMP = "Timestamp";
	//private static final String ROWKEY = "RowKey";
	
	
	public static String poQuery(DataHelper request){
		LOG.info("#### Starting QueryBuilder.poQuery ###" + request);
		
		 // Create filters to limit the data
	   String partitionFilter = TableQuery.generateFilterCondition(PARTITION_KEY, QueryComparisons.EQUAL, request.getPartitionValue());
	      
	   String timestampFilter = TableQuery.generateFilterCondition(TIMESTAMP, QueryComparisons.GREATER_THAN_OR_EQUAL,request.getTimestamp());
     //  String timestampFilter = TableQuery.generateFilterCondition(TIMESTAMP, QueryComparisons.LESS_THAN_OR_EQUAL,request.getTimestamp());
      
       String partitionAndTimestampFilter = TableQuery.combineFilters( partitionFilter, Operators.AND, timestampFilter);
       String statusFilter = TableQuery.generateFilterCondition(STATUS_KEY,QueryComparisons.EQUAL, request.getStatus());
       
		 // Combine all the filters
       String combinedFilter = TableQuery.combineFilters(partitionAndTimestampFilter, Operators.AND, statusFilter);
       LOG.info("#### Ending  QueryBuilder.poQuery ###" + combinedFilter);
		return combinedFilter;
	}
	
	public static String partitionWhereCondition(String partitionKey){
		return String.format("(PartitionKey eq '%s')",partitionKey);//(PartitionKey eq 'SYMIX_PO_2016');
	}
	
	public static String geWhereCondition(String partitionKey,String startRowKey){
		return String.format("(PartitionKey eq '%s') and (RowKey ge '%s') ",partitionKey,startRowKey);
	}
	
	public static String leWhereCondition(String partitionKey,String endRowKey){
		return String.format("(PartitionKey eq '%s') and  (RowKey le '%s') ",partitionKey,endRowKey);
	}
	
	
	public static String partitionWhereCondition(String partitionKey,String startRowKey,String endRowKey){
		 String whereCondition=null;
		 
		 if(StringUtils.isBlank(partitionKey) ){
			 return whereCondition;
		 }
		
		String startRowKey1 = startRowKey + ";" ;
		String  endRowKey1 = endRowKey + "=" ;
		 
		if(StringUtils.isBlank(startRowKey) && StringUtils.isBlank(endRowKey)){
			whereCondition = partitionWhereCondition(partitionKey);
		}else if(StringUtils.isBlank(endRowKey)){
			whereCondition = geWhereCondition(partitionKey,startRowKey1); 
		}else if(StringUtils.isBlank(startRowKey)){
			whereCondition = geWhereCondition(partitionKey,endRowKey1); 
		}else{
			whereCondition = String.format("(PartitionKey eq '%s') and (RowKey ge '%s') and (RowKey le '%s') ",partitionKey,startRowKey1,endRowKey1);
		}
		
		return whereCondition;
	}
	
	public static String errorDataQuery(String partitionKey){
		 String partitionFilter = TableQuery.generateFilterCondition(PARTITION_KEY, QueryComparisons.EQUAL, partitionKey);
	     String statusFilter = TableQuery.generateFilterCondition(STATUS_KEY,QueryComparisons.EQUAL, Constants.STATUS_ERROR);
	     return  TableQuery.combineFilters(partitionFilter, Operators.AND, statusFilter);
	}
	
}
