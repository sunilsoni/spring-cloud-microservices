/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import java.util.List;

import com.jci.dto.GrDetails;

/**
 * <p>
 * <strong> The Goods Receipt Details Response Class.</strong>
 * <p>
 *
 * @author csonisk
 */
public class GrDetailsRes {
	
	
	/** The date. */
	private String date;
	
	/** The message. */
	private String message;
	
	/** The status. */
	private String status;
	
	/** The code. */
	private Integer code;
	
	/** The gr list. */
	private List<GrDetails> grList;
	
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
	public Integer getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * Gets the gr list.
	 *
	 * @return the gr list
	 */
	public List<GrDetails> getGrList() {
		return grList;
	}
	
	/**
	 * Sets the gr list.
	 *
	 * @param grList the new gr list
	 */
	public void setGrList(List<GrDetails> grList) {
		this.grList = grList;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GrDetailsRes [date=" + date + ", message=" + message + ", status=" + status + ", code=" + code
				+ ", grList=" + grList + "]";
	}
	



}
