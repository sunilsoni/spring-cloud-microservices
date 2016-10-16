/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

/**
 * <p>
 * <strong> The Po Item Class.</strong>
 * <p>
 *
 * @author csonisk
 */
public class PoItem {
	
	/** The order number. */
	private String orderNumber;
	
	/** The line ID. */
	private Integer lineID;
	
	/** The request ID. */
	private Integer requestID;
	
	/** The item. */
	private Object itemList;
	
	/**
	 * Gets the order number.
	 *
	 * @return the order number
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	
	/**
	 * Sets the order number.
	 *
	 * @param orderNumber the new order number
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	/**
	 * Gets the line ID.
	 *
	 * @return the line ID
	 */
	public Integer getLineID() {
		return lineID;
	}
	
	/**
	 * Sets the line ID.
	 *
	 * @param lineID the new line ID
	 */
	public void setLineID(Integer lineID) {
		this.lineID = lineID;
	}
	
	/**
	 * Gets the request ID.
	 *
	 * @return the request ID
	 */
	public Integer getRequestID() {
		return requestID;
	}
	
	/**
	 * Sets the request ID.
	 *
	 * @param requestID the new request ID
	 */
	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
	}

	public Object getItemList() {
		return itemList;
	}

	public void setItemList(Object itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "PoItem [orderNumber=" + orderNumber + ", lineID=" + lineID + ", requestID=" + requestID + ", itemList="
				+ itemList + "]";
	}
	
	
	
}
