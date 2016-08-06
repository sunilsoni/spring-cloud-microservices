package com.jci.item.azure.query;

public class ScrollingParam {
	
	private String partition ;
	private String startRowKey ;
	private String endRowKey ;
	
	private String row ;
	private int size ;
	
	public ScrollingParam() {
		this.size = 2 ;//Need to change
	}
	
	public String getPartition() {
		return partition;
	}
	
	public void setPartition(String partition) {
		this.partition = partition;
	}
	
	public String getRow() {
		return row;
	}
	
	public void setRow(String row) {
		this.row = row;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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

	@Override
	public String toString() {
		return "ScrollingParam [partition=" + partition + ", startRowKey=" + startRowKey + ", endRowKey=" + endRowKey
				+ ", row=" + row + ", size=" + size + "]";
	}

	

	
}
