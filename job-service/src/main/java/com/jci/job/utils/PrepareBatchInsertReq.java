/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.job.api.req.BatchInsertReq;
import com.jci.job.api.req.GrBody;
import com.jci.job.api.req.ItemBody;
import com.jci.job.api.req.PoBody;
import com.jci.job.api.req.SuppBody;
import com.jci.job.api.res.GrDetails;
import com.jci.job.api.res.GrDetailsRes;
import com.jci.job.api.res.Item;
import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetails;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.PoItem;
import com.jci.job.api.res.SuppDetailsRes;
import com.jci.job.api.res.Supplier;
import com.jci.job.entity.GrEntity;
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
		
		for (PoDetails po : poList) {
			
			erpName = po.getErp().toUpperCase();
			
			String partitionKey = po.getErp().toUpperCase();
			String orderNumber = po.getOrderNumber();
			
			PoEntity poEntity = new PoEntity(partitionKey,orderNumber);
			
			if(poDetailsList.contains(poEntity)){
				continue;
			}
			rowKeyList.add(orderNumber);
			
			//poEntity.setErpName(po.getErp());
			poEntity.setRegion(po.getRegion());
			poEntity.setPlant(po.getPlant());
			 
			poEntity.setOrderCreationDate(CommonUtils.stringToDate(po.getOrderCreationDate()));
			poEntity.setSupplierDeliveryState(Constants.STATUS_IN_TRANSIT);
			poEntity.setSuppType(po.getSupplierType());
			
			poDetailsList.add(poEntity);
			
			prepareReq = new PoBody();
			prepareReq.setErp(po.getErp());
			prepareReq.setOrderNumber(orderNumber);
			prepareReq.setPlant(po.getPlant());
			prepareReq.setRegion(po.getRegion());
			req.add(prepareReq);
			
			
			List<PoItem> itemList = po.getItemList();
			PoItemsEntity itemEntity = null;
			
			for (PoItem item : itemList) {
				 itemEntity = new PoItemsEntity(partitionKey,(item.getOrderNumber()+"_"+item.getLineID()+"_"+item.getRequestID()));
				 if(poItemDetailsList.contains(itemEntity)){
						continue;
				 }
				 
				 try {
					itemEntity.setJsonString(mapper.writeValueAsString(item.getItem()));
				} catch (JsonProcessingException e) {
				    LOG.error("### Exception in PrepareBatchInsertReq.preparePoReq ###"+e);
				}
				itemEntity.setOrderNumber(item.getOrderNumber());
				if(itemEntity!=null){
					poItemDetailsList.add(itemEntity);
			   }
			}
		}
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
		tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poDetailsList);
		tableNameToEntityMap.put(Constants.TABLE_PO_ITEM_DETAILS, poItemDetailsList);
		
		res = new BatchInsertReq();
		res.setErpName(erpName);
		res.setTableNameToEntityMap(tableNameToEntityMap);
		res.setReq(req);
		res.setRowKeyList(rowKeyList);
		return res;
	}
	
	/**
	 * Prepare gr req.
	 *
	 * @param responseBody the response body
	 * @return the batch insert req
	 */
	public static BatchInsertReq prepareGrReq(GrDetailsRes responseBody) {
		
		List<GrDetails> grList = responseBody.getGrList();
		ObjectMapper mapper = new ObjectMapper();
		List<TableEntity> grDetailsList = new ArrayList<>();
		GrBody prepareReq = null;
		List<Object> req =  new ArrayList<>();
		List<String> rowKeyList = new ArrayList<>();
		
		BatchInsertReq res = null;
		String erpName = "";
		for (GrDetails gr : grList) {
			
			 String partitionKey =gr.getErp().toUpperCase();
	         
		//	Object gr;
			String receiptID = gr.getReceiptID();
			erpName = gr.getErp().toUpperCase();
			
			GrEntity entity = new GrEntity(partitionKey,receiptID);
			
			if(grDetailsList.contains(entity)){
				continue;
			}
			rowKeyList.add(receiptID);
			//entity.setErpName(gr.getErp());
			entity.setRegion(gr.getRegion());
			entity.setPlant(gr.getPlant());
			entity.setSupplierDeliveryState(Constants.STATUS_IN_TRANSIT);
			entity.setSuppType(gr.getSupplierType());
			
			
			try {
                entity.setJsonString(mapper.writeValueAsString(gr.getGr()));
            } catch (JsonProcessingException e) {
                LOG.error("### Exception in PrepareBatchInsertReq.prepareGrReq ###"+e);
            }
			grDetailsList.add(entity);
			prepareReq = new GrBody();
			prepareReq.setReceiptID(receiptID);
			prepareReq.setErp(gr.getErp());
			prepareReq.setPlant(gr.getPlant());
			prepareReq.setRegion(gr.getRegion());
			req.add(prepareReq);
		}
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
		tableNameToEntityMap.put(Constants.TABLE_GR_DETAILS, grDetailsList);
		
		res = new BatchInsertReq();
		res.setErpName(erpName);
		res.setTableNameToEntityMap(tableNameToEntityMap);
		res.setReq(req);
		res.setRowKeyList(rowKeyList);
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
		
		BatchInsertReq res = new BatchInsertReq();
		res.setErpName(erpName);
		res.setTableNameToEntityMap(tableNameToEntityMap);
		res.setReq(req);
		res.setRowKeyList(rowKeyList);
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
		
		BatchInsertReq res = new BatchInsertReq();
		res.setErpName(erpName.toUpperCase());
		res.setTableNameToEntityMap(tableNameToEntityMap);
		res.setRowKeyList(rowKeyList);
		res.setReq(req);
		
		return res;
	}

}
