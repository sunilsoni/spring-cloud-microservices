/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import java.util.List;

/**
 * <p>
 * <strong> The Supplier Details Response Class.</strong>
 * <p>
 *
 * @author csonisk
 */
public class SuppDetailsRes {

	/** The date. */
	private String date;
	
	/** The message. */
	private String message;
	
	/** The status. */
	private String status;
	
	/** The code. */
	private int code;
	
	/** The supplier list. */
	private List<Supplier> supplierList;
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * Gets the supplier list.
	 *
	 * @return the supplier list
	 */
	public List<Supplier> getSupplierList() {
		return supplierList;
	}
	
	/**
	 * Sets the supplier list.
	 *
	 * @param supplierList the new supplier list
	 */
	public void setSupplierList(List<Supplier> supplierList) {
		this.supplierList = supplierList;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SuppDetailsRes [date=" + date + ", message=" + message + ", status=" + status + ", code=" + code
				+ ", supplierList=" + supplierList + "]";
	}
	
	
}
