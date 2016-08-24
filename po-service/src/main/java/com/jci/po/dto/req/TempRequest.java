package com.jci.po.dto.req;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempRequest {

	Map<String,List<HashMap<String, Object>>>  poNumToItemsMap ;

	public Map<String, List<HashMap<String, Object>>> getPoNumToItemsMap() {
		return poNumToItemsMap;
	}

	public void setPoNumToItemsMap(Map<String, List<HashMap<String, Object>>> poNumToItemsMap) {
		this.poNumToItemsMap = poNumToItemsMap;
	}

	@Override
	public String toString() {
		return "TempRequest [poNumToItemsMap=" + poNumToItemsMap + "]";
	}
	
	
}
