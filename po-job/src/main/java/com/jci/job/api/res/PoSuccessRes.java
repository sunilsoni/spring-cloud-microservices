/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import java.util.HashMap;
import java.util.List;

/**
 * The Class PoSuccessRes.
 */
public class PoSuccessRes {
	
	/** The po list. */
	private List<HashMap<String,String>> poList;

	/**
	 * Gets the po list.
	 *
	 * @return the po list
	 */
	public List<HashMap<String, String>> getPoList() {
		return poList;
	}

	/**
	 * Sets the po list.
	 *
	 * @param poList the po list
	 */
	public void setPoList(List<HashMap<String, String>> poList) {
		this.poList = poList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoSuccessRes [poList=" + poList + "]";
	}

	
	
}
