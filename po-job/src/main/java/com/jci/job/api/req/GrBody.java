package com.jci.job.api.req;

public class GrBody {
	
	private String receiptID;
	private String erp;
	private String region;
	private String plant;
	
	public String getReceiptID() {
		return receiptID;
	}
	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}
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
	
	@Override
	public String toString() {
		return "GrBody [receiptID=" + receiptID + ", erp=" + erp + ", region=" + region + ", plant=" + plant + "]";
	}
	


}
