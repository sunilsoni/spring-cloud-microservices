package com.jci.item.dto;

import java.util.ArrayList;
import java.util.HashMap;

import com.jci.item.azure.data.ResultSet;

public class SegmentedDetailRes {

	private boolean isError;
	private String message;//OK
	
	private HashMap<String, ResultSet>  resultSet;
	
	HashMap<String, ArrayList<Integer>> graphData;

	HashMap<String, ResultSet>  supplierData;
	HashMap<String, ResultSet>  itemData;
	
	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<String, ResultSet> getResultSet() {
		return resultSet;
	}

	public void setResultSet(HashMap<String, ResultSet> resultSet) {
		this.resultSet = resultSet;
	}

	public HashMap<String, ArrayList<Integer>> getGraphData() {
		return graphData;
	}

	public void setGraphData(HashMap<String, ArrayList<Integer>> graphData) {
		this.graphData = graphData;
	}

	public HashMap<String, ResultSet> getSupplierData() {
		return supplierData;
	}

	public void setSupplierData(HashMap<String, ResultSet> supplierData) {
		this.supplierData = supplierData;
	}

	public HashMap<String, ResultSet> getItemData() {
		return itemData;
	}

	public void setItemData(HashMap<String, ResultSet> itemData) {
		this.itemData = itemData;
	}

	@Override
	public String toString() {
		return "SegmentedDetailResponse [isError=" + isError + ", message=" + message + ", resultSet=" + resultSet
				+ ", graphData=" + graphData + ", supplierData=" + supplierData + ", itemData=" + itemData + "]";
	}



	
	
}
