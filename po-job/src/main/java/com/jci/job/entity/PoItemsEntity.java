package com.jci.job.entity;

import java.util.Date;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class PoItemsEntity extends TableServiceEntity {

	public PoItemsEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;// po number
	}

	public PoItemsEntity() {
	}

	private int id;
	
	private String dataSource ;

	/**
	 * #*Order Number Sequence No: 0
	 */
	private String orderNumber;

	/**
	 * *Order Creation Date Sequence No: 1
	 */
	private Date orderCreationDate;
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
	private String customerId;
	/**
	 * *Customer Description Sequence No: 5
	 */
	private String CustomerDescription;
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
	 * *Customer County Sequence No: 16
	 */
	private String customerConty;
	/**
	 * *Customer State Sequence No: 17
	 */
	private String customerState;

	/**
	 * *Customer Country Sequence No: 18
	 */
	private String customerContry;
	/**
	 * *Customer Zip Sequence No: 19
	 */
	private String customerZip;
	/**
	 * **Supplier ID Sequence No: 20
	 */
	private String supplierId;
	/**
	 * *Supplier Description Sequence No: 21
	 */
	private String supplierDicription;
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
	private String BuyerCode;
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
	private String DeliveryTerm;
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
	private String inCoTerms;
	/**
	 * *Customer Order Notes Sequence No: 44
	 */
	private String customerOrderNotes;
	/**
	 * *Supplier Order Notes Sequence No: 45
	 */
	private String suplierOrderNotes;
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
	private String RemitToAddressDescriptor;
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
	private String FlexStringPOHeader4;
	/**
	 * *Flex String PO Header 5 Sequence No: 73
	 */
	private String FlexStringPOHeader5;
	/**
	 * *Flex String PO Header 6 Sequence No: 74
	 */
	private String FlexStringPOHeader6;
	/**
	 * *Flex String PO Header 7 Sequence No: 75
	 */
	private String FlexStringPOHeader7;
	/**
	 * *Flex String PO Header 8 Sequence No: 76
	 */
	private String FlexStringPOHeader8;
	/**
	 * *Flex String PO Header 9 Sequence No: 77
	 */
	private String FlexStringPOHeader9;
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
	private int lineId;
	/**
	 * Line Status Sequence No: 94
	 */
	private String lineStatus;
	/**
	 * *Customer Item Id Sequence No: 95
	 */
	private String customerItemId;
	/**
	 * *Customer Item Description Sequence No: 96
	 */
	private String customerItemDescription;
	/**
	 * *Supplier Item Id Sequence No: 97
	 */
	private String supplierItemId;
	/**
	 * *Supplier Item Description Sequence No: 98
	 */
	private String supplierItemDescription;
	/**
	 * *Unit Price Sequence No: 99
	 */
	private double unitPrice;
	/**
	 * *Price Basis Sequence No: 100
	 */
	private String priceBasis;
	private String paymentCurrency;
	private double totalLineAmount;
	private String UOM;
	private String customerOrderLineNotes;
	private String suoplierOrderLineNotes;
	private String drawingVersion;
	private String drawingNumber;
	private String itemCategory;
	private String shipToLocation;
	private String flexStringPOLine5;
	private String flexStringPOLine6;
	private String flexStringPOLine7;
	private String flexStringPOLine8;
	private String flexStringPOLine9;
	private int freeItemFlag;
	private int flexIntPOLine2;
	private int flexIntPOLine3;
	private int flexIntPOLine4;
	private int flexIntPOLine5;
	private double flexFloatPOLine1;
	private double flexFloatPOLine2;
	private double flexFloatPOLine3;
	private double flexFloatPOLine4;
	private double flexFloatPOLine5;
	private Date flexDatePOLine1;
	private Date flexDatePOLine2;
	private Date flexDatePOLine3;
	private Date flexDatePOLine4;
	private Date flexDatePOLine5;
	private int requestNumber;
	private String requestStatus;
	private String action;
	private double requestQty;
	private Date requestDate;
	private Date requestedShipDate;
	private String carrier;
	private String customerSite;
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
	private String refOrderType;
	private String refOrderId;
	private String refOrderLineId;
	private String refOrderRequestId;
	private String refCustomerId;
	private String refSupplierId;
	private String flexStringPORequest1;
	private String flexStringPORequest2;
	private String flexStringPORequest3;
	private String flexStringPORequest4;
	private String flexStringPORequest5;
	private String flexStringPORequest6;
	private String flexStringPORequest7;
	private String flexStringPORequest8;
	private String flexStringPORequest9;
	private int flexPORequest1;
	private int flexPORequest2;
	private int flexPORequest3;
	private int flexPORequest4;
	private int flexPORequest5;
	private double flexFloatPORequest1;
	private double flexFloatPORequest2;
	private double flexFloatPORequest3;
	private double flexFloatPORequest4;
	private double flexFloatPORequest5;
	private Date flexDatePORequest1;
	private Date flexDatePORequest2;
	private Date flexDatePORequest3;
	private Date flexDatePORequest4;
	private Date flexDatePORequest5;
	private int promiseId;
	private double promiseQty;
	private Date promiseDate;
	private Date promiseShipDate;
	private String supplierSite;
	private String shipFromAddressDescriptor;
	private String shipFromAddress1;
	private String shipFromAddress2;
	private String shipFromAddress3;
	private String shipFromAddress4;
	private String shipFromAddress5;
	private String shipFromCity;
	private String shipFromCounty;
	private String shipFromState;
	private String shipFromCountry;
	private String shipFromZip;
	private String flexStringPOPromise1;
	private String flexStringPOPromise2;
	private String flexStringPOPromise3;
	private String flexStringPOPromise4;
	private String flexStringPOPromise5;
	private String flexStringPOPromise6;
	private String flexStringPOPromise7;
	private String flexStringPOPromise8;
	private String flexStringPOPromise9;
	private int flexIntPOPromise1;
	private int flexIntPOPromise2;
	private int flexIntPOPromise3;
	private int flexIntPOPromise4;
	private int flexIntPOPromise5;
	private double flexFloatPOPromise1;
	private double flexFloatPOPromise2;
	private double flexFloatPOPromise3;
	private double flexFloatPOPromise4;
	private double flexFloatPOPromise5;
	private Date flexDatePOPromise1;
	private Date flexDatePOPromise2;
	private Date flexDatePOPromise3;
	private Date flexDatePOPromise4;
	private Date flexDatePOPromise5;
	private String revNo;
	private String shipToCustomerId;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(Date orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerDescription() {
		return CustomerDescription;
	}

	public void setCustomerDescription(String CustomerDescription) {
		this.CustomerDescription = CustomerDescription;
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

	public String getcustomerAddressDescriptor() {
		return customerAddressDescriptor;
	}

	public void setcustomerAddressDescriptor(String customerAddressDescriptor) {
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

	public String getCustomerConty() {
		return customerConty;
	}

	public void setCustomerConty(String customerConty) {
		this.customerConty = customerConty;
	}

	public String getCustomerContry() {
		return customerContry;
	}

	public void setCustomerContry(String customerContry) {
		this.customerContry = customerContry;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerZip() {
		return customerZip;
	}

	public void setCustomerZip(String customerZip) {
		this.customerZip = customerZip;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierDicription() {
		return supplierDicription;
	}

	public void setSupplierDicription(String supplierDicription) {
		this.supplierDicription = supplierDicription;
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
		return BuyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		BuyerCode = buyerCode;
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
		return DeliveryTerm;
	}

	public void setDeliveryTerm(String deliveryTerm) {
		DeliveryTerm = deliveryTerm;
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

	public String getInCoTerm() {
		return inCoTerms;
	}

	public void setInCoTerm(String inCoTerms) {
		this.inCoTerms = inCoTerms;
	}

	public String getCustomerOrderNotes() {
		return customerOrderNotes;
	}

	public void setCustomerOrderNotes(String customerOrderNotes) {
		this.customerOrderNotes = customerOrderNotes;
	}

	public String getSuplierOrderNotes() {
		return suplierOrderNotes;
	}

	public void setSuplierOrderNotes(String suplierOrderNotes) {
		this.suplierOrderNotes = suplierOrderNotes;
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
		return RemitToAddressDescriptor;
	}

	public void setRemitToAddressDescriptor(String remitToAddressDescriptor) {
		RemitToAddressDescriptor = remitToAddressDescriptor;
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
		return FlexStringPOHeader4;
	}

	public void setFlexStringPOHeader4(String flexStringPOHeader4) {
		FlexStringPOHeader4 = flexStringPOHeader4;
	}

	public String getFlexStringPOHeader5() {
		return FlexStringPOHeader5;
	}

	public void setFlexStringPOHeader5(String flexStringPOHeader5) {
		FlexStringPOHeader5 = flexStringPOHeader5;
	}

	public String getFlexStringPOHeader6() {
		return FlexStringPOHeader6;
	}

	public void setFlexStringPOHeader6(String flexStringPOHeader6) {
		FlexStringPOHeader6 = flexStringPOHeader6;
	}

	public String getFlexStringPOHeader7() {
		return FlexStringPOHeader7;
	}

	public void setFlexStringPOHeader7(String flexStringPOHeader7) {
		FlexStringPOHeader7 = flexStringPOHeader7;
	}

	public String getFlexStringPOHeader8() {
		return FlexStringPOHeader8;
	}

	public void setFlexStringPOHeader8(String flexStringPOHeader8) {
		FlexStringPOHeader8 = flexStringPOHeader8;
	}

	public String getFlexStringPOHeader9() {
		return FlexStringPOHeader9;
	}

	public void setFlexStringPOHeader9(String flexStringPOHeader9) {
		FlexStringPOHeader9 = flexStringPOHeader9;
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

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getCustomerItemId() {
		return customerItemId;
	}

	public void setCustomerItemId(String customerItemId) {
		this.customerItemId = customerItemId;
	}

	public String getCustomerItemDescription() {
		return customerItemDescription;
	}

	public void setCustomerItemDescription(String customerItemDescription) {
		this.customerItemDescription = customerItemDescription;
	}

	public String getSupplierItemId() {
		return supplierItemId;
	}

	public void setSupplierItemId(String supplierItemId) {
		this.supplierItemId = supplierItemId;
	}

	public String getSupplierItemDescription() {
		return supplierItemDescription;
	}

	public void setSupplierItemDescription(String supplierItemDescription) {
		this.supplierItemDescription = supplierItemDescription;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getUOM() {
		return UOM;
	}

	public void setUOM(String uOM) {
		UOM = uOM;
	}

	public String getCustomerOrderLineNotes() {
		return customerOrderLineNotes;
	}

	public void setCustomerOrderLineNotes(String customerOrderLineNotes) {
		this.customerOrderLineNotes = customerOrderLineNotes;
	}

	public String getSuoplierOrderLineNotes() {
		return suoplierOrderLineNotes;
	}

	public void setSuoplierOrderLineNotes(String suoplierOrderLineNotes) {
		this.suoplierOrderLineNotes = suoplierOrderLineNotes;
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

	public int getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public double getRequestQty() {
		return requestQty;
	}

	public void setRequestQty(double requestQty) {
		this.requestQty = requestQty;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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
		return customerSite;
	}

	public void setCustomerSite(String customerSite) {
		this.customerSite = customerSite;
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

	public String getRefOrderId() {
		return refOrderId;
	}

	public void setRefOrderId(String refOrderId) {
		this.refOrderId = refOrderId;
	}

	public String getRefOrderLineId() {
		return refOrderLineId;
	}

	public void setRefOrderLineId(String refOrderLineId) {
		this.refOrderLineId = refOrderLineId;
	}

	public String getRefOrderRequestId() {
		return refOrderRequestId;
	}

	public void setRefOrderRequestId(String refOrderRequestId) {
		this.refOrderRequestId = refOrderRequestId;
	}

	public String getRefCustomerId() {
		return refCustomerId;
	}

	public void setRefCustomerId(String refCustomerId) {
		this.refCustomerId = refCustomerId;
	}

	public String getRefSupplierId() {
		return refSupplierId;
	}

	public void setRefSupplierId(String refSupplierId) {
		this.refSupplierId = refSupplierId;
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

	public int getFlexPORequest1() {
		return flexPORequest1;
	}

	public void setFlexPORequest1(int flexPORequest1) {
		this.flexPORequest1 = flexPORequest1;
	}

	public int getFlexPORequest2() {
		return flexPORequest2;
	}

	public void setFlexPORequest2(int flexPORequest2) {
		this.flexPORequest2 = flexPORequest2;
	}

	public int getFlexPORequest3() {
		return flexPORequest3;
	}

	public void setFlexPORequest3(int flexPORequest3) {
		this.flexPORequest3 = flexPORequest3;
	}

	public int getFlexPORequest4() {
		return flexPORequest4;
	}

	public void setFlexPORequest4(int flexPORequest4) {
		this.flexPORequest4 = flexPORequest4;
	}

	public int getFlexPORequest5() {
		return flexPORequest5;
	}

	public void setFlexPORequest5(int flexPORequest5) {
		this.flexPORequest5 = flexPORequest5;
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

	public int getPromiseId() {
		return promiseId;
	}

	public void setPromiseId(int promiseId) {
		this.promiseId = promiseId;
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

	public Date getPromiseShipDate() {
		return promiseShipDate;
	}

	public void setPromiseShipDate(Date promiseShipDate) {
		this.promiseShipDate = promiseShipDate;
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
		return flexIntPOPromise1;
	}

	public void setFlexIntPOPromise1(int flexIntPOPromise1) {
		this.flexIntPOPromise1 = flexIntPOPromise1;
	}

	public int getFlexIntPOPromise2() {
		return flexIntPOPromise2;
	}

	public void setFlexIntPOPromise2(int flexIntPOPromise2) {
		this.flexIntPOPromise2 = flexIntPOPromise2;
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

	public double getFlexFloatPOPromise1() {
		return flexFloatPOPromise1;
	}

	public void setFlexFloatPOPromise1(double flexFloatPOPromise1) {
		this.flexFloatPOPromise1 = flexFloatPOPromise1;
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

	public String getRevNo() {
		return revNo;
	}

	public void setRevNo(String revNo) {
		this.revNo = revNo;
	}

	public String getShipToCustomerId() {
		return shipToCustomerId;
	}

	public void setShipToCustomerId(String shipToCustomerId) {
		this.shipToCustomerId = shipToCustomerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	


}
