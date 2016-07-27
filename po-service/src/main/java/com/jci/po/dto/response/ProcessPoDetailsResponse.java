package com.jci.po.dto.response;

public class ProcessPoDetailsResponse {

	private String message;
	private boolean isProcessed;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isProcessed() {
		return isProcessed;
	}
	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	@Override
	public String toString() {
		return "ProcessPoDetailsResponse [message=" + message + ", isProcessed=" + isProcessed + "]";
	}
	
	
}
