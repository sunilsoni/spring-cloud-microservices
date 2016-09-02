/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.supp.utils;


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
		return erpName+"_"+"PO";	
	}
}
