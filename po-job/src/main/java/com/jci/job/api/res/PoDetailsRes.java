package com.jci.job.api.res;

import java.util.List;
import java.util.Map;

import com.jci.job.entity.PoItemsEntity;

public class PoDetailsRes {

	private boolean isErrorInDataFetch;
	private String errorMsg;
	private Map<String, Map<String,List<PoItemsEntity>>> dsToPoItemList;
	
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
	public Map<String, Map<String, List<PoItemsEntity>>> getDsToPoItemList() {
		return dsToPoItemList;
	}
	public void setDsToPoItemList(Map<String, Map<String, List<PoItemsEntity>>> dsToPoItemList) {
		this.dsToPoItemList = dsToPoItemList;
	}
	
	@Override
	public String toString() {
		return "PoDetailsResponse [isErrorInDataFetch=" + isErrorInDataFetch + ", errorMsg=" + errorMsg
				+ ", dsToPoItemList=" + dsToPoItemList + "]";
	}
	
	
}
