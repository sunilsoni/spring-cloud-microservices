package com.jci.po.dto.res;

import java.util.List;

public class ProcessErrorRes {
	
	private List<String> errorList;
	private List<String> successList;
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
	@Override
	public String toString() {
		return "ProcessErrorRes [errorList=" + errorList + ", successList=" + successList + "]";
	}

	
	
}
