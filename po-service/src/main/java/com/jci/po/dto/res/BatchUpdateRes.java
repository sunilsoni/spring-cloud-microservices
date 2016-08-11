package com.jci.po.dto.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BatchUpdateRes {
	
	private List<String> errorList;
	private List<String> successList;
	HashMap<String, ArrayList<Integer>> graphData;
	private boolean isError;
	private String message;
	
	
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	public List<String> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<String> successList) {
		this.successList = successList;
	}
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
	
	
	public HashMap<String, ArrayList<Integer>> getGraphData() {
		return graphData;
	}
	public void setGraphData(HashMap<String, ArrayList<Integer>> graphData) {
		this.graphData = graphData;
	}
	
	
	@Override
	public String toString() {
		return "BatchUpdateRes [errorList=" + errorList + ", successList=" + successList + ", graphData=" + graphData
				+ ", isError=" + isError + ", message=" + message + "]";
	}


	
}
