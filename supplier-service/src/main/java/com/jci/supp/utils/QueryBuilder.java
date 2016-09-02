package com.jci.supp.utils;

public class QueryBuilder {
	
/*	private static final String PARTITION_KEY = "PartitionKey";
	private static final String STATUS_KEY = "Status";
	private static final String TIMESTAMP = "Timestamp";*/
	//private static final String ROWKEY = "RowKey";
	
	public static String partitionWhereCondition(String partitionKey){
		return String.format("(PartitionKey eq '%s')",partitionKey);//(PartitionKey eq 'SYMIX_PO_2016');
	}
	
	
}
