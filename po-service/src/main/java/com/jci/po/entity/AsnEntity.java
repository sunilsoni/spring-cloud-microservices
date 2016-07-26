package com.jci.po.entity;

import java.util.Date;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class AsnEntity extends TableServiceEntity {

	public AsnEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;// po number
	}

	public AsnEntity() {
	}
	

	
	/*
	 * *#*Shipment ID
	 * SequenceNo. 1
	 *  
	 */

	private String shipmentId;
	/*
	 * *Customer ID
	 * SequenceNo. 2
	 *  
	 */
	private String custmerId;
	/*
	 * Customer Description
	 * SequenceNo. 3
	 *  
	 */
	private String customerDescription;
	/*
	 * Customer DUNS
	 * SequenceNo. 4
	 *  
	 */
	private String customerDUNS;
	/*
	 * Customer DUNS4
	 * SequenceNo. 5
	 *  
	 */
	private String customerDUNS4;
	/*
	 * Customer Address Descriptor
	 * SequenceNo. 6
	 *  
	 */
	private String customerAddressDescriptor;
	/*
	 * Customer Address 1
	 * SequenceNo. 7
	 *  
	 */
	private String customerAddress1;
	/*
	 * Customer Address 2
	 * SequenceNo. 8
	 *  
	 */
	private String customerAddress2;
	/*
	 * Customer Address 3
	 * SequenceNo. 9
	 *  
	 */
	private String customerAddress3;
	/*
	 * Customer Address 4
	 * SequenceNo. 10
	 *  
	 */
	private String customerAddress4;
	/*
	 * Customer Address 5
	 * SequenceNo. 11
	 *  
	 */
	private String customerAddress5;
	/*
	 * Customer City
	 * SequenceNo. 12
	 *  
	 */
	private String customerCity;
	/*
	 * Customer County
	 * SequenceNo. 13
	 *  
	 */
	private String customerCounty;
	/*
	 * Customer State
	 * SequenceNo. 14
	 *  
	 */
	private String customerState;
	/*
	 * Customer Country
	 * SequenceNo. 15
	 *  
	 */
	private String customerCountry;
	/*
	 * Customer Zip
	 * SequenceNo. 16
	 *  
	 */
	private String customerZip;
	/*
	 * *Supplier ID  
	 * SequenceNo. 17
	 *  
	 */
	private String supplierId;
	/*
	 * Supplier Description
	 * SequenceNo. 18
	 *  
	 */
	private String supplierDescription;
	/*
	 * Supplier DUNS
	 * SequenceNo. 19
	 *  
	 */
	private String supplierDUNS;
	/*
	 * Supplier DUNS
	 * SequenceNo. 20
	 *  
	 */
	private String supplierDUNS4;
	/*
	 * Supplier Address Descriptor
	 * SequenceNo. 21
	 *  
	 */
	private String supplierAddressDescriptor;
	/*
	 * Supplier Address 1
	 * SequenceNo. 22
	 *  
	 */
	private String supplierAddress1;
	/*
	 * Supplier Address 2
	 * SequenceNo. 23
	 *  
	 */
	private String supplierAddress2;
	/*
	 * Supplier Address 3
	 * SequenceNo. 24
	 *  
	 */
	private String supplierAddress3;
	/*
	 * Supplier Address 4
	 * SequenceNo. 25
	 *  
	 */
	private String supplierAddress4;
	/*
	 * Supplier Address 5
	 * SequenceNo. 26
	 *  
	 */
	private String supplierAddress5;
	/*
	 * Supplier City
	 * SequenceNo. 27
	 *  
	 */
	private String supplierCity;
	/*
	 * Supplier County
	 * SequenceNo. 28
	 *  
	 */
	private String supplierCounty;
	/*
	 * Supplier State
	 * SequenceNo. 29
	 *  
	 */
	private String supplierState;
	/*
	 * Supplier Country
	 * SequenceNo. 30
	 *  
	 */
	private String supplierCountry;
	/*
	 * Supplier Zip
	 * SequenceNo. 31
	 *  
	 */
	private String supplierZip;
	/*
	 * Shipment Creation Date
	 * SequenceNo. 32
	 *  
	 */
	private Date shipmentCreationDate;
	/*
	 * Shipment Creator Code 
	 * SequenceNo. 33
	 *  
	 */
	private String shipmentCreatorCode;
	/*
	 * Shipment Status
	 * SequenceNo. 34
	 *  
	 */
	private String shipmentStatus;
	/*
	 * *Shipment Date
	 * SequenceNo. 35
	 *  
	 */
	private String shipmentDate;
	/*
	 * *Planned Delivery Date
	 * SequenceNo. 36
	 *  
	 */
	private Date plannedDeliveryDate;
	/*
	 * Final Site ETA
	 * SequenceNo. 37
	 *  
	 */
	private Date finalSiteETA;
	/*
	 * Last Known Milestone
	 * SequenceNo. 38
	 *  
	 */
	private String lastKnownMilestone;
	/*
	 * Last Known Milestone Arrival
	 * SequenceNo. 39
	 *  
	 */
	private Date lastKnownMilestoneArrival;
	/*
	 * Last Known Milestone Departure
	 * SequenceNo. 40
	 *  
	 */
	private Date lastKnownMilestoneDeparture;
	/*
	 * Carrier
	 * SequenceNo. 41
	 *  
	 */
	private String carrier;
	/*
	 * shipment mode 
	 * SequenceNo. 42
	 *  
	 */
	private String shipmentMode;
	/*
	 * Way Bill Number
	 * SequenceNo. 43
	 *  
	 */
	private String waybillNo;
	/*	
	 * Shipment Delivery BOL #
	 * Sequence No. 44
	*/

	private String shipmentDeliveryBOLNo;
	/*	
	 * Packing Slip #
	 * Sequence No. 45
	*/
	private String packingSlipNo;
	/*	
	 * Carrier Reference No.
	 * Sequence No. 46
	*/
	private String carrierReferenceNo;
	/*	
	 * Shipper Reference #
	 * Sequence No. 47
	*/
	private String shipperReferenceNo;
	/*	
	 * *Ship To Type (Site / Hub)
	 * Sequence No. 48
	*/
	private String ShipToTypeSiteOrHub;
	/*	
	 * Country of Origin
	 * Sequence No. 49
	*/
	private String countryOfOrigin;
	/*	
	 * # of Packages
	 * Sequence No. 50
	*/
	private Long numberOfPackages;
	/*	
	 * License Plate
	 * Sequence No. 51
	*/
	private String licensePlate;
	/*	
	 * Importer of #
	 * Sequence No. 52
	*/
	private String ImporterOfNo;
	/*	
	 * Consignee #
	 * Sequence No. 53
	*/
	private String consigneeNo;
	/*	
	 * Foreign Port Of Unlading
	 * Sequence No. 54
	*/
	private String foreignPortOfUnlading;
	/*	
	 * Place Of Delivery
	 * Sequence No. 55
	*/
	private String placeOfDelivery;
	/*	
	 * *Ship To Site
	 * Sequence No. 56
	*/
	private String shipToSite;
	/*	
	 * Ship To Party Name 
	 * Sequence No. 57
	*/
	private String shipToPartyName;
	/*	
	 * Ship To Party DUNS 
	 * Sequence No. 58
	*/
	private String ShipToPartyDUNS;
	/*	
	 * Ship To Address Descriptor
	 * Sequence No. 59
	*/
	private String shipToAddressDescriptor;
	/*	
	 * Ship To Address 1
	 * Sequence No. 60
	*/
	private String shipToAddress1;
	/*	
	 * Ship To Address 2
	 * Sequence No. 61
	*/
	private String shipToAddress2;
	/*	
	 * Ship To Address 3
	 * Sequence No. 62
	*/
	private String shipToAddress3;
	/*	
	 * Ship To Address 4
	 * Sequence No. 63
	*/
	private String shipToAddress4;
	/*	
	 * Ship To Address 5
	 * Sequence No. 64
	*/
	private String shipToAddress5;
	/*	
	 * Ship To City
	 * Sequence No. 65
	*/
	private String shipToCity;
	/*	
	 * Ship To County
	 * Sequence No. 66
	*/
	private String shipToCounty;
	/*	
	 * Ship To State
	 * Sequence No. 67
	*/
	private String shipToState;
	/*	
	 * Ship To Country
	 * Sequence No. 68
	*/
	private String shipToCountry;
	/*	
	 * Ship To Zip
	 * Sequence No. 69
	*/
	private String shipToZip;
	/*	
	 * Ship To Site
	 * Sequence No. 70
	*/
	private String supplierSite;
	/*	
	 * Ship From Address Descriptor
	 * Sequence No. 71
	*/
	private String shipFromAddressDescriptor;
	/*	
	 * Ship From Address 1
	 * Sequence No. 72
	*/
	private String shipFromAddress1;
	/*	
	 * Ship From Address 2
	 * Sequence No. 73
	*/
	private String shipFromAddress2;
	/*	
	 * Ship From Address 3
	 * Sequence No. 74
	*/
	private String shipFromAddress3;
	/*	
	 * Ship From Address 4
	 * Sequence No. 75
	*/
	private String shipFromAddress4;
	/*	
	 * Ship From Address 5
	 * Sequence No. 76
	*/
	private String shipFromAddress5;
	/*	
	 * Ship From City
	 * Sequence No. 77
	*/
	private String shipFromCity;
	/*	
	 * Ship From County
	 * Sequence No. 78
	*/
	private String shipFromCounty;
	/*	
	 * Ship From State
	 * Sequence No. 79
	*/
	private String shipFromState;
	/*	
	 * Ship From Country
	 * Sequence No. 80
	*/
	private String shipFromCountry;
	/*	
	 * Ship From Zip
	 * Sequence No. 81
	*/
	private String shipFromZip;
	/*	
	 * Bill To
	 * Sequence No. 82
	*/
	private String billTo;
	/*	
	 * Bill To Address Descriptor
	 * Sequence No. 83
	*/
	
	private String billToAddressDescriptor;
	/*	
	 * Bill To Address 1
	 * Sequence No. 84
	*/
	private String billToAddress1;
	/*	
	 * Bill To Address 2
	 * Sequence No. 85
	*/
	private String billToAddress2;
	/*	
	 * Bill To Address 3
	 * Sequence No. 86
	*/
	private String billToAddress3;
	/*	
	 * Bill To Address 4
	 * Sequence No. 87
	*/

	private String billToAddress4;
	/*	
	 * Bill To Address 5
	 * Sequence No. 88
	*/
	private String billToAddress5;
	/*	
	 * Bill To City
	 * Sequence No. 89
	*/
	private String billToCity;
	/*	
	 * Bill To Country
	 * Sequence No. 90
	*/
	private String billToCounty;
	/*	
	 * Bill To State
	 * Sequence No. 91
	*/
	private String billToState;
	/*	
	 * Bill To Country
	 * Sequence No. 92
	*/
	private String billToCountry;
	/*	
	 * Bill To Zip
	 * Sequence No. 93
	*/
	private String billToZip;
	/*	
	 * Booking Party Name 
	 * Sequence No. 94
	*/
	private String bookingPartyName;
	/*	
	 * Booking Party DUNS 
	 * Sequence No. 95
	*/
	private String bookingPartyDUNS;
	/*	
	 * Booking Party Descriptor
	 * Sequence No. 96
	*/
	private String bookingPartyAddressDescriptor;
	/*	
	 * Booking Party Address 1
	 * Sequence No. 97
	*/
	private String bookingPartyAddress1;
	/*	
	 * Booking Party Address 2
	 * Sequence No. 98
	*/
	private String bookingPartyAddress2;
	/*	
	 * Booking Party Address 3
	 * Sequence No. 99
	*/
	private String bookingPartyAddress3;
	/*	
	 * Booking Party Address 4
	 * Sequence No. 100
	*/
	private String bookingPartyAddress4;
	/*	
	 * Booking Party Address 5
	 * Sequence No. 101
	*/
	private String bookingPartyAddress5;
	/*	
	 * Booking Party City
	 * Sequence No. 102
	*/
	private String bookingPartyCity;
	/*	
	 * Booking Party County
	 * Sequence No. 103
	*/
	private String bookingPartyCounty;
	/*	
	 * Booking Party State
	 * Sequence No. 104
	*/
	private String bookingPartyState;
	/*	
	 * Booking Party Zip
	 * Sequence No. 105
	*/
	private String bookingPartyZip;
	/*	
	 * Shipment Tracking URL
	 * Sequence No. 106
	*/
	private String shipmentTrackingUrl;
	/*	
	 * Shipment Tracking Number
	 * Sequence No. 107
	*/
	private String shipmentTrackingNumber;
	/*	
	 * Pro Number
	 * Sequence No. 108
	*/
	private String proNumber;
	/*	
	 * Buyer Name
	 * Sequence No. 109
	*/
	private String buyerName;
	/*	
	 * Buyer Phone Number
	 * Sequence No. 110
	*/
	private String buyerPhoeNumber;
	/*	
	 * Shipper Contact Person
	 * Sequence No. 111
	*/
	private String shipperContactPerson;
	private String shipperContactPhone;
	private String shippingvehicleLicenseNumber;
	private String flexStringASNHeader9;
	private int flexIntASNHeader1;
	private int flexIntASNHeader2;
	private int flexIntASNHeader3;
	private int flexIntASNHeader4;
	private int flexIntASNHeader5;
	private Double FlexFloatASNHeader1;
	private Double flexfloatASNHeader2;
	private Double flexFloatASNHeader3;
	private Double flexFloatASNHeader4;
	private Double flexFloatASNHeader5;
	private Date flexDateASNHeader1;
	private Date flexDateASNHeader2;
	private Date flexDateASNHeader3;
	private Date flexDateASNHeader4;
	private Date flexDateASNHeader5;
	private String shipmentLineID;
	private String shipmentLineStatus;
	private String action;
	private String customerItemID;
	private String customerItemDescription;
	private String supplierItemID;
	private String supplierItemDescription;
	private String buyerCode;
	private String shippedQuantity;
	private String unitOfMeasure;
	private Double freightCost;
	private String currency;
	private String packingSlipNoLine;
	private String notesToCustomer;
	private String NoOfBoxes;
	private String weight;
	private String commodityHTS6;
	private String manufacturerName;
	private String manufacturerDUNS;
	private String manufacturerAddressDescriptor;
	private String manufacturerAddress1;
	private String manufacturerAddress2;
	private String manufacturerAddress3;
	private String manufacturerAddress4;
	private String manufacturerAddress5;
	private String manufacturerCity;
	private String manufacturerCounty;
	private String manufacturerState;
	private String manufacturerCountry;
	private String manufacturerZip;
	private String containerStuffingLocationName;
	private String containerStuffingLocationDUNS;
	private String containerStuffingLocationAddressDescriptor;
	private String containerStuffingLocationAddress1;
	private String containerStuffingLocationAddress2;
	private String containerStuffingLocationAddress3;
	private String containerStuffingLocationAddress4;
	private String containerStuffingLocationAddress5;
	private String containerStuffingLocationCity;
	private String containerStuffingLocationCounty;
	private String containerStuffingLocationState;
	private String containerStuffingLocationCountry;
	private String containerStuffingLocationZip;
	private String consolidatorName;
	private String consolidatorDUNS;
	private String consolidatorAddressDescriptor;
	private String consolidatorAddress1;
	private String consolidatorAddress2;
	private String consolidatorAddress3;
	private String consolidatorAddress4;
	private String consolidatorAddress5;
	private String consolidatorCity;
	private String consolidatorCounty;
	private String consolidatorState;
	private String consolidatorCountry;
	private String consolidatorZip;
	private String refCustomerID;
	private String refSupplierID;
	private String refOrderType;
	private String refOrderID;
	private int refOrderLineID;
	private int refOrderRequestID;
	private int refOrderPromiseID;
	private String serialNumber;
	private String qaRequirementFlag;
	private String salesOrderNumber;
	private String flexStringASNLine4;
	private String flexStringASNLine5;
	private String flexStringASNLine6;
	private String flexStringASNLine7;
	private String flexStringASNLine8;
	private String flexStringASNLine9;
	private int flexIntASNLine1;
	private int flexIntASNLine2;
	private int flexIntASNLine3;
	private int flexIntASNLine4;
	private int flexIntASNLine5;
	private Double flexFloatASNLine1;
	private Double flexFloatASNLine2;
	private Double flexFloatASNLine3;
	private Double flexFloatASNLine4;
	private Double flexFloatASNLine5;
	private Date flexDateASNLine1;
	private Date flexDateASNLine2;
	private Date flexDateASNLine3;
	private Date flexDateASNLine4;
	private Date flexDateASNLine5;
	private String shipToCustomerID;
	private String shipToCustomerDesc;
	private String shipToCustomerItemID;
	private String shipToCustomerItemDesc;
	private String dropShipmentFlag;
	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getCustmerId() {
		return custmerId;
	}

	public void setCustmerId(String custmerId) {
		this.custmerId = custmerId;
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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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

	public Date getShipmentCreationDate() {
		return shipmentCreationDate;
	}

	public void setShipmentCreationDate(Date shipmentCreationDate) {
		this.shipmentCreationDate = shipmentCreationDate;
	}

	public String getShipmentCreatorCode() {
		return shipmentCreatorCode;
	}

	public void setShipmentCreatorCode(String shipmentCreatorCode) {
		this.shipmentCreatorCode = shipmentCreatorCode;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public Date getPlannedDeliveryDate() {
		return plannedDeliveryDate;
	}

	public void setPlannedDeliveryDate(Date plannedDeliveryDate) {
		this.plannedDeliveryDate = plannedDeliveryDate;
	}

	public Date getFinalSiteETA() {
		return finalSiteETA;
	}

	public void setFinalSiteETA(Date finalSiteETA) {
		this.finalSiteETA = finalSiteETA;
	}

	public String getLastKnownMilestone() {
		return lastKnownMilestone;
	}

	public void setLastKnownMilestone(String lastKnownMilestone) {
		this.lastKnownMilestone = lastKnownMilestone;
	}

	public Date getLastKnownMilestoneArrival() {
		return lastKnownMilestoneArrival;
	}

	public void setLastKnownMilestoneArrival(Date lastKnownMilestoneArrival) {
		this.lastKnownMilestoneArrival = lastKnownMilestoneArrival;
	}

	public Date getLastKnownMilestoneDeparture() {
		return lastKnownMilestoneDeparture;
	}

	public void setLastKnownMilestoneDeparture(Date lastKnownMilestoneDeparture) {
		this.lastKnownMilestoneDeparture = lastKnownMilestoneDeparture;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getShipmentMode() {
		return shipmentMode;
	}

	public void setShipmentMode(String shipmentMode) {
		this.shipmentMode = shipmentMode;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getShipmentDeliveryBOLNo() {
		return shipmentDeliveryBOLNo;
	}

	public void setShipmentDeliveryBOLNo(String shipmentDeliveryBOLNo) {
		this.shipmentDeliveryBOLNo = shipmentDeliveryBOLNo;
	}

	public String getPackingSlipNo() {
		return packingSlipNo;
	}

	public void setPackingSlipNo(String packingSlipNo) {
		this.packingSlipNo = packingSlipNo;
	}

	public String getCarrierReferenceNo() {
		return carrierReferenceNo;
	}

	public void setCarrierReferenceNo(String carrierReferenceNo) {
		this.carrierReferenceNo = carrierReferenceNo;
	}

	public String getShipperReferenceNo() {
		return shipperReferenceNo;
	}

	public void setShipperReferenceNo(String shipperReferenceNo) {
		this.shipperReferenceNo = shipperReferenceNo;
	}

	public String getShipToTypeSiteOrHub() {
		return ShipToTypeSiteOrHub;
	}

	public void setShipToTypeSiteOrHub(String shipToTypeSiteOrHub) {
		ShipToTypeSiteOrHub = shipToTypeSiteOrHub;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public Long getNumberOfPackages() {
		return numberOfPackages;
	}

	public void setNumberOfPackages(Long numberOfPackages) {
		this.numberOfPackages = numberOfPackages;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getImporterOfNo() {
		return ImporterOfNo;
	}

	public void setImporterOfNo(String importerOfNo) {
		ImporterOfNo = importerOfNo;
	}

	public String getConsigneeNo() {
		return consigneeNo;
	}

	public void setConsigneeNo(String consigneeNo) {
		this.consigneeNo = consigneeNo;
	}

	public String getForeignPortOfUnlading() {
		return foreignPortOfUnlading;
	}

	public void setForeignPortOfUnlading(String foreignPortOfUnlading) {
		this.foreignPortOfUnlading = foreignPortOfUnlading;
	}

	public String getPlaceOfDelivery() {
		return placeOfDelivery;
	}

	public void setPlaceOfDelivery(String placeOfDelivery) {
		this.placeOfDelivery = placeOfDelivery;
	}

	public String getShipToSite() {
		return shipToSite;
	}

	public void setShipToSite(String shipToSite) {
		this.shipToSite = shipToSite;
	}

	public String getShipToPartyName() {
		return shipToPartyName;
	}

	public void setShipToPartyName(String shipToPartyName) {
		this.shipToPartyName = shipToPartyName;
	}

	public String getShipToPartyDUNS() {
		return ShipToPartyDUNS;
	}

	public void setShipToPartyDUNS(String shipToPartyDUNS) {
		ShipToPartyDUNS = shipToPartyDUNS;
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

	public String getBookingPartyName() {
		return bookingPartyName;
	}

	public void setBookingPartyName(String bookingPartyName) {
		this.bookingPartyName = bookingPartyName;
	}

	public String getBookingPartyDUNS() {
		return bookingPartyDUNS;
	}

	public void setBookingPartyDUNS(String bookingPartyDUNS) {
		this.bookingPartyDUNS = bookingPartyDUNS;
	}

	public String getBookingPartyAddressDescriptor() {
		return bookingPartyAddressDescriptor;
	}

	public void setBookingPartyAddressDescriptor(String bookingPartyAddressDescriptor) {
		this.bookingPartyAddressDescriptor = bookingPartyAddressDescriptor;
	}

	public String getBookingPartyAddress1() {
		return bookingPartyAddress1;
	}

	public void setBookingPartyAddress1(String bookingPartyAddress1) {
		this.bookingPartyAddress1 = bookingPartyAddress1;
	}

	public String getBookingPartyAddress2() {
		return bookingPartyAddress2;
	}

	public void setBookingPartyAddress2(String bookingPartyAddress2) {
		this.bookingPartyAddress2 = bookingPartyAddress2;
	}

	public String getBookingPartyAddress3() {
		return bookingPartyAddress3;
	}

	public void setBookingPartyAddress3(String bookingPartyAddress3) {
		this.bookingPartyAddress3 = bookingPartyAddress3;
	}

	public String getBookingPartyAddress4() {
		return bookingPartyAddress4;
	}

	public void setBookingPartyAddress4(String bookingPartyAddress4) {
		this.bookingPartyAddress4 = bookingPartyAddress4;
	}

	public String getBookingPartyAddress5() {
		return bookingPartyAddress5;
	}

	public void setBookingPartyAddress5(String bookingPartyAddress5) {
		this.bookingPartyAddress5 = bookingPartyAddress5;
	}

	public String getBookingPartyCity() {
		return bookingPartyCity;
	}

	public void setBookingPartyCity(String bookingPartyCity) {
		this.bookingPartyCity = bookingPartyCity;
	}

	public String getBookingPartyCounty() {
		return bookingPartyCounty;
	}

	public void setBookingPartyCounty(String bookingPartyCounty) {
		this.bookingPartyCounty = bookingPartyCounty;
	}

	public String getBookingPartyState() {
		return bookingPartyState;
	}

	public void setBookingPartyState(String bookingPartyState) {
		this.bookingPartyState = bookingPartyState;
	}

	public String getBookingPartyZip() {
		return bookingPartyZip;
	}

	public void setBookingPartyZip(String bookingPartyZip) {
		this.bookingPartyZip = bookingPartyZip;
	}

	public String getShipmentTrackingUrl() {
		return shipmentTrackingUrl;
	}

	public void setShipmentTrackingUrl(String shipmentTrackingUrl) {
		this.shipmentTrackingUrl = shipmentTrackingUrl;
	}

	public String getShipmentTrackingNumber() {
		return shipmentTrackingNumber;
	}

	public void setShipmentTrackingNumber(String shipmentTrackingNumber) {
		this.shipmentTrackingNumber = shipmentTrackingNumber;
	}

	public String getProNumber() {
		return proNumber;
	}

	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerPhoeNumber() {
		return buyerPhoeNumber;
	}

	public void setBuyerPhoeNumber(String buyerPhoeNumber) {
		this.buyerPhoeNumber = buyerPhoeNumber;
	}

	public String getShipperContactPerson() {
		return shipperContactPerson;
	}

	public void setShipperContactPerson(String shipperContactPerson) {
		this.shipperContactPerson = shipperContactPerson;
	}

	public String getShipperContactPhone() {
		return shipperContactPhone;
	}

	public void setShipperContactPhone(String shipperContactPhone) {
		this.shipperContactPhone = shipperContactPhone;
	}

	public String getShippingvehicleLicenseNumber() {
		return shippingvehicleLicenseNumber;
	}

	public void setShippingvehicleLicenseNumber(String shippingvehicleLicenseNumber) {
		this.shippingvehicleLicenseNumber = shippingvehicleLicenseNumber;
	}

	public String getFlexStringASNHeader9() {
		return flexStringASNHeader9;
	}

	public void setFlexStringASNHeader9(String flexStringASNHeader9) {
		this.flexStringASNHeader9 = flexStringASNHeader9;
	}

	public int getFlexIntASNHeader1() {
		return flexIntASNHeader1;
	}

	public void setFlexIntASNHeader1(int flexIntASNHeader1) {
		this.flexIntASNHeader1 = flexIntASNHeader1;
	}

	public int getFlexIntASNHeader2() {
		return flexIntASNHeader2;
	}

	public void setFlexIntASNHeader2(int flexIntASNHeader2) {
		this.flexIntASNHeader2 = flexIntASNHeader2;
	}

	public int getFlexIntASNHeader3() {
		return flexIntASNHeader3;
	}

	public void setFlexIntASNHeader3(int flexIntASNHeader3) {
		this.flexIntASNHeader3 = flexIntASNHeader3;
	}

	public int getFlexIntASNHeader4() {
		return flexIntASNHeader4;
	}

	public void setFlexIntASNHeader4(int flexIntASNHeader4) {
		this.flexIntASNHeader4 = flexIntASNHeader4;
	}

	public int getFlexIntASNHeader5() {
		return flexIntASNHeader5;
	}

	public void setFlexIntASNHeader5(int flexIntASNHeader5) {
		this.flexIntASNHeader5 = flexIntASNHeader5;
	}

	public Double getFlexFloatASNHeader1() {
		return FlexFloatASNHeader1;
	}

	public void setFlexFloatASNHeader1(Double flexFloatASNHeader1) {
		FlexFloatASNHeader1 = flexFloatASNHeader1;
	}

	public Double getFlexfloatASNHeader2() {
		return flexfloatASNHeader2;
	}

	public void setFlexfloatASNHeader2(Double flexfloatASNHeader2) {
		this.flexfloatASNHeader2 = flexfloatASNHeader2;
	}

	public Double getFlexFloatASNHeader3() {
		return flexFloatASNHeader3;
	}

	public void setFlexFloatASNHeader3(Double flexFloatASNHeader3) {
		this.flexFloatASNHeader3 = flexFloatASNHeader3;
	}

	public Double getFlexFloatASNHeader4() {
		return flexFloatASNHeader4;
	}

	public void setFlexFloatASNHeader4(Double flexFloatASNHeader4) {
		this.flexFloatASNHeader4 = flexFloatASNHeader4;
	}

	public Double getFlexFloatASNHeader5() {
		return flexFloatASNHeader5;
	}

	public void setFlexFloatASNHeader5(Double flexFloatASNHeader5) {
		this.flexFloatASNHeader5 = flexFloatASNHeader5;
	}

	public Date getFlexDateASNHeader1() {
		return flexDateASNHeader1;
	}

	public void setFlexDateASNHeader1(Date flexDateASNHeader1) {
		this.flexDateASNHeader1 = flexDateASNHeader1;
	}

	public Date getFlexDateASNHeader2() {
		return flexDateASNHeader2;
	}

	public void setFlexDateASNHeader2(Date flexDateASNHeader2) {
		this.flexDateASNHeader2 = flexDateASNHeader2;
	}

	public Date getFlexDateASNHeader3() {
		return flexDateASNHeader3;
	}

	public void setFlexDateASNHeader3(Date flexDateASNHeader3) {
		this.flexDateASNHeader3 = flexDateASNHeader3;
	}

	public Date getFlexDateASNHeader4() {
		return flexDateASNHeader4;
	}

	public void setFlexDateASNHeader4(Date flexDateASNHeader4) {
		this.flexDateASNHeader4 = flexDateASNHeader4;
	}

	public Date getFlexDateASNHeader5() {
		return flexDateASNHeader5;
	}

	public void setFlexDateASNHeader5(Date flexDateASNHeader5) {
		this.flexDateASNHeader5 = flexDateASNHeader5;
	}

	public String getShipmentLineID() {
		return shipmentLineID;
	}

	public void setShipmentLineID(String shipmentLineID) {
		this.shipmentLineID = shipmentLineID;
	}

	public String getShipmentLineStatus() {
		return shipmentLineStatus;
	}

	public void setShipmentLineStatus(String shipmentLineStatus) {
		this.shipmentLineStatus = shipmentLineStatus;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCustomerItemID() {
		return customerItemID;
	}

	public void setCustomerItemID(String customerItemID) {
		this.customerItemID = customerItemID;
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

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getShippedQuantity() {
		return shippedQuantity;
	}

	public void setShippedQuantity(String shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Double getFreightCost() {
		return freightCost;
	}

	public void setFreightCost(Double freightCost) {
		this.freightCost = freightCost;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPackingSlipNoLine() {
		return packingSlipNoLine;
	}

	public void setPackingSlipNoLine(String packingSlipNoLine) {
		this.packingSlipNoLine = packingSlipNoLine;
	}

	public String getNotesToCustomer() {
		return notesToCustomer;
	}

	public void setNotesToCustomer(String notesToCustomer) {
		this.notesToCustomer = notesToCustomer;
	}

	public String getNoOfBoxes() {
		return NoOfBoxes;
	}

	public void setNoOfBoxes(String noOfBoxes) {
		NoOfBoxes = noOfBoxes;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCommodityHTS6() {
		return commodityHTS6;
	}

	public void setCommodityHTS6(String commodityHTS6) {
		this.commodityHTS6 = commodityHTS6;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getManufacturerDUNS() {
		return manufacturerDUNS;
	}

	public void setManufacturerDUNS(String manufacturerDUNS) {
		this.manufacturerDUNS = manufacturerDUNS;
	}

	public String getManufacturerAddressDescriptor() {
		return manufacturerAddressDescriptor;
	}

	public void setManufacturerAddressDescriptor(String manufacturerAddressDescriptor) {
		this.manufacturerAddressDescriptor = manufacturerAddressDescriptor;
	}

	public String getManufacturerAddress1() {
		return manufacturerAddress1;
	}

	public void setManufacturerAddress1(String manufacturerAddress1) {
		this.manufacturerAddress1 = manufacturerAddress1;
	}

	public String getManufacturerAddress2() {
		return manufacturerAddress2;
	}

	public void setManufacturerAddress2(String manufacturerAddress2) {
		this.manufacturerAddress2 = manufacturerAddress2;
	}

	public String getManufacturerAddress3() {
		return manufacturerAddress3;
	}

	public void setManufacturerAddress3(String manufacturerAddress3) {
		this.manufacturerAddress3 = manufacturerAddress3;
	}

	public String getManufacturerAddress4() {
		return manufacturerAddress4;
	}

	public void setManufacturerAddress4(String manufacturerAddress4) {
		this.manufacturerAddress4 = manufacturerAddress4;
	}

	public String getManufacturerAddress5() {
		return manufacturerAddress5;
	}

	public void setManufacturerAddress5(String manufacturerAddress5) {
		this.manufacturerAddress5 = manufacturerAddress5;
	}

	public String getManufacturerCity() {
		return manufacturerCity;
	}

	public void setManufacturerCity(String manufacturerCity) {
		this.manufacturerCity = manufacturerCity;
	}

	public String getManufacturerCounty() {
		return manufacturerCounty;
	}

	public void setManufacturerCounty(String manufacturerCounty) {
		this.manufacturerCounty = manufacturerCounty;
	}

	public String getManufacturerState() {
		return manufacturerState;
	}

	public void setManufacturerState(String manufacturerState) {
		this.manufacturerState = manufacturerState;
	}

	public String getManufacturerCountry() {
		return manufacturerCountry;
	}

	public void setManufacturerCountry(String manufacturerCountry) {
		this.manufacturerCountry = manufacturerCountry;
	}

	public String getManufacturerZip() {
		return manufacturerZip;
	}

	public void setManufacturerZip(String manufacturerZip) {
		this.manufacturerZip = manufacturerZip;
	}

	public String getContainerStuffingLocationName() {
		return containerStuffingLocationName;
	}

	public void setContainerStuffingLocationName(String containerStuffingLocationName) {
		this.containerStuffingLocationName = containerStuffingLocationName;
	}

	public String getContainerStuffingLocationDUNS() {
		return containerStuffingLocationDUNS;
	}

	public void setContainerStuffingLocationDUNS(String containerStuffingLocationDUNS) {
		this.containerStuffingLocationDUNS = containerStuffingLocationDUNS;
	}

	public String getContainerStuffingLocationAddressDescriptor() {
		return containerStuffingLocationAddressDescriptor;
	}

	public void setContainerStuffingLocationAddressDescriptor(String containerStuffingLocationAddressDescriptor) {
		this.containerStuffingLocationAddressDescriptor = containerStuffingLocationAddressDescriptor;
	}

	public String getContainerStuffingLocationAddress1() {
		return containerStuffingLocationAddress1;
	}

	public void setContainerStuffingLocationAddress1(String containerStuffingLocationAddress1) {
		this.containerStuffingLocationAddress1 = containerStuffingLocationAddress1;
	}

	public String getContainerStuffingLocationAddress2() {
		return containerStuffingLocationAddress2;
	}

	public void setContainerStuffingLocationAddress2(String containerStuffingLocationAddress2) {
		this.containerStuffingLocationAddress2 = containerStuffingLocationAddress2;
	}

	public String getContainerStuffingLocationAddress3() {
		return containerStuffingLocationAddress3;
	}

	public void setContainerStuffingLocationAddress3(String containerStuffingLocationAddress3) {
		this.containerStuffingLocationAddress3 = containerStuffingLocationAddress3;
	}

	public String getContainerStuffingLocationAddress4() {
		return containerStuffingLocationAddress4;
	}

	public void setContainerStuffingLocationAddress4(String containerStuffingLocationAddress4) {
		this.containerStuffingLocationAddress4 = containerStuffingLocationAddress4;
	}

	public String getContainerStuffingLocationAddress5() {
		return containerStuffingLocationAddress5;
	}

	public void setContainerStuffingLocationAddress5(String containerStuffingLocationAddress5) {
		this.containerStuffingLocationAddress5 = containerStuffingLocationAddress5;
	}

	public String getContainerStuffingLocationCity() {
		return containerStuffingLocationCity;
	}

	public void setContainerStuffingLocationCity(String containerStuffingLocationCity) {
		this.containerStuffingLocationCity = containerStuffingLocationCity;
	}

	public String getContainerStuffingLocationCounty() {
		return containerStuffingLocationCounty;
	}

	public void setContainerStuffingLocationCounty(String containerStuffingLocationCounty) {
		this.containerStuffingLocationCounty = containerStuffingLocationCounty;
	}

	public String getContainerStuffingLocationState() {
		return containerStuffingLocationState;
	}

	public void setContainerStuffingLocationState(String containerStuffingLocationState) {
		this.containerStuffingLocationState = containerStuffingLocationState;
	}

	public String getContainerStuffingLocationCountry() {
		return containerStuffingLocationCountry;
	}

	public void setContainerStuffingLocationCountry(String containerStuffingLocationCountry) {
		this.containerStuffingLocationCountry = containerStuffingLocationCountry;
	}

	public String getContainerStuffingLocationZip() {
		return containerStuffingLocationZip;
	}

	public void setContainerStuffingLocationZip(String containerStuffingLocationZip) {
		this.containerStuffingLocationZip = containerStuffingLocationZip;
	}

	public String getConsolidatorName() {
		return consolidatorName;
	}

	public void setConsolidatorName(String consolidatorName) {
		this.consolidatorName = consolidatorName;
	}

	public String getConsolidatorDUNS() {
		return consolidatorDUNS;
	}

	public void setConsolidatorDUNS(String consolidatorDUNS) {
		this.consolidatorDUNS = consolidatorDUNS;
	}

	public String getConsolidatorAddressDescriptor() {
		return consolidatorAddressDescriptor;
	}

	public void setConsolidatorAddressDescriptor(String consolidatorAddressDescriptor) {
		this.consolidatorAddressDescriptor = consolidatorAddressDescriptor;
	}

	public String getConsolidatorAddress1() {
		return consolidatorAddress1;
	}

	public void setConsolidatorAddress1(String consolidatorAddress1) {
		this.consolidatorAddress1 = consolidatorAddress1;
	}

	public String getConsolidatorAddress2() {
		return consolidatorAddress2;
	}

	public void setConsolidatorAddress2(String consolidatorAddress2) {
		this.consolidatorAddress2 = consolidatorAddress2;
	}

	public String getConsolidatorAddress3() {
		return consolidatorAddress3;
	}

	public void setConsolidatorAddress3(String consolidatorAddress3) {
		this.consolidatorAddress3 = consolidatorAddress3;
	}

	public String getConsolidatorAddress4() {
		return consolidatorAddress4;
	}

	public void setConsolidatorAddress4(String consolidatorAddress4) {
		this.consolidatorAddress4 = consolidatorAddress4;
	}

	public String getConsolidatorAddress5() {
		return consolidatorAddress5;
	}

	public void setConsolidatorAddress5(String consolidatorAddress5) {
		this.consolidatorAddress5 = consolidatorAddress5;
	}

	public String getConsolidatorCity() {
		return consolidatorCity;
	}

	public void setConsolidatorCity(String consolidatorCity) {
		this.consolidatorCity = consolidatorCity;
	}

	public String getConsolidatorCounty() {
		return consolidatorCounty;
	}

	public void setConsolidatorCounty(String consolidatorCounty) {
		this.consolidatorCounty = consolidatorCounty;
	}

	public String getConsolidatorState() {
		return consolidatorState;
	}

	public void setConsolidatorState(String consolidatorState) {
		this.consolidatorState = consolidatorState;
	}

	public String getConsolidatorCountry() {
		return consolidatorCountry;
	}

	public void setConsolidatorCountry(String consolidatorCountry) {
		this.consolidatorCountry = consolidatorCountry;
	}

	public String getConsolidatorZip() {
		return consolidatorZip;
	}

	public void setConsolidatorZip(String consolidatorZip) {
		this.consolidatorZip = consolidatorZip;
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

	public int getRefOrderLineID() {
		return refOrderLineID;
	}

	public void setRefOrderLineID(int refOrderLineID) {
		this.refOrderLineID = refOrderLineID;
	}

	public int getRefOrderRequestID() {
		return refOrderRequestID;
	}

	public void setRefOrderRequestID(int refOrderRequestID) {
		this.refOrderRequestID = refOrderRequestID;
	}

	public int getRefOrderPromiseID() {
		return refOrderPromiseID;
	}

	public void setRefOrderPromiseID(int refOrderPromiseID) {
		this.refOrderPromiseID = refOrderPromiseID;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getQaRequirementFlag() {
		return qaRequirementFlag;
	}

	public void setQaRequirementFlag(String qaRequirementFlag) {
		this.qaRequirementFlag = qaRequirementFlag;
	}

	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public String getFlexStringASNLine4() {
		return flexStringASNLine4;
	}

	public void setFlexStringASNLine4(String flexStringASNLine4) {
		this.flexStringASNLine4 = flexStringASNLine4;
	}

	public String getFlexStringASNLine5() {
		return flexStringASNLine5;
	}

	public void setFlexStringASNLine5(String flexStringASNLine5) {
		this.flexStringASNLine5 = flexStringASNLine5;
	}

	public String getFlexStringASNLine6() {
		return flexStringASNLine6;
	}

	public void setFlexStringASNLine6(String flexStringASNLine6) {
		this.flexStringASNLine6 = flexStringASNLine6;
	}

	public String getFlexStringASNLine7() {
		return flexStringASNLine7;
	}

	public void setFlexStringASNLine7(String flexStringASNLine7) {
		this.flexStringASNLine7 = flexStringASNLine7;
	}

	public String getFlexStringASNLine8() {
		return flexStringASNLine8;
	}

	public void setFlexStringASNLine8(String flexStringASNLine8) {
		this.flexStringASNLine8 = flexStringASNLine8;
	}

	public String getFlexStringASNLine9() {
		return flexStringASNLine9;
	}

	public void setFlexStringASNLine9(String flexStringASNLine9) {
		this.flexStringASNLine9 = flexStringASNLine9;
	}

	public int getFlexIntASNLine1() {
		return flexIntASNLine1;
	}

	public void setFlexIntASNLine1(int flexIntASNLine1) {
		this.flexIntASNLine1 = flexIntASNLine1;
	}

	public int getFlexIntASNLine2() {
		return flexIntASNLine2;
	}

	public void setFlexIntASNLine2(int flexIntASNLine2) {
		this.flexIntASNLine2 = flexIntASNLine2;
	}

	public int getFlexIntASNLine3() {
		return flexIntASNLine3;
	}

	public void setFlexIntASNLine3(int flexIntASNLine3) {
		this.flexIntASNLine3 = flexIntASNLine3;
	}

	public int getFlexIntASNLine4() {
		return flexIntASNLine4;
	}

	public void setFlexIntASNLine4(int flexIntASNLine4) {
		this.flexIntASNLine4 = flexIntASNLine4;
	}

	public int getFlexIntASNLine5() {
		return flexIntASNLine5;
	}

	public void setFlexIntASNLine5(int flexIntASNLine5) {
		this.flexIntASNLine5 = flexIntASNLine5;
	}

	public Double getFlexFloatASNLine1() {
		return flexFloatASNLine1;
	}

	public void setFlexFloatASNLine1(Double flexFloatASNLine1) {
		this.flexFloatASNLine1 = flexFloatASNLine1;
	}

	public Double getFlexFloatASNLine2() {
		return flexFloatASNLine2;
	}

	public void setFlexFloatASNLine2(Double flexFloatASNLine2) {
		this.flexFloatASNLine2 = flexFloatASNLine2;
	}

	public Double getFlexFloatASNLine3() {
		return flexFloatASNLine3;
	}

	public void setFlexFloatASNLine3(Double flexFloatASNLine3) {
		this.flexFloatASNLine3 = flexFloatASNLine3;
	}

	public Double getFlexFloatASNLine4() {
		return flexFloatASNLine4;
	}

	public void setFlexFloatASNLine4(Double flexFloatASNLine4) {
		this.flexFloatASNLine4 = flexFloatASNLine4;
	}

	public Double getFlexFloatASNLine5() {
		return flexFloatASNLine5;
	}

	public void setFlexFloatASNLine5(Double flexFloatASNLine5) {
		this.flexFloatASNLine5 = flexFloatASNLine5;
	}

	public Date getFlexDateASNLine1() {
		return flexDateASNLine1;
	}

	public void setFlexDateASNLine1(Date flexDateASNLine1) {
		this.flexDateASNLine1 = flexDateASNLine1;
	}

	public Date getFlexDateASNLine2() {
		return flexDateASNLine2;
	}

	public void setFlexDateASNLine2(Date flexDateASNLine2) {
		this.flexDateASNLine2 = flexDateASNLine2;
	}

	public Date getFlexDateASNLine3() {
		return flexDateASNLine3;
	}

	public void setFlexDateASNLine3(Date flexDateASNLine3) {
		this.flexDateASNLine3 = flexDateASNLine3;
	}

	public Date getFlexDateASNLine4() {
		return flexDateASNLine4;
	}

	public void setFlexDateASNLine4(Date flexDateASNLine4) {
		this.flexDateASNLine4 = flexDateASNLine4;
	}

	public Date getFlexDateASNLine5() {
		return flexDateASNLine5;
	}

	public void setFlexDateASNLine5(Date flexDateASNLine5) {
		this.flexDateASNLine5 = flexDateASNLine5;
	}

	public String getShipToCustomerID() {
		return shipToCustomerID;
	}

	public void setShipToCustomerID(String shipToCustomerID) {
		this.shipToCustomerID = shipToCustomerID;
	}

	public String getShipToCustomerDesc() {
		return shipToCustomerDesc;
	}

	public void setShipToCustomerDesc(String shipToCustomerDesc) {
		this.shipToCustomerDesc = shipToCustomerDesc;
	}

	public String getShipToCustomerItemID() {
		return shipToCustomerItemID;
	}

	public void setShipToCustomerItemID(String shipToCustomerItemID) {
		this.shipToCustomerItemID = shipToCustomerItemID;
	}

	public String getShipToCustomerItemDesc() {
		return shipToCustomerItemDesc;
	}

	public void setShipToCustomerItemDesc(String shipToCustomerItemDesc) {
		this.shipToCustomerItemDesc = shipToCustomerItemDesc;
	}

	public String getDropShipmentFlag() {
		return dropShipmentFlag;
	}

	public void setDropShipmentFlag(String dropShipmentFlag) {
		this.dropShipmentFlag = dropShipmentFlag;
	}

	@Override
	public String toString() {
		return "AsnEntity [shipmentId=" + shipmentId + ", custmerId=" + custmerId + ", customerDescription="
				+ customerDescription + ", customerDUNS=" + customerDUNS + ", customerDUNS4=" + customerDUNS4
				+ ", customerAddressDescriptor=" + customerAddressDescriptor + ", customerAddress1=" + customerAddress1
				+ ", customerAddress2=" + customerAddress2 + ", customerAddress3=" + customerAddress3
				+ ", customerAddress4=" + customerAddress4 + ", customerAddress5=" + customerAddress5
				+ ", customerCity=" + customerCity + ", customerCounty=" + customerCounty + ", customerState="
				+ customerState + ", customerCountry=" + customerCountry + ", customerZip=" + customerZip
				+ ", supplierId=" + supplierId + ", supplierDescription=" + supplierDescription + ", supplierDUNS="
				+ supplierDUNS + ", supplierDUNS4=" + supplierDUNS4 + ", supplierAddressDescriptor="
				+ supplierAddressDescriptor + ", supplierAddress1=" + supplierAddress1 + ", supplierAddress2="
				+ supplierAddress2 + ", supplierAddress3=" + supplierAddress3 + ", supplierAddress4=" + supplierAddress4
				+ ", supplierAddress5=" + supplierAddress5 + ", supplierCity=" + supplierCity + ", supplierCounty="
				+ supplierCounty + ", supplierState=" + supplierState + ", supplierCountry=" + supplierCountry
				+ ", supplierZip=" + supplierZip + ", shipmentCreationDate=" + shipmentCreationDate
				+ ", shipmentCreatorCode=" + shipmentCreatorCode + ", shipmentStatus=" + shipmentStatus
				+ ", shipmentDate=" + shipmentDate + ", plannedDeliveryDate=" + plannedDeliveryDate + ", finalSiteETA="
				+ finalSiteETA + ", lastKnownMilestone=" + lastKnownMilestone + ", lastKnownMilestoneArrival="
				+ lastKnownMilestoneArrival + ", lastKnownMilestoneDeparture=" + lastKnownMilestoneDeparture
				+ ", carrier=" + carrier + ", shipmentMode=" + shipmentMode + ", waybillNo=" + waybillNo
				+ ", shipmentDeliveryBOLNo=" + shipmentDeliveryBOLNo + ", packingSlipNo=" + packingSlipNo
				+ ", carrierReferenceNo=" + carrierReferenceNo + ", shipperReferenceNo=" + shipperReferenceNo
				+ ", ShipToTypeSiteOrHub=" + ShipToTypeSiteOrHub + ", countryOfOrigin=" + countryOfOrigin
				+ ", numberOfPackages=" + numberOfPackages + ", licensePlate=" + licensePlate + ", ImporterOfNo="
				+ ImporterOfNo + ", consigneeNo=" + consigneeNo + ", foreignPortOfUnlading=" + foreignPortOfUnlading
				+ ", placeOfDelivery=" + placeOfDelivery + ", shipToSite=" + shipToSite + ", shipToPartyName="
				+ shipToPartyName + ", ShipToPartyDUNS=" + ShipToPartyDUNS + ", shipToAddressDescriptor="
				+ shipToAddressDescriptor + ", shipToAddress1=" + shipToAddress1 + ", shipToAddress2=" + shipToAddress2
				+ ", shipToAddress3=" + shipToAddress3 + ", shipToAddress4=" + shipToAddress4 + ", shipToAddress5="
				+ shipToAddress5 + ", shipToCity=" + shipToCity + ", shipToCounty=" + shipToCounty + ", shipToState="
				+ shipToState + ", shipToCountry=" + shipToCountry + ", shipToZip=" + shipToZip + ", supplierSite="
				+ supplierSite + ", shipFromAddressDescriptor=" + shipFromAddressDescriptor + ", shipFromAddress1="
				+ shipFromAddress1 + ", shipFromAddress2=" + shipFromAddress2 + ", shipFromAddress3=" + shipFromAddress3
				+ ", shipFromAddress4=" + shipFromAddress4 + ", shipFromAddress5=" + shipFromAddress5
				+ ", shipFromCity=" + shipFromCity + ", shipFromCounty=" + shipFromCounty + ", shipFromState="
				+ shipFromState + ", shipFromCountry=" + shipFromCountry + ", shipFromZip=" + shipFromZip + ", billTo="
				+ billTo + ", billToAddressDescriptor=" + billToAddressDescriptor + ", billToAddress1=" + billToAddress1
				+ ", billToAddress2=" + billToAddress2 + ", billToAddress3=" + billToAddress3 + ", billToAddress4="
				+ billToAddress4 + ", billToAddress5=" + billToAddress5 + ", billToCity=" + billToCity
				+ ", billToCounty=" + billToCounty + ", billToState=" + billToState + ", billToCountry=" + billToCountry
				+ ", billToZip=" + billToZip + ", bookingPartyName=" + bookingPartyName + ", bookingPartyDUNS="
				+ bookingPartyDUNS + ", bookingPartyAddressDescriptor=" + bookingPartyAddressDescriptor
				+ ", bookingPartyAddress1=" + bookingPartyAddress1 + ", bookingPartyAddress2=" + bookingPartyAddress2
				+ ", bookingPartyAddress3=" + bookingPartyAddress3 + ", bookingPartyAddress4=" + bookingPartyAddress4
				+ ", bookingPartyAddress5=" + bookingPartyAddress5 + ", bookingPartyCity=" + bookingPartyCity
				+ ", bookingPartyCounty=" + bookingPartyCounty + ", bookingPartyState=" + bookingPartyState
				+ ", bookingPartyZip=" + bookingPartyZip + ", shipmentTrackingUrl=" + shipmentTrackingUrl
				+ ", shipmentTrackingNumber=" + shipmentTrackingNumber + ", proNumber=" + proNumber + ", buyerName="
				+ buyerName + ", buyerPhoeNumber=" + buyerPhoeNumber + ", shipperContactPerson=" + shipperContactPerson
				+ ", shipperContactPhone=" + shipperContactPhone + ", shippingvehicleLicenseNumber="
				+ shippingvehicleLicenseNumber + ", flexStringASNHeader9=" + flexStringASNHeader9
				+ ", flexIntASNHeader1=" + flexIntASNHeader1 + ", flexIntASNHeader2=" + flexIntASNHeader2
				+ ", flexIntASNHeader3=" + flexIntASNHeader3 + ", flexIntASNHeader4=" + flexIntASNHeader4
				+ ", flexIntASNHeader5=" + flexIntASNHeader5 + ", FlexFloatASNHeader1=" + FlexFloatASNHeader1
				+ ", flexfloatASNHeader2=" + flexfloatASNHeader2 + ", flexFloatASNHeader3=" + flexFloatASNHeader3
				+ ", flexFloatASNHeader4=" + flexFloatASNHeader4 + ", flexFloatASNHeader5=" + flexFloatASNHeader5
				+ ", flexDateASNHeader1=" + flexDateASNHeader1 + ", flexDateASNHeader2=" + flexDateASNHeader2
				+ ", flexDateASNHeader3=" + flexDateASNHeader3 + ", flexDateASNHeader4=" + flexDateASNHeader4
				+ ", flexDateASNHeader5=" + flexDateASNHeader5 + ", shipmentLineID=" + shipmentLineID
				+ ", shipmentLineStatus=" + shipmentLineStatus + ", action=" + action + ", customerItemID="
				+ customerItemID + ", customerItemDescription=" + customerItemDescription + ", supplierItemID="
				+ supplierItemID + ", supplierItemDescription=" + supplierItemDescription + ", buyerCode=" + buyerCode
				+ ", shippedQuantity=" + shippedQuantity + ", unitOfMeasure=" + unitOfMeasure + ", freightCost="
				+ freightCost + ", currency=" + currency + ", packingSlipNoLine=" + packingSlipNoLine
				+ ", notesToCustomer=" + notesToCustomer + ", NoOfBoxes=" + NoOfBoxes + ", weight=" + weight
				+ ", commodityHTS6=" + commodityHTS6 + ", manufacturerName=" + manufacturerName + ", manufacturerDUNS="
				+ manufacturerDUNS + ", manufacturerAddressDescriptor=" + manufacturerAddressDescriptor
				+ ", manufacturerAddress1=" + manufacturerAddress1 + ", manufacturerAddress2=" + manufacturerAddress2
				+ ", manufacturerAddress3=" + manufacturerAddress3 + ", manufacturerAddress4=" + manufacturerAddress4
				+ ", manufacturerAddress5=" + manufacturerAddress5 + ", manufacturerCity=" + manufacturerCity
				+ ", manufacturerCounty=" + manufacturerCounty + ", manufacturerState=" + manufacturerState
				+ ", manufacturerCountry=" + manufacturerCountry + ", manufacturerZip=" + manufacturerZip
				+ ", containerStuffingLocationName=" + containerStuffingLocationName
				+ ", containerStuffingLocationDUNS=" + containerStuffingLocationDUNS
				+ ", containerStuffingLocationAddressDescriptor=" + containerStuffingLocationAddressDescriptor
				+ ", containerStuffingLocationAddress1=" + containerStuffingLocationAddress1
				+ ", containerStuffingLocationAddress2=" + containerStuffingLocationAddress2
				+ ", containerStuffingLocationAddress3=" + containerStuffingLocationAddress3
				+ ", containerStuffingLocationAddress4=" + containerStuffingLocationAddress4
				+ ", containerStuffingLocationAddress5=" + containerStuffingLocationAddress5
				+ ", containerStuffingLocationCity=" + containerStuffingLocationCity
				+ ", containerStuffingLocationCounty=" + containerStuffingLocationCounty
				+ ", containerStuffingLocationState=" + containerStuffingLocationState
				+ ", containerStuffingLocationCountry=" + containerStuffingLocationCountry
				+ ", containerStuffingLocationZip=" + containerStuffingLocationZip + ", consolidatorName="
				+ consolidatorName + ", consolidatorDUNS=" + consolidatorDUNS + ", consolidatorAddressDescriptor="
				+ consolidatorAddressDescriptor + ", consolidatorAddress1=" + consolidatorAddress1
				+ ", consolidatorAddress2=" + consolidatorAddress2 + ", consolidatorAddress3=" + consolidatorAddress3
				+ ", consolidatorAddress4=" + consolidatorAddress4 + ", consolidatorAddress5=" + consolidatorAddress5
				+ ", consolidatorCity=" + consolidatorCity + ", consolidatorCounty=" + consolidatorCounty
				+ ", consolidatorState=" + consolidatorState + ", consolidatorCountry=" + consolidatorCountry
				+ ", consolidatorZip=" + consolidatorZip + ", refCustomerID=" + refCustomerID + ", refSupplierID="
				+ refSupplierID + ", refOrderType=" + refOrderType + ", refOrderID=" + refOrderID + ", refOrderLineID="
				+ refOrderLineID + ", refOrderRequestID=" + refOrderRequestID + ", refOrderPromiseID="
				+ refOrderPromiseID + ", serialNumber=" + serialNumber + ", qaRequirementFlag=" + qaRequirementFlag
				+ ", salesOrderNumber=" + salesOrderNumber + ", flexStringASNLine4=" + flexStringASNLine4
				+ ", flexStringASNLine5=" + flexStringASNLine5 + ", flexStringASNLine6=" + flexStringASNLine6
				+ ", flexStringASNLine7=" + flexStringASNLine7 + ", flexStringASNLine8=" + flexStringASNLine8
				+ ", flexStringASNLine9=" + flexStringASNLine9 + ", flexIntASNLine1=" + flexIntASNLine1
				+ ", flexIntASNLine2=" + flexIntASNLine2 + ", flexIntASNLine3=" + flexIntASNLine3 + ", flexIntASNLine4="
				+ flexIntASNLine4 + ", flexIntASNLine5=" + flexIntASNLine5 + ", flexFloatASNLine1=" + flexFloatASNLine1
				+ ", flexFloatASNLine2=" + flexFloatASNLine2 + ", flexFloatASNLine3=" + flexFloatASNLine3
				+ ", flexFloatASNLine4=" + flexFloatASNLine4 + ", flexFloatASNLine5=" + flexFloatASNLine5
				+ ", flexDateASNLine1=" + flexDateASNLine1 + ", flexDateASNLine2=" + flexDateASNLine2
				+ ", flexDateASNLine3=" + flexDateASNLine3 + ", flexDateASNLine4=" + flexDateASNLine4
				+ ", flexDateASNLine5=" + flexDateASNLine5 + ", shipToCustomerID=" + shipToCustomerID
				+ ", shipToCustomerDesc=" + shipToCustomerDesc + ", shipToCustomerItemID=" + shipToCustomerItemID
				+ ", shipToCustomerItemDesc=" + shipToCustomerItemDesc + ", dropShipmentFlag=" + dropShipmentFlag + "]";
	}

	
	
}
