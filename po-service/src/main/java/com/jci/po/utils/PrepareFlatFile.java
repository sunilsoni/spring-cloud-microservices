package com.jci.po.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrepareFlatFile {
	
	private static final Logger LOG = LoggerFactory.getLogger(PrepareFlatFile.class);
	
	public  Map<String,List<String>>  prepareSupplierData(HashMap<Integer,String> mapping,Map<String,List<HashMap<String, Object>>>  poNumToItemsMap){
		LOG.info("### Starting PrepareFlatFile.prepareSupplierData");
		
		Map<String,List<String>> fileNameToRowsMap = new HashMap<String,List<String>>();
		
		List<String> lines = null;
		
		for (Map.Entry<String,List<HashMap<String, Object>>> entry : poNumToItemsMap.entrySet()){
			LOG.info(entry.getKey() + "/" + entry.getValue());
		    
			lines = new ArrayList<String>();
			List<HashMap<String, Object>> list =  entry.getValue();
			int size = list.size();
			
			for(int i=0;i<size;i++){
				lines.add(fixedLengthString((list.get(i)),mapping).toString());
			}
		    
		    String fileName = getFileName(entry.getKey());
		    fileNameToRowsMap.put(fileName, lines);
		}
		
		LOG.info("### Ending PrepareFlatFile.prepareSupplierData");
		return fileNameToRowsMap;
	}
	
	
	public StringBuilder fixedLengthString(HashMap<String, Object> po,HashMap<Integer,String> mapping){
		
		StringBuilder line = new StringBuilder();
		int size = mapping.size();
		
		for(int i=0;i<mapping.size();i++){
			String azureCoumnName = mapping.get(i);
			if((size-1)==i){
				if(isBlank(String.valueOf(po.get(azureCoumnName)))){
					line.append("");
				}else{
					line.append((po.get(azureCoumnName)));
				}	
			}else{
				if(isBlank(String.valueOf(po.get(azureCoumnName)))){
					line.append(appendTab(""));
				}else{
					line.append(appendTab(po.get(azureCoumnName)));
				}	
			}
			
		}
		return line;
		
	}
	public static boolean isBlank(String val){
		if("null".equals(val)){
			return true;
		}
		
		if(StringUtils.isBlank(val)){
			return true;
		}
		return false;
	}
	
	public static String getFileName(String poNum) {
		
		StringBuilder name = new StringBuilder();
		
		name.append(poNum);
		name.append(".");
		name.append(Constants.FILE_SENDER_DUNS);
		name.append("_");
		name.append(Constants.FILE_RECEIVER_DUNS);
		name.append("_");
		name.append(Constants.FILE_MESSAGE_TYPE);
		name.append("_");
		name.append(Constants.FILE_VERSION);
		name.append("_");
		name.append(Constants.FILE_SITEID);
		name.append("_");
		
		SimpleDateFormat isoFormat = new SimpleDateFormat(Constants.FILE_DATE_FORMAT);
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		String timestamp = isoFormat.format(new Date());
		
		name.append(timestamp);
		name.append(".txt");
		return name.toString();
		
	}
	
	public static String appendTab(Object value) {
		if(value==null || "".equals(value) || "null".equals(value)){
			return "\t";
		}else{
			return value+"\t";
		}
	    
	}

}
