/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jci.job.entity.GrEntity;
import com.jci.job.entity.ItemEntity;
import com.jci.job.entity.MiscDataEntity;
import com.jci.job.entity.PoEntity;
import com.jci.job.entity.SuppEntity;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableQuery;

/**
 * <p>
 * <strong>Define all  Common utilities methods here .</strong>
 * <p>
 *
 * @author csonisk
 */
public class CommonUtils {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CommonUtils.class);
	
	/**
	 * Convert String to date.
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
	 * Gets the new rowkeys.
	 *
	 * @param partitionKey the partition key
	 * @param tableName the table name
	 * @param rowKeys the row keys
	 * @param cloudTable the cloud table
	 * @return the new rowkeys
	 */
	public static List<Map<String,Integer>> getNewRowkeys(String partitionKey,String tableName,List<String> rowKeys,CloudTable cloudTable  ){ 
        final int queryBatchSize = 100;
        List<String> temp = new ArrayList<> ();
        
        Map<String,Integer> oldRowkeyToStatusMap = new HashMap<>();
        
        if(Constants.TABLE_PO_DETAILS.equals(tableName)){
            for (int i = 0; i < rowKeys.size(); i++) {
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
            for (int i = 0; i < rowKeys.size(); i++) {
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
            for (int i = 0; i < rowKeys.size(); i++) {
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
            for (int i = 0; i < rowKeys.size(); i++) {
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
        for (int i = 0; i < rowKeys.size(); i++) {
        	if(!oldRowkeyToStatusMap.containsKey(rowKeys.get(i))){
        		newRowkeyToStatusMap.put(rowKeys.get(i), 1);
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
	public static MiscDataEntity getMiscEntity(MiscDataEntity miscEntity,String tableName,List<Map<String,Integer>> rowKeyData){
		LOG.info("### Starting  CommonUtils.getMiscEntity ####");
		Map<String,Integer> newRowkeyToStatusMap = rowKeyData.get(0);
		Map<String,Integer> oldRowkeyToStatusMap = rowKeyData.get(1);
		
		int newRowkeyToStatusMapSize = newRowkeyToStatusMap==null ? 0 : newRowkeyToStatusMap.size();
		int oldRowkeyToStatusMapSize = oldRowkeyToStatusMap==null ? 0 : oldRowkeyToStatusMap.size();
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
			    LOG.info("newRowkeyToStatusMapSize--->"+newRowkeyToStatusMapSize);
				LOG.info("inTransitCount--->"+inTransitCount);
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
       }
		LOG.info("### Ending  CommonUtils.getMiscEntity ####"+miscEntity);
	 return miscEntity;
	}

}
