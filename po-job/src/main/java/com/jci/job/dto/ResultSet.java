package com.jci.job.dto;

import java.util.HashMap;
import java.util.List;

public class ResultSet {
	
	List<HashMap<String, Object>> successData ;
	List<HashMap<String, String>> errorData ;
	
	public ResultSet() {
		
	}
	
	public ResultSet(List<HashMap<String, Object>> successData,List<HashMap<String, String>> errorData) {
		this.successData = successData ;
		this.errorData = errorData ;
	}

	public List<HashMap<String, Object>> getSuccessData() {
		return successData;
	}

	public void setSuccessData(List<HashMap<String, Object>> successData) {
		this.successData = successData;
	}

	public List<HashMap<String, String>> getErrorData() {
		return errorData;
	}

	public void setErrorData(List<HashMap<String, String>> errorData) {
		this.errorData = errorData;
	}

	@Override
	public String toString() {
		return "ResultSet [successData=" + successData + ", errorData=" + errorData + "]";
	}
	
	
}
