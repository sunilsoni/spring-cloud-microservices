package com.jci.job.api.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	private String supplierID;
	private String customerItemID;
	private String erp;
	private String plant;
	private String region;
	private String supplierType;
	private Object item;

	public Object getItem() {
		return item;
	}

	public void setItem(Object item) {
		this.item = item;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getCustomerItemID() {
		return customerItemID;
	}

	public void setCustomerItemID(String customerItemID) {
		this.customerItemID = customerItemID;
	}

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

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

}
