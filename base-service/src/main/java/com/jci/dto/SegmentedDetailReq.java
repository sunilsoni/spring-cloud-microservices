/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.dto;

import com.jci.azure.PaginationParam;

/**
 * <p>
 * <strong> The Class SegmentedDetailReq.</strong>
 * <p>
 *
 * @author csonisk
 */
public class SegmentedDetailReq {

	/** The is first request. */
	private boolean isFirstRequest;
	
	/** The pagination param. */
	private PaginationParam paginationParam;
	
	/** The partition. */
	private String partition;
	
	/** The table name. */
	private String tableName;
	
	/** The erp name. */
	private String erpName;
	
	/** The size. */
	private int size;
	
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
	 * Checks if is first request.
	 *
	 * @return true, if is first request
	 */
	public boolean isFirstRequest() {
		return isFirstRequest;
	}

	/**
	 * Sets the first request.
	 *
	 * @param isFirstRequest the new first request
	 */
	public void setFirstRequest(boolean isFirstRequest) {
		this.isFirstRequest = isFirstRequest;
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
	 * Gets the pagination param.
	 *
	 * @return the pagination param
	 */
	public PaginationParam getPaginationParam() {
		return paginationParam;
	}

	/**
	 * Sets the pagination param.
	 *
	 * @param paginationParam the new pagination param
	 */
	public void setPaginationParam(PaginationParam paginationParam) {
		this.paginationParam = paginationParam;
	}

	/**
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Sets the table name.
	 *
	 * @param tableName the new table name
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Gets the erp name.
	 *
	 * @return the erp name
	 */
	public String getErpName() {
		return erpName;
	}

	/**
	 * Sets the erp name.
	 *
	 * @param erpName the new erp name
	 */
	public void setErpName(String erpName) {
		this.erpName = erpName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SegmentedDetailRequest [isFirstRequest=" + isFirstRequest + ", paginationParam=" + paginationParam
				+ ", partition=" + partition + ", tableName=" + tableName + ", erpName=" + erpName + ", size=" + size
				+ "]";
	}



}
