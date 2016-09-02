package com.jci.supp.azure.query;

public class PaginationParam {
	
	private String lastPartition ;
	private String lastRow ;
	
	private String nextPartition ;
	private String nextRow ;
	
	public String getLastPartition() {
		return lastPartition;
	}
	
	public void setLastPartition(String lastPartition) {
		this.lastPartition = lastPartition;
	}
	
	public String getLastRow() {
		return lastRow;
	}
	
	public void setLastRow(String lastRow) {
		this.lastRow = lastRow;
	}
	
	public String getNextPartition() {
		return nextPartition;
	}
	
	public void setNextPartition(String nextPartition) {
		this.nextPartition = nextPartition;
	}
	
	public String getNextRow() {
		return nextRow;
	}
	
	public void setNextRow(String nextRow) {
		this.nextRow = nextRow;
	}

	@Override
	public String toString() {
		return "PaginationParam [lastPartition=" + lastPartition + ", lastRow=" + lastRow + ", nextPartition="
				+ nextPartition + ", nextRow=" + nextRow + "]";
	}
	
}
