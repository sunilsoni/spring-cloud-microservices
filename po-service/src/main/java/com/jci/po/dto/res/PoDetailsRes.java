package com.jci.po.dto.res;

import java.util.List;

public class PoDetailsRes {
	
	private boolean isError;
	private String message;
	
	private List<PoDetails> successPos;
	private List<PoDetails> errorPos;
	
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
	public List<PoDetails> getSuccessPoDetails() {
		return successPos;
	}
	public void setSuccessPoDetails(List<PoDetails> successPoDetails) {
		this.successPos = successPoDetails;
	}
	public List<PoDetails> getErrorPoDetails() {
		return errorPos;
	}
	public void setErrorPoDetails(List<PoDetails> errorPoDetails) {
		this.errorPos = errorPoDetails;
	}
	@Override
	public String toString() {
		return "PoDetailsRes [isError=" + isError + ", message=" + message + ", successPos=" + successPos
				+ ", errorPos=" + errorPos + "]";
	}



	 

	
}
