/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.supp.dto;

import java.util.ArrayList;
import java.util.HashMap;

import com.jci.supp.azure.data.ResultSet;


/**
 * <p>
 * <strong>The Class SegmentedDetailRes.</strong>
 * <p>
 *
 * @author csonisk
 */
public class SegmentedDetailRes {

	/** The is error. */
	private boolean isError;
	
	/** The message. */
	private String message;//OK
	
	/** The result set. */
	private HashMap<String, ResultSet>  resultSet;
	
	/** The graph data. */
	private HashMap<String, ArrayList<Integer>> graphData;

	/** The supplier data. */
	private HashMap<String, ResultSet>  supplierData;
	
	/** The item data. */
	private HashMap<String, ResultSet>  itemData;
	
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
	public HashMap<String, ResultSet> getResultSet() {
		return resultSet;
	}

	/**
	 * Sets the result set.
	 *
	 * @param resultSet the result set
	 */
	public void setResultSet(HashMap<String, ResultSet> resultSet) {
		this.resultSet = resultSet;
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

	/**
	 * Gets the supplier data.
	 *
	 * @return the supplier data
	 */
	public HashMap<String, ResultSet> getSupplierData() {
		return supplierData;
	}

	/**
	 * Sets the supplier data.
	 *
	 * @param supplierData the supplier data
	 */
	public void setSupplierData(HashMap<String, ResultSet> supplierData) {
		this.supplierData = supplierData;
	}

	/**
	 * Gets the item data.
	 *
	 * @return the item data
	 */
	public HashMap<String, ResultSet> getItemData() {
		return itemData;
	}

	/**
	 * Sets the item data.
	 *
	 * @param itemData the item data
	 */
	public void setItemData(HashMap<String, ResultSet> itemData) {
		this.itemData = itemData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SegmentedDetailResponse [isError=" + isError + ", message=" + message + ", resultSet=" + resultSet
				+ ", graphData=" + graphData + ", supplierData=" + supplierData + ", itemData=" + itemData + "]";
	}



	
	
}
