package com.jci.item.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class SupplierEntity extends TableServiceEntity {

	public SupplierEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	public SupplierEntity() {
	}


	/*
	 * 1*
	 */

	private String enterpriseCode;
	/* 2* */
	private String supplierId;
	/* 3* */
	private String supplierDescription;
	/* 4 */
	private String DUNS;
	/* 5 */
	private String globalSupplierId;
	/* 6 */
	private String supplierTaaxNumber;
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
	private String remitToAddess2;
	/* 23 */
	private String remitToAddess3;
	/* 24 */
	private String remitToAddess4;
	/* 25 */
	private String remitToAddess5;
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
	private String FlexStringSupplier01;
	/* 32 */
	private String FlexStringSupplier02;
	/* 33 */
	private String FlexStringSupplier03;
	/* 34 */
	private String FlexStringSupplier04;
	/* 35 */
	private String FlexStringSupplier05;
	/* 36 */
	private String supplierPromiseNeeded;
	/* 37 */
	private String supplierHoldFlag;
	/* 38 */
	private String supplierStatus;
	/* 39 */
	private Double bpoRemainingQtyTol;
	/* 40 */
	private String supplierShipFromSiteInfo;
	/* 41 */
	private String supplierShipFromSiteDescription;
	/* 42 */
	private int transitLeadTime;

	
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
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
	public String getDUNS() {
		return DUNS;
	}
	public void setDUNS(String dUNS) {
		DUNS = dUNS;
	}
	public String getGlobalSupplierId() {
		return globalSupplierId;
	}
	public void setGlobalSupplierId(String globalSupplierId) {
		this.globalSupplierId = globalSupplierId;
	}
	public String getSupplierTaaxNumber() {
		return supplierTaaxNumber;
	}
	public void setSupplierTaaxNumber(String supplierTaaxNumber) {
		this.supplierTaaxNumber = supplierTaaxNumber;
	}
	public String getSupplierAddressExternalId() {
		return supplierAddressExternalId;
	}
	public void setSupplierAddressExternalId(String supplierAddressExternalId) {
		this.supplierAddressExternalId = supplierAddressExternalId;
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
	public String getRemitToAddess2() {
		return remitToAddess2;
	}
	public void setRemitToAddess2(String remitToAddess2) {
		this.remitToAddess2 = remitToAddess2;
	}
	public String getRemitToAddess3() {
		return remitToAddess3;
	}
	public void setRemitToAddess3(String remitToAddess3) {
		this.remitToAddess3 = remitToAddess3;
	}
	public String getRemitToAddess4() {
		return remitToAddess4;
	}
	public void setRemitToAddess4(String remitToAddess4) {
		this.remitToAddess4 = remitToAddess4;
	}
	public String getRemitToAddess5() {
		return remitToAddess5;
	}
	public void setRemitToAddess5(String remitToAddess5) {
		this.remitToAddess5 = remitToAddess5;
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
		return FlexStringSupplier01;
	}
	public void setFlexStringSupplier01(String flexStringSupplier01) {
		FlexStringSupplier01 = flexStringSupplier01;
	}
	public String getFlexStringSupplier02() {
		return FlexStringSupplier02;
	}
	public void setFlexStringSupplier02(String flexStringSupplier02) {
		FlexStringSupplier02 = flexStringSupplier02;
	}
	public String getFlexStringSupplier03() {
		return FlexStringSupplier03;
	}
	public void setFlexStringSupplier03(String flexStringSupplier03) {
		FlexStringSupplier03 = flexStringSupplier03;
	}
	public String getFlexStringSupplier04() {
		return FlexStringSupplier04;
	}
	public void setFlexStringSupplier04(String flexStringSupplier04) {
		FlexStringSupplier04 = flexStringSupplier04;
	}
	public String getFlexStringSupplier05() {
		return FlexStringSupplier05;
	}
	public void setFlexStringSupplier05(String flexStringSupplier05) {
		FlexStringSupplier05 = flexStringSupplier05;
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
	public Double getBpoRemainingQtyTol() {
		return bpoRemainingQtyTol;
	}
	public void setBpoRemainingQtyTol(Double bpoRemainingQtyTol) {
		this.bpoRemainingQtyTol = bpoRemainingQtyTol;
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

	public String getSupplierAddressDescriptor() {
		return supplierAddressDescriptor;
	}

	public void setSupplierAddressDescriptor(String supplierAddressDescriptor) {
		this.supplierAddressDescriptor = supplierAddressDescriptor;
	}

	@Override
	public String toString() {
		return "SupplierEntity [enterpriseCode=" + enterpriseCode + ", supplierId=" + supplierId
				+ ", supplierDescription=" + supplierDescription + ", DUNS=" + DUNS + ", globalSupplierId="
				+ globalSupplierId + ", supplierTaaxNumber=" + supplierTaaxNumber + ", supplierAddressExternalId="
				+ supplierAddressExternalId + ", supplierAddressDescriptor=" + supplierAddressDescriptor
				+ ", supplierAddress1=" + supplierAddress1 + ", supplierAddress2=" + supplierAddress2
				+ ", supplierAddress3=" + supplierAddress3 + ", supplierAddress4=" + supplierAddress4
				+ ", supplierAddress5=" + supplierAddress5 + ", supplierCity=" + supplierCity + ", supplierCounty="
				+ supplierCounty + ", supplierState=" + supplierState + ", supplierCountry=" + supplierCountry
				+ ", supplierZip=" + supplierZip + ", remitToAddressExternalId=" + remitToAddressExternalId
				+ ", remitToAddressDescriptor=" + remitToAddressDescriptor + ", remitToAddress1=" + remitToAddress1
				+ ", remitToAddess2=" + remitToAddess2 + ", remitToAddess3=" + remitToAddess3 + ", remitToAddess4="
				+ remitToAddess4 + ", remitToAddess5=" + remitToAddess5 + ", remitToCity=" + remitToCity
				+ ", remitToCounty=" + remitToCounty + ", remitToState=" + remitToState + ", remitToCountry="
				+ remitToCountry + ", remitToZip=" + remitToZip + ", FlexStringSupplier01=" + FlexStringSupplier01
				+ ", FlexStringSupplier02=" + FlexStringSupplier02 + ", FlexStringSupplier03=" + FlexStringSupplier03
				+ ", FlexStringSupplier04=" + FlexStringSupplier04 + ", FlexStringSupplier05=" + FlexStringSupplier05
				+ ", supplierPromiseNeeded=" + supplierPromiseNeeded + ", supplierHoldFlag=" + supplierHoldFlag
				+ ", supplierStatus=" + supplierStatus + ", bpoRemainingQtyTol=" + bpoRemainingQtyTol
				+ ", supplierShipFromSiteInfo=" + supplierShipFromSiteInfo + ", supplierShipFromSiteDescription="
				+ supplierShipFromSiteDescription + ", transitLeadTime=" + transitLeadTime + "]";
	}

	
	
	
}
