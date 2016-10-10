package com.jci.po.dto.res;

import java.util.List;


/**
 * The Class ProcessErrorRes.
 */
public class ProcessErrorRes {
	
	/** The error list. */
	private List<String> errorList;
	
	/** The success list. */
	private List<String> successList;
	
	/**
	 * Gets the error list.
	 *
	 * @return the error list
	 */
	public List<String> getErrorList() {
		return errorList;
	}
	
	/**
	 * Sets the error list.
	 *
	 * @param errorList the new error list
	 */
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
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
		return "ProcessErrorRes [errorList=" + errorList + ", successList=" + successList + "]";
	}

	
	
}
