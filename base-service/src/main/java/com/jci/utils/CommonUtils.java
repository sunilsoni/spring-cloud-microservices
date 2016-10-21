/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.entity.GrEntity;
import com.jci.entity.ItemEntity;
import com.jci.entity.MiscDataEntity;
import com.jci.entity.PoEntity;
import com.jci.entity.SuppEntity;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableQuery;
/**
 * <p>
 * <strong>Define all  Common utility methods here .</strong>
 * <p>
 *
 * @author csonisk
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
	public static Date stringToDate(String dateStr){ // NO_UCD (unused code)
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
	public TreeMap<String,HashMap<Integer,String>> getDestMapping(String folderUrl){ // NO_UCD (unused code)
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

	
	/**
	 * Gets the git json mapping.
	 *
	 * @param destName the dest name
	 * @param jasonValue the jason value
	 * @return the git json mapping
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public TreeMap<String,HashMap<Integer,String>> getGitJsonMapping(String destName, String jasonValue) throws JsonParseException, JsonMappingException, IOException{
		HashMap<Integer, String> map=null;
		ObjectMapper mapper = new ObjectMapper(); 
		TreeMap<String,HashMap<Integer,String>> mappingList = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	    TypeReference<HashMap<Integer,String>> typeRef  = new TypeReference<HashMap<Integer,String>>() {};
		map = mapper.readValue(jasonValue, typeRef);
		mappingList.put(destName, map);
		return mappingList ;
	}
	
	/**
	 * Removes the temp.
	 *
	 * @param str the str
	 * @return the string
	 */
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
	
	
	
	/**
	 * Delete temp files.
	 *
	 * @param tempFiles the temp files
	 */
	public static void deleteTempFiles(List<File> tempFiles){
		  for (File temp : tempFiles) {
	            try {
					FileDeleteStrategy.FORCE.delete(temp);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }  
	}
	
	
	/**
	 * Gets the header string.
	 *
	 * @param mapping the mapping
	 * @return the header string
	 */
	public   String getHeaderString(HashMap<Integer,String> mapping){
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
	
	
	/**
	 * Fixed length string.
	 *
	 * @param po the po
	 * @param mapping the mapping
	 * @return the string builder
	 */
	public   StringBuilder fixedLengthString(HashMap<String, Object> po,HashMap<Integer,String> mapping){
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
	public   static boolean isBlank(String val){
		if("null".equals(val)){
			return true;
		}
		
		if(StringUtils.isBlank(val)){
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the file name.
	 *
	 * @param poNum the po num
	 * @param siteId the site id
	 * @param msgType the msg type
	 * @return the file name
	 */
	private    String getFileName(String poNum,String siteId,String msgType) {
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
		
		/*if(Constants.MESSAGE_TYPE_SUPP.equals(msgType) || Constants.MESSAGE_TYPE_ITEM.equals(msgType)){
			 isoFormat = new SimpleDateFormat(Constants.DATE_FORMAT_SUPP); 
			 name.append(timestamp);
			 name.append("0000000");
		}else{
			isoFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
			name.append(timestamp);
		}*/
		
		name.append(timestamp);
		if(!StringUtils.isBlank(poNum)){
			//name.append(".");
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
	private   static String appendTab(Object value) {
		if(value==null || "".equals(value) || "null".equals(value)){
			return "\t";
		}else{
			return value+"\t";
		}
	}
	
	/**
	 * Prepare supp data.
	 *
	 * @param poNum the po num
	 * @param mapping the mapping
	 * @param rowList the row list
	 * @param plantName the plant name
	 * @param msgType the msg type
	 * @return the map
	 */
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
	
	public  Map<String,List<String>>  prepareSuppData(String poNum, HashMap<Integer,String> mapping,HashMap<String, Object> rowMap,String plantName,String msgType){
		Map<String,List<String>> fileNameToRowsMap = new HashMap<>();
		List<String> lines = new ArrayList<>();
		lines.add(getHeaderString(mapping));
		lines.add(fixedLengthString((rowMap),mapping).toString());
	    String fileName = getFileName(poNum,plantName,msgType);
	    fileNameToRowsMap.put(fileName, lines);
	return fileNameToRowsMap;
}
	
	/**
	 * Gets the new rowkeys.
	 *
	 * @param partitionKey the partition key
	 * @param tableName the table name
	 * @param rowKeys the row keys
	 * @param cloudTable the cloud table
	 * @return the new rowkeys
	 */
	public static List<Map<String,Integer>> getNewRowkeys(String partitionKey,String tableName,List<String> rowKeys,CloudTable cloudTable  ){ 
        final int queryBatchSize = 50;
        List<String> temp = new ArrayList<> ();
        
        Map<String,Integer> oldRowkeyToStatusMap = new HashMap<>();
        int rowKeysSize = rowKeys==null ? 0 : rowKeys.size();
        
        if(Constants.TABLE_PO_DETAILS.equals(tableName)){
            for (int i = 0; i < rowKeysSize; i++) {
                temp.add(rowKeys.get(i));
                if (i!=0 && i % queryBatchSize == 0) {
                    String query = QueryBuilder.processPosQuery(partitionKey,temp);
                    TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
                    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
                    	oldRowkeyToStatusMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
                    }
                    temp.clear();
                }
            }
            
            if (temp.size()>0) {
                String query = QueryBuilder.processPosQuery(partitionKey,temp);
                TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
                for (PoEntity entity : cloudTable.execute(partitionQuery)) {
                	oldRowkeyToStatusMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
                }
                temp.clear();
            }
        }else if (Constants.TABLE_SUPPLIER.equals(tableName)){
            for (int i = 0; i < rowKeysSize; i++) {
                temp.add(rowKeys.get(i));
                if (i!=0 && i % queryBatchSize == 0) {
                    
                    String query = QueryBuilder.processPosQuery(partitionKey,temp);
                    TableQuery<SuppEntity> partitionQuery =  TableQuery.from(SuppEntity.class).where(query);
                    for (SuppEntity entity : cloudTable.execute(partitionQuery)) {
                    	oldRowkeyToStatusMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
                    }
                    temp.clear();
                }
            }
            
            if (temp.size()>0) {
                String query = QueryBuilder.processPosQuery(partitionKey,temp);
                TableQuery<SuppEntity> partitionQuery =  TableQuery.from(SuppEntity.class).where(query);
                for (SuppEntity entity : cloudTable.execute(partitionQuery)) {
                	oldRowkeyToStatusMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
                }
                temp.clear();
            }
        }else if(Constants.TABLE_ITEM.equals(tableName)){
            for (int i = 0; i <rowKeysSize; i++) {
                temp.add(rowKeys.get(i));
                if (i!=0 && i % queryBatchSize == 0) {
                    
                    String query = QueryBuilder.processPosQuery(partitionKey,temp);
                    TableQuery<ItemEntity> partitionQuery =  TableQuery.from(ItemEntity.class).where(query);
                    for (ItemEntity entity : cloudTable.execute(partitionQuery)) {
                    	oldRowkeyToStatusMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
                    }
                    temp.clear();
                }
            }
            
            if (temp.size()>0) {
                String query = QueryBuilder.processPosQuery(partitionKey,temp);
                TableQuery<ItemEntity> partitionQuery =  TableQuery.from(ItemEntity.class).where(query);
                for (ItemEntity entity : cloudTable.execute(partitionQuery)) {
                	oldRowkeyToStatusMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
                }
                temp.clear();
            }
        }else if(Constants.TABLE_GR_DETAILS.equals(tableName)){
            for (int i = 0; i < rowKeysSize; i++) {
                temp.add(rowKeys.get(i));
                if (i!=0 && i % queryBatchSize == 0) {
                    
                    String query = QueryBuilder.processPosQuery(partitionKey,temp);
                    TableQuery<GrEntity> partitionQuery =  TableQuery.from(GrEntity.class).where(query);
                    for (GrEntity entity : cloudTable.execute(partitionQuery)) {
                    	oldRowkeyToStatusMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
                    }
                    temp.clear();
                }
            }
            
            if (temp.size()>0) {
                String query = QueryBuilder.processPosQuery(partitionKey,temp);
                TableQuery<GrEntity> partitionQuery =  TableQuery.from(GrEntity.class).where(query);
                for (GrEntity entity : cloudTable.execute(partitionQuery)) {
                	oldRowkeyToStatusMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
                }
                temp.clear();
            }
        }
        
        Map<String,Integer> newRowkeyToStatusMap = new HashMap<>();
        for (int i = 0; i < rowKeysSize; i++) {
        	if(!oldRowkeyToStatusMap.containsKey(rowKeys.get(i))){
        		newRowkeyToStatusMap.put(rowKeys.get(i), Constants.STATUS_IN_TRANSIT);
        	}
        }
        
        List<Map<String,Integer>> res = new ArrayList<>();
        res.add(newRowkeyToStatusMap);//new rowkeys
        res.add(oldRowkeyToStatusMap);//old row keys
        return res;
    }

	/**
	 * Gets the misc entity.
	 *
	 * @param miscEntity the misc entity
	 * @param tableName the table name
	 * @param rowKeyData the row key data
	 * @return the misc entity
	 */
	public static MiscDataEntity getMiscEntity(MiscDataEntity miscEntity, int valueSize){
		LOG.info("### Starting  CommonUtils.getMiscEntity ####"+valueSize);
	    int successCount=0;
	    if(miscEntity.getPoProcessedCount() == null){
	        miscEntity.setPoProcessedCount(valueSize);
	    }else{
	    	successCount = miscEntity.getPoProcessedCount();
		    if(valueSize>0){
		    	successCount = successCount+valueSize;
		    }
		    miscEntity.setPoProcessedCount(successCount);  
	    }
		LOG.info("### Ending  CommonUtils.getMiscEntity ####"+miscEntity);
	 return miscEntity;
	}
	
	public static MiscDataEntity getMiscEntity(MiscDataEntity miscEntity,String tableName,List<Map<String,Integer>> rowKeyData){
		LOG.info("### Starting  CommonUtils.getMiscEntity ####"+tableName);
		Map<String,Integer> newRowkeyToStatusMap = rowKeyData.get(0);
		Map<String,Integer> oldRowkeyToStatusMap = rowKeyData.get(1);
		
		int newRowkeyToStatusMapSize = newRowkeyToStatusMap==null ? 0 : newRowkeyToStatusMap.size();
		int oldRowkeyToStatusMapSize = oldRowkeyToStatusMap==null ? 0 : oldRowkeyToStatusMap.size();
		LOG.error("oldRowkeyToStatusMapSize-->"+oldRowkeyToStatusMapSize);
		LOG.error("newRowkeyToStatusMapSize-->"+newRowkeyToStatusMapSize);
		
		int errorCount=0;
	    int successCount=0;
	    int inTransitCount=0;
		if(Constants.TABLE_PO_DETAILS.equals(tableName)){
		    
		    if(miscEntity.getPoIntransitCount() == null){
		        miscEntity.setPoIntransitCount(newRowkeyToStatusMapSize);
		    }else{
		    	inTransitCount = miscEntity.getPoIntransitCount();
			    if(oldRowkeyToStatusMapSize>0){
			    	for (Map.Entry<String,Integer> entry : oldRowkeyToStatusMap.entrySet()){
			    		if(entry.getValue()==Constants.STATUS_ERROR){
			    			errorCount = errorCount+1;
			    			inTransitCount = inTransitCount+1;
			    		}else if(entry.getValue()==Constants.STATUS_SUCCESS){
			    			successCount = successCount+1;
			    			inTransitCount = inTransitCount+1;
			    		}
				    }
			    }
			    if(newRowkeyToStatusMapSize>0){
			    	inTransitCount = inTransitCount+newRowkeyToStatusMapSize;
			    }
			    
			    if(errorCount>0){
			    	errorCount = miscEntity.getPoErrorCount()-errorCount;
			    	miscEntity.setPoErrorCount(errorCount);
			    }
			    if(successCount>0){
			    	successCount = miscEntity.getPoProcessedCount()-successCount;
			    	miscEntity.setPoProcessedCount(successCount);
			    }
			    miscEntity.setPoIntransitCount(inTransitCount);  
		    }
		}else if (Constants.TABLE_SUPPLIER.equals(tableName)){
		    if(miscEntity.getSuppIntransitCount() == null){
                miscEntity.setSuppIntransitCount(newRowkeyToStatusMapSize);
            }else{
		    	inTransitCount = miscEntity.getSuppIntransitCount();
			    if(oldRowkeyToStatusMapSize>0){
			    	for (Map.Entry<String,Integer> entry : oldRowkeyToStatusMap.entrySet()){
			    		if(entry.getValue()==Constants.STATUS_ERROR){
			    			errorCount = errorCount+1;
			    			inTransitCount = inTransitCount+1;
			    		}else if(entry.getValue()==Constants.STATUS_SUCCESS){
			    			successCount = successCount+1;
			    			inTransitCount = inTransitCount+1;
			    		}
				    }
			    }
			    LOG.error("successCount-->"+successCount);
			    LOG.error("inTransitCount-->"+inTransitCount);
			    LOG.error("errorCount-->"+errorCount);
			    if(newRowkeyToStatusMapSize>0){
			    	inTransitCount = inTransitCount+newRowkeyToStatusMapSize;
			    }
			    
			    if(errorCount>0){
			    	errorCount = miscEntity.getSuppErrorCount()-errorCount;
			    	 miscEntity.setSuppErrorCount(errorCount);
			    }
			    if(successCount>0){
			    	successCount = miscEntity.getSuppProcessedCount()-successCount;
			    	miscEntity.setSuppProcessedCount(successCount);
			    }
			    miscEntity.setSuppIntransitCount(inTransitCount);  
		    }
		}else if(Constants.TABLE_ITEM.equals(tableName)){
		    if(miscEntity.getItemIntransitCount() == null){
                miscEntity.setItemIntransitCount(newRowkeyToStatusMapSize);
            }else{
		    	inTransitCount = miscEntity.getItemIntransitCount();
			    if(oldRowkeyToStatusMapSize>0){
			    	for (Map.Entry<String,Integer> entry : oldRowkeyToStatusMap.entrySet()){
			    		if(entry.getValue()==Constants.STATUS_ERROR){
			    			errorCount = errorCount+1;
			    			inTransitCount = inTransitCount+1;
			    		}else if(entry.getValue()==Constants.STATUS_SUCCESS){
			    			successCount = successCount+1;
			    			inTransitCount = inTransitCount+1;
			    		}
				    }
			    }
			    if(newRowkeyToStatusMapSize>0){
			    	inTransitCount = inTransitCount+newRowkeyToStatusMapSize;
			    }
			    
			    if(errorCount>0){
			    	errorCount = miscEntity.getItemErrorCount()-errorCount;
			    	 miscEntity.setItemErrorCount(errorCount);
			    }
			    if(successCount>0){
			    	successCount = miscEntity.getItemProcessedCount()-successCount;
			    	miscEntity.setItemProcessedCount(successCount);
			    }
			    miscEntity.setItemIntransitCount(inTransitCount);  
		    }
			 
		}else if(Constants.TABLE_GR_DETAILS.equals(tableName)){
		    if(miscEntity.getGrIntransitCount() == null){
                miscEntity.setGrIntransitCount(newRowkeyToStatusMapSize);
            }else{
		    	inTransitCount = miscEntity.getGrIntransitCount();
			    if(oldRowkeyToStatusMapSize>0){
			    	for (Map.Entry<String,Integer> entry : oldRowkeyToStatusMap.entrySet()){
			    		if(entry.getValue()==Constants.STATUS_ERROR){
			    			errorCount = errorCount+1;
			    			inTransitCount = inTransitCount+1;
			    		}else if(entry.getValue()==Constants.STATUS_SUCCESS){
			    			successCount = successCount+1;
			    			inTransitCount = inTransitCount+1;
			    		}
				    }
			    }
			    if(newRowkeyToStatusMapSize>0){
			    	inTransitCount = inTransitCount+newRowkeyToStatusMapSize;
			    }
			    
			    if(errorCount>0){
			    	errorCount = miscEntity.getGrErrorCount()-errorCount;
			    	 miscEntity.setGrErrorCount(errorCount);
			    }
			    if(successCount>0){
			    	successCount = miscEntity.getGrProcessedCount()-successCount;
			    	miscEntity.setGrProcessedCount(successCount);
			    }
			    miscEntity.setGrIntransitCount(inTransitCount);  
		    }
       } else{
    	   return null;
       }
		LOG.info("### Ending  CommonUtils.getMiscEntity ####"+miscEntity);
	 return miscEntity;
	}
	
	
	private static final AtomicLong LAST_TIME_MS = new AtomicLong();
	public static long uniqueCurrentTimeMS() {
	    long now = System.currentTimeMillis();
	    while(true) {
	        long lastTime = LAST_TIME_MS.get();
	        if (lastTime >= now)
	            now = lastTime+1;
	        if (LAST_TIME_MS.compareAndSet(lastTime, now))
	            return now;
	    }
	}
	
}
