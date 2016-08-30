package com.jci.job.api.res;

import java.util.HashMap;
import java.util.List;

public class SupplierSuccessRes {
	
	private List<HashMap<String,String>> supplierList;

	public List<HashMap<String, String>> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<HashMap<String, String>> supplierList) {
		this.supplierList = supplierList;
	}

	@Override
	public String toString() {
		return "SupplierSuccessRes [supplierList=" + supplierList + "]";
	}

	
	
	
}
