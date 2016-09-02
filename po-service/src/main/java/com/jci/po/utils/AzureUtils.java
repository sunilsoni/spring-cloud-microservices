/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.utils;


/**
 * The Class AzureUtils.
 */
public class AzureUtils {
	
	/**
	 * Gets the partition key.
	 *
	 * @param erpName the erp name
	 * @return the partition key
	 */
	public static String getPartitionKey(String erpName){
		//String partitionKey = erpName+"_"+"PO"+"_"+Calendar.getInstance().get(Calendar.YEAR);
		String partitionKey = erpName.toUpperCase()+"_"+"PO";	
		return partitionKey;
	}
}
