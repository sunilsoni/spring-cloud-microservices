package com.jci.job.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SupplierDetailsRes;
import com.jci.job.azure.BatchInsertReq;
import com.jci.job.entity.ItemEntity;
import com.jci.job.entity.PoEntity;
import com.jci.job.entity.PoItemsEntity;
import com.jci.job.entity.SupplierEntity;
import com.microsoft.azure.storage.table.TableEntity;

public class PrepareBatchInsertReq {
	
	public static List<BatchInsertReq> prepareReq(PoDetailsRes responseBody) {

		Map<String, Map<String,List<PoItemsEntity>>> dsToPoItemList =  responseBody.getDsToPoItemList();

		List<TableEntity> poDetailsList = new ArrayList<TableEntity>();
		List<TableEntity> poItemDetailsList =  new ArrayList<TableEntity>();
		
		//Map<String,List<PoItemsEntity>> poNumToEntityListMap;
		List<BatchInsertReq> finalList = new ArrayList<BatchInsertReq>();
		for ( Map.Entry<String,Map<String,List<PoItemsEntity>>> entry : dsToPoItemList.entrySet()) {//loop1
			
			String partitionKey = CommonUtils.getPartitionKey(entry.getKey().toUpperCase());
			//partitionKey = "SAP_PO";//1:delete this code
			for (Map.Entry<String,List<PoItemsEntity>> entry1 : entry.getValue().entrySet()) {//loop2 po numbers map
				PoEntity itemEntity = new PoEntity(partitionKey,entry1.getKey());
				
				int count = 0;
				for (PoItemsEntity entity : entry1.getValue()) {//loop3 po items list
					++count;
					if(count==1){
						itemEntity.setDescription(entity.getCustomerItemDescription());
					}
					entity.setPartitionKey(partitionKey);
					entity.setRowKey(entity.getOrderNumber()+"_"+entity.getLineID()+"_"+entity.getLineStatus());
					poItemDetailsList.add(entity);
				 }
				itemEntity.setOrderCreationDate(new Date());
				itemEntity.setSourceErpName(entry.getKey().toUpperCase());
				//itemEntity.setSourceErpName("SAP");//2:delete this code
				itemEntity.setStatus(Constants.STATUS_IN_TRANSIT);
				poDetailsList.add(itemEntity);
				
			}
			
			HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<String,List<TableEntity>>();
			tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poDetailsList);
			tableNameToEntityMap.put(Constants.TABLE_PO_ITEM_DETAILS, poItemDetailsList);
			
			BatchInsertReq res = new BatchInsertReq();
			res.setErpName(entry.getKey().toUpperCase());
			res.setTableNameToEntityMap(tableNameToEntityMap);
			finalList.add(res);
		}	
		return finalList;
	}
	
	public static List<BatchInsertReq> prepareSupplierReq(SupplierDetailsRes responseBody) {

		Map<String, List<SupplierEntity>> dsToSupplierList =  responseBody.getDsToSupplierList();

		List<TableEntity> poItemDetailsList =  new ArrayList<TableEntity>();
		List<BatchInsertReq> finalList = new ArrayList<BatchInsertReq>();
		
		for ( Entry<String, List<SupplierEntity>> entry : dsToSupplierList.entrySet()) {//loop1
			String partitionKey = CommonUtils.getPartitionKey(entry.getKey().toUpperCase());//DS name like symix
			//partitionKey = "SAP_PO";//delete this code
			for (SupplierEntity entity : entry.getValue()) {
				entity.setPartitionKey(partitionKey);
				entity.setRowKey(entity.getSupplierID());
				poItemDetailsList.add(entity);
			 }
			
				
			HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<String,List<TableEntity>>();
			tableNameToEntityMap.put(Constants.TABLE_SUPPLIER, poItemDetailsList);
			
			BatchInsertReq res = new BatchInsertReq();
			res.setErpName(entry.getKey().toUpperCase());
			//res.setErpName("SAP");//2:delete this code
			res.setTableNameToEntityMap(tableNameToEntityMap);
			finalList.add(res);
		}	
		return finalList;
	}
	
	public static List<BatchInsertReq> prepareItemReq(ItemDetailsRes responseBody) {

		Map<String, List<ItemEntity>> dsToItemList =  responseBody.getDsToItemList();
		List<TableEntity> poItemDetailsList =  new ArrayList<TableEntity>();
		List<BatchInsertReq> finalList = new ArrayList<BatchInsertReq>();
		
		for ( Entry<String, List<ItemEntity>> entry : dsToItemList.entrySet()) {//loop1
			String partitionKey = CommonUtils.getPartitionKey(entry.getKey().toUpperCase());
			//partitionKey = "SAP_PO";//delete this code
			for (ItemEntity entity : entry.getValue()) {
				entity.setPartitionKey(partitionKey);
				//entity.setRowKey((entity.getCustomerItemID()+"_"+entity.getSupplierID()));
				entity.setRowKey((entity.getCustomerItemID()));
				poItemDetailsList.add(entity);
			 }
			
			HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<String,List<TableEntity>>();
			tableNameToEntityMap.put(Constants.TABLE_ITEM, poItemDetailsList);
			
			BatchInsertReq res = new BatchInsertReq();
			res.setErpName(entry.getKey().toUpperCase());
			//res.setErpName("SAP");//2:delete this code
			res.setTableNameToEntityMap(tableNameToEntityMap);
			finalList.add(res);
		}	
		return finalList;
	}

}
