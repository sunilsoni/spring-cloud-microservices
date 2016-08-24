package com.jci.po.dto.res;

import com.jci.po.azure.data.ResultSet;

public class PoItemDetailRes {
	
	private boolean isError;
	private String message;
	
	private ResultSet  resultSet;

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
		return "PoItemDetailRes [isError=" + isError + ", message=" + message + ", resultSet=" + resultSet + "]";
	}



	
	
}
