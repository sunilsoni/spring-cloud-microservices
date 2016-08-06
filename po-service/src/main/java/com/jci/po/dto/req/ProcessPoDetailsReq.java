package com.jci.po.dto.req;

import java.util.Map;

public class ProcessPoDetailsReq {

	private Map<String,String> partitionKeyRowKeyMap;

	public Map<String, String> getPartitionKeyRowKeyMap() {
		return partitionKeyRowKeyMap;
	}

	public void setPartitionKeyRowKeyMap(Map<String, String> partitionKeyRowKeyMap) {
		this.partitionKeyRowKeyMap = partitionKeyRowKeyMap;
	}

	@Override
	public String toString() {
		return "ProcessPoDetailsRequest [partitionKeyRowKeyMap=" + partitionKeyRowKeyMap + "]";
	}
	
	
	
}
