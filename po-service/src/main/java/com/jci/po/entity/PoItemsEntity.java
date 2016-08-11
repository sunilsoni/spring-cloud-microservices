package com.jci.po.entity;

import java.util.Date;


import com.microsoft.azure.storage.table.TableServiceEntity;


public class PoItemsEntity extends TableServiceEntity {

	public PoItemsEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;// po number
	}

	public PoItemsEntity() {
	}
	// 1: Order Number String
	private String OrderNumber;

	// 2: Status String
	private String status;
	
	// 3: Message String
	private String message;

	// 4: Date Date
	private Date date;
	 
	// 1: Order Creation Date Date
	private Date OrderCreationDate;
	
	/**
	 * *Order Status Sequence No: 2
	 */
	private String orderStatus;
	
	/**
	 * *Order Priority Sequence No: 3
	 */
	private String orderPriority;
	
	/**
	 * **Customer ID Sequence No: 4
	 */
	private String CustomerID;
	
	/**
	 * *Customer Description Sequence No: 5
	 */
	private String customerDescription;
	
	/**
	 * *Customer DUNS Sequence No: 6
	 */
	private String customerDUNS;
	
	/**
	 * *Customer DUNS+4 Sequence No: 7
	 */
	private String customerDUNS4;
	
	/**
	 * *Customer Tax Number Sequence No: 8
	 */
	private String customerTaxNumber;
	
	/**
	 * *Customer Address - Descriptor Sequence No: 9
	 */
	private String customerAddressDescriptor;
	
	/**
	 * *Customer Address 1 Sequence No: 10
	 */
	private String customerAddress1;
	
	
	/**
	 * *Customer Address 2 Sequence No: 11
	 */
	private String customerAddress2;
	
	
	/**
	 * *Customer Address 3 Sequence No: 12
	 */
	private String customerAddress3;
	
	
	/**
	 * *Customer Address 4 Sequence No: 13
	 */
	private String customerAddress4;
	
	/**
	 * *Customer Address 5 Sequence No: 14
	 */
	private String customerAddress5;
	
	/**
	 * *Customer City Sequence No: 15
	 */
	private String customerCity;
	
	/**
	 * Customer County Sequence No: 16	 */
	 
	private String customerCounty;
	
	/**
	 * *Customer State Sequence No: 17
	 */
	private String customerState;

	/**
	 * *Customer Country Sequence No: 18
	 */
	private String customerCountry;
	
	/**
	 * *Customer Zip Sequence No: 19
	 */
	private String customerZip;
	
	/**
	 * **Supplier ID Sequence No: 20
	 */
	private String SupplierID;
	
	/**
	 * *Supplier Description Sequence No: 21
	 */
	private String supplierDescription;
	
	/**
	 * *Supplier DUNS Sequence No: 22
	 */
	private String supplierDUNS;
	
	/**
	 * *Supplier DUNS+4 Sequence No: 23
	 */
	private String supplierDUNS4;
	
	/**
	 * *Supplier Address - Descriptor Sequence No: 24
	 */
	private String supplierAddressDescriptor;
	
	/**
	 * *Supplier Address 1 Sequence No: 25
	 */
	private String supplierAddress1;
	
	/**
	 * *Supplier Address 2 Sequence No: 26
	 */
	private String supplierAddress2;
	
	/**
	 * *Supplier Address 3 Sequence No: 27
	 */
	private String supplierAddress3;
	
	/**
	 * *Supplier Address 4 Sequence No: 28
	 */
	private String supplierAddress4;
	
	/**
	 * *Supplier Address 5 Sequence No: 29
	 */
	private String supplierAddress5;
	
	/**
	 * *Supplier City Sequence No: 30
	 */
	private String supplierCity;
	
	/**
	 * *Supplier County Sequence No: 31
	 */
	private String supplierCounty;
	
	/**
	 * *Supplier State Sequence No: 32
	 */
	private String supplierState;
	
	/**
	 * *Supplier Country Sequence No: 33
	 */
	private String supplierCountry;
	
	/**
	 * *Supplier Zip Sequence No: 34
	 */
	private String supplierZip;
	
	/**
	 * *Buyer Code Sequence No: 35
	 */
	private String buyerCode;
	
	/**
	 * *Buyer Contact Sequence No: 36
	 */
	private String buyerContact;
	
	/**
	 * *Buyer Name Sequence No: 37
	 */
	private String buyerName;
	
	/**
	 * *Buyer Email Sequence No: 38
	 */
	private String buyerEmail;
	
	/**
	 * *Supplier Email Sequence No: 39
	 */
	private String supplierEmail;
	
	/**
	 * *Delivery Term Sequence No: 40
	 */
	private String deliveryTerm;
	
	/**
	 * *Payment Terms Sequence No: 41
	 */
	private String paymentTerms;
	
	/**
	 * *Total Order Amount Sequence No: 42
	 */
	private double totalOrderAmount;
	
	/**
	 * *InCO Terms Sequence No: 43
	 */
	private String incoTerms;
	
	/**
	 * *Customer Order Notes Sequence No: 44
	 */
	private String customerOrderNotes;
	
	/**
	 * *Supplier Order Notes Sequence No: 45
	 */
	private String supplierOrderNotes;
	
	/**
	 * *Bill To Sequence No: 46
	 */
	private String billTo;
	
	/**
	 * *Bill To Address - Descriptor Sequence No: 47
	 */
	private String billToAddressDescriptor;
	
	/**
	 * *Bill To Address 1 Sequence No: 48
	 */
	private String billToAddress1;
	
	/**
	 * *Bill To Address 2 Sequence No: 49
	 */
	private String billToAddress2;
	
	/**
	 * *Bill To Address 3 Sequence No: 50
	 */
	private String billToAddress3;
	
	/**
	 * *Bill To Address 4 Sequence No: 51
	 */
	private String billToAddress4;
	
	/**
	 * *Bill To Address 5 Sequence No: 52
	 */
	private String billToAddress5;
	
	/**
	 * *Bill To City Sequence No: 53
	 */
	private String billToCity;
	
	/**
	 * *Bill To County Sequence No: 54
	 */
	private String billToCounty;
	
	/**
	 * *Bill To State Sequence No: 55
	 */
	private String billToState;
	
	/**
	 * *Bill To Country Sequence No: 56
	 */
	private String billToCountry;
	
	/**
	 * *Bill To Zip Sequence No: 57
	 */
	private String billToZip;
	
	/**
	 * *Bill To Remit To Address- Descriptor Sequence No: 58
	 */
	private String remitToAddressDescriptor;
	
	/**
	 * *Remit To Address 1 Sequence No: 59
	 */
	private String remitToAddress1;
	
	/**
	 * *Remit To Address 2 Sequence No: 60
	 */
	private String remitToAddress2;
	
	/**
	 * *Remit To Address 3 Sequence No: 61
	 */
	private String remitToAddress3;
	
	/**
	 * *Remit To Address 4 Sequence No: 62
	 */
	private String remitToAddress4;
	
	/**
	 * *Remit To Address 5 Sequence No: 63
	 */
	private String remitToAddress5;
	
	/**
	 * *Remit To City Sequence No: 64
	 */
	private String remitToCity;
	
	/**
	 * *Remit To County Sequence No: 65
	 */
	private String remitToCounty;
	
	/**
	 * *Remit To State Sequence No: 66
	 */
	private String remitToState;
	
	/**
	 * *Remit To Country Sequence No: 67
	 */
	private String remitToCountry;
	
	/**
	 * *Remit To Zip Sequence No: 68
	 */
	private String remitToZip;
	
	/**
	 * *Buyer Contact Phone Sequence No: 69
	 */
	private String buyerContactPhone;
	
	/**
	 * *Remit To Buyer Contact Fax Sequence No: 70
	 */
	private String buyerContactFax;
	
	/**
	 * *Order Type Sequence No: 71
	 */
	private String orderType;
	
	/**
	 * *Flex String PO Header 4 Sequence No: 72
	 */
	private String flexStringPOHeader4;
	
	/**
	 * *Flex String PO Header 5 Sequence No: 73
	 */
	private String flexStringPOHeader5;
	
	/**
	 * *Flex String PO Header 6 Sequence No: 74
	 */
	private String flexStringPOHeader6;
	
	/**
	 * *Flex String PO Header 7 Sequence No: 75
	 */
	private String flexStringPOHeader7;
	
	/**
	 * *Flex String PO Header 8 Sequence No: 76
	 */
	private String flexStringPOHeader8;
	
	/**
	 * *Flex String PO Header 9 Sequence No: 77
	 */
	private String flexStringPOHeader9;
	
	/**
	 * *Flex Int PO Header 1 Sequence No: 78
	 */
	private int flexIntPOHeader1;
	
	/**
	 * *Flex Int PO Header 2 Sequence No: 79
	 */
	private int flexIntPOHeader2;
	
	/**
	 * *Flex Int PO Header 3 Sequence No: 80
	 */
	private int flexIntPOHeader3;
	
	/**
	 * *Flex Int PO Header 4 Sequence No: 81
	 */
	private int flexIntPOHeader4;
	
	/**
	 * *Flex Int PO Header 5 Sequence No: 82
	 */
	private int flexIntPOHeader5;
	
	/**
	 * *Flex float PO Header 1 Sequence No: 83
	 */
	private double flexFloatPOHeader1;
	
	/**
	 * *Flex float PO Header 2 Sequence No: 84
	 */
	private double flexFloatPOHeader2;
	
	/**
	 * *Flex float PO Header 3 Sequence No: 85
	 */
	private double flexFloatPOHeader3;
	
	/**
	 * *Flex float PO Header 4 Sequence No: 86
	 */
	private double flexFloatPOHeader4;
	
	/**
	 * *Flex float PO Header 5 Sequence No: 87
	 */
	private double flexFloatPOHeader5;
	
	/**
	 * *Flex Date PO Header 1 Sequence No: 88
	 */
	private Date flexDatePOHeader1;
	
	/**
	 * *Flex Date PO Header 2 Sequence No: 89
	 */
	private Date flexDatePOHeader2;
	
	/**
	 * *Flex Date PO Header 3 Sequence No: 90
	 */
	private Date flexDatePOHeader3;
	
	/**
	 * *Flex Date PO Header 4 Sequence No: 91
	 */
	private Date flexDatePOHeader4;
	
	/**
	 * *Flex Date PO Header 5 Sequence No: 92
	 */
	private Date flexDatePOHeader5;
	
	/**
	 * Line Id Sequence No: 93
	 */
	private int LineID;//po-line
	
	/**
	 * Line Status Sequence No: 94
	 */
	private String lineStatus;
	
	/**
	 * *Customer Item Id Sequence No: 95
	 */
	private String CustomerItemID;
	
	/**
	 * *Customer Item Description Sequence No: 96
	 */
	private String customerItemDescription;
	
	/**
	 * *Supplier Item Id Sequence No: 97
	 */
	private String supplierItemID;
	
	/**
	 * *Supplier Item Description Sequence No: 98
	 */
	private String supplierItemDescription;
	
	/**
	 * *Unit Price Sequence No: 99
	 */
	private double UnitPrice;
	
	/**
	 * *Price Basis Sequence No: 100
	 */
	private String priceBasis;
	
	private String paymentCurrency;

	// 102: Total Line Amount double
	private double totalLineAmount;

	// 103: UOM VARCHAR(64)
	private String uOM;
	
	// 104: Customer Order Line Notes VARCHAR(4000)
	private String customerOrderLineNotes;

	// 105: Supplier Order Line Notes VARCHAR(4000)
	private String supplierOrderLineNotes;

	// 106: Drawing Version VARCHAR(255)
	private String drawingVersion;

	// 107: Drawing Number VARCHAR(255)
	private String drawingNumber;

	// 108: Item Category VARCHAR(255)
	private String itemCategory;

	// 109: Ship To Location VARCHAR(128)
	private String shipToLocation;

	// 110: Flex String PO Line 5 VARCHAR(128)
	private String flexStringPOLine5;

	// 111: Flex String PO Line 6 VARCHAR(128)
	private String flexStringPOLine6;

	// 112: Flex String PO Line 7 VARCHAR(64)
	private String flexStringPOLine7;

	// 113: Flex String PO Line 8 VARCHAR(64)
	private String flexStringPOLine8;

	// 114: Flex String PO Line 9 VARCHAR(64)
	private String flexStringPOLine9;

	// 115: Free Item Flag NUMBER
	private int freeItemFlag;

	// 116: Flex Int PO Line 2 NUMBER
	private int flexIntPOLine2;

	// 117: Flex Int PO Line 3 NUMBER
	private int flexIntPOLine3;

	// 118: Flex Int PO Line 4 NUMBER
	private int flexIntPOLine4;

	// 119: Flex Int PO Line 5 NUMBER
	private int flexIntPOLine5;

	// 120: Flex double PO Line 1 double
	private double flexFloatPOLine1;

	// 121: Flex double PO Line 2 double
	private double flexFloatPOLine2;

	// 122: Flex double PO Line 3 double
	private double flexFloatPOLine3;

	// 123: Flex double PO Line 4 double
	private double flexFloatPOLine4;

	// 124: Flex double PO Line 5 double
	private double flexFloatPOLine5;

	// 125: Flex Date PO Line 1 Date
	private Date flexDatePOLine1;

	// 126: Flex Date PO Line 2 Date
	private Date flexDatePOLine2;

	// 127: Flex Date PO Line 3 Date
	private Date flexDatePOLine3;

	// 128: Flex Date PO Line 4 Date
	private Date flexDatePOLine4;

	// 129: Flex Date PO Line 5 Date
	private Date flexDatePOLine5;

	// 130: Request ID NUMBER
	private int RequestID;

	// 131: Request Status VARCHAR(64)
	private String requestStatus;

	// 132: Action VARCHAR(64)
	private String Action;

	// 133: Request Qty double
	private double RequestQty;

	// 134: Request Date Date
	private Date RequestDate;

	// 135: Requested Ship Date Date
	private Date requestedShipDate;

	// 136: Carrier VARCHAR(64)
	private String carrier;

	// 137: Customer Site VARCHAR(64)
	private String CustomerSite;

	// 138: Ship To Address - Descriptor VARCHAR(64)
	private String shipToAddressDescriptor;

	// 139: Ship To Address 1 VARCHAR(256)
	private String shipToAddress1;

	// 140: Ship To Address 2 VARCHAR(256)
	private String shipToAddress2;

	// 141: Ship To Address 3 VARCHAR(256)
	private String shipToAddress3;

	// 142: Ship To Address 4 VARCHAR(256)
	private String shipToAddress4;

	// 143: Ship To Address 5 VARCHAR(256)
	private String shipToAddress5;

	// 144: Ship To City VARCHAR(64)
	private String shipToCity;

	// 145: Ship To County VARCHAR(64)
	private String shipToCounty;

	// 146: Ship To State VARCHAR(64)
	private String shipToState;

	// 147: Ship To Country VARCHAR(64)
	private String shipToCountry;

	// 148: Ship To Zip VARCHAR(64)
	private String shipToZip;

	// 149: Ref Order Type VARCHAR(64)
	private String refOrderType;

	// 150: Ref Order ID VARCHAR(64)
	private String refOrderID;

	// 151: Ref Order Line ID VARCHAR(64)
	private String refOrderLineID;

	// 152: Ref Order Request ID VARCHAR(64)
	private String refOrderRequestID;

	// 153: Ref Customer ID VARCHAR(64)
	private String refCustomerID;

	// 154: Ref Supplier ID VARCHAR(64)
	private String refSupplierID;

	// 155: Flex String PO Request 1 VARCHAR(255)
	private String flexStringPORequest1;

	// 156: Flex String PO Request 2 VARCHAR(255)
	private String flexStringPORequest2;

	// 157: Flex String PO Request 3 VARCHAR(255)
	private String flexStringPORequest3;

	// 158: Flex String PO Request 4 VARCHAR(128)
	private String flexStringPORequest4;

	// 159: Flex String PO Request 5 VARCHAR(128)
	private String flexStringPORequest5;

	// 160: Flex String PO Request 6 VARCHAR(128)
	private String flexStringPORequest6;

	// 161: Flex String PO Request 7 VARCHAR(64)
	private String flexStringPORequest7;

	// 162: Flex String PO Request 8 VARCHAR(64)
	private String flexStringPORequest8;

	// 163: Flex String PO Request 9 VARCHAR(64)
	private String flexStringPORequest9;

	// 164: Flex Int PO Request 1 NUMBER
	private int flexIntPORequest1;

	// 165: Flex Int PO Request 2 NUMBER
	private int flexIntPORequest2;

	// 166: Flex Int PO Request 3 NUMBER
	private int flexIntPORequest3;

	// 167: Flex Int PO Request 4 NUMBER
	private int flexIntPORequest4;

	// 168: Flex Int PO Request 5 NUMBER
	private int flexIntPORequest5;

	// 169: Flex double PO Request 1 double
	private double flexFloatPORequest1;

	// 170: Flex double PO Request 2 double
	private double flexFloatPORequest2;

	// 171: Flex double PO Request 3 double
	private double flexFloatPORequest3;

	// 172: Flex double PO Request 4 double
	private double flexFloatPORequest4;

	// 173: Flex double PO Request 5 double
	private double flexFloatPORequest5;

	// 174: Flex Date PO Request 1 Date
	private Date flexDatePORequest1;

	// 175: Flex Date PO Request 2 Date
	private Date flexDatePORequest2;

	// 176: Flex Date PO Request 3 Date
	private Date flexDatePORequest3;

	// 177: Flex Date PO Request 4 Date
	private Date flexDatePORequest4;

	// 178: Flex Date PO Request 5 Date
	private Date flexDatePORequest5;

	// 179: Promise ID NUMBER
	private int promiseID;

	// 180: Promise Qty double
	private double promiseQty;

	// 181: Promise Date Date
	private Date promiseDate;

	// 182: Promised Ship Date Date
	private Date promisedShipDate;

	// 183: Supplier Site VARCHAR(64)
	private String supplierSite;

	// 184: Ship From Address - Descriptor VARCHAR(64)
	private String shipFromAddressDescriptor;

	// 185: Ship From Address 1 VARCHAR(256)
	private String shipFromAddress1;

	// 186: Ship From Address 2 VARCHAR(256)
	private String shipFromAddress2;

	// 187: Ship From Address 3 VARCHAR(256)
	private String shipFromAddress3;

	// 188: Ship From Address 4 VARCHAR(256)
	private String shipFromAddress4;

	// 189: Ship From Address 5 VARCHAR(256)
	private String shipFromAddress5;

	// 190: Ship From City VARCHAR(64)
	private String shipFromCity;

	// 191: Ship From County VARCHAR(64)
	private String shipFromCounty;

	// 192: Ship From State VARCHAR(64)
	private String shipFromState;

	// 193: Ship From Country VARCHAR(64)
	private String shipFromCountry;

	// 194: Ship From Zip VARCHAR(64)
	private String shipFromZip;

	// 195: Flex String PO Promise 1 VARCHAR(255)
	private String flexStringPOPromise1;

	// 196: Flex String PO Promise 2 VARCHAR(255)
	private String flexStringPOPromise2;

	// 197: Flex String PO Promise 3 VARCHAR(255)
	private String flexStringPOPromise3;

	// 198: Flex String PO Promise 4 VARCHAR(128)
	private String flexStringPOPromise4;

	// 199: Flex String PO Promise 5 VARCHAR(128)
	private String flexStringPOPromise5;

	// 200: Flex String PO Promise 6 VARCHAR(128)
	private String flexStringPOPromise6;

	// 201: Flex String PO Promise 7 VARCHAR(64)
	private String flexStringPOPromise7;

	// 202: Flex String PO Promise 8 VARCHAR(64)
	private String flexStringPOPromise8;

	// 203: Flex String PO Promise 9 VARCHAR(64)
	private String flexStringPOPromise9;

	// 204: Flex Int PO Promise 1 NUMBER
	private int FlexIntPOPromise1;

	// 205: Flex Int PO Promise 2 NUMBER
	private int FlexIntPOPromise2;

	// 206: Flex Int PO Promise 3 NUMBER
	private int flexIntPOPromise3;

	// 207: Flex Int PO Promise 4 NUMBER
	private int flexIntPOPromise4;

	// 208: Flex Int PO Promise 5 NUMBER
	private int flexIntPOPromise5;

	// 209: Flex double PO Promise 1 double
	private double flex_Float_PO_Promise1;

	// 210: Flex double PO Promise 2 double
	private double flexFloatPOPromise2;

	// 211: Flex double PO Promise 3 double
	private double flexFloatPOPromise3;

	// 212: Flex double PO Promise 4 double
	private double flexFloatPOPromise4;

	// 213: Flex double PO Promise 5 double
	private double flexFloatPOPromise5;

	// 214: Flex Date PO Promise 1 Date
	private Date flexDatePOPromise1;

	// 215: Flex Date PO Promise 2 Date
	private Date flexDatePOPromise2;

	// 216: Flex Date PO Promise 3 Date
	private Date flexDatePOPromise3;

	// 217: Flex Date PO Promise 4 Date
	private Date flexDatePOPromise4;

	// 218: Flex Date PO Promise 5 Date
	private Date flexDatePOPromise5;
	
	// 219: Rev # VARCHAR(64)
	private String rev;

	// 220: Ship To Customer ID VARCHAR(64)
	private String shipToCustomerID;

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getOrderCreationDate() {
		return OrderCreationDate;
	}

	public void setOrderCreationDate(Date orderCreationDate) {
		OrderCreationDate = orderCreationDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderPriority() {
		return orderPriority;
	}

	public void setOrderPriority(String orderPriority) {
		this.orderPriority = orderPriority;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}

	public String getCustomerDUNS() {
		return customerDUNS;
	}

	public void setCustomerDUNS(String customerDUNS) {
		this.customerDUNS = customerDUNS;
	}

	public String getCustomerDUNS4() {
		return customerDUNS4;
	}

	public void setCustomerDUNS4(String customerDUNS4) {
		this.customerDUNS4 = customerDUNS4;
	}

	public String getCustomerTaxNumber() {
		return customerTaxNumber;
	}

	public void setCustomerTaxNumber(String customerTaxNumber) {
		this.customerTaxNumber = customerTaxNumber;
	}

	public String getCustomerAddressDescriptor() {
		return customerAddressDescriptor;
	}

	public void setCustomerAddressDescriptor(String customerAddressDescriptor) {
		this.customerAddressDescriptor = customerAddressDescriptor;
	}

	public String getCustomerAddress1() {
		return customerAddress1;
	}

	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}

	public String getCustomerAddress2() {
		return customerAddress2;
	}

	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}

	public String getCustomerAddress3() {
		return customerAddress3;
	}

	public void setCustomerAddress3(String customerAddress3) {
		this.customerAddress3 = customerAddress3;
	}

	public String getCustomerAddress4() {
		return customerAddress4;
	}

	public void setCustomerAddress4(String customerAddress4) {
		this.customerAddress4 = customerAddress4;
	}

	public String getCustomerAddress5() {
		return customerAddress5;
	}

	public void setCustomerAddress5(String customerAddress5) {
		this.customerAddress5 = customerAddress5;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerCounty() {
		return customerCounty;
	}

	public void setCustomerCounty(String customerCounty) {
		this.customerCounty = customerCounty;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerCountry() {
		return customerCountry;
	}

	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	public String getCustomerZip() {
		return customerZip;
	}

	public void setCustomerZip(String customerZip) {
		this.customerZip = customerZip;
	}

	public String getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(String supplierID) {
		SupplierID = supplierID;
	}

	public String getSupplierDescription() {
		return supplierDescription;
	}

	public void setSupplierDescription(String supplierDescription) {
		this.supplierDescription = supplierDescription;
	}

	public String getSupplierDUNS() {
		return supplierDUNS;
	}

	public void setSupplierDUNS(String supplierDUNS) {
		this.supplierDUNS = supplierDUNS;
	}

	public String getSupplierDUNS4() {
		return supplierDUNS4;
	}

	public void setSupplierDUNS4(String supplierDUNS4) {
		this.supplierDUNS4 = supplierDUNS4;
	}

	public String getSupplierAddressDescriptor() {
		return supplierAddressDescriptor;
	}

	public void setSupplierAddressDescriptor(String supplierAddressDescriptor) {
		this.supplierAddressDescriptor = supplierAddressDescriptor;
	}

	public String getSupplierAddress1() {
		return supplierAddress1;
	}

	public void setSupplierAddress1(String supplierAddress1) {
		this.supplierAddress1 = supplierAddress1;
	}

	public String getSupplierAddress2() {
		return supplierAddress2;
	}

	public void setSupplierAddress2(String supplierAddress2) {
		this.supplierAddress2 = supplierAddress2;
	}

	public String getSupplierAddress3() {
		return supplierAddress3;
	}

	public void setSupplierAddress3(String supplierAddress3) {
		this.supplierAddress3 = supplierAddress3;
	}

	public String getSupplierAddress4() {
		return supplierAddress4;
	}

	public void setSupplierAddress4(String supplierAddress4) {
		this.supplierAddress4 = supplierAddress4;
	}

	public String getSupplierAddress5() {
		return supplierAddress5;
	}

	public void setSupplierAddress5(String supplierAddress5) {
		this.supplierAddress5 = supplierAddress5;
	}

	public String getSupplierCity() {
		return supplierCity;
	}

	public void setSupplierCity(String supplierCity) {
		this.supplierCity = supplierCity;
	}

	public String getSupplierCounty() {
		return supplierCounty;
	}

	public void setSupplierCounty(String supplierCounty) {
		this.supplierCounty = supplierCounty;
	}

	public String getSupplierState() {
		return supplierState;
	}

	public void setSupplierState(String supplierState) {
		this.supplierState = supplierState;
	}

	public String getSupplierCountry() {
		return supplierCountry;
	}

	public void setSupplierCountry(String supplierCountry) {
		this.supplierCountry = supplierCountry;
	}

	public String getSupplierZip() {
		return supplierZip;
	}

	public void setSupplierZip(String supplierZip) {
		this.supplierZip = supplierZip;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getBuyerContact() {
		return buyerContact;
	}

	public void setBuyerContact(String buyerContact) {
		this.buyerContact = buyerContact;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getDeliveryTerm() {
		return deliveryTerm;
	}

	public void setDeliveryTerm(String deliveryTerm) {
		this.deliveryTerm = deliveryTerm;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public double getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public String getIncoTerms() {
		return incoTerms;
	}

	public void setIncoTerms(String incoTerms) {
		this.incoTerms = incoTerms;
	}

	public String getCustomerOrderNotes() {
		return customerOrderNotes;
	}

	public void setCustomerOrderNotes(String customerOrderNotes) {
		this.customerOrderNotes = customerOrderNotes;
	}

	public String getSupplierOrderNotes() {
		return supplierOrderNotes;
	}

	public void setSupplierOrderNotes(String supplierOrderNotes) {
		this.supplierOrderNotes = supplierOrderNotes;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getBillToAddressDescriptor() {
		return billToAddressDescriptor;
	}

	public void setBillToAddressDescriptor(String billToAddressDescriptor) {
		this.billToAddressDescriptor = billToAddressDescriptor;
	}

	public String getBillToAddress1() {
		return billToAddress1;
	}

	public void setBillToAddress1(String billToAddress1) {
		this.billToAddress1 = billToAddress1;
	}

	public String getBillToAddress2() {
		return billToAddress2;
	}

	public void setBillToAddress2(String billToAddress2) {
		this.billToAddress2 = billToAddress2;
	}

	public String getBillToAddress3() {
		return billToAddress3;
	}

	public void setBillToAddress3(String billToAddress3) {
		this.billToAddress3 = billToAddress3;
	}

	public String getBillToAddress4() {
		return billToAddress4;
	}

	public void setBillToAddress4(String billToAddress4) {
		this.billToAddress4 = billToAddress4;
	}

	public String getBillToAddress5() {
		return billToAddress5;
	}

	public void setBillToAddress5(String billToAddress5) {
		this.billToAddress5 = billToAddress5;
	}

	public String getBillToCity() {
		return billToCity;
	}

	public void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}

	public String getBillToCounty() {
		return billToCounty;
	}

	public void setBillToCounty(String billToCounty) {
		this.billToCounty = billToCounty;
	}

	public String getBillToState() {
		return billToState;
	}

	public void setBillToState(String billToState) {
		this.billToState = billToState;
	}

	public String getBillToCountry() {
		return billToCountry;
	}

	public void setBillToCountry(String billToCountry) {
		this.billToCountry = billToCountry;
	}

	public String getBillToZip() {
		return billToZip;
	}

	public void setBillToZip(String billToZip) {
		this.billToZip = billToZip;
	}

	public String getRemitToAddressDescriptor() {
		return remitToAddressDescriptor;
	}

	public void setRemitToAddressDescriptor(String remitToAddressDescriptor) {
		this.remitToAddressDescriptor = remitToAddressDescriptor;
	}

	public String getRemitToAddress1() {
		return remitToAddress1;
	}

	public void setRemitToAddress1(String remitToAddress1) {
		this.remitToAddress1 = remitToAddress1;
	}

	public String getRemitToAddress2() {
		return remitToAddress2;
	}

	public void setRemitToAddress2(String remitToAddress2) {
		this.remitToAddress2 = remitToAddress2;
	}

	public String getRemitToAddress3() {
		return remitToAddress3;
	}

	public void setRemitToAddress3(String remitToAddress3) {
		this.remitToAddress3 = remitToAddress3;
	}

	public String getRemitToAddress4() {
		return remitToAddress4;
	}

	public void setRemitToAddress4(String remitToAddress4) {
		this.remitToAddress4 = remitToAddress4;
	}

	public String getRemitToAddress5() {
		return remitToAddress5;
	}

	public void setRemitToAddress5(String remitToAddress5) {
		this.remitToAddress5 = remitToAddress5;
	}

	public String getRemitToCity() {
		return remitToCity;
	}

	public void setRemitToCity(String remitToCity) {
		this.remitToCity = remitToCity;
	}

	public String getRemitToCounty() {
		return remitToCounty;
	}

	public void setRemitToCounty(String remitToCounty) {
		this.remitToCounty = remitToCounty;
	}

	public String getRemitToState() {
		return remitToState;
	}

	public void setRemitToState(String remitToState) {
		this.remitToState = remitToState;
	}

	public String getRemitToCountry() {
		return remitToCountry;
	}

	public void setRemitToCountry(String remitToCountry) {
		this.remitToCountry = remitToCountry;
	}

	public String getRemitToZip() {
		return remitToZip;
	}

	public void setRemitToZip(String remitToZip) {
		this.remitToZip = remitToZip;
	}

	public String getBuyerContactPhone() {
		return buyerContactPhone;
	}

	public void setBuyerContactPhone(String buyerContactPhone) {
		this.buyerContactPhone = buyerContactPhone;
	}

	public String getBuyerContactFax() {
		return buyerContactFax;
	}

	public void setBuyerContactFax(String buyerContactFax) {
		this.buyerContactFax = buyerContactFax;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getFlexStringPOHeader4() {
		return flexStringPOHeader4;
	}

	public void setFlexStringPOHeader4(String flexStringPOHeader4) {
		this.flexStringPOHeader4 = flexStringPOHeader4;
	}

	public String getFlexStringPOHeader5() {
		return flexStringPOHeader5;
	}

	public void setFlexStringPOHeader5(String flexStringPOHeader5) {
		this.flexStringPOHeader5 = flexStringPOHeader5;
	}

	public String getFlexStringPOHeader6() {
		return flexStringPOHeader6;
	}

	public void setFlexStringPOHeader6(String flexStringPOHeader6) {
		this.flexStringPOHeader6 = flexStringPOHeader6;
	}

	public String getFlexStringPOHeader7() {
		return flexStringPOHeader7;
	}

	public void setFlexStringPOHeader7(String flexStringPOHeader7) {
		this.flexStringPOHeader7 = flexStringPOHeader7;
	}

	public String getFlexStringPOHeader8() {
		return flexStringPOHeader8;
	}

	public void setFlexStringPOHeader8(String flexStringPOHeader8) {
		this.flexStringPOHeader8 = flexStringPOHeader8;
	}

	public String getFlexStringPOHeader9() {
		return flexStringPOHeader9;
	}

	public void setFlexStringPOHeader9(String flexStringPOHeader9) {
		this.flexStringPOHeader9 = flexStringPOHeader9;
	}

	public int getFlexIntPOHeader1() {
		return flexIntPOHeader1;
	}

	public void setFlexIntPOHeader1(int flexIntPOHeader1) {
		this.flexIntPOHeader1 = flexIntPOHeader1;
	}

	public int getFlexIntPOHeader2() {
		return flexIntPOHeader2;
	}

	public void setFlexIntPOHeader2(int flexIntPOHeader2) {
		this.flexIntPOHeader2 = flexIntPOHeader2;
	}

	public int getFlexIntPOHeader3() {
		return flexIntPOHeader3;
	}

	public void setFlexIntPOHeader3(int flexIntPOHeader3) {
		this.flexIntPOHeader3 = flexIntPOHeader3;
	}

	public int getFlexIntPOHeader4() {
		return flexIntPOHeader4;
	}

	public void setFlexIntPOHeader4(int flexIntPOHeader4) {
		this.flexIntPOHeader4 = flexIntPOHeader4;
	}

	public int getFlexIntPOHeader5() {
		return flexIntPOHeader5;
	}

	public void setFlexIntPOHeader5(int flexIntPOHeader5) {
		this.flexIntPOHeader5 = flexIntPOHeader5;
	}

	public double getFlexFloatPOHeader1() {
		return flexFloatPOHeader1;
	}

	public void setFlexFloatPOHeader1(double flexFloatPOHeader1) {
		this.flexFloatPOHeader1 = flexFloatPOHeader1;
	}

	public double getFlexFloatPOHeader2() {
		return flexFloatPOHeader2;
	}

	public void setFlexFloatPOHeader2(double flexFloatPOHeader2) {
		this.flexFloatPOHeader2 = flexFloatPOHeader2;
	}

	public double getFlexFloatPOHeader3() {
		return flexFloatPOHeader3;
	}

	public void setFlexFloatPOHeader3(double flexFloatPOHeader3) {
		this.flexFloatPOHeader3 = flexFloatPOHeader3;
	}

	public double getFlexFloatPOHeader4() {
		return flexFloatPOHeader4;
	}

	public void setFlexFloatPOHeader4(double flexFloatPOHeader4) {
		this.flexFloatPOHeader4 = flexFloatPOHeader4;
	}

	public double getFlexFloatPOHeader5() {
		return flexFloatPOHeader5;
	}

	public void setFlexFloatPOHeader5(double flexFloatPOHeader5) {
		this.flexFloatPOHeader5 = flexFloatPOHeader5;
	}

	public Date getFlexDatePOHeader1() {
		return flexDatePOHeader1;
	}

	public void setFlexDatePOHeader1(Date flexDatePOHeader1) {
		this.flexDatePOHeader1 = flexDatePOHeader1;
	}

	public Date getFlexDatePOHeader2() {
		return flexDatePOHeader2;
	}

	public void setFlexDatePOHeader2(Date flexDatePOHeader2) {
		this.flexDatePOHeader2 = flexDatePOHeader2;
	}

	public Date getFlexDatePOHeader3() {
		return flexDatePOHeader3;
	}

	public void setFlexDatePOHeader3(Date flexDatePOHeader3) {
		this.flexDatePOHeader3 = flexDatePOHeader3;
	}

	public Date getFlexDatePOHeader4() {
		return flexDatePOHeader4;
	}

	public void setFlexDatePOHeader4(Date flexDatePOHeader4) {
		this.flexDatePOHeader4 = flexDatePOHeader4;
	}

	public Date getFlexDatePOHeader5() {
		return flexDatePOHeader5;
	}

	public void setFlexDatePOHeader5(Date flexDatePOHeader5) {
		this.flexDatePOHeader5 = flexDatePOHeader5;
	}

	public int getLineID() {
		return LineID;
	}

	public void setLineID(int lineID) {
		LineID = lineID;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getCustomerItemID() {
		return CustomerItemID;
	}

	public void setCustomerItemID(String customerItemID) {
		CustomerItemID = customerItemID;
	}

	public String getCustomerItemDescription() {
		return customerItemDescription;
	}

	public void setCustomerItemDescription(String customerItemDescription) {
		this.customerItemDescription = customerItemDescription;
	}

	public String getSupplierItemID() {
		return supplierItemID;
	}

	public void setSupplierItemID(String supplierItemID) {
		this.supplierItemID = supplierItemID;
	}

	public String getSupplierItemDescription() {
		return supplierItemDescription;
	}

	public void setSupplierItemDescription(String supplierItemDescription) {
		this.supplierItemDescription = supplierItemDescription;
	}

	public double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}

	public String getPriceBasis() {
		return priceBasis;
	}

	public void setPriceBasis(String priceBasis) {
		this.priceBasis = priceBasis;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public double getTotalLineAmount() {
		return totalLineAmount;
	}

	public void setTotalLineAmount(double totalLineAmount) {
		this.totalLineAmount = totalLineAmount;
	}

	public String getuOM() {
		return uOM;
	}

	public void setuOM(String uOM) {
		this.uOM = uOM;
	}

	public String getCustomerOrderLineNotes() {
		return customerOrderLineNotes;
	}

	public void setCustomerOrderLineNotes(String customerOrderLineNotes) {
		this.customerOrderLineNotes = customerOrderLineNotes;
	}

	public String getSupplierOrderLineNotes() {
		return supplierOrderLineNotes;
	}

	public void setSupplierOrderLineNotes(String supplierOrderLineNotes) {
		this.supplierOrderLineNotes = supplierOrderLineNotes;
	}

	public String getDrawingVersion() {
		return drawingVersion;
	}

	public void setDrawingVersion(String drawingVersion) {
		this.drawingVersion = drawingVersion;
	}

	public String getDrawingNumber() {
		return drawingNumber;
	}

	public void setDrawingNumber(String drawingNumber) {
		this.drawingNumber = drawingNumber;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getShipToLocation() {
		return shipToLocation;
	}

	public void setShipToLocation(String shipToLocation) {
		this.shipToLocation = shipToLocation;
	}

	public String getFlexStringPOLine5() {
		return flexStringPOLine5;
	}

	public void setFlexStringPOLine5(String flexStringPOLine5) {
		this.flexStringPOLine5 = flexStringPOLine5;
	}

	public String getFlexStringPOLine6() {
		return flexStringPOLine6;
	}

	public void setFlexStringPOLine6(String flexStringPOLine6) {
		this.flexStringPOLine6 = flexStringPOLine6;
	}

	public String getFlexStringPOLine7() {
		return flexStringPOLine7;
	}

	public void setFlexStringPOLine7(String flexStringPOLine7) {
		this.flexStringPOLine7 = flexStringPOLine7;
	}

	public String getFlexStringPOLine8() {
		return flexStringPOLine8;
	}

	public void setFlexStringPOLine8(String flexStringPOLine8) {
		this.flexStringPOLine8 = flexStringPOLine8;
	}

	public String getFlexStringPOLine9() {
		return flexStringPOLine9;
	}

	public void setFlexStringPOLine9(String flexStringPOLine9) {
		this.flexStringPOLine9 = flexStringPOLine9;
	}

	public int getFreeItemFlag() {
		return freeItemFlag;
	}

	public void setFreeItemFlag(int freeItemFlag) {
		this.freeItemFlag = freeItemFlag;
	}

	public int getFlexIntPOLine2() {
		return flexIntPOLine2;
	}

	public void setFlexIntPOLine2(int flexIntPOLine2) {
		this.flexIntPOLine2 = flexIntPOLine2;
	}

	public int getFlexIntPOLine3() {
		return flexIntPOLine3;
	}

	public void setFlexIntPOLine3(int flexIntPOLine3) {
		this.flexIntPOLine3 = flexIntPOLine3;
	}

	public int getFlexIntPOLine4() {
		return flexIntPOLine4;
	}

	public void setFlexIntPOLine4(int flexIntPOLine4) {
		this.flexIntPOLine4 = flexIntPOLine4;
	}

	public int getFlexIntPOLine5() {
		return flexIntPOLine5;
	}

	public void setFlexIntPOLine5(int flexIntPOLine5) {
		this.flexIntPOLine5 = flexIntPOLine5;
	}

	public double getFlexFloatPOLine1() {
		return flexFloatPOLine1;
	}

	public void setFlexFloatPOLine1(double flexFloatPOLine1) {
		this.flexFloatPOLine1 = flexFloatPOLine1;
	}

	public double getFlexFloatPOLine2() {
		return flexFloatPOLine2;
	}

	public void setFlexFloatPOLine2(double flexFloatPOLine2) {
		this.flexFloatPOLine2 = flexFloatPOLine2;
	}

	public double getFlexFloatPOLine3() {
		return flexFloatPOLine3;
	}

	public void setFlexFloatPOLine3(double flexFloatPOLine3) {
		this.flexFloatPOLine3 = flexFloatPOLine3;
	}

	public double getFlexFloatPOLine4() {
		return flexFloatPOLine4;
	}

	public void setFlexFloatPOLine4(double flexFloatPOLine4) {
		this.flexFloatPOLine4 = flexFloatPOLine4;
	}

	public double getFlexFloatPOLine5() {
		return flexFloatPOLine5;
	}

	public void setFlexFloatPOLine5(double flexFloatPOLine5) {
		this.flexFloatPOLine5 = flexFloatPOLine5;
	}

	public Date getFlexDatePOLine1() {
		return flexDatePOLine1;
	}

	public void setFlexDatePOLine1(Date flexDatePOLine1) {
		this.flexDatePOLine1 = flexDatePOLine1;
	}

	public Date getFlexDatePOLine2() {
		return flexDatePOLine2;
	}

	public void setFlexDatePOLine2(Date flexDatePOLine2) {
		this.flexDatePOLine2 = flexDatePOLine2;
	}

	public Date getFlexDatePOLine3() {
		return flexDatePOLine3;
	}

	public void setFlexDatePOLine3(Date flexDatePOLine3) {
		this.flexDatePOLine3 = flexDatePOLine3;
	}

	public Date getFlexDatePOLine4() {
		return flexDatePOLine4;
	}

	public void setFlexDatePOLine4(Date flexDatePOLine4) {
		this.flexDatePOLine4 = flexDatePOLine4;
	}

	public Date getFlexDatePOLine5() {
		return flexDatePOLine5;
	}

	public void setFlexDatePOLine5(Date flexDatePOLine5) {
		this.flexDatePOLine5 = flexDatePOLine5;
	}

	public int getRequestID() {
		return RequestID;
	}

	public void setRequestID(int requestID) {
		RequestID = requestID;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public double getRequestQty() {
		return RequestQty;
	}

	public void setRequestQty(double requestQty) {
		RequestQty = requestQty;
	}

	public Date getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(Date requestDate) {
		RequestDate = requestDate;
	}

	public Date getRequestedShipDate() {
		return requestedShipDate;
	}

	public void setRequestedShipDate(Date requestedShipDate) {
		this.requestedShipDate = requestedShipDate;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCustomerSite() {
		return CustomerSite;
	}

	public void setCustomerSite(String customerSite) {
		CustomerSite = customerSite;
	}

	public String getShipToAddressDescriptor() {
		return shipToAddressDescriptor;
	}

	public void setShipToAddressDescriptor(String shipToAddressDescriptor) {
		this.shipToAddressDescriptor = shipToAddressDescriptor;
	}

	public String getShipToAddress1() {
		return shipToAddress1;
	}

	public void setShipToAddress1(String shipToAddress1) {
		this.shipToAddress1 = shipToAddress1;
	}

	public String getShipToAddress2() {
		return shipToAddress2;
	}

	public void setShipToAddress2(String shipToAddress2) {
		this.shipToAddress2 = shipToAddress2;
	}

	public String getShipToAddress3() {
		return shipToAddress3;
	}

	public void setShipToAddress3(String shipToAddress3) {
		this.shipToAddress3 = shipToAddress3;
	}

	public String getShipToAddress4() {
		return shipToAddress4;
	}

	public void setShipToAddress4(String shipToAddress4) {
		this.shipToAddress4 = shipToAddress4;
	}

	public String getShipToAddress5() {
		return shipToAddress5;
	}

	public void setShipToAddress5(String shipToAddress5) {
		this.shipToAddress5 = shipToAddress5;
	}

	public String getShipToCity() {
		return shipToCity;
	}

	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}

	public String getShipToCounty() {
		return shipToCounty;
	}

	public void setShipToCounty(String shipToCounty) {
		this.shipToCounty = shipToCounty;
	}

	public String getShipToState() {
		return shipToState;
	}

	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}

	public String getShipToCountry() {
		return shipToCountry;
	}

	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}

	public String getShipToZip() {
		return shipToZip;
	}

	public void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}

	public String getRefOrderType() {
		return refOrderType;
	}

	public void setRefOrderType(String refOrderType) {
		this.refOrderType = refOrderType;
	}

	public String getRefOrderID() {
		return refOrderID;
	}

	public void setRefOrderID(String refOrderID) {
		this.refOrderID = refOrderID;
	}

	public String getRefOrderLineID() {
		return refOrderLineID;
	}

	public void setRefOrderLineID(String refOrderLineID) {
		this.refOrderLineID = refOrderLineID;
	}

	public String getRefOrderRequestID() {
		return refOrderRequestID;
	}

	public void setRefOrderRequestID(String refOrderRequestID) {
		this.refOrderRequestID = refOrderRequestID;
	}

	public String getRefCustomerID() {
		return refCustomerID;
	}

	public void setRefCustomerID(String refCustomerID) {
		this.refCustomerID = refCustomerID;
	}

	public String getRefSupplierID() {
		return refSupplierID;
	}

	public void setRefSupplierID(String refSupplierID) {
		this.refSupplierID = refSupplierID;
	}

	public String getFlexStringPORequest1() {
		return flexStringPORequest1;
	}

	public void setFlexStringPORequest1(String flexStringPORequest1) {
		this.flexStringPORequest1 = flexStringPORequest1;
	}

	public String getFlexStringPORequest2() {
		return flexStringPORequest2;
	}

	public void setFlexStringPORequest2(String flexStringPORequest2) {
		this.flexStringPORequest2 = flexStringPORequest2;
	}

	public String getFlexStringPORequest3() {
		return flexStringPORequest3;
	}

	public void setFlexStringPORequest3(String flexStringPORequest3) {
		this.flexStringPORequest3 = flexStringPORequest3;
	}

	public String getFlexStringPORequest4() {
		return flexStringPORequest4;
	}

	public void setFlexStringPORequest4(String flexStringPORequest4) {
		this.flexStringPORequest4 = flexStringPORequest4;
	}

	public String getFlexStringPORequest5() {
		return flexStringPORequest5;
	}

	public void setFlexStringPORequest5(String flexStringPORequest5) {
		this.flexStringPORequest5 = flexStringPORequest5;
	}

	public String getFlexStringPORequest6() {
		return flexStringPORequest6;
	}

	public void setFlexStringPORequest6(String flexStringPORequest6) {
		this.flexStringPORequest6 = flexStringPORequest6;
	}

	public String getFlexStringPORequest7() {
		return flexStringPORequest7;
	}

	public void setFlexStringPORequest7(String flexStringPORequest7) {
		this.flexStringPORequest7 = flexStringPORequest7;
	}

	public String getFlexStringPORequest8() {
		return flexStringPORequest8;
	}

	public void setFlexStringPORequest8(String flexStringPORequest8) {
		this.flexStringPORequest8 = flexStringPORequest8;
	}

	public String getFlexStringPORequest9() {
		return flexStringPORequest9;
	}

	public void setFlexStringPORequest9(String flexStringPORequest9) {
		this.flexStringPORequest9 = flexStringPORequest9;
	}

	public int getFlexIntPORequest1() {
		return flexIntPORequest1;
	}

	public void setFlexIntPORequest1(int flexIntPORequest1) {
		this.flexIntPORequest1 = flexIntPORequest1;
	}

	public int getFlexIntPORequest2() {
		return flexIntPORequest2;
	}

	public void setFlexIntPORequest2(int flexIntPORequest2) {
		this.flexIntPORequest2 = flexIntPORequest2;
	}

	public int getFlexIntPORequest3() {
		return flexIntPORequest3;
	}

	public void setFlexIntPORequest3(int flexIntPORequest3) {
		this.flexIntPORequest3 = flexIntPORequest3;
	}

	public int getFlexIntPORequest4() {
		return flexIntPORequest4;
	}

	public void setFlexIntPORequest4(int flexIntPORequest4) {
		this.flexIntPORequest4 = flexIntPORequest4;
	}

	public int getFlexIntPORequest5() {
		return flexIntPORequest5;
	}

	public void setFlexIntPORequest5(int flexIntPORequest5) {
		this.flexIntPORequest5 = flexIntPORequest5;
	}

	public double getFlexFloatPORequest1() {
		return flexFloatPORequest1;
	}

	public void setFlexFloatPORequest1(double flexFloatPORequest1) {
		this.flexFloatPORequest1 = flexFloatPORequest1;
	}

	public double getFlexFloatPORequest2() {
		return flexFloatPORequest2;
	}

	public void setFlexFloatPORequest2(double flexFloatPORequest2) {
		this.flexFloatPORequest2 = flexFloatPORequest2;
	}

	public double getFlexFloatPORequest3() {
		return flexFloatPORequest3;
	}

	public void setFlexFloatPORequest3(double flexFloatPORequest3) {
		this.flexFloatPORequest3 = flexFloatPORequest3;
	}

	public double getFlexFloatPORequest4() {
		return flexFloatPORequest4;
	}

	public void setFlexFloatPORequest4(double flexFloatPORequest4) {
		this.flexFloatPORequest4 = flexFloatPORequest4;
	}

	public double getFlexFloatPORequest5() {
		return flexFloatPORequest5;
	}

	public void setFlexFloatPORequest5(double flexFloatPORequest5) {
		this.flexFloatPORequest5 = flexFloatPORequest5;
	}

	public Date getFlexDatePORequest1() {
		return flexDatePORequest1;
	}

	public void setFlexDatePORequest1(Date flexDatePORequest1) {
		this.flexDatePORequest1 = flexDatePORequest1;
	}

	public Date getFlexDatePORequest2() {
		return flexDatePORequest2;
	}

	public void setFlexDatePORequest2(Date flexDatePORequest2) {
		this.flexDatePORequest2 = flexDatePORequest2;
	}

	public Date getFlexDatePORequest3() {
		return flexDatePORequest3;
	}

	public void setFlexDatePORequest3(Date flexDatePORequest3) {
		this.flexDatePORequest3 = flexDatePORequest3;
	}

	public Date getFlexDatePORequest4() {
		return flexDatePORequest4;
	}

	public void setFlexDatePORequest4(Date flexDatePORequest4) {
		this.flexDatePORequest4 = flexDatePORequest4;
	}

	public Date getFlexDatePORequest5() {
		return flexDatePORequest5;
	}

	public void setFlexDatePORequest5(Date flexDatePORequest5) {
		this.flexDatePORequest5 = flexDatePORequest5;
	}

	public int getPromiseID() {
		return promiseID;
	}

	public void setPromiseID(int promiseID) {
		this.promiseID = promiseID;
	}

	public double getPromiseQty() {
		return promiseQty;
	}

	public void setPromiseQty(double promiseQty) {
		this.promiseQty = promiseQty;
	}

	public Date getPromiseDate() {
		return promiseDate;
	}

	public void setPromiseDate(Date promiseDate) {
		this.promiseDate = promiseDate;
	}

	public Date getPromisedShipDate() {
		return promisedShipDate;
	}

	public void setPromisedShipDate(Date promisedShipDate) {
		this.promisedShipDate = promisedShipDate;
	}

	public String getSupplierSite() {
		return supplierSite;
	}

	public void setSupplierSite(String supplierSite) {
		this.supplierSite = supplierSite;
	}

	public String getShipFromAddressDescriptor() {
		return shipFromAddressDescriptor;
	}

	public void setShipFromAddressDescriptor(String shipFromAddressDescriptor) {
		this.shipFromAddressDescriptor = shipFromAddressDescriptor;
	}

	public String getShipFromAddress1() {
		return shipFromAddress1;
	}

	public void setShipFromAddress1(String shipFromAddress1) {
		this.shipFromAddress1 = shipFromAddress1;
	}

	public String getShipFromAddress2() {
		return shipFromAddress2;
	}

	public void setShipFromAddress2(String shipFromAddress2) {
		this.shipFromAddress2 = shipFromAddress2;
	}

	public String getShipFromAddress3() {
		return shipFromAddress3;
	}

	public void setShipFromAddress3(String shipFromAddress3) {
		this.shipFromAddress3 = shipFromAddress3;
	}

	public String getShipFromAddress4() {
		return shipFromAddress4;
	}

	public void setShipFromAddress4(String shipFromAddress4) {
		this.shipFromAddress4 = shipFromAddress4;
	}

	public String getShipFromAddress5() {
		return shipFromAddress5;
	}

	public void setShipFromAddress5(String shipFromAddress5) {
		this.shipFromAddress5 = shipFromAddress5;
	}

	public String getShipFromCity() {
		return shipFromCity;
	}

	public void setShipFromCity(String shipFromCity) {
		this.shipFromCity = shipFromCity;
	}

	public String getShipFromCounty() {
		return shipFromCounty;
	}

	public void setShipFromCounty(String shipFromCounty) {
		this.shipFromCounty = shipFromCounty;
	}

	public String getShipFromState() {
		return shipFromState;
	}

	public void setShipFromState(String shipFromState) {
		this.shipFromState = shipFromState;
	}

	public String getShipFromCountry() {
		return shipFromCountry;
	}

	public void setShipFromCountry(String shipFromCountry) {
		this.shipFromCountry = shipFromCountry;
	}

	public String getShipFromZip() {
		return shipFromZip;
	}

	public void setShipFromZip(String shipFromZip) {
		this.shipFromZip = shipFromZip;
	}

	public String getFlexStringPOPromise1() {
		return flexStringPOPromise1;
	}

	public void setFlexStringPOPromise1(String flexStringPOPromise1) {
		this.flexStringPOPromise1 = flexStringPOPromise1;
	}

	public String getFlexStringPOPromise2() {
		return flexStringPOPromise2;
	}

	public void setFlexStringPOPromise2(String flexStringPOPromise2) {
		this.flexStringPOPromise2 = flexStringPOPromise2;
	}

	public String getFlexStringPOPromise3() {
		return flexStringPOPromise3;
	}

	public void setFlexStringPOPromise3(String flexStringPOPromise3) {
		this.flexStringPOPromise3 = flexStringPOPromise3;
	}

	public String getFlexStringPOPromise4() {
		return flexStringPOPromise4;
	}

	public void setFlexStringPOPromise4(String flexStringPOPromise4) {
		this.flexStringPOPromise4 = flexStringPOPromise4;
	}

	public String getFlexStringPOPromise5() {
		return flexStringPOPromise5;
	}

	public void setFlexStringPOPromise5(String flexStringPOPromise5) {
		this.flexStringPOPromise5 = flexStringPOPromise5;
	}

	public String getFlexStringPOPromise6() {
		return flexStringPOPromise6;
	}

	public void setFlexStringPOPromise6(String flexStringPOPromise6) {
		this.flexStringPOPromise6 = flexStringPOPromise6;
	}

	public String getFlexStringPOPromise7() {
		return flexStringPOPromise7;
	}

	public void setFlexStringPOPromise7(String flexStringPOPromise7) {
		this.flexStringPOPromise7 = flexStringPOPromise7;
	}

	public String getFlexStringPOPromise8() {
		return flexStringPOPromise8;
	}

	public void setFlexStringPOPromise8(String flexStringPOPromise8) {
		this.flexStringPOPromise8 = flexStringPOPromise8;
	}

	public String getFlexStringPOPromise9() {
		return flexStringPOPromise9;
	}

	public void setFlexStringPOPromise9(String flexStringPOPromise9) {
		this.flexStringPOPromise9 = flexStringPOPromise9;
	}

	public int getFlexIntPOPromise1() {
		return FlexIntPOPromise1;
	}

	public void setFlexIntPOPromise1(int flexIntPOPromise1) {
		FlexIntPOPromise1 = flexIntPOPromise1;
	}

	public int getFlexIntPOPromise2() {
		return FlexIntPOPromise2;
	}

	public void setFlexIntPOPromise2(int flexIntPOPromise2) {
		FlexIntPOPromise2 = flexIntPOPromise2;
	}

	public int getFlexIntPOPromise3() {
		return flexIntPOPromise3;
	}

	public void setFlexIntPOPromise3(int flexIntPOPromise3) {
		this.flexIntPOPromise3 = flexIntPOPromise3;
	}

	public int getFlexIntPOPromise4() {
		return flexIntPOPromise4;
	}

	public void setFlexIntPOPromise4(int flexIntPOPromise4) {
		this.flexIntPOPromise4 = flexIntPOPromise4;
	}

	public int getFlexIntPOPromise5() {
		return flexIntPOPromise5;
	}

	public void setFlexIntPOPromise5(int flexIntPOPromise5) {
		this.flexIntPOPromise5 = flexIntPOPromise5;
	}

	public double getFlex_Float_PO_Promise1() {
		return flex_Float_PO_Promise1;
	}

	public void setFlex_Float_PO_Promise1(double flex_Float_PO_Promise1) {
		this.flex_Float_PO_Promise1 = flex_Float_PO_Promise1;
	}

	public double getFlexFloatPOPromise2() {
		return flexFloatPOPromise2;
	}

	public void setFlexFloatPOPromise2(double flexFloatPOPromise2) {
		this.flexFloatPOPromise2 = flexFloatPOPromise2;
	}

	public double getFlexFloatPOPromise3() {
		return flexFloatPOPromise3;
	}

	public void setFlexFloatPOPromise3(double flexFloatPOPromise3) {
		this.flexFloatPOPromise3 = flexFloatPOPromise3;
	}

	public double getFlexFloatPOPromise4() {
		return flexFloatPOPromise4;
	}

	public void setFlexFloatPOPromise4(double flexFloatPOPromise4) {
		this.flexFloatPOPromise4 = flexFloatPOPromise4;
	}

	public double getFlexFloatPOPromise5() {
		return flexFloatPOPromise5;
	}

	public void setFlexFloatPOPromise5(double flexFloatPOPromise5) {
		this.flexFloatPOPromise5 = flexFloatPOPromise5;
	}

	public Date getFlexDatePOPromise1() {
		return flexDatePOPromise1;
	}

	public void setFlexDatePOPromise1(Date flexDatePOPromise1) {
		this.flexDatePOPromise1 = flexDatePOPromise1;
	}

	public Date getFlexDatePOPromise2() {
		return flexDatePOPromise2;
	}

	public void setFlexDatePOPromise2(Date flexDatePOPromise2) {
		this.flexDatePOPromise2 = flexDatePOPromise2;
	}

	public Date getFlexDatePOPromise3() {
		return flexDatePOPromise3;
	}

	public void setFlexDatePOPromise3(Date flexDatePOPromise3) {
		this.flexDatePOPromise3 = flexDatePOPromise3;
	}

	public Date getFlexDatePOPromise4() {
		return flexDatePOPromise4;
	}

	public void setFlexDatePOPromise4(Date flexDatePOPromise4) {
		this.flexDatePOPromise4 = flexDatePOPromise4;
	}

	public Date getFlexDatePOPromise5() {
		return flexDatePOPromise5;
	}

	public void setFlexDatePOPromise5(Date flexDatePOPromise5) {
		this.flexDatePOPromise5 = flexDatePOPromise5;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public String getShipToCustomerID() {
		return shipToCustomerID;
	}

	public void setShipToCustomerID(String shipToCustomerID) {
		this.shipToCustomerID = shipToCustomerID;
	}

	@Override
	public String toString() {
		return "PoItemsEntity [OrderNumber=" + OrderNumber + ", status=" + status + ", message=" + message + ", date="
				+ date + ", OrderCreationDate=" + OrderCreationDate + ", orderStatus=" + orderStatus
				+ ", orderPriority=" + orderPriority + ", CustomerID=" + CustomerID + ", customerDescription="
				+ customerDescription + ", customerDUNS=" + customerDUNS + ", customerDUNS4=" + customerDUNS4
				+ ", customerTaxNumber=" + customerTaxNumber + ", customerAddressDescriptor="
				+ customerAddressDescriptor + ", customerAddress1=" + customerAddress1 + ", customerAddress2="
				+ customerAddress2 + ", customerAddress3=" + customerAddress3 + ", customerAddress4=" + customerAddress4
				+ ", customerAddress5=" + customerAddress5 + ", customerCity=" + customerCity + ", customerCounty="
				+ customerCounty + ", customerState=" + customerState + ", customerCountry=" + customerCountry
				+ ", customerZip=" + customerZip + ", SupplierID=" + SupplierID + ", supplierDescription="
				+ supplierDescription + ", supplierDUNS=" + supplierDUNS + ", supplierDUNS4=" + supplierDUNS4
				+ ", supplierAddressDescriptor=" + supplierAddressDescriptor + ", supplierAddress1=" + supplierAddress1
				+ ", supplierAddress2=" + supplierAddress2 + ", supplierAddress3=" + supplierAddress3
				+ ", supplierAddress4=" + supplierAddress4 + ", supplierAddress5=" + supplierAddress5
				+ ", supplierCity=" + supplierCity + ", supplierCounty=" + supplierCounty + ", supplierState="
				+ supplierState + ", supplierCountry=" + supplierCountry + ", supplierZip=" + supplierZip
				+ ", buyerCode=" + buyerCode + ", buyerContact=" + buyerContact + ", buyerName=" + buyerName
				+ ", buyerEmail=" + buyerEmail + ", supplierEmail=" + supplierEmail + ", deliveryTerm=" + deliveryTerm
				+ ", paymentTerms=" + paymentTerms + ", totalOrderAmount=" + totalOrderAmount + ", incoTerms="
				+ incoTerms + ", customerOrderNotes=" + customerOrderNotes + ", supplierOrderNotes="
				+ supplierOrderNotes + ", billTo=" + billTo + ", billToAddressDescriptor=" + billToAddressDescriptor
				+ ", billToAddress1=" + billToAddress1 + ", billToAddress2=" + billToAddress2 + ", billToAddress3="
				+ billToAddress3 + ", billToAddress4=" + billToAddress4 + ", billToAddress5=" + billToAddress5
				+ ", billToCity=" + billToCity + ", billToCounty=" + billToCounty + ", billToState=" + billToState
				+ ", billToCountry=" + billToCountry + ", billToZip=" + billToZip + ", remitToAddressDescriptor="
				+ remitToAddressDescriptor + ", remitToAddress1=" + remitToAddress1 + ", remitToAddress2="
				+ remitToAddress2 + ", remitToAddress3=" + remitToAddress3 + ", remitToAddress4=" + remitToAddress4
				+ ", remitToAddress5=" + remitToAddress5 + ", remitToCity=" + remitToCity + ", remitToCounty="
				+ remitToCounty + ", remitToState=" + remitToState + ", remitToCountry=" + remitToCountry
				+ ", remitToZip=" + remitToZip + ", buyerContactPhone=" + buyerContactPhone + ", buyerContactFax="
				+ buyerContactFax + ", orderType=" + orderType + ", flexStringPOHeader4=" + flexStringPOHeader4
				+ ", flexStringPOHeader5=" + flexStringPOHeader5 + ", flexStringPOHeader6=" + flexStringPOHeader6
				+ ", flexStringPOHeader7=" + flexStringPOHeader7 + ", flexStringPOHeader8=" + flexStringPOHeader8
				+ ", flexStringPOHeader9=" + flexStringPOHeader9 + ", flexIntPOHeader1=" + flexIntPOHeader1
				+ ", flexIntPOHeader2=" + flexIntPOHeader2 + ", flexIntPOHeader3=" + flexIntPOHeader3
				+ ", flexIntPOHeader4=" + flexIntPOHeader4 + ", flexIntPOHeader5=" + flexIntPOHeader5
				+ ", flexFloatPOHeader1=" + flexFloatPOHeader1 + ", flexFloatPOHeader2=" + flexFloatPOHeader2
				+ ", flexFloatPOHeader3=" + flexFloatPOHeader3 + ", flexFloatPOHeader4=" + flexFloatPOHeader4
				+ ", flexFloatPOHeader5=" + flexFloatPOHeader5 + ", flexDatePOHeader1=" + flexDatePOHeader1
				+ ", flexDatePOHeader2=" + flexDatePOHeader2 + ", flexDatePOHeader3=" + flexDatePOHeader3
				+ ", flexDatePOHeader4=" + flexDatePOHeader4 + ", flexDatePOHeader5=" + flexDatePOHeader5 + ", LineID="
				+ LineID + ", lineStatus=" + lineStatus + ", CustomerItemID=" + CustomerItemID
				+ ", customerItemDescription=" + customerItemDescription + ", supplierItemID=" + supplierItemID
				+ ", supplierItemDescription=" + supplierItemDescription + ", UnitPrice=" + UnitPrice + ", priceBasis="
				+ priceBasis + ", paymentCurrency=" + paymentCurrency + ", totalLineAmount=" + totalLineAmount
				+ ", uOM=" + uOM + ", customerOrderLineNotes=" + customerOrderLineNotes + ", supplierOrderLineNotes="
				+ supplierOrderLineNotes + ", drawingVersion=" + drawingVersion + ", drawingNumber=" + drawingNumber
				+ ", itemCategory=" + itemCategory + ", shipToLocation=" + shipToLocation + ", flexStringPOLine5="
				+ flexStringPOLine5 + ", flexStringPOLine6=" + flexStringPOLine6 + ", flexStringPOLine7="
				+ flexStringPOLine7 + ", flexStringPOLine8=" + flexStringPOLine8 + ", flexStringPOLine9="
				+ flexStringPOLine9 + ", freeItemFlag=" + freeItemFlag + ", flexIntPOLine2=" + flexIntPOLine2
				+ ", flexIntPOLine3=" + flexIntPOLine3 + ", flexIntPOLine4=" + flexIntPOLine4 + ", flexIntPOLine5="
				+ flexIntPOLine5 + ", flexFloatPOLine1=" + flexFloatPOLine1 + ", flexFloatPOLine2=" + flexFloatPOLine2
				+ ", flexFloatPOLine3=" + flexFloatPOLine3 + ", flexFloatPOLine4=" + flexFloatPOLine4
				+ ", flexFloatPOLine5=" + flexFloatPOLine5 + ", flexDatePOLine1=" + flexDatePOLine1
				+ ", flexDatePOLine2=" + flexDatePOLine2 + ", flexDatePOLine3=" + flexDatePOLine3 + ", flexDatePOLine4="
				+ flexDatePOLine4 + ", flexDatePOLine5=" + flexDatePOLine5 + ", RequestID=" + RequestID
				+ ", requestStatus=" + requestStatus + ", Action=" + Action + ", RequestQty=" + RequestQty
				+ ", RequestDate=" + RequestDate + ", requestedShipDate=" + requestedShipDate + ", carrier=" + carrier
				+ ", CustomerSite=" + CustomerSite + ", shipToAddressDescriptor=" + shipToAddressDescriptor
				+ ", shipToAddress1=" + shipToAddress1 + ", shipToAddress2=" + shipToAddress2 + ", shipToAddress3="
				+ shipToAddress3 + ", shipToAddress4=" + shipToAddress4 + ", shipToAddress5=" + shipToAddress5
				+ ", shipToCity=" + shipToCity + ", shipToCounty=" + shipToCounty + ", shipToState=" + shipToState
				+ ", shipToCountry=" + shipToCountry + ", shipToZip=" + shipToZip + ", refOrderType=" + refOrderType
				+ ", refOrderID=" + refOrderID + ", refOrderLineID=" + refOrderLineID + ", refOrderRequestID="
				+ refOrderRequestID + ", refCustomerID=" + refCustomerID + ", refSupplierID=" + refSupplierID
				+ ", flexStringPORequest1=" + flexStringPORequest1 + ", flexStringPORequest2=" + flexStringPORequest2
				+ ", flexStringPORequest3=" + flexStringPORequest3 + ", flexStringPORequest4=" + flexStringPORequest4
				+ ", flexStringPORequest5=" + flexStringPORequest5 + ", flexStringPORequest6=" + flexStringPORequest6
				+ ", flexStringPORequest7=" + flexStringPORequest7 + ", flexStringPORequest8=" + flexStringPORequest8
				+ ", flexStringPORequest9=" + flexStringPORequest9 + ", flexIntPORequest1=" + flexIntPORequest1
				+ ", flexIntPORequest2=" + flexIntPORequest2 + ", flexIntPORequest3=" + flexIntPORequest3
				+ ", flexIntPORequest4=" + flexIntPORequest4 + ", flexIntPORequest5=" + flexIntPORequest5
				+ ", flexFloatPORequest1=" + flexFloatPORequest1 + ", flexFloatPORequest2=" + flexFloatPORequest2
				+ ", flexFloatPORequest3=" + flexFloatPORequest3 + ", flexFloatPORequest4=" + flexFloatPORequest4
				+ ", flexFloatPORequest5=" + flexFloatPORequest5 + ", flexDatePORequest1=" + flexDatePORequest1
				+ ", flexDatePORequest2=" + flexDatePORequest2 + ", flexDatePORequest3=" + flexDatePORequest3
				+ ", flexDatePORequest4=" + flexDatePORequest4 + ", flexDatePORequest5=" + flexDatePORequest5
				+ ", promiseID=" + promiseID + ", promiseQty=" + promiseQty + ", promiseDate=" + promiseDate
				+ ", promisedShipDate=" + promisedShipDate + ", supplierSite=" + supplierSite
				+ ", shipFromAddressDescriptor=" + shipFromAddressDescriptor + ", shipFromAddress1=" + shipFromAddress1
				+ ", shipFromAddress2=" + shipFromAddress2 + ", shipFromAddress3=" + shipFromAddress3
				+ ", shipFromAddress4=" + shipFromAddress4 + ", shipFromAddress5=" + shipFromAddress5
				+ ", shipFromCity=" + shipFromCity + ", shipFromCounty=" + shipFromCounty + ", shipFromState="
				+ shipFromState + ", shipFromCountry=" + shipFromCountry + ", shipFromZip=" + shipFromZip
				+ ", flexStringPOPromise1=" + flexStringPOPromise1 + ", flexStringPOPromise2=" + flexStringPOPromise2
				+ ", flexStringPOPromise3=" + flexStringPOPromise3 + ", flexStringPOPromise4=" + flexStringPOPromise4
				+ ", flexStringPOPromise5=" + flexStringPOPromise5 + ", flexStringPOPromise6=" + flexStringPOPromise6
				+ ", flexStringPOPromise7=" + flexStringPOPromise7 + ", flexStringPOPromise8=" + flexStringPOPromise8
				+ ", flexStringPOPromise9=" + flexStringPOPromise9 + ", FlexIntPOPromise1=" + FlexIntPOPromise1
				+ ", FlexIntPOPromise2=" + FlexIntPOPromise2 + ", flexIntPOPromise3=" + flexIntPOPromise3
				+ ", flexIntPOPromise4=" + flexIntPOPromise4 + ", flexIntPOPromise5=" + flexIntPOPromise5
				+ ", flex_Float_PO_Promise1=" + flex_Float_PO_Promise1 + ", flexFloatPOPromise2=" + flexFloatPOPromise2
				+ ", flexFloatPOPromise3=" + flexFloatPOPromise3 + ", flexFloatPOPromise4=" + flexFloatPOPromise4
				+ ", flexFloatPOPromise5=" + flexFloatPOPromise5 + ", flexDatePOPromise1=" + flexDatePOPromise1
				+ ", flexDatePOPromise2=" + flexDatePOPromise2 + ", flexDatePOPromise3=" + flexDatePOPromise3
				+ ", flexDatePOPromise4=" + flexDatePOPromise4 + ", flexDatePOPromise5=" + flexDatePOPromise5 + ", rev="
				+ rev + ", shipToCustomerID=" + shipToCustomerID + "]";
	}


	


}
