package com.jci.job.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetails;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SupplierDetailsRes;
import com.jci.job.azure.BatchInsertReq;
import com.jci.job.entity.ItemEntity;
import com.jci.job.entity.PoEntity;
import com.jci.job.entity.PoItemsEntity;
import com.jci.job.entity.SupplierEntity;
import com.microsoft.azure.storage.table.TableEntity;

public class PrepareBatchInsertReq {
	
	private static final Logger LOG = LoggerFactory.getLogger(PrepareBatchInsertReq.class);
	
	public static BatchInsertReq prepareReq(PoDetailsRes responseBody,String erpName, String region, String plant,String lineID,String requestID) {
		List<TableEntity> poDetailsList = new ArrayList<TableEntity>();
		List<TableEntity> poItemDetailsList =  new ArrayList<TableEntity>();
		ObjectMapper mapper = new ObjectMapper();
		BatchInsertReq res = null;
		/*int reqCode = responseBody.getCode();
		String reqDate = responseBody.getDate(); 
		String reqMessage = responseBody.getMessage();
		String reqStatus = responseBody.getStatus();*/
		
		String partitionKey = CommonUtils.getPartitionKey(erpName);
		
		List<PoDetails> poList = responseBody.getPoList();
		for (PoDetails po : poList) {
			
			String orderCreationDate = po.getOrderCreationDate();
			String orderNumber = po.getOrderNumber();
			
			
			//Prepare PO Entity Data
			PoEntity poEntity = new PoEntity(partitionKey,orderNumber);
			poEntity.setAsn( po.isAsn());

			//Sunil: Need to discuss this 
			poEntity.setDestSuppliers("");			
			
			poEntity.setErpName(erpName);
			poEntity.setRegion(region);
			poEntity.setPlant(plant);
			
			poEntity.setGrNum(po.getGrNumber());
			poEntity.setOrderCreationDate(CommonUtils.stringToDate(orderCreationDate));
			poEntity.setPoACK(po.isPoACK());
			poEntity.setStatus(Constants.STATUS_IN_TRANSIT);
			poEntity.setSupplierType( po.getSupplierType());
			List<Object> itemList= po.getItemList();
			PoItemsEntity itemEntity = null;
			
					
			for (Object itemJsonString : itemList) {
				try {
					JsonNode actualObj = mapper.readTree(mapper.writeValueAsString(itemJsonString));
					itemEntity = new PoItemsEntity(partitionKey,(orderNumber+"_"+actualObj.get(lineID)+"_"+actualObj.get(requestID)));
					itemEntity.setItemJsonString(mapper.writeValueAsString(itemJsonString));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if(itemEntity!=null){
					poItemDetailsList.add(itemEntity);
				}
			}	
			
			//LOG.info("poEntity-->"+poEntity);
			poDetailsList.add(poEntity);	
			
			HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<String,List<TableEntity>>();
			tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poDetailsList);
			tableNameToEntityMap.put(Constants.TABLE_PO_ITEM_DETAILS, poItemDetailsList);
			
			res = new BatchInsertReq();
			res.setErpName(erpName);
			res.setTableNameToEntityMap(tableNameToEntityMap);
		}		
		return res;
	}
	
	public static BatchInsertReq prepareSupplierReq(SupplierDetailsRes responseBody,String erpName,String supplierID) {
		ObjectMapper mapper = new ObjectMapper();
		List<Object>  supplierList =  responseBody.getSupplierList();
		String partitionKey = CommonUtils.getPartitionKey(erpName);
		List<TableEntity> supplierDetailsList =  new ArrayList<TableEntity>();
		
		SupplierEntity entity=null;
		for (Object supplierVal : supplierList) {
			JsonNode actualObj;
			try {
				actualObj = mapper.readTree(mapper.writeValueAsString(supplierVal));
				entity = new SupplierEntity(partitionKey,(actualObj.get(supplierID).asText()));
				
				entity.setSupplierJsonString(mapper.writeValueAsString(supplierVal));
				supplierDetailsList.add(entity);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<String,List<TableEntity>>();
		tableNameToEntityMap.put(Constants.TABLE_SUPPLIER, supplierDetailsList);
		
		BatchInsertReq res = new BatchInsertReq();
		res.setErpName(erpName);
		res.setTableNameToEntityMap(tableNameToEntityMap);
		
		return res;
	}
	
	public static BatchInsertReq prepareItemReq(ItemDetailsRes responseBody,String erpName, String customerItemID,String supplierID) {
		ObjectMapper mapper = new ObjectMapper();
		List<Object>  itemList =  responseBody.getItemList();
		String partitionKey = CommonUtils.getPartitionKey(erpName);
		List<TableEntity> itemDetailsList =  new ArrayList<TableEntity>();
		
		ItemEntity entity=null;
		for (Object itemVal : itemList) {
			JsonNode actualObj;
			try {
				actualObj = mapper.readTree(mapper.writeValueAsString(itemVal));
				entity = new ItemEntity(partitionKey,(actualObj.get(customerItemID).asText()+"_"+actualObj.get(supplierID).asText()));
				entity.setItemJsonString(mapper.writeValueAsString(itemVal));
				itemDetailsList.add(entity);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<String,List<TableEntity>>();
		tableNameToEntityMap.put(Constants.TABLE_ITEM, itemDetailsList);
		
		BatchInsertReq res = new BatchInsertReq();
		res.setErpName(erpName.toUpperCase());
		res.setTableNameToEntityMap(tableNameToEntityMap);
		return res;
	}

}
