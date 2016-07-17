package com.jci.po.apis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	

	/*public static String fixedLengthString(String string, int length) {
	    return String.format("%1$"+length+ "s", string);
	}*/

	
}
