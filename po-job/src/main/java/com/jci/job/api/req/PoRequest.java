package com.jci.job.api.req;

import java.util.Date;

public class PoRequest {

	private String plant;
	private String region;
	private String orderNumber;
	private Date orderCreationDate;

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

	public Date getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(Date orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}

	@Override
	public String toString() {
		return "PoRequest [plant=" + plant + ", region=" + region + ", orderNumber=" + orderNumber
				+ ", orderCreationDate=" + orderCreationDate + "]";
	}
	

}
