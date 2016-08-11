package com.jci.job.api.res;

import java.util.List;
import java.util.Map;

import com.jci.job.entity.ItemEntity;

public class ItemDetailsRes {

	private boolean isErrorInDataFetch;
	private String errorMsg;
	private Map<String, List<ItemEntity>> dsToItemList;
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
	public Map<String, List<ItemEntity>> getDsToItemList() {
		return dsToItemList;
	}
	public void setDsToItemList(Map<String, List<ItemEntity>> dsToItemList) {
		this.dsToItemList = dsToItemList;
	}
	
	
	@Override
	public String toString() {
		return "ItemDetailsRes [isErrorInDataFetch=" + isErrorInDataFetch + ", errorMsg=" + errorMsg + ", dsToItemList="
				+ dsToItemList + "]";
	}
	
	

	
	
}
