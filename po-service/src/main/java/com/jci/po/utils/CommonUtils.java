/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.po.service.PoServiceImpl;


/**
 * <p>
 * <strong> The Class CommonUtils.</strong>
 * <p>
 *
 * @author csonisk
 */
public class CommonUtils {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PoServiceImpl.class);
	
	/**
	 * Gets the partition key.
	 *
	 * @param erpName the erp name
	 * @return the partition key
	 */
	public static String getPartitionKey(String erpName){
		return erpName.toUpperCase()+"_"+"PO";
	}
	
	/**
	 * Gets the destination mapping.
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
}
