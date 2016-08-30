package com.jci.job.api.res;

import java.util.HashMap;
import java.util.List;

public class PoSuccessRes {
	
	private List<HashMap<String,String>> poList;

	public List<HashMap<String, String>> getPoList() {
		return poList;
	}

	public void setPoList(List<HashMap<String, String>> poList) {
		this.poList = poList;
	}

	@Override
	public String toString() {
		return "PoSuccessRes [poList=" + poList + "]";
	}

	
	
}
