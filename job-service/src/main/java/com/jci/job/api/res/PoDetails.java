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
	
	
	/** The erp. */
	private String erp;
	
	/** The plant. */
	private String plant;
	
	/** The region. */
	private String region;
	
	/** The order number. */
	private String orderNumber;
	
	/** The order creation date. */
	private String orderCreationDate;
	
	/** The po ACK. */
	private Boolean poACK;
	
	/** The asn. */
	private Boolean asn;
	
	/** The gr number. */
	private String grNumber;
	
	/** The supplier type. */
	private String supplierType;
	
	/** The item list. */
	private List<PoItem> itemList;
	
	/**
	 * Gets the erp.
	 *
	 * @return the erp
	 */
	public String getErp() {
		return erp;
	}
	
	/**
	 * Sets the erp.
	 *
	 * @param erp the new erp
	 */
	public void setErp(String erp) {
		this.erp = erp;
	}
	
	/**
	 * Gets the plant.
	 *
	 * @return the plant
	 */
	public String getPlant() {
		return plant;
	}
	
	/**
	 * Sets the plant.
	 *
	 * @param plant the new plant
	 */
	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	
	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	
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
	 * Gets the po ACK.
	 *
	 * @return the po ACK
	 */
	public Boolean getPoACK() {
		return poACK;
	}
	
	/**
	 * Sets the po ACK.
	 *
	 * @param poACK the new po ACK
	 */
	public void setPoACK(Boolean poACK) {
		this.poACK = poACK;
	}
	
	/**
	 * Gets the asn.
	 *
	 * @return the asn
	 */
	public Boolean getAsn() {
		return asn;
	}
	
	/**
	 * Sets the asn.
	 *
	 * @param asn the new asn
	 */
	public void setAsn(Boolean asn) {
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
	 * Gets the supplier type.
	 *
	 * @return the supplier type
	 */
	public String getSupplierType() {
		return supplierType;
	}
	
	/**
	 * Sets the supplier type.
	 *
	 * @param supplierType the new supplier type
	 */
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	
	/**
	 * Gets the item list.
	 *
	 * @return the item list
	 */
	public List<PoItem> getItemList() {
		return itemList;
	}
	
	/**
	 * Sets the item list.
	 *
	 * @param itemList the new item list
	 */
	public void setItemList(List<PoItem> itemList) {
		this.itemList = itemList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoDetails [erp=" + erp + ", plant=" + plant + ", region=" + region + ", orderNumber=" + orderNumber
				+ ", orderCreationDate=" + orderCreationDate + ", poACK=" + poACK + ", asn=" + asn + ", grNumber="
				+ grNumber + ", supplierType=" + supplierType + ", itemList=" + itemList + "]";
	}


	
	
}
