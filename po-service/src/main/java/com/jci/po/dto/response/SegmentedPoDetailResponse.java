package com.jci.po.dto.response;

public class SegmentedPoDetailResponse {

	private boolean isError;
	private String message;//OK
	
	private ResultSet resultSet;

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

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	@Override
	public String toString() {
		return "SegmentedPoDetailResponse [isError=" + isError + ", message=" + message + ", resultSet=" + resultSet
				+ "]";
	}
	
	
	
}
