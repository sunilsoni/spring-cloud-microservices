/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.supp.utils;


/**
 * <p>
 * <strong> The Class AzureUtils.</strong>
 * <p>
 *
 * @author csonisk
 */
public class AzureUtils {
	
	public static String getPartitionKey(String erpName){
		return "SUPPLIER"+"_"+erpName.toUpperCase();	
	}
}
