package com.jci.item.utils;

public class QueryBuilder {
	
	/*private static final Logger LOG = LoggerFactory.getLogger(QueryBuilder.class);
	private static final String PARTITION_KEY = "PartitionKey";
	private static final String STATUS_KEY = "Status";
	private static final String TIMESTAMP = "Timestamp";*/
	//private static final String ROWKEY = "RowKey";
	
	public static String partitionWhereCondition(String partitionKey){
		return String.format("(PartitionKey eq '%s')",partitionKey);//(PartitionKey eq 'SYMIX_PO_2016');
	}
	
}
