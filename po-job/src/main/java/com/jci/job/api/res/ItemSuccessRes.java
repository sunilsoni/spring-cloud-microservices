/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import java.util.HashMap;
import java.util.List;

/**
 * The Class ItemSuccessRes.
 */
public class ItemSuccessRes {
	
	/** The item list. */
	private List<HashMap<String,String>> itemList;

	/**
	 * Gets the item list.
	 *
	 * @return the item list
	 */
	public List<HashMap<String, String>> getItemList() {
		return itemList;
	}

	/**
	 * Sets the item list.
	 *
	 * @param itemList the item list
	 */
	public void setItemList(List<HashMap<String, String>> itemList) {
		this.itemList = itemList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemSuccessRes [itemList=" + itemList + "]";
	}

	
	
}
