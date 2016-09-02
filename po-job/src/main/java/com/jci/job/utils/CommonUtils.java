/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * Gets the partition key.
	 *
	 * @param erpName the erp name
	 * @return the partition key
	 */
	public static String getPartitionKey(String erpName){
		String partitionKey = erpName.toUpperCase()+"_"+"PO";	
		return partitionKey;
	}
	
	/**
	 * Gets the dest mapping.
	 *
	 * @param folderUrl the folder url
	 * @return the dest mapping
	 */
	public HashMap<String,HashMap<Integer,String>> getDestMapping(String folderUrl){
		HashMap<Integer, String> map=null;
		ObjectMapper mapper = new ObjectMapper(); 
		
		HashMap<String,HashMap<Integer,String>> mappingList = new HashMap<>();
		
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
		return mappingList ;
	}
	
	/**
	 * Gets the region mapping.
	 *
	 * @param folderUrl the folder url
	 * @return the region mapping
	 */
	public HashMap<String,HashMap<String,String>> getRegionMapping(String folderUrl){
		HashMap<String,String> map=null;
		ObjectMapper mapper = new ObjectMapper(); 
		
		HashMap<String,HashMap<String,String>> mappingList = new HashMap<>();
		
		File folder = new File(folderUrl);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {//reading only 1 file need to make changes
	      if (listOfFiles[i].isFile()) {
		    TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
			try {
				map = mapper.readValue(listOfFiles[i], typeRef);
				mappingList.put(FilenameUtils.removeExtension(listOfFiles[i].getName()), map);
			} catch (IOException e) {
				LOG.error("### Exception in   ####",e);
				
			} 
	      }
		}
		return mappingList ;
	}
	

	
}
