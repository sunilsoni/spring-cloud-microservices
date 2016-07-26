package com.jci.po.dto.request;

public class PoDetailsRequest {
	
	private boolean isFirstSearch;

	
	public boolean isFirstSearch() {
		return isFirstSearch;
	}

	public void setFirstSearch(boolean isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	@Override
	public String toString() {
		return "PoDetailsRequest [isFirstSearch=" + isFirstSearch + "]";
	}




	
}
