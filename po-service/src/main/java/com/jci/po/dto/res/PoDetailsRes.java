/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.dto.res;

import java.util.List;


/**
 * <p>
 * <strong> The Class PoDetailsRes.</strong>
 * <p>
 *
 * @author csonisk
 */
public class PoDetailsRes {
	
	/** The is error. */
	private boolean isError;
	
	/** The message. */
	private String message;
	
	/** The success pos. */
	private List<PoDetails> successPos;
	
	/** The error pos. */
	private List<PoDetails> errorPos;
	
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
	 * Gets the success po details.
	 *
	 * @return the success po details
	 */
	public List<PoDetails> getSuccessPoDetails() {
		return successPos;
	}
	
	/**
	 * Sets the success po details.
	 *
	 * @param successPoDetails the new success po details
	 */
	public void setSuccessPoDetails(List<PoDetails> successPoDetails) {
		this.successPos = successPoDetails;
	}
	
	/**
	 * Gets the error po details.
	 *
	 * @return the error po details
	 */
	public List<PoDetails> getErrorPoDetails() {
		return errorPos;
	}
	
	/**
	 * Sets the error po details.
	 *
	 * @param errorPoDetails the new error po details
	 */
	public void setErrorPoDetails(List<PoDetails> errorPoDetails) {
		this.errorPos = errorPoDetails;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoDetailsRes [isError=" + isError + ", message=" + message + ", successPos=" + successPos
				+ ", errorPos=" + errorPos + "]";
	}



	 

	
}
