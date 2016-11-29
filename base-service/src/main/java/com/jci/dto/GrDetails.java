/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.dto;

import java.util.List;

import lombok.Data;

/**
 * <p>
 * <strong> The Goods Receipt Details Class.</strong>
 * <p>
 *
 * @author csonisk
 */
@Data
public class GrDetails {
	
	
	/** The receipt ID. */
	private String receiptID;
	
	/** The erp. */
	private String erp;
	
	/** The plant. */
	private String plant;
	
	/** The region. */
	private String region;
	
	/** The supplier type. */
	private String supplierType;
	
	private String customerID;
	private String customerDescription;
	private String customerDUNS;
	private String customerDUNS4;
	private String supplierID;
	private String supplierDescription;
	private String supplierDUNS;
	private String supplierDUNS4;
	private String receiptCreationDate;
	private String buyerCode;
	private String receivedAtHubOrSite;
	private String receiptStatus;
	private String receiptDateHdr;
	private String receivingSite;
	private String receiptQuantity;
	private String shipToAddressDescriptor;
	private String shipToAddress1;
	private String shipToAddress2;
	private String shipToAddress3;
	private String shipToAddress4;
	private String shipToAddress5;
	private String shipToCity;
	private String shipToCounty;
	private String shipToState;
	private String shipToCountry;
	private String shipToZip;
	private String flexStringReceiptHeader1;
	private String flexStringReceiptHeader2;
	private String flexStringReceiptHeader3;
	private String flexStringReceiptHeader4;
	private String flexStringReceiptHeader5;
	private String flexStringReceiptHeader6;
	private String flexStringReceiptHeader7;
	private String flexStringReceiptHeader8;
	private String flexStringReceiptHeader9;
	private String flexIntReceiptHeader1;
	private String flexIntReceiptHeader2;
	private String flexIntReceiptHeader3;
	private String flexIntReceiptHeader4;
	private String flexIntReceiptHeader5;
	private String flexFloatReceiptHeader1;
	private String flexFloatReceiptHeader2;
	private String flexFloatReceiptHeader3;
	private String flexFloatReceiptHeader4;
	private String flexFloatReceiptHeader5;
	private String flexDateReceiptHeader1;
	private String flexDateReceiptHeader2;
	private String flexDateReceiptHeader3;
	private String flexDateReceiptHeader4;
	private String flexDateReceiptHeader5;
	
	private List<Object> grItemList;
	
}
