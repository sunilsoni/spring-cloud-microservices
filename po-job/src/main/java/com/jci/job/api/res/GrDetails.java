/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

/**
 * The Class PoDetails.
 */
public class GrDetails {
	
	
	private String receiptID;
	private String receiptDate;
	private String erp;
	private String plant;
	private String region;
	private String supplierType;
	private Object gr;
	
	
	public String getReceiptID() {
		return receiptID;
	}
	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
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
	public Object getGr() {
		return gr;
	}
	public void setGr(Object gr) {
		this.gr = gr;
	}
	
	
	@Override
	public String toString() {
		return "GrDetails [receiptID=" + receiptID + ", receiptDate=" + receiptDate + ", erp=" + erp + ", plant="
				+ plant + ", region=" + region + ", supplierType=" + supplierType + ", gr=" + gr + "]";
	}
	
	
	
}
