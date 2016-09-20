/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.dto.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * <strong> The Class BatchUpdateRes.</strong>
 * <p>
 *
 * @author csonisk
 */
public class BatchUpdateRes {
	
	/** The error list. */
	private List<String> errorList;
	
	/** The success list. */
	private List<String> successList;
	
	/** The graph data. */
	private HashMap<String, ArrayList<Integer>> graphData;
	
	/** The is error. */
	private boolean isError;
	
	/** The message. */
	private String message;
	
	
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
	 * Gets the graph data.
	 *
	 * @return the graph data
	 */
	public HashMap<String, ArrayList<Integer>> getGraphData() {
		return graphData;
	}
	
	/**
	 * Sets the graph data.
	 *
	 * @param graphData the graph data
	 */
	public void setGraphData(HashMap<String, ArrayList<Integer>> graphData) {
		this.graphData = graphData;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BatchUpdateRes [errorList=" + errorList + ", successList=" + successList + ", graphData=" + graphData
				+ ", isError=" + isError + ", message=" + message + "]";
	}


	
}
