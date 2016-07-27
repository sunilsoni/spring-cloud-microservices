package com.jci.po.dto.request;

import com.jci.po.azure.query.PaginationParam;

public class SegmentedDetailRequest {

	private boolean isFirstRequest;
	
	private PaginationParam paginationParam;
	
	private String startRowKey;
	private String endRowKey;
	private String partition;
	
	private String tableName;
	private String erpName;
	private int size;
	
	public String getPartition() {
		return partition;
	}

	public void setPartition(String partition) {
		this.partition = partition;
	}

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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getErpName() {
		return erpName;
	}

	public void setErpName(String erpName) {
		this.erpName = erpName;
	}

	@Override
	public String toString() {
		return "SegmentedDetailRequest [isFirstRequest=" + isFirstRequest + ", paginationParam=" + paginationParam
				+ ", startRowKey=" + startRowKey + ", endRowKey=" + endRowKey + ", partition=" + partition
				+ ", tableName=" + tableName + ", erpName=" + erpName + ", size=" + size + "]";
	}


}
