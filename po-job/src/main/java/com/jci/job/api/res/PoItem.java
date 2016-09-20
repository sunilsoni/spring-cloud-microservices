package com.jci.job.api.res;

public class PoItem {
	
	private String orderNumber;
	private Integer lineID;
	private Integer requestID;
	private Object item;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getLineID() {
		return lineID;
	}
	public void setLineID(Integer lineID) {
		this.lineID = lineID;
	}
	public Integer getRequestID() {
		return requestID;
	}
	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
	}
	public Object getItem() {
		return item;
	}
	public void setItem(Object item) {
		this.item = item;
	}
	
	@Override
	public String toString() {
		return "PoItem [orderNumber=" + orderNumber + ", lineID=" + lineID + ", requestID=" + requestID + ", item="
				+ item + "]";
	}
	
	
}
