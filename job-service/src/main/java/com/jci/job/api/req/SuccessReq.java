/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.req;

import java.util.List;

/**
 * <p>
 * <strong> The Success Request Class.</strong>
 * <p>
 *
 * @author csonisk
 */
public class SuccessReq {

	/** The po list. */
	private List<Object> poList;
	
	/** The item list. */
	private List<Object> itemList;
	
	/** The supplier list. */
	private List<Object> supplierList;
	
	/** The gr list. */
	private List<Object> grList;

	/**
	 * Gets the po list.
	 *
	 * @return the po list
	 */
	public List<Object> getPoList() {
		return poList;
	}

	/**
	 * Sets the po list.
	 *
	 * @param poList the new po list
	 */
	public void setPoList(List<Object> poList) {
		this.poList = poList;
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
	 * Gets the supplier list.
	 *
	 * @return the supplier list
	 */
	public List<Object> getSupplierList() {
		return supplierList;
	}

	/**
	 * Sets the supplier list.
	 *
	 * @param supplierList the new supplier list
	 */
	public void setSupplierList(List<Object> supplierList) {
		this.supplierList = supplierList;
	}

	
	/**
	 * Gets the gr list.
	 *
	 * @return the gr list
	 */
	public List<Object> getGrList() {
		return grList;
	}

	/**
	 * Sets the gr list.
	 *
	 * @param grList the new gr list
	 */
	public void setGrList(List<Object> grList) {
		this.grList = grList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SuccessReq [poList=" + poList + ", itemList=" + itemList + ", supplierList=" + supplierList
				+ ", grList=" + grList + "]";
	}





	
}
