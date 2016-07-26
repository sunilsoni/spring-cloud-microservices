package com.jci.po.dto.request;

import com.jci.po.azure.query.PaginationParam;

public class SegmentedPoDetailRequest {

	private boolean isFirstRequest;
	
	private PaginationParam paginationParam;
	
	private String startRowKey;
	private String endRowKey;
	private String partition;
	
	public String getPartition() {
		return partition;
	}

	public void setPartition(String partition) {
		this.partition = partition;
	}

	private int size;

	public boolean isFirstRequest() {
		return isFirstRequest;
	}

	public void setFirstRequest(boolean isFirstRequest) {
		this.isFirstRequest = isFirstRequest;
	}


	public String getStartRowKey() {
		return startRowKey;
	}

	public void setStartRowKey(String startRowKey) {
		this.startRowKey = startRowKey;
	}

	public String getEndRowKey() {
		return endRowKey;
	}

	public void setEndRowKey(String endRowKey) {
		this.endRowKey = endRowKey;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public PaginationParam getPaginationParam() {
		return paginationParam;
	}

	public void setPaginationParam(PaginationParam paginationParam) {
		this.paginationParam = paginationParam;
	}

	@Override
	public String toString() {
		return "SegmentedPoDetailRequest [isFirstRequest=" + isFirstRequest + ", paginationParam=" + paginationParam
				+ ", startRowKey=" + startRowKey + ", endRowKey=" + endRowKey + ", partition=" + partition + ", size="
				+ size + "]";
	}





	
	
}
