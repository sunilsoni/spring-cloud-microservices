package com.jci.job.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

public class PrepareBatchInsertReq {
	
	//private static final Logger LOG = LoggerFactory.getLogger(PrepareBatchInsertReq.class);
	
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
			//poEntity.setDestSupp("");			
			
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
	
	public static BatchInsertReq prepareSuppReq(SuppDetailsRes responseBody,String erpName, String region, String plant,String suppID) {
		ObjectMapper mapper = new ObjectMapper();
		List<Object>  suppList =  responseBody.getSuppList();
		String partitionKey = CommonUtils.getPartitionKey(erpName);
		List<TableEntity> suppDetailsList =  new ArrayList<TableEntity>();
		
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
				e.printStackTrace();
			}
		}
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<String,List<TableEntity>>();
		tableNameToEntityMap.put(Constants.TABLE_SUPPLIER, suppDetailsList);
		
		BatchInsertReq res = new BatchInsertReq();
		res.setErpName(erpName);
		res.setTableNameToEntityMap(tableNameToEntityMap);
		
		return res;
	}
	
	public static BatchInsertReq prepareItemReq(ItemDetailsRes responseBody,String erpName, String region, String plant, String customerItemID,String suppID) {
		ObjectMapper mapper = new ObjectMapper();
		List<Object>  itemList =  responseBody.getItemList();
		String partitionKey = CommonUtils.getPartitionKey(erpName);
		List<TableEntity> itemDetailsList =  new ArrayList<TableEntity>();
		
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
