package com.jci.job.api.req;

public class ItemBody {
	
	private String erp;
	private String plant;
	private String region;
	private String supplierID;
	private String customerItemID;
	
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
	public String getCustomerItemID() {
		return customerItemID;
	}
	public void setCustomerItemID(String customerItemID) {
		this.customerItemID = customerItemID;
	}
	public String getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	@Override
	public String toString() {
		return "ItemBody [erp=" + erp + ", plant=" + plant + ", region=" + region + ", supplierID=" + supplierID
				+ ", customerItemID=" + customerItemID + "]";
	}
	
	
	
}
