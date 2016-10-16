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
import com.fasterxml.jackson.core.type.TypeReference;
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
			
			//String poNumFull = po.getOrderNumber();//ponumber-line id-release id
			//String[] poNumArr = poNumFull.split("-");
			
			String orderNumber = po.getOrderNumber();;		
			PoEntity poEntity = new PoEntity(partitionKey,orderNumber);
			rowKeyList.add(orderNumber);
			
			
			if(!poDetailsList.contains(poEntity)){
				poEntity.setRegion(po.getRegion());
				poEntity.setPlant(po.getPlant());
				 
				poEntity.setOrderCreationDate(CommonUtils.stringToDate(po.getOrderCreationDate()));
				poEntity.setSupplierDeliveryState(Constants.STATUS_IN_TRANSIT);
				poEntity.setSuppType(po.getSupplierType());
				
				//start
			    poEntity.setOrderStatus(po.getOrderStatus());
				poEntity.setOrderPriority(po.getOrderPriority());
				poEntity.setCustomerID(po.getCustomerID());
				
				poEntity.setCustomerDescription(po.getCustomerDescription());
				poEntity.setCustomerDUNS(po.getCustomerDUNS());
				poEntity.setCustomerDUNS4(po.getCustomerDUNS4());
				poEntity.setCustomerTaxNumber(po.getCustomerTaxNumber());
				poEntity.setCustomerAddressDescriptor(po.getCustomerAddressDescriptor());
				poEntity.setCustomerAddress1(po.getCustomerAddress1());
				poEntity.setCustomerAddress2(po.getCustomerAddress2());
				poEntity.setCustomerAddress3(po.getCustomerAddress3());
				poEntity.setCustomerAddress4(po.getCustomerAddress4());
				poEntity.setCustomerAddress5(po.getCustomerAddress5());
				poEntity.setCustomerCity(po.getCustomerCity());
				poEntity.setCustomerCounty(po.getCustomerCounty());
				poEntity.setCustomerState(po.getCustomerState());
				poEntity.setCustomerCountry(po.getCustomerCountry());
				poEntity.setCustomerZip(po.getCustomerZip());
				poEntity.setSupplierID(po.getSupplierID());
				poEntity.setSupplierDescription(po.getSupplierDescription());
				poEntity.setSupplierDUNS(po.getSupplierDUNS());
				poEntity.setSupplierDUNS4(po.getSupplierDUNS4());
				poEntity.setSupplierAddressDescriptor(po.getSupplierAddressDescriptor());
				poEntity.setSupplierAddress1(po.getSupplierAddress1());
				poEntity.setSupplierAddress2(po.getSupplierAddress2());
				poEntity.setSupplierAddress3(po.getSupplierAddress3());
				poEntity.setSupplierAddress4(po.getSupplierAddress4());
				poEntity.setSupplierAddress5(po.getSupplierAddress5());
				poEntity.setSupplierCity(po.getSupplierCity());
				poEntity.setSupplierCounty(po.getSupplierCounty());
				poEntity.setSupplierState(po.getSupplierState());
				poEntity.setSupplierCountry(po.getSupplierCountry());
				poEntity.setSupplierZip(po.getSupplierZip());
				poEntity.setBuyerCode(po.getBuyerCode());
				poEntity.setBuyerContact(po.getBuyerContact());
				poEntity.setBuyerName(po.getBuyerName());
				poEntity.setBuyerEmail(po.getBuyerEmail());
				poEntity.setSupplierEmail(po.getSupplierEmail());
				poEntity.setDeliveryTerm(po.getDeliveryTerm());
				poEntity.setPaymentTerms(po.getPaymentTerms());
				poEntity.setTotalOrderAmount(po.getTotalOrderAmount());
				poEntity.setInCoTerms(po.getInCoTerms());
				poEntity.setCustomerOrderNotes(po.getCustomerOrderNotes());
				poEntity.setSupplierOrderNotes(po.getSupplierOrderNotes());
				poEntity.setBillTo(po.getBillTo());
				poEntity.setBillToAddressDescriptor(po.getBillToAddressDescriptor());
				poEntity.setBillToAddress1(po.getBillToAddress1());
				poEntity.setBillToAddress2(po.getBillToAddress2());
				poEntity.setBillToAddress3(po.getBillToAddress3());
				poEntity.setBillToAddress4(po.getBillToAddress4());
				poEntity.setBillToAddress5(po.getBillToAddress5());
				poEntity.setBillToCity(po.getBillToCity());
				poEntity.setBillToCounty(po.getBillToCounty());
				poEntity.setBillToState(po.getBillToState());
				poEntity.setBillToCountry(po.getBillToCountry());
				poEntity.setBillToZip(po.getBillToZip());
				poEntity.setRemitToAddressDescriptor(po.getRemitToAddressDescriptor());
				poEntity.setRemitToAddress1(po.getRemitToAddress1());
				poEntity.setRemitToAddress2(po.getRemitToAddress2());
				poEntity.setRemitToAddress3(po.getRemitToAddress3());
				poEntity.setRemitToAddress4(po.getRemitToAddress4());
				poEntity.setRemitToAddress5(po.getRemitToAddress5());
				poEntity.setRemitToCity(po.getRemitToCity());
				poEntity.setRemitToCounty(po.getRemitToCounty());
				poEntity.setRemitToState(po.getRemitToState());
				poEntity.setRemitToCountry(po.getRemitToCountry());
				poEntity.setRemitToZip(po.getRemitToZip());
				poEntity.setBuyerContactPhone(po.getBuyerContactPhone());
				poEntity.setBuyerContactFax(po.getBuyerContactFax());
				poEntity.setOrderType(po.getOrderType());
				poEntity.setFlexStringPOHeader4(po.getFlexStringPOHeader4());
				poEntity.setFlexStringPOHeader5(po.getFlexStringPOHeader5());
				poEntity.setFlexStringPOHeader6(po.getFlexStringPOHeader6());
				poEntity.setFlexStringPOHeader7(po.getFlexStringPOHeader7());
				poEntity.setFlexStringPOHeader8(po.getFlexStringPOHeader8());
				poEntity.setFlexStringPOHeader9(po.getFlexStringPOHeader9());
				poEntity.setFlexIntPOHeader1(po.getFlexIntPOHeader1());
				poEntity.setFlexIntPOHeader2(po.getFlexIntPOHeader2());
				poEntity.setFlexIntPOHeader3(po.getFlexIntPOHeader3());
				poEntity.setFlexIntPOHeader4(po.getFlexIntPOHeader4());
				poEntity.setFlexIntPOHeader5(po.getFlexIntPOHeader5());
				poEntity.setFlexFloatPOHeader1(po.getFlexFloatPOHeader1());
				poEntity.setFlexFloatPOHeader2(po.getFlexFloatPOHeader2());
				poEntity.setFlexFloatPOHeader3(po.getFlexFloatPOHeader3());
				poEntity.setFlexFloatPOHeader4(po.getFlexFloatPOHeader4());
				poEntity.setFlexFloatPOHeader5(po.getFlexFloatPOHeader5());
				poEntity.setFlexDatePOHeader1(po.getFlexDatePOHeader1());
				poEntity.setFlexDatePOHeader2(po.getFlexDatePOHeader2());
				poEntity.setFlexDatePOHeader3(po.getFlexDatePOHeader3());
				poEntity.setFlexDatePOHeader4(po.getFlexDatePOHeader4());
				poEntity.setFlexDatePOHeader5(po.getFlexDatePOHeader5());
				
				poDetailsList.add(poEntity);
			}
			
			 
			prepareReq = new PoBody();
			prepareReq.setErp(po.getErp());
			prepareReq.setOrderNumber(orderNumber);
			prepareReq.setPlant(po.getPlant());
			prepareReq.setRegion(po.getRegion());
			req.add(prepareReq);
			
			List<Object> itemList = po.getItemList();
			LOG.info("itemList size--->"+itemList.size());
			
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
	public static BatchInsertReq prepareGrReq(GrDetailsRes responseBody,List<Map> map) {
		
		
		Map<String,Double> grMap = map.get(0);
		Map<String,Integer> poMap = map.get(1);
		
		List<GrDetails> grList = responseBody.getGrList();
		ObjectMapper mapper = new ObjectMapper();
		List<TableEntity> grDetailsList = new ArrayList<>();
		List<TableEntity> grItemDetailsList =  new ArrayList<>();
		
		List<TableEntity> poDetailsList = new ArrayList<>();
		List<TableEntity> poItemsDetailsList = new ArrayList<>();
		
		GrBody prepareReq = null;
		List<Object> req =  new ArrayList<>();
		List<String> rowKeyList = new ArrayList<>();
		
		BatchInsertReq res = null;
		String erpName = "";
		GrItemsEntity itemEntity = null;
		TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
		for (GrDetails gr : grList) {
			
			 String partitionKey =gr.getErp().toUpperCase();
	         
			String receiptID = gr.getReceiptID();
			//String[] poNumArr = receiptID.split("-");
			erpName = gr.getErp().toUpperCase();
			
			GrEntity entity = new GrEntity(partitionKey,receiptID);
			
			if(!grDetailsList.contains(entity)){
				entity.setRegion(gr.getRegion());
				//entity.setOrderNumber(poNumArr[0]);
				entity.setPlant(gr.getPlant());
				entity.setSupplierDeliveryState(Constants.STATUS_IN_TRANSIT);
				entity.setSuppType(gr.getSupplierType());
				
				//new
				entity.setReceiptID(gr.getReceiptID());
				entity.setPlant(gr.getPlant());
				entity.setSupplierType(gr.getSupplierType());
				entity.setCustomerID(gr.getCustomerID());
				entity.setCustomerDescription(gr.getCustomerDescription());
				entity.setCustomerDUNS(gr.getCustomerDUNS());
				entity.setCustomerDUNS4(gr.getCustomerDUNS4());
				entity.setSupplierID(gr.getSupplierID());
				entity.setSupplierDescription(gr.getSupplierDescription());
				entity.setSupplierDUNS(gr.getSupplierDUNS());
				entity.setSupplierDUNS4(gr.getSupplierDUNS4());
				entity.setReceiptCreationDate(gr.getReceiptCreationDate());
				entity.setBuyerCode(gr.getBuyerCode());
				entity.setReceivedAtHubOrSite(gr.getReceivedAtHubOrSite());
				entity.setReceiptStatus(gr.getReceiptStatus());
				entity.setReceiptDateHdr(gr.getReceiptDateHdr());
				entity.setReceivingSite(gr.getReceivingSite());
				//entity.setReceiptQuantity(gr.getReceiptQuantity());
				entity.setShipToAddressDescriptor(gr.getShipToAddressDescriptor());
				entity.setShipToAddress1(gr.getShipToAddress1());
				entity.setShipToAddress2(gr.getShipToAddress2());
				entity.setShipToAddress3(gr.getShipToAddress3());
				entity.setShipToAddress4(gr.getShipToAddress4());
				entity.setShipToAddress5(gr.getShipToAddress5());
				entity.setShipToCity(gr.getShipToCity());
				entity.setShipToCounty(gr.getShipToCounty());
				entity.setShipToState(gr.getShipToState());
				entity.setShipToCountry(gr.getShipToCountry());
				entity.setShipToZip(gr.getShipToZip());
				entity.setFlexStringReceiptHeader1(gr.getFlexStringReceiptHeader1());
				entity.setFlexStringReceiptHeader2(gr.getFlexStringReceiptHeader2());
				entity.setFlexStringReceiptHeader3(gr.getFlexStringReceiptHeader3());
				entity.setFlexStringReceiptHeader4(gr.getFlexStringReceiptHeader4());
				entity.setFlexStringReceiptHeader5(gr.getFlexStringReceiptHeader5());
				entity.setFlexStringReceiptHeader6(gr.getFlexStringReceiptHeader6());
				entity.setFlexStringReceiptHeader7(gr.getFlexStringReceiptHeader7());
				entity.setFlexStringReceiptHeader8(gr.getFlexStringReceiptHeader8());
				entity.setFlexStringReceiptHeader9(gr.getFlexStringReceiptHeader9());
				entity.setFlexIntReceiptHeader1(gr.getFlexIntReceiptHeader1());
				entity.setFlexIntReceiptHeader2(gr.getFlexIntReceiptHeader2());
				entity.setFlexIntReceiptHeader3(gr.getFlexIntReceiptHeader3());
				entity.setFlexIntReceiptHeader4(gr.getFlexIntReceiptHeader4());
				entity.setFlexIntReceiptHeader5(gr.getFlexIntReceiptHeader5());
				entity.setFlexFloatReceiptHeader1(gr.getFlexFloatReceiptHeader1());
				entity.setFlexFloatReceiptHeader2(gr.getFlexFloatReceiptHeader2());
				entity.setFlexFloatReceiptHeader3(gr.getFlexFloatReceiptHeader3());
				entity.setFlexFloatReceiptHeader4(gr.getFlexFloatReceiptHeader4());
				entity.setFlexFloatReceiptHeader5(gr.getFlexFloatReceiptHeader5());
				entity.setFlexDateReceiptHeader1(gr.getFlexDateReceiptHeader1());
				entity.setFlexDateReceiptHeader2(gr.getFlexDateReceiptHeader2());
				entity.setFlexDateReceiptHeader3(gr.getFlexDateReceiptHeader3());
				entity.setFlexDateReceiptHeader4(gr.getFlexDateReceiptHeader4());
				entity.setFlexDateReceiptHeader5(gr.getFlexDateReceiptHeader5());
				grDetailsList.add(entity);	
			}
			
			
			List<Object> itemList = gr.getGrItemList();
			
			for (Object item : itemList) {
				 itemEntity = new GrItemsEntity(partitionKey,receiptID);
				 if(grItemDetailsList.contains(itemEntity)){
						continue;
				 }
				 String str =null;
				 try {
					str = mapper.writeValueAsString(item);
					itemEntity.setJsonString(str);
					HashMap<String, Object> hashmap = mapper.readValue(str, typeRef);
					LOG.info("receiptQuantity--->"+hashmap.get("receiptQuantity"));
					if(hashmap.get("receiptQuantity")!=null){
						double newQty=Double.parseDouble(String.valueOf(hashmap.get("receiptQuantity")));
						if(grMap.containsKey(receiptID)){
							newQty   = newQty+grMap.get(receiptID);
						}
						itemEntity.setReceiptQuantity(newQty);
					}
				} catch (IOException e) {
				    LOG.error("### Exception in PrepareBatchInsertReq.preparePoReq ###"+e);
				}
				
				if(itemEntity!=null){
					grItemDetailsList.add(itemEntity);
			    }
				
				//PO & PO Item Table
				if(!poMap.containsKey(receiptID)){			 
					
					PoEntity poEntity = new PoEntity(partitionKey,receiptID);
					if(poDetailsList.contains(poEntity)){
						continue;
				    }
					poEntity.setRegion(gr.getRegion());
					poEntity.setPlant(gr.getPlant());
					 
					//poEntity.setOrderCreationDate(CommonUtils.stringToDate(gr.getOrderCreationDate()));
					poEntity.setSupplierDeliveryState(Constants.STATUS_SUCCESS);
					poEntity.setSuppType(gr.getSupplierType());
					
					//start
				    poEntity.setOrderStatus(gr.getReceiptStatus());
					//poEntity.setOrderPriority(gr.getOrderPriority());
					poEntity.setCustomerID(gr.getCustomerID());
					
					poEntity.setCustomerDescription(gr.getCustomerDescription());
					poEntity.setCustomerDUNS(gr.getCustomerDUNS());
					poEntity.setCustomerDUNS4(gr.getCustomerDUNS4());
					poEntity.setSupplierID(gr.getSupplierID());
					poEntity.setSupplierDescription(gr.getSupplierDescription());
					poEntity.setSupplierDUNS(gr.getSupplierDUNS());
					poEntity.setSupplierDUNS4(gr.getSupplierDUNS4());
					
					poDetailsList.add(poEntity);
					
					PoItemsEntity poItemsEntity = new PoItemsEntity(partitionKey,receiptID);
					if(poItemsDetailsList.contains(poItemsEntity)){
						continue;
					}
					poItemsEntity.setJsonString(str);
					poItemsEntity.setOrderNumber(receiptID);
					
					poItemsDetailsList.add(poItemsEntity) ;
				}
			}
			rowKeyList.add(receiptID);
			
			prepareReq = new GrBody();
			prepareReq.setReceiptID(receiptID);
			prepareReq.setErp(gr.getErp());
			prepareReq.setPlant(gr.getPlant());
			prepareReq.setRegion(gr.getRegion());
			req.add(prepareReq);
		}
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
		tableNameToEntityMap.put(Constants.TABLE_GR_DETAILS, grDetailsList);
		tableNameToEntityMap.put(Constants.TABLE_GR_ITEM_DETAILS, grItemDetailsList);
		
		if(poDetailsList.size()>0){
			tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poDetailsList);
			tableNameToEntityMap.put(Constants.TABLE_PO_ITEM_DETAILS, poItemsDetailsList);
		}
		
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
