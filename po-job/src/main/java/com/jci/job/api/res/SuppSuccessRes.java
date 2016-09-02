package com.jci.job.api.res;

import java.util.HashMap;
import java.util.List;

public class SuppSuccessRes {
	
	private List<HashMap<String,String>> suppList;

	public List<HashMap<String, String>> getSuppList() {
		return suppList;
	}

	public void setSuppList(List<HashMap<String, String>> suppList) {
		this.suppList = suppList;
	}

	@Override
	public String toString() {
		return "SuppSuccessRes [suppList=" + suppList + "]";
	}

	
	
	
	
}
