/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.req;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.dto.GrDetails;
import com.jci.entity.GrEntity;
import com.jci.entity.GrItemsEntity;
import com.jci.entity.ItemEntity;
import com.jci.entity.PoEntity;
import com.jci.entity.PoItemsEntity;
import com.jci.entity.SuppEntity;
import com.jci.job.api.res.GrDetailsRes;
import com.jci.job.api.res.Item;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetails;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SuppDetailsRes;
import com.jci.job.api.res.Supplier;
import com.jci.utils.CommonUtils;
import com.jci.utils.Constants;
import com.microsoft.azure.storage.table.TableEntity;

/**
 * The Class Prepare Batch Insert Request.
 */
public class PrepareBatchInsertReq {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PrepareBatchInsertReq.class);
	
	/**
	 * Prepare req.
	 *
	 * @param responseBody the response body
	 * @return the batch insert req
	 */
	public static BatchInsertReq preparePoReq(PoDetailsRes responseBody) {
		
		List<PoDetails> poList = responseBody.getPoList();
		
		ObjectMapper mapper = new ObjectMapper();
		List<TableEntity> poDetailsList = new ArrayList<>();
		List<TableEntity> poItemDetailsList =  new ArrayList<>();
		
		PoBody prepareReq = null;
		List<Object> req =  new ArrayList<>();
		
		List<String> rowKeyList = new ArrayList<>();
		BatchInsertReq res = null;
		String erpName = "";
		
		if(poList==null || poList.size()<1){
            return res;
        }
		
		LOG.info("poList size--->"+poList.size());
		
		for (PoDetails po : poList) {
			
			erpName = po.getErp().toUpperCase();
			
			String partitionKey = po.getErp().toUpperCase();
			//String orderNumber = po.getOrderNumber();
			
			String orderNumber = po.getOrderNumber();;		
			PoEntity poEntity = new PoEntity(partitionKey,orderNumber);
			rowKeyList.add(orderNumber);
			prepareReq = new PoBody();
			prepareReq.setErp(po.getErp());
			prepareReq.setOrderNumber(orderNumber);
			prepareReq.setPlant(po.getPlant());
			prepareReq.setRegion(po.getRegion());
			req.add(prepareReq);
			
			
			if(CommonUtils.isBlank(orderNumber) || CommonUtils.isBlank(po.getPlant()) || CommonUtils.isBlank(po.getRegion()) || CommonUtils.isBlank(po.getErp())){
				continue;
			}
			List<Object> itemList = po.getItemList();
			PoItemsEntity itemEntity = null;
			
			for (Object item : itemList) {
				 itemEntity = new PoItemsEntity(partitionKey,orderNumber);//item.getOrderNumber()+"_"+item.getLineID()+"_"+item.getRequestID()
				 if(poItemDetailsList.contains(itemEntity)){
						continue;
				 }
				 
				 try {
					itemEntity.setJsonString(mapper.writeValueAsString(item));
				} catch (JsonProcessingException e) {
				    LOG.error("### Exception in PrepareBatchInsertReq.preparePoReq ###"+e);
				}
				
				if(itemEntity!=null){
					itemEntity.setOrderNumber(orderNumber);
					poItemDetailsList.add(itemEntity);
			   }
			}

			if(!poDetailsList.contains(poEntity)){
				poEntity.setRegion(po.getRegion());
				poEntity.setPlant(po.getPlant());
				 
				poEntity.setOrderCreationDate(CommonUtils.stringToDate(po.getOrderCreationDate()));
				poEntity.setSupplierDeliveryState(Constants.STATUS_IN_TRANSIT);
				poEntity.setSuppType(po.getSupplierType());
				
				po.setItemList(null);
				try {
					poEntity.setJsonString(mapper.writeValueAsString(po));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				poDetailsList.add(poEntity);
			}
		}
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
		tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poDetailsList);
		tableNameToEntityMap.put(Constants.TABLE_PO_ITEM_DETAILS, poItemDetailsList);
		
		Map<String,List<String>> tableNameToRowkeyListMap = new HashMap<>();
		tableNameToRowkeyListMap.put(Constants.TABLE_PO_DETAILS, rowKeyList);
		res = new BatchInsertReq();
		res.setErpName(erpName);
		res.setTableNameToEntityMap(tableNameToEntityMap);
		res.setReq(req);
		res.setTableNameToRowkeyListMap(tableNameToRowkeyListMap);
		return res;
	}
	
	/**
	 * Prepare gr req.
	 *
	 * @param responseBody the response body
	 * @return the batch insert req
	 */
	public static BatchInsertReq prepareGrReq(GrDetailsRes responseBody,Map<String,Integer> poMap) {
		LOG.info("poMap-->"+poMap);
		List<GrDetails> grList = responseBody.getGrList();
		ObjectMapper mapper = new ObjectMapper();
		List<TableEntity> grDetailsList = new ArrayList<>();
		List<TableEntity> grItemDetailsList =  new ArrayList<>();
		
		List<TableEntity> poDetailsList = new ArrayList<>();
		List<TableEntity> poItemsDetailsList = new ArrayList<>();
		
		GrBody prepareReq = null;
		List<Object> req =  new ArrayList<>();
		List<String> poRowKeyList = new ArrayList<>();
		List<String> grRowKeyList = new ArrayList<>();
		
		BatchInsertReq res = null;
		String erpName = "";
		GrItemsEntity itemEntity = null;
		Map<String,List<String>> tableNameToRowkeyListMap = new HashMap<>();
		 
		//TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
		for (GrDetails gr : grList) {
			
			String partitionKey =gr.getErp().toUpperCase();
	         
			String receiptID = gr.getReceiptID();
			erpName = gr.getErp().toUpperCase();
			String rowKey = String.valueOf(CommonUtils.uniqueCurrentTimeMS());
			GrEntity entity = new GrEntity(partitionKey,rowKey);
			entity.setReceiptId(receiptID);
			  
			if(CommonUtils.isBlank(receiptID) || CommonUtils.isBlank(gr.getPlant()) || CommonUtils.isBlank(gr.getRegion()) || CommonUtils.isBlank(gr.getErp())){
				continue;
			}
			
			String jsonString=null;
			String itemJsonString=null;
			List<Object> itemList = gr.getGrItemList();
			for (Object item : itemList) {
				
				 itemEntity = new GrItemsEntity(partitionKey,rowKey);
				 if(grItemDetailsList.contains(itemEntity)){
						continue;
				 }
				 String str =null;
				 try {
					 itemJsonString = mapper.writeValueAsString(item);
					itemEntity.setJsonString(itemJsonString);
				} catch (IOException e) {
				    LOG.error("### Exception in PrepareBatchInsertReq.preparePoReq ###"+e);
				}
				
				if(itemEntity!=null){
					grItemDetailsList.add(itemEntity);
			    }
				
				gr.setGrItemList(null);
				try {
					jsonString = mapper.writeValueAsString(gr);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				//PO & PO Item Table
				if(!poMap.containsKey(receiptID)){			 
					PoEntity poEntity = new PoEntity(partitionKey,receiptID);
					if(!poDetailsList.contains(poEntity)){
						poEntity.setRegion(gr.getRegion());
						poEntity.setPlant(gr.getPlant());
						//poEntity.setOrderCreationDate(CommonUtils.stringToDate(gr.getOrderCreationDate()));
						poEntity.setSupplierDeliveryState(Constants.STATUS_SUCCESS);
						poEntity.setSuppType(gr.getSupplierType());
						
						//start
						//gr.setGrItemList(null);
						poEntity.setJsonString(jsonString);
						poDetailsList.add(poEntity);
						poRowKeyList.add(receiptID);
						 
						PoItemsEntity poItemsEntity = new PoItemsEntity(partitionKey,receiptID);
						poItemsEntity.setJsonString(str);
						poItemsEntity.setOrderNumber(receiptID);
						if(itemJsonString!=null){
							poItemsEntity.setJsonString(itemJsonString);
							//poItemsEntity.setJsonString("{}");
						}
						poItemsDetailsList.add(poItemsEntity) ;
				    }
				}
			}
			
			if(!grDetailsList.contains(entity)){
				entity.setRegion(gr.getRegion());
				//entity.setOrderNumber(poNumArr[0]);
				entity.setPlant(gr.getPlant());
				entity.setSupplierDeliveryState(Constants.STATUS_IN_TRANSIT);
				entity.setSuppType(gr.getSupplierType());
				if(jsonString!=null){
					entity.setJsonString(jsonString);
					//entity.setJsonString("{}");
				}
				grDetailsList.add(entity);	
				grRowKeyList.add(rowKey);
			}
			prepareReq = new GrBody();
			prepareReq.setReceiptID(receiptID);
			prepareReq.setErp(gr.getErp());
			prepareReq.setPlant(gr.getPlant());
			prepareReq.setRegion(gr.getRegion());
			req.add(prepareReq);
		}
		tableNameToRowkeyListMap.put(Constants.TABLE_GR_DETAILS, grRowKeyList);
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
		tableNameToEntityMap.put(Constants.TABLE_GR_DETAILS, grDetailsList);
		tableNameToEntityMap.put(Constants.TABLE_GR_ITEM_DETAILS, grItemDetailsList);
		
		res = new BatchInsertReq();
		if(poDetailsList.size()>0){
			tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poDetailsList);
			tableNameToEntityMap.put(Constants.TABLE_PO_ITEM_DETAILS, poItemsDetailsList);
			res.setDummyGrData(true);
			tableNameToRowkeyListMap.put(Constants.TABLE_PO_DETAILS, poRowKeyList);
		}
		res.setErpName(erpName);
		res.setTableNameToEntityMap(tableNameToEntityMap);
		res.setReq(req);
		res.setTableNameToRowkeyListMap(tableNameToRowkeyListMap);
		return res;
	}
	
	/**
	 * Prepare supp req.
	 *
	 * @param responseBody the response body
	 * @return the batch insert req
	 */
	public static BatchInsertReq prepareSuppReq(SuppDetailsRes responseBody) {
		ObjectMapper mapper = new ObjectMapper();
		List<Supplier>  suppList =  responseBody.getSupplierList();
		if(suppList==null){
			return null;
		}
		List<TableEntity> suppDetailsList =  new ArrayList<>();
		String erpName = "";
		List<String> rowKeyList = new ArrayList<>();
		SuppBody prepareReq = null;
		List<Object> req =  new ArrayList<>();
		
		for (Supplier suppVal : suppList) {
			erpName = suppVal.getErp().toUpperCase();;
			SuppEntity entity = new SuppEntity(erpName,suppVal.getSupplierID());
			
			if(suppDetailsList.contains(entity)){
				continue;
			}
			
			if(CommonUtils.isBlank(suppVal.getSupplierID()) || CommonUtils.isBlank(suppVal.getPlant()) || CommonUtils.isBlank(suppVal.getRegion()) || CommonUtils.isBlank(suppVal.getErp())){
				continue;
			}
			
			
			try {
				entity.setJsonString(mapper.writeValueAsString(suppVal.getSupplier()));
			} catch (JsonProcessingException e) {
			    LOG.error("### Exception in PrepareBatchInsertReq.prepareSuppReq ###"+e);
			}
			rowKeyList.add(suppVal.getSupplierID());
			entity.setSupplierDeliveryState(Constants.STATUS_IN_TRANSIT);
			entity.setRegion(suppVal.getRegion());
			entity.setPlant(suppVal.getPlant());
			entity.setSuppType(suppVal.getSupplierType());
			suppDetailsList.add(entity); 
			
			prepareReq = new SuppBody();
			prepareReq.setErp(erpName);
			prepareReq.setPlant(suppVal.getPlant());
			prepareReq.setRegion(suppVal.getRegion());
			prepareReq.setSupplierID(suppVal.getSupplierID());
			req.add(prepareReq);
		}
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
		tableNameToEntityMap.put(Constants.TABLE_SUPPLIER, suppDetailsList);
		

		Map<String,List<String>> tableNameToRowkeyListMap = new HashMap<>();
		tableNameToRowkeyListMap.put(Constants.TABLE_SUPPLIER, rowKeyList);
		
		
		BatchInsertReq res = new BatchInsertReq();
		res.setErpName(erpName);
		res.setTableNameToEntityMap(tableNameToEntityMap);
		res.setReq(req);
		res.setTableNameToRowkeyListMap(tableNameToRowkeyListMap);
		return res;
	}
	
	/**
	 * Prepare item req.
	 *
	 * @param responseBody the response body
	 * @return the batch insert req
	 */
	public static BatchInsertReq prepareItemReq(ItemDetailsRes responseBody) {
		ObjectMapper mapper = new ObjectMapper();
		List<Item>  itemList =  responseBody.getItemList();
		if(itemList==null){
			return null;
		}
		
		List<TableEntity> list =  new ArrayList<>();
		String erpName = "";
		
		ItemBody prepareReq = null;
		List<Object> req =  new ArrayList<>();
		List<String> rowKeyList = new ArrayList<>();
		for (Item itemVal : itemList) {
			erpName = itemVal.getErp().toUpperCase();;
			
			ItemEntity entity = new ItemEntity(erpName,(itemVal.getCustomerItemID()+"_"+itemVal.getSupplierID()));
			
			if(list.contains(entity)){
				continue;
			}
			
			if(CommonUtils.isBlank(itemVal.getSupplierID()) || CommonUtils.isBlank(itemVal.getPlant()) || CommonUtils.isBlank(itemVal.getRegion()) || CommonUtils.isBlank(itemVal.getErp())){
				continue;
			}
			
			try {
				entity.setJsonString(mapper.writeValueAsString(itemVal.getItem()));
			} catch (JsonProcessingException e) {
			    LOG.error("### Exception in PrepareBatchInsertReq.prepareItemReq ###"+e);
			}
			rowKeyList.add(itemVal.getCustomerItemID()+"_"+itemVal.getSupplierID());
			entity.setSupplierDeliveryState(Constants.STATUS_IN_TRANSIT);
			entity.setRegion(itemVal.getRegion());
			entity.setPlant(itemVal.getPlant());
			entity.setSuppType(itemVal.getSupplierType());
			entity.setCustomerItemID(itemVal.getCustomerItemID());
			
			list.add(entity);
			
			prepareReq = new ItemBody();
			prepareReq.setErp(erpName);
			prepareReq.setPlant(itemVal.getPlant());
			prepareReq.setRegion(itemVal.getRegion());
			prepareReq.setCustomerItemID(itemVal.getCustomerItemID());
			prepareReq.setSupplierID(itemVal.getSupplierID());
			req.add(prepareReq);
		}
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
		tableNameToEntityMap.put(Constants.TABLE_ITEM, list);
		
		Map<String,List<String>> tableNameToRowkeyListMap = new HashMap<>();
		tableNameToRowkeyListMap.put(Constants.TABLE_ITEM, rowKeyList);
		
		BatchInsertReq res = new BatchInsertReq();
		res.setErpName(erpName.toUpperCase());
		res.setTableNameToEntityMap(tableNameToEntityMap);
		res.setTableNameToRowkeyListMap(tableNameToRowkeyListMap);
		res.setReq(req);
		
		return res;
	}

}
