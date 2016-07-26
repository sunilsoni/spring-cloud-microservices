package com.jci.po.dto.response;

import java.util.List;

public class PoDetailsResponse {
	
	private boolean isError;
	
	private List<PoDetails> PoDetails;

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public List<PoDetails> getPoDetails() {
		return PoDetails;
	}

	public void setPoDetails(List<PoDetails> poDetails) {
		PoDetails = poDetails;
	}

	@Override
	public String toString() {
		return "PoDetailsResponse [isError=" + isError + ", PoDetails=" + PoDetails + "]";
	}
	
	
	 

	
}
