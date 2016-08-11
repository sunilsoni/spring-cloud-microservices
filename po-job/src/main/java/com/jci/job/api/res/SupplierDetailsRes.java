package com.jci.job.api.res;

import java.util.List;
import java.util.Map;

import com.jci.job.entity.SupplierEntity;

public class SupplierDetailsRes {

	private boolean isErrorInDataFetch;
	private String errorMsg;
	private Map<String, List<SupplierEntity>> dsToSupplierList;
	public boolean isErrorInDataFetch() {
		return isErrorInDataFetch;
	}
	public void setErrorInDataFetch(boolean isErrorInDataFetch) {
		this.isErrorInDataFetch = isErrorInDataFetch;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Map<String, List<SupplierEntity>> getDsToSupplierList() {
		return dsToSupplierList;
	}
	public void setDsToSupplierList(Map<String, List<SupplierEntity>> dsToSupplierList) {
		this.dsToSupplierList = dsToSupplierList;
	}
	@Override
	public String toString() {
		return "SupplierDetailsRes [isErrorInDataFetch=" + isErrorInDataFetch + ", errorMsg=" + errorMsg
				+ ", dsToSupplierList=" + dsToSupplierList + "]";
	}
	
	
}
