/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.item.utils;

/**
 * <p>
 * <strong>The Class QueryBuilder for Azure Tables.</strong>
 * <p>
 *
 * @author csonisk
 */
public class QueryBuilder {
	
	/**
	 * Partition where condition.
	 *
	 * @param partitionKey the partition key
	 * @return the string
	 */
	public static String partitionWhereCondition(String partitionKey){
		return String.format("(PartitionKey eq '%s')",partitionKey);
	}
}
