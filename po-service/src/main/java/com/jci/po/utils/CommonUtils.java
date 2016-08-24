package com.jci.po.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


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
	
	
	/*public static String fixedLengthString(String string, int length) {
	    return String.format("%1$"+length+ "s", string);
	}*/
	
	public static HashMap<Integer,String> getDestMapping(){

		ObjectMapper mapper = new ObjectMapper(); 
	    File from = new File("C:/Apigee/micro-services/Work/supplier-collaboration-config-dev/e2open.json"); 
	    System.out.println("from " + from.getName()); 
	    TypeReference<HashMap<Integer,String>> typeRef  = new TypeReference<HashMap<Integer,String>>() {};
	    HashMap<Integer, String> map=null;
		try {
			map = mapper.readValue(from, typeRef);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	   
		return map ;
	}
	

}
