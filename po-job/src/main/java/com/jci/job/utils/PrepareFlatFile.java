package com.jci.job.utils;

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

import com.jci.job.azure.FlatFile;


public class PrepareFlatFile {

	private static final Logger LOG = LoggerFactory.getLogger(PrepareFlatFile.class);
	
	public  Map<String,List<String>>  prepareSupplierData(HashMap<Integer,String> mapping,Map<String,List<HashMap<String, Object>>>  poNumToItemsMap,FlatFile config){
		LOG.info("### Starting PrepareFlatFile.prepareSupplierData");
		
		Map<String,List<String>> fileNameToRowsMap = new HashMap<String,List<String>>();
		
		List<String> lines = null;
		
		for (Map.Entry<String,List<HashMap<String, Object>>> entry : poNumToItemsMap.entrySet()){
			//LOG.info(entry.getKey() + "/" + entry.getValue());
		    
			lines = new ArrayList<String>();
			List<HashMap<String, Object>> list =  entry.getValue();
			int size = list.size();
			
			for(int i=0;i<size;i++){
				lines.add(fixedLengthString((list.get(i)),mapping).toString());
			}
		    
		    String fileName = getFileName(entry.getKey(),config);
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
	
	public  String getFileName(String poNum,FlatFile ff) {
		
		StringBuilder name = new StringBuilder();
		
		name.append(poNum);
		name.append(".");
		name.append(ff.getSenderDuns());
		name.append("_");
		name.append(ff.getReceiverDuns());
		name.append("_");
		name.append(ff.getMessageType());
		name.append("_");
		name.append(ff.getVersion());
		name.append("_");
		name.append(ff.getSiteId());
		name.append("_");
		
		SimpleDateFormat isoFormat = new SimpleDateFormat(ff.getDateFormat());
		isoFormat.setTimeZone(TimeZone.getTimeZone(ff.getTimeZone()));

		String timestamp =isoFormat.format(new Date());
		
		name.append(timestamp);
		//name.append(".txt");  //Commented as we are using tempfile creation
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
