/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.job.api.req.BatchInsertReq;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetails;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SuppDetailsRes;
import com.jci.job.entity.ItemEntity;
import com.jci.job.entity.PoEntity;
import com.jci.job.entity.PoItemsEntity;
import com.jci.job.entity.SuppEntity;
import com.microsoft.azure.storage.table.TableEntity;

/**
 * The Class PrepareBatchInsertReq.
 */
public class PrepareBatchInsertReq {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PrepareBatchInsertReq.class);
	
	/**
	 * Prepare req.
	 *
	 * @param responseBody the response body
	 * @param erpName the erp name
	 * @param region the region
	 * @param plant the plant
	 * @param lineID the line ID
	 * @param requestID the request ID
	 * @return the batch insert req
	 */
	public static BatchInsertReq prepareReq(PoDetailsRes responseBody,String erpName, String region, String plant,String lineID,String requestID) {
		List<TableEntity> poDetailsList = new ArrayList<>();
		List<TableEntity> poItemDetailsList =  new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		BatchInsertReq res = null;
		
		String partitionKey = CommonUtils.getPartitionKey(erpName);
		List<PoDetails> poList = responseBody.getPoList();
		for (PoDetails po : poList) {
			
			String orderCreationDate = po.getOrderCreationDate();
			String orderNumber = po.getOrderNumber();
			
			//Prepare PO Entity Data
			PoEntity poEntity = new PoEntity(partitionKey,orderNumber);
			poEntity.setAsn( po.isAsn());

			poEntity.setErpName(erpName);
			poEntity.setRegion(region);
			poEntity.setPlant(plant);
			
			poEntity.setGrNum(po.getGrNumber());
			poEntity.setOrderCreationDate(CommonUtils.stringToDate(orderCreationDate));
			poEntity.setPoACK(po.isPoACK());
			poEntity.setStatus(Constants.STATUS_IN_TRANSIT);
			poEntity.setSuppType( po.getSuppType());
			List<Object> itemList= po.getItemList();
			PoItemsEntity itemEntity = null;
					
			for (Object jsonString : itemList) {
				try {
					JsonNode actualObj = mapper.readTree(mapper.writeValueAsString(jsonString));
					itemEntity = new PoItemsEntity(partitionKey,(orderNumber+"_"+actualObj.get(lineID)+"_"+actualObj.get(requestID)));
					itemEntity.setJsonString(mapper.writeValueAsString(jsonString));
					itemEntity.setOrderNumber(orderNumber);
				} catch (IOException e) {
					LOG.error("### Exception in   ####",e);
					
				}
				
				if(itemEntity!=null){
					poItemDetailsList.add(itemEntity);
				}
			}	
			
			poDetailsList.add(poEntity);	
			HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
			tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poDetailsList);
			tableNameToEntityMap.put(Constants.TABLE_PO_ITEM_DETAILS, poItemDetailsList);
			
			res = new BatchInsertReq();
			res.setErpName(erpName);
			res.setTableNameToEntityMap(tableNameToEntityMap);
		}		
		return res;
	}
	
	/**
	 * Prepare supp req.
	 *
	 * @param responseBody the response body
	 * @param erpName the erp name
	 * @param region the region
	 * @param plant the plant
	 * @param suppID the supp ID
	 * @return the batch insert req
	 */
	public static BatchInsertReq prepareSuppReq(SuppDetailsRes responseBody,String erpName, String region, String plant,String suppID) {
		ObjectMapper mapper = new ObjectMapper();
		List<Object>  suppList =  responseBody.getSuppList();
		String partitionKey = CommonUtils.getPartitionKey(erpName);
		List<TableEntity> suppDetailsList =  new ArrayList<>();
		
		SuppEntity entity=null;
		for (Object suppVal : suppList) {
			JsonNode actualObj;
			try {
				actualObj = mapper.readTree(mapper.writeValueAsString(suppVal));
				entity = new SuppEntity(partitionKey,(actualObj.get(suppID).asText()));
				entity.setJsonString(mapper.writeValueAsString(suppVal));
				entity.setStatus(Constants.STATUS_IN_TRANSIT);
				entity.setRegion(region);
				entity.setPlant(plant);
				suppDetailsList.add(entity);
			} catch (IOException e) {
				LOG.error("### Exception in   ####",e);
				
			}
		}
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
		tableNameToEntityMap.put(Constants.TABLE_SUPPLIER, suppDetailsList);
		
		BatchInsertReq res = new BatchInsertReq();
		res.setErpName(erpName);
		res.setTableNameToEntityMap(tableNameToEntityMap);
		
		return res;
	}
	
	/**
	 * Prepare item req.
	 *
	 * @param responseBody the response body
	 * @param erpName the erp name
	 * @param region the region
	 * @param plant the plant
	 * @param customerItemID the customer item ID
	 * @param suppID the supp ID
	 * @return the batch insert req
	 */
	public static BatchInsertReq prepareItemReq(ItemDetailsRes responseBody,String erpName, String region, String plant, String customerItemID,String suppID) {
		ObjectMapper mapper = new ObjectMapper();
		List<Object>  itemList =  responseBody.getItemList();
		String partitionKey = CommonUtils.getPartitionKey(erpName);
		List<TableEntity> itemDetailsList =  new ArrayList<>();
		
		ItemEntity entity=null;
		for (Object itemVal : itemList) {
			JsonNode actualObj;
			try {
				actualObj = mapper.readTree(mapper.writeValueAsString(itemVal));
				entity = new ItemEntity(partitionKey,(actualObj.get(customerItemID).asText()+"_"+actualObj.get(suppID).asText()));
				entity.setJsonString(mapper.writeValueAsString(itemVal));
				entity.setStatus(Constants.STATUS_IN_TRANSIT);
				entity.setRegion(region);
				entity.setPlant(plant);
				itemDetailsList.add(entity);
			} catch (IOException e) {
				
				LOG.error("### Exception in   ####",e);
			}
			
		}
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
		tableNameToEntityMap.put(Constants.TABLE_ITEM, itemDetailsList);
		
		BatchInsertReq res = new BatchInsertReq();
		res.setErpName(erpName.toUpperCase());
		res.setTableNameToEntityMap(tableNameToEntityMap);
		return res;
	}

}
