/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.dto.res;

import com.jci.azure.ResultSet;



/**
 * <p>
 * <strong> The Class PoItemDetailRes.</strong>
 * <p>
 *
 * @author csonisk
 */
public class PoItemDetailRes {
	
	/** The is error. */
	private boolean isError;
	
	/** The message. */
	private String message;
	
	/** The result set. */
	private ResultSet  resultSet;

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
	 * Gets the result set.
	 *
	 * @return the result set
	 */
	public ResultSet getResultSet() {
		return resultSet;
	}

	/**
	 * Sets the result set.
	 *
	 * @param resultSet the new result set
	 */
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoItemDetailRes [isError=" + isError + ", message=" + message + ", resultSet=" + resultSet + "]";
	}
}
