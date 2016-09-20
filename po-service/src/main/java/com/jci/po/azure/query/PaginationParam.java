/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.azure.query;



/**
 * <p>
 * <strong>The Class PaginationParam for managing UI Pagination.</strong>
 * <p>
 *
 * @author csonisk
 */
public class PaginationParam {
	
	/** The last partition. */
	private String lastPartition ;
	
	/** The last row. */
	private String lastRow ;
	
	/** The next partition. */
	private String nextPartition ;
	
	/** The next row. */
	private String nextRow ;
	
	/**
	 * Gets the last partition.
	 *
	 * @return the last partition
	 */
	public String getLastPartition() {
		return lastPartition;
	}
	
	/**
	 * Sets the last partition.
	 *
	 * @param lastPartition the new last partition
	 */
	public void setLastPartition(String lastPartition) {
		this.lastPartition = lastPartition;
	}
	
	/**
	 * Gets the last row.
	 *
	 * @return the last row
	 */
	public String getLastRow() {
		return lastRow;
	}
	
	/**
	 * Sets the last row.
	 *
	 * @param lastRow the new last row
	 */
	public void setLastRow(String lastRow) {
		this.lastRow = lastRow;
	}
	
	/**
	 * Gets the next partition.
	 *
	 * @return the next partition
	 */
	public String getNextPartition() {
		return nextPartition;
	}
	
	/**
	 * Sets the next partition.
	 *
	 * @param nextPartition the new next partition
	 */
	public void setNextPartition(String nextPartition) {
		this.nextPartition = nextPartition;
	}
	
	/**
	 * Gets the next row.
	 *
	 * @return the next row
	 */
	public String getNextRow() {
		return nextRow;
	}
	
	/**
	 * Sets the next row.
	 *
	 * @param nextRow the new next row
	 */
	public void setNextRow(String nextRow) {
		this.nextRow = nextRow;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaginationParam [lastPartition=" + lastPartition + ", lastRow=" + lastRow + ", nextPartition="
				+ nextPartition + ", nextRow=" + nextRow + "]";
	}
	
}
