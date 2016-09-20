package com.jci.job.api.req;

public class PoBody {
	
	private String erp;
	private String plant;
	private String region;
	private String orderNumber;
	
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
	@Override
	public String toString() {
		return "PoDetailsReq [erp=" + erp + ", plant=" + plant + ", region=" + region + ", orderNumber=" + orderNumber
				+ "]";
	}
	
	
}
