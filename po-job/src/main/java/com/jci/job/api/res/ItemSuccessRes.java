package com.jci.job.api.res;

import java.util.HashMap;
import java.util.List;

public class ItemSuccessRes {
	
	private List<HashMap<String,String>> itemList;

	public List<HashMap<String, String>> getItemList() {
		return itemList;
	}

	public void setItemList(List<HashMap<String, String>> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "ItemSuccessRes [itemList=" + itemList + "]";
	}

	
	
}
