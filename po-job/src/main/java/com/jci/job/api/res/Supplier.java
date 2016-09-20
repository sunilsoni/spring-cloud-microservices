package com.jci.job.api.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Supplier {

	private String supplierID;
	private Object supplier;
	private String erp;
	private String region;
	private String plant;
	private String supplierType;

	public String getErp() {
		return erp;
	}

	public void setErp(String erp) {
		this.erp = erp;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public Object getSupplier() {
		return supplier;
	}

	public void setSupplier(Object supplier) {
		this.supplier = supplier;
	}

}
