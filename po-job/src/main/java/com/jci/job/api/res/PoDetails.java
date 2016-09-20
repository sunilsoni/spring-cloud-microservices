/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import java.util.List;

/**
 * The Class PoDetails.
 */
public class PoDetails {
	
	
	private String erp;
	private String plant;
	private String region;
	private String orderNumber;
	private String orderCreationDate;
	private Boolean poACK;
	private Boolean asn;
	private String grNumber;
	private String supplierType;
	private List<PoItem> itemList;
	
	public String getErp() {
		return erp;
	}
	public void setErp(String erp) {
		this.erp = erp;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderCreationDate() {
		return orderCreationDate;
	}
	public void setOrderCreationDate(String orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}
	public Boolean getPoACK() {
		return poACK;
	}
	public void setPoACK(Boolean poACK) {
		this.poACK = poACK;
	}
	public Boolean getAsn() {
		return asn;
	}
	public void setAsn(Boolean asn) {
		this.asn = asn;
	}
	public String getGrNumber() {
		return grNumber;
	}
	public void setGrNumber(String grNumber) {
		this.grNumber = grNumber;
	}
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public List<PoItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<PoItem> itemList) {
		this.itemList = itemList;
	}
	
	@Override
	public String toString() {
		return "PoDetails [erp=" + erp + ", plant=" + plant + ", region=" + region + ", orderNumber=" + orderNumber
				+ ", orderCreationDate=" + orderCreationDate + ", poACK=" + poACK + ", asn=" + asn + ", grNumber="
				+ grNumber + ", supplierType=" + supplierType + ", itemList=" + itemList + "]";
	}


	
	
}
