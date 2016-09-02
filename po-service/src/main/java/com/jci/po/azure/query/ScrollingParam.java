/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.azure.query;


/**
 * The Class ScrollingParam.
 */
public class ScrollingParam {
	
	/** The partition. */
	private String partition ;
	
	/** The start row key. */
	private String startRowKey ;
	
	/** The end row key. */
	private String endRowKey ;
	
	/** The row. */
	private String row ;
	
	/** The size. */
	private int size ;
	
	/**
	 * Instantiates a new scrolling param.
	 */
	public ScrollingParam() {
		this.size = 100 ;//Need to change
	}
	
	/**
	 * Gets the partition.
	 *
	 * @return the partition
	 */
	public String getPartition() {
		return partition;
	}
	
	/**
	 * Sets the partition.
	 *
	 * @param partition the new partition
	 */
	public void setPartition(String partition) {
		this.partition = partition;
	}
	
	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public String getRow() {
		return row;
	}
	
	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(String row) {
		this.row = row;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the start row key.
	 *
	 * @return the start row key
	 */
	public String getStartRowKey() {
		return startRowKey;
	}

	/**
	 * Sets the start row key.
	 *
	 * @param startRowKey the new start row key
	 */
	public void setStartRowKey(String startRowKey) {
		this.startRowKey = startRowKey;
	}

	/**
	 * Gets the end row key.
	 *
	 * @return the end row key
	 */
	public String getEndRowKey() {
		return endRowKey;
	}

	/**
	 * Sets the end row key.
	 *
	 * @param endRowKey the new end row key
	 */
	public void setEndRowKey(String endRowKey) {
		this.endRowKey = endRowKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScrollingParam [partition=" + partition + ", startRowKey=" + startRowKey + ", endRowKey=" + endRowKey
				+ ", row=" + row + ", size=" + size + "]";
	}

	

	
}
