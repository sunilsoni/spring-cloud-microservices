/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import java.util.List;

/**
 * <p>
 * <strong> The Batch Insert Response Class.</strong>
 * <p>
 *
 * @author csonisk
 */

public class BatchInsertRes { // NO_UCD (unused code)
	
	/** The is error. */
 private boolean isError;
	
	/** The success list. */
	private  List<String> successList;
	
	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return isError;
	}
	
	/**
	 * Sets the error.
	 *
	 * @param isError the new error
	 */
	public void setError(boolean isError) {
		this.isError = isError;
	}
	
	/**
	 * Gets the success list.
	 *
	 * @return the success list
	 */
	public List<String> getSuccessList() {
		return successList;
	}
	
	/**
	 * Sets the success list.
	 *
	 * @param successList the new success list
	 */
	public void setSuccessList(List<String> successList) {
		this.successList = successList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BatchInsertRes [isError=" + isError + ", successList=" + successList + "]";
	}
	
}
