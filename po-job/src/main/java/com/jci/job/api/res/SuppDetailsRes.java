/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import java.util.List;

/**
 * The Class SuppDetailsRes.
 */
public class SuppDetailsRes {

	/** The code. */
	private int code;
	
	/** The status. */
	private String status;
	
	/** The message. */
	private String message;
	
	/** The date. */
	private String date;
	
	/** The supp list. */
	private List<Object> suppList;

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
	 * Gets the supp list.
	 *
	 * @return the supp list
	 */
	public List<Object> getSuppList() {
		return suppList;
	}

	/**
	 * Sets the supp list.
	 *
	 * @param suppList the new supp list
	 */
	public void setSuppList(List<Object> suppList) {
		this.suppList = suppList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SuppDetailsRes [code=" + code + ", status=" + status + ", message=" + message + ", date=" + date
				+ ", suppList=" + suppList + "]";
	}

}
