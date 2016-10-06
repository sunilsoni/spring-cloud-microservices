/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <p>
 * <strong> The Item Class.</strong>
 * <p>
 *
 * @author csonisk
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	/** The supplier ID. */
	private String supplierID;
	
	/** The customer item ID. */
	private String customerItemID;
	
	/** The erp. */
	private String erp;
	
	/** The plant. */
	private String plant;
	
	/** The region. */
	private String region;
	
	/** The supplier type. */
	private String supplierType;
	
	/** The item. */
	private Object item;

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public Object getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(Object item) {
		this.item = item;
	}

	/**
	 * Gets the supplier ID.
	 *
	 * @return the supplier ID
	 */
	public String getSupplierID() {
		return supplierID;
	}

	/**
	 * Sets the supplier ID.
	 *
	 * @param supplierID the new supplier ID
	 */
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	/**
	 * Gets the customer item ID.
	 *
	 * @return the customer item ID
	 */
	public String getCustomerItemID() {
		return customerItemID;
	}

	/**
	 * Sets the customer item ID.
	 *
	 * @param customerItemID the new customer item ID
	 */
	public void setCustomerItemID(String customerItemID) {
		this.customerItemID = customerItemID;
	}

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

}
