package com.jci.supp.utils;

public class AzureUtils {
	 
	public static String getPartitionKey(String erpName){
		//String partitionKey = erpName+"_"+"PO"+"_"+Calendar.getInstance().get(Calendar.YEAR);
		String partitionKey = erpName+"_"+"PO";	
		return partitionKey;
	}

}
