package com.jci.po.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
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
	
	
	/*public static String fixedLengthString(String string, int length) {
	    return String.format("%1$"+length+ "s", string);
	}*/
	
	public HashMap<String,HashMap<Integer,String>> getDestMapping(String folderUrl){
		HashMap<Integer, String> map=null;
		ObjectMapper mapper = new ObjectMapper(); 
		
		HashMap<String,HashMap<Integer,String>> mappingList = new HashMap<String,HashMap<Integer,String>>();
		
		File folder = new File(folderUrl);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {//reading only 1 file need to make changes
	      if (listOfFiles[i].isFile()) {
		    TypeReference<HashMap<Integer,String>> typeRef  = new TypeReference<HashMap<Integer,String>>() {};
			try {
				map = mapper.readValue(listOfFiles[i], typeRef);
				mappingList.put(FilenameUtils.removeExtension(listOfFiles[i].getName()), map);
			} catch (IOException e) {
				e.printStackTrace();
			} 
	      } else if (listOfFiles[i].isDirectory()) {
	        System.out.println("Directory " + listOfFiles[i].getName());
	      }
		}
		return mappingList ;
	}
	
	
}
