/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import java.util.HashMap;
import java.util.List;

/**
 * The Class SuppSuccessRes.
 */
public class SuppSuccessRes {
	
	/** The supp list. */
	private List<HashMap<String,String>> suppList;

	/**
	 * Gets the supp list.
	 *
	 * @return the supp list
	 */
	public List<HashMap<String, String>> getSuppList() {
		return suppList;
	}

	/**
	 * Sets the supp list.
	 *
	 * @param suppList the supp list
	 */
	public void setSuppList(List<HashMap<String, String>> suppList) {
		this.suppList = suppList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SuppSuccessRes [suppList=" + suppList + "]";
	}

	
	
	
	
}
