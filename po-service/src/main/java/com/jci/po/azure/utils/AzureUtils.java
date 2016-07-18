package com.jci.po.azure.utils;

import java.util.Calendar;

public class AzureUtils {


	public static String getPartitionKey(String erpName){
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String partitionKey = year+"_"+"PO"+"_"+erpName;
		
		return partitionKey;
		
	}
}
