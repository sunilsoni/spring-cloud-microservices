package com.jci.job.utils;

public class QueryBuilder {
	
	public static String partitionWhereCondition(String partitionKey){
		return String.format("(PartitionKey eq '%s')",partitionKey);//(PartitionKey eq 'SYMIX_PO_2016');
	}
	
	public static String geWhereCondition(String partitionKey,String startRowKey){
		return String.format("(PartitionKey eq '%s') and (RowKey ge '%s') ",partitionKey,startRowKey);
	}
	
	public static String leWhereCondition(String partitionKey,String endRowKey){
		return String.format("(PartitionKey eq '%s') and  (RowKey le '%s') ",partitionKey,endRowKey);
	}
	
}
