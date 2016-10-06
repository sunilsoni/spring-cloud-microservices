/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * The Class CommonUtils.
 */
public class CommonUtils {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CommonUtils.class);
	
	/**
	 * String to date.
	 *
	 * @param dateStr the date str
	 * @return the date
	 */
	static Date stringToDate(String dateStr){
		if(StringUtils.isBlank(dateStr) || "null".equals(dateStr)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date convertedCurrentDate=null;
		try {
			convertedCurrentDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			LOG.error("### Exception in   ####",e);
			
		}
	   return convertedCurrentDate;
	}
	
	
	/**
	 * Gets the dest mapping.
	 *
	 * @param folderUrl the folder url
	 * @return the dest mapping
	 */
	public TreeMap<String,HashMap<Integer,String>> getDestMapping(String folderUrl){
		HashMap<Integer, String> map=null;
		ObjectMapper mapper = new ObjectMapper(); 
		
		TreeMap<String,HashMap<Integer,String>> mappingList = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		File folder = new File(folderUrl);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {//reading only 1 file need to make changes
	      if (listOfFiles[i].isFile()) {
		    TypeReference<HashMap<Integer,String>> typeRef  = new TypeReference<HashMap<Integer,String>>() {};
			try {
				map = mapper.readValue(listOfFiles[i], typeRef);
				mappingList.put(FilenameUtils.removeExtension(listOfFiles[i].getName()), map);
			} catch (IOException e) {
				LOG.error("### Exception in   ####",e);
				
			} 
	      } 
		}
		LOG.error("mappingList--->"+mappingList);
		return mappingList ;
	}
	
	public TreeMap<String,HashMap<Integer,String>> getGitJsonMapping(String destName, String jasonValue){
		HashMap<Integer, String> map=null;
		ObjectMapper mapper = new ObjectMapper(); 
		TreeMap<String,HashMap<Integer,String>> mappingList = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	    TypeReference<HashMap<Integer,String>> typeRef  = new TypeReference<HashMap<Integer,String>>() {};
		try {
			map = mapper.readValue(jasonValue, typeRef);
			mappingList.put(destName, map);
		} catch (IOException e) {
			LOG.error("### Exception in   ####",e);
		} 
		return mappingList ;
	}
	
	public static String removeTemp(String str){
		int index=0;
	    if(str.contains("\\")){
	    	index = str.lastIndexOf("\\");
	    }else{
	    	index = str.lastIndexOf("/");
	    }
	    
	    str =  str.substring(index+1);
	    
		return str;
	}
	
	public static File createTempDirectory() throws IOException{
		    final String baseTempPath = System.getProperty("java.io.tmpdir");

		    File tempDir = new File(baseTempPath + File.separator + "SCTemp" + System.nanoTime());
		    if (tempDir.exists() == false) {
		        tempDir.mkdir();
		    }

		    return tempDir;
	}
	
	public static void deleteTempFiles(List<File> tempFiles){
		  for (File temp : tempFiles) {
	            try {
					FileDeleteStrategy.FORCE.delete(temp);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }  
	}
	
	public  Map<String,List<String>>  prepareSuppData(String poNum, HashMap<Integer,String> mapping,List<HashMap<String, Object>> rowList,String plantName,String msgType){
			Map<String,List<String>> fileNameToRowsMap = new HashMap<>();
			List<String> lines = new ArrayList<>();
			lines.add(getHeaderString(mapping));
			int size = rowList.size();
			for(int i=0;i<size;i++){
				lines.add(fixedLengthString((rowList.get(i)),mapping).toString());
			}
		    String fileName = getFileName(poNum,plantName,msgType);
		    fileNameToRowsMap.put(fileName, lines);
		return fileNameToRowsMap;
	}
	
	private  String getHeaderString(HashMap<Integer,String> mapping){
		StringBuilder line = new StringBuilder();
		for(int i=0;i<mapping.size();i++){
		 	String str = StringUtils.capitalize(mapping.get(i));
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
			String[] upperStr = str.split("(?<=[a-z])(?=[A-Z])");
			String joined = "#" + String.join(" ", upperStr);
			line.append(joined + "\t");
		 }
		
		return line.toString();
	}
	
	
	private  StringBuilder fixedLengthString(HashMap<String, Object> po,HashMap<Integer,String> mapping){
		StringBuilder line = new StringBuilder();
		int size = mapping.size();
		for(int i=0;i<size;i++){
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
	
	/**
	 * Checks if is blank.
	 *
	 * @param val the val
	 * @return true, if is blank
	 */
	private  static boolean isBlank(String val){
		if("null".equals(val)){
			return true;
		}
		
		if(StringUtils.isBlank(val)){
			return true;
		}
		return false;
	}
	
	private   String getFileName(String poNum,String siteId,String msgType) {
		StringBuilder name = new StringBuilder();
		
		if(!StringUtils.isBlank(poNum)){
		    name.append(poNum);
	        name.append(".");
		}
		
		name.append(Constants.SENDER_DUNS);
		name.append("_");
		name.append(Constants.RECEIVER_DUNS);
		name.append("_");
		name.append(msgType);
		name.append("_");
		name.append(Constants.VERSION);
		name.append("_");
		name.append(siteId);
		name.append("_");
		
		SimpleDateFormat isoFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		isoFormat.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));
		String timestamp =isoFormat.format(new Date());
		
		if(Constants.MESSAGE_TYPE_SUPP.equals(msgType) || Constants.MESSAGE_TYPE_ITEM.equals(msgType)){
			 isoFormat = new SimpleDateFormat(Constants.DATE_FORMAT_SUPP); 
			 name.append(timestamp);
			 name.append("0000000");
		}else{
			isoFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
			name.append(timestamp);
		}
		if(!StringUtils.isBlank(poNum)){
		    name.append(poNum);
		}
		name.append(".txt");  
		return name.toString();
	}
	/**
	 * Append tab.
	 *
	 * @param value the value
	 * @return the string
	 */
	private  static String appendTab(Object value) {
		if(value==null || "".equals(value) || "null".equals(value)){
			return "\t";
		}else{
			return value+"\t";
		}
	}
	
	/**
	 * Process file.
	 *
	 * @param toFile the to file
	 * @param url the url
	 * @return true, if successful
	 */
	public static boolean processFile(File toFile,String url) {
		boolean isSuccess=false;
		 InputStream input =null; 
		 try{
			 LOG.info(" getAbsolutePath--->"+ toFile.getAbsolutePath());
			 String mimeType= URLConnection.guessContentTypeFromName(toFile.getName());
			 RestTemplate template = new RestTemplate();

			 MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
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
			 /*if("success".equalsIgnoreCase(result)){  Sunil: Fix this issue
				 isSuccess=true;
			 }*/
			LOG.info(" result--->"+ result);
			 isSuccess=true;//Sunil: Fix this issue
		}catch(Exception e) {
			LOG.error("### Exception in   ####",e);
			
		}finally{
			try {
				input.close();
				FileUtils.forceDelete(toFile);
			} catch (IOException e1) {
				LOG.error("### Exception in   ####",e1);
				e1.printStackTrace();
			}
			
		}
		return isSuccess ;
	}
	
}
