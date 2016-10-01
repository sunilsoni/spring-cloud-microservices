package com.jci.flatfile.utils;

import java.util.List;

public class ProcessErrorRes {


	List<String> successList;
	List<String> errorList;
	
	public List<String> getSuccessList() {
		return successList;
	}
	public void setSuccessList(List<String> successList) {
		this.successList = successList;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	@Override
	public String toString() {
		return "ProcessErrorRes [successList=" + successList + ", errorList=" + errorList + "]";
	}
	
}
