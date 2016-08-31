package com.jci.job.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
	
	
	private  StringBuilder fixedLengthString(HashMap<String, Object> po,HashMap<Integer,String> mapping){
		
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
	private  static boolean isBlank(String val){
		if("null".equals(val)){
			return true;
		}
		
		if(StringUtils.isBlank(val)){
			return true;
		}
		return false;
	}
	
	private   String getFileName(String poNum,FlatFile ff) {
		
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
	
	private  static String appendTab(Object value) {
		if(value==null || "".equals(value) || "null".equals(value)){
			return "\t";
		}else{
			return value+"\t";
		}
	    
	}
	
	public static boolean processFile(File toFile,String url) {
		boolean isSuccess=false;
		LOG.info(" url--->"+ url);
		 InputStream input =null; 
		 try{
			 LOG.info(" getAbsolutePath--->"+ toFile.getAbsolutePath());
			 
			 String mimeType= URLConnection.guessContentTypeFromName(toFile.getName());
			 RestTemplate template = new RestTemplate();

			 MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<String, Object>();
			 requestMap.add("name", toFile.getName());
			 requestMap.add("filename", toFile.getName());
			 requestMap.set("Content-Type",mimeType);
			 requestMap.set("Content-Length",(int)toFile.length());			 
			 
			 input = new FileInputStream(toFile);
			 ByteArrayResource contentsAsResource = new ByteArrayResource(IOUtils.toByteArray(input)){
			             @Override
			             public String getFilename(){
			                 return toFile.getName();
			             }
			 };
			// requestMap.add("file",  entry.getValue());
			 requestMap.add("file", contentsAsResource);
			// map.add("file", res.getLines());
			 
			// ResponseEntity<String> result =  suppClient.sendFlatFile(requestMap);
			 String result = template.postForObject((url+"?filename="+toFile.getName()), requestMap, String.class);
			LOG.info(" result--->"+ result);
			 isSuccess=true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				input.close();
				//FileUtils.forceDelete(toFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		return isSuccess ;
	}
	
	
	
	
	
	
	

}
