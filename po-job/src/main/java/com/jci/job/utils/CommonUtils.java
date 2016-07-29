package com.jci.job.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;


public class CommonUtils {

	public static Date stringToDate(String dateStr){
		
		if(StringUtils.isBlank(dateStr) || "null".equals(dateStr)){
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date convertedCurrentDate=null;
		try {
			convertedCurrentDate = sdf.parse(dateStr);
			// String date=sdf.format(convertedCurrentDate );
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   return convertedCurrentDate;
	}
	
	public static String appendTab(Object value) {
		if(value==null || "".equals(value) || "null".equals(value)){
			return "\t";
		}else{
			return value+"\t";
		}
	    
	}
	
	
	public static String getGUID() throws Exception {

		String guid = UUID.randomUUID().toString();
		if(StringUtils.isBlank(guid)) {
			throw new Exception("GUID generation error: null");
		}
		
		// GUID width fixed at 36,left pad if less 
		String token = StringUtils.leftPad(guid, 36, "0");
		token = StringUtils.substring(guid,0,36);
		return token ;
	}
	
	public static String getPartitionKey(String erpName){
		//String partitionKey = erpName+"_"+"PO"+"_"+Calendar.getInstance().get(Calendar.YEAR);
		String partitionKey = erpName+"_"+"PO";	
		return partitionKey;
	}
}
