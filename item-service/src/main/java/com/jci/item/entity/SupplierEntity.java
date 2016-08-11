package com.jci.item.entity;

import java.util.Date;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class SupplierEntity extends TableServiceEntity {

	public SupplierEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	public SupplierEntity() {
	}

	private String supplierNumber;
	private String status;
	private String message;
	private Date date;

	/*
	 * 1*
	 */

	private String EnterpriseCode;
	/* 2* */
	private String SupplierID;//PK
	/* 3* */
	private String SupplierDescription;
	/* 4 */
	private String dUNS;
	/* 5 */
	private String globalSupplierID;
	/* 6 */
	private String supplierTaxNumber;
	/* 7 */
	private String supplierAddressExternalId;
	/* 8 */
	private String supplierAddressDescriptor;
	/* 9 */
	private String supplierAddress1;
	/* 10 */
	private String supplierAddress2;
	/* 11 */

	private String supplierAddress3;
	/* 12 */
	private String supplierAddress4;
	/* 13 */
	private String supplierAddress5;
	/* 14 */
	private String supplierCity;
	/* 15 */
	private String supplierCounty;
	/* 16 */
	private String supplierState;
	/* 17 */
	private String supplierCountry;
	/* 18 */
	private String supplierZip;
	/* 19 */
	private String remitToAddressExternalId;
	/* 20 */
	private String remitToAddressDescriptor;
	/* 21 */
	private String remitToAddress1;
	/* 22 */
	private String remitToAddress2;
	/* 23 */
	private String remitToAddress3;
	/* 24 */
	private String remitToAddress4;
	/* 25 */
	private String remitToAddress5;
	/* 26 */
	private String remitToCity;
	/* 27 */
	private String remitToCounty;
	/* 28 */
	private String remitToState;
	/* 29 */
	private String remitToCountry;
	/* 30 */
	private String remitToZip;
	/* 31 */
	private String flexStringSupplier01;
	/* 32 */
	private String flexStringSupplier02;
	/* 33 */
	private String flexStringSupplier03;
	/* 34 */
	private String flexStringSupplier04;
	/* 35 */
	private String flexStringSupplier05;
	/* 36 */
	private String supplierPromiseNeeded;
	/* 37 */
	private String supplierHoldFlag;
	/* 38 */
	private String supplierStatus;
	/* 39 */
	private String bPORemainingQtyTol;
	/* 40 */
	private String supplierShipFromSiteInfo;
	/* 41 */
	private String supplierShipFromSiteDescription;
	/* 42 */
	private int transitLeadTime;
	
	
	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
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

	public String getEnterpriseCode() {
		return EnterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		EnterpriseCode = enterpriseCode;
	}

	public String getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(String supplierID) {
		SupplierID = supplierID;
	}

	public String getSupplierDescription() {
		return SupplierDescription;
	}

	public void setSupplierDescription(String supplierDescription) {
		SupplierDescription = supplierDescription;
	}

	public String getdUNS() {
		return dUNS;
	}

	public void setdUNS(String dUNS) {
		this.dUNS = dUNS;
	}

	public String getGlobalSupplierID() {
		return globalSupplierID;
	}

	public void setGlobalSupplierID(String globalSupplierID) {
		this.globalSupplierID = globalSupplierID;
	}

	public String getSupplierTaxNumber() {
		return supplierTaxNumber;
	}

	public void setSupplierTaxNumber(String supplierTaxNumber) {
		this.supplierTaxNumber = supplierTaxNumber;
	}

	public String getSupplierAddressExternalId() {
		return supplierAddressExternalId;
	}

	public void setSupplierAddressExternalId(String supplierAddressExternalId) {
		this.supplierAddressExternalId = supplierAddressExternalId;
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

	public String getRemitToAddressExternalId() {
		return remitToAddressExternalId;
	}

	public void setRemitToAddressExternalId(String remitToAddressExternalId) {
		this.remitToAddressExternalId = remitToAddressExternalId;
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

	public String getFlexStringSupplier01() {
		return flexStringSupplier01;
	}

	public void setFlexStringSupplier01(String flexStringSupplier01) {
		this.flexStringSupplier01 = flexStringSupplier01;
	}

	public String getFlexStringSupplier02() {
		return flexStringSupplier02;
	}

	public void setFlexStringSupplier02(String flexStringSupplier02) {
		this.flexStringSupplier02 = flexStringSupplier02;
	}

	public String getFlexStringSupplier03() {
		return flexStringSupplier03;
	}

	public void setFlexStringSupplier03(String flexStringSupplier03) {
		this.flexStringSupplier03 = flexStringSupplier03;
	}

	public String getFlexStringSupplier04() {
		return flexStringSupplier04;
	}

	public void setFlexStringSupplier04(String flexStringSupplier04) {
		this.flexStringSupplier04 = flexStringSupplier04;
	}

	public String getFlexStringSupplier05() {
		return flexStringSupplier05;
	}

	public void setFlexStringSupplier05(String flexStringSupplier05) {
		this.flexStringSupplier05 = flexStringSupplier05;
	}

	public String getSupplierPromiseNeeded() {
		return supplierPromiseNeeded;
	}

	public void setSupplierPromiseNeeded(String supplierPromiseNeeded) {
		this.supplierPromiseNeeded = supplierPromiseNeeded;
	}

	public String getSupplierHoldFlag() {
		return supplierHoldFlag;
	}

	public void setSupplierHoldFlag(String supplierHoldFlag) {
		this.supplierHoldFlag = supplierHoldFlag;
	}

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public String getbPORemainingQtyTol() {
		return bPORemainingQtyTol;
	}

	public void setbPORemainingQtyTol(String bPORemainingQtyTol) {
		this.bPORemainingQtyTol = bPORemainingQtyTol;
	}

	public String getSupplierShipFromSiteInfo() {
		return supplierShipFromSiteInfo;
	}

	public void setSupplierShipFromSiteInfo(String supplierShipFromSiteInfo) {
		this.supplierShipFromSiteInfo = supplierShipFromSiteInfo;
	}

	public String getSupplierShipFromSiteDescription() {
		return supplierShipFromSiteDescription;
	}

	public void setSupplierShipFromSiteDescription(String supplierShipFromSiteDescription) {
		this.supplierShipFromSiteDescription = supplierShipFromSiteDescription;
	}

	public int getTransitLeadTime() {
		return transitLeadTime;
	}

	public void setTransitLeadTime(int transitLeadTime) {
		this.transitLeadTime = transitLeadTime;
	}

	@Override
	public String toString() {
		return "SupplierEntity [supplierNumber=" + supplierNumber + ", status=" + status + ", message=" + message
				+ ", date=" + date + ", EnterpriseCode=" + EnterpriseCode + ", SupplierID=" + SupplierID
				+ ", SupplierDescription=" + SupplierDescription + ", dUNS=" + dUNS + ", globalSupplierID="
				+ globalSupplierID + ", supplierTaxNumber=" + supplierTaxNumber + ", supplierAddressExternalId="
				+ supplierAddressExternalId + ", supplierAddressDescriptor=" + supplierAddressDescriptor
				+ ", supplierAddress1=" + supplierAddress1 + ", supplierAddress2=" + supplierAddress2
				+ ", supplierAddress3=" + supplierAddress3 + ", supplierAddress4=" + supplierAddress4
				+ ", supplierAddress5=" + supplierAddress5 + ", supplierCity=" + supplierCity + ", supplierCounty="
				+ supplierCounty + ", supplierState=" + supplierState + ", supplierCountry=" + supplierCountry
				+ ", supplierZip=" + supplierZip + ", remitToAddressExternalId=" + remitToAddressExternalId
				+ ", remitToAddressDescriptor=" + remitToAddressDescriptor + ", remitToAddress1=" + remitToAddress1
				+ ", remitToAddress2=" + remitToAddress2 + ", remitToAddress3=" + remitToAddress3 + ", remitToAddress4="
				+ remitToAddress4 + ", remitToAddress5=" + remitToAddress5 + ", remitToCity=" + remitToCity
				+ ", remitToCounty=" + remitToCounty + ", remitToState=" + remitToState + ", remitToCountry="
				+ remitToCountry + ", remitToZip=" + remitToZip + ", flexStringSupplier01=" + flexStringSupplier01
				+ ", flexStringSupplier02=" + flexStringSupplier02 + ", flexStringSupplier03=" + flexStringSupplier03
				+ ", flexStringSupplier04=" + flexStringSupplier04 + ", flexStringSupplier05=" + flexStringSupplier05
				+ ", supplierPromiseNeeded=" + supplierPromiseNeeded + ", supplierHoldFlag=" + supplierHoldFlag
				+ ", supplierStatus=" + supplierStatus + ", bPORemainingQtyTol=" + bPORemainingQtyTol
				+ ", supplierShipFromSiteInfo=" + supplierShipFromSiteInfo + ", supplierShipFromSiteDescription="
				+ supplierShipFromSiteDescription + ", transitLeadTime=" + transitLeadTime + "]";
	}

}
