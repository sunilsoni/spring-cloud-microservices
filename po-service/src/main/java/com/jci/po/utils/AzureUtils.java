/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.utils;


/**
 * <p>
 * <strong> The Class AzureUtils.</strong>
 * <p>
 *
 * @author csonisk
 */
public class AzureUtils {
	
	/**
	 * Gets the partition key.
	 *
	 * @param erpName the erp name
	 * @return the partition key
	 */
	public static String getPoPartitionKey(String erpName){
		return "PO"+"_"+erpName.toUpperCase();	
	}
	
	public static String getPoItemPartitionKey(String erpName){
		return "PO_ITEM"+"_"+erpName.toUpperCase();	
	}
}
