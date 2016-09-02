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

	/** The order number. */
	private String orderNumber;
	
	/** The supp type. */
	private String suppType;
	
	/** The po ACK. */
	private boolean poACK;
	
	/** The asn. */
	private boolean asn;
	
	/** The gr number. */
	private String grNumber;
	
	/** The order creation date. */
	private String orderCreationDate;	
	
	/** The item list. */
	private List<Object> itemList;


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
	 * Checks if is po ACK.
	 *
	 * @return true, if is po ACK
	 */
	public boolean isPoACK() {
		return poACK;
	}


	/**
	 * Sets the po ACK.
	 *
	 * @param poACK the new po ACK
	 */
	public void setPoACK(boolean poACK) {
		this.poACK = poACK;
	}


	/**
	 * Checks if is asn.
	 *
	 * @return true, if is asn
	 */
	public boolean isAsn() {
		return asn;
	}


	/**
	 * Sets the asn.
	 *
	 * @param asn the new asn
	 */
	public void setAsn(boolean asn) {
		this.asn = asn;
	}


	/**
	 * Gets the gr number.
	 *
	 * @return the gr number
	 */
	public String getGrNumber() {
		return grNumber;
	}


	/**
	 * Sets the gr number.
	 *
	 * @param grNumber the new gr number
	 */
	public void setGrNumber(String grNumber) {
		this.grNumber = grNumber;
	}


	/**
	 * Gets the order creation date.
	 *
	 * @return the order creation date
	 */
	public String getOrderCreationDate() {
		return orderCreationDate;
	}


	/**
	 * Sets the order creation date.
	 *
	 * @param orderCreationDate the new order creation date
	 */
	public void setOrderCreationDate(String orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}


	/**
	 * Gets the item list.
	 *
	 * @return the item list
	 */
	public List<Object> getItemList() {
		return itemList;
	}


	/**
	 * Sets the item list.
	 *
	 * @param itemList the new item list
	 */
	public void setItemList(List<Object> itemList) {
		this.itemList = itemList;
	}


	/**
	 * Gets the supp type.
	 *
	 * @return the supp type
	 */
	public String getSuppType() {
		return suppType;
	}


	/**
	 * Sets the supp type.
	 *
	 * @param suppType the new supp type
	 */
	public void setSuppType(String suppType) {
		this.suppType = suppType;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoDetails [orderNumber=" + orderNumber + ", suppType=" + suppType + ", poACK=" + poACK + ", asn=" + asn
				+ ", grNumber=" + grNumber + ", orderCreationDate=" + orderCreationDate + ", itemList=" + itemList
				+ "]";
	}


	
	
}
