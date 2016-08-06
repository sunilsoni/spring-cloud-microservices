package com.jci.po.dto.res;

import java.util.List;

public class PoDetailsReq {
	
	private String message;
	private boolean isError;
	private List<String> errorData;
	private List<String> successData;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public List<String> getErrorData() {
		return errorData;
	}
	public void setErrorData(List<String> errorData) {
		this.errorData = errorData;
	}
	public List<String> getSuccessData() {
		return successData;
	}
	public void setSuccessData(List<String> successData) {
		this.successData = successData;
	}
	
	@Override
	public String toString() {
		return "PoDetailsRequest [message=" + message + ", isError=" + isError + ", errorData=" + errorData
				+ ", successData=" + successData + "]";
	}
}
