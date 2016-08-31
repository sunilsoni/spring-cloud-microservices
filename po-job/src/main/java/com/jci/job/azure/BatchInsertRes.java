package com.jci.job.azure;

import java.util.List;

public class BatchInsertRes { // NO_UCD (unused code)
	
	private boolean isError;
	private  List<String> successList;
	
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public List<String> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<String> successList) {
		this.successList = successList;
	}
	
	@Override
	public String toString() {
		return "BatchInsertRes [isError=" + isError + ", successList=" + successList + "]";
	}
	
}
