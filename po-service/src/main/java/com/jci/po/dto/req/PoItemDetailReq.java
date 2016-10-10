/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.dto.req;

import com.jci.azure.PaginationParam;



/**
 * <p>
 * <strong> The Class PoItemDetailReq.</strong>
 * <p>
 *
 * @author csonisk
 */
public class PoItemDetailReq {

	/** The erp name. */
	private String erpName;
	
	/** The po num. */
	private String poNum;
	
	/** The table name. */
	private String tableName;
	
	/** The size. */
	private int size;
	
	/** The pagination param. */
	private PaginationParam paginationParam;

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

	/**
	 * Gets the po num.
	 *
	 * @return the po num
	 */
	public String getPoNum() {
		return poNum;
	}

	/**
	 * Sets the po num.
	 *
	 * @param poNum the new po num
	 */
	public void setPoNum(String poNum) {
		this.poNum = poNum;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoItemDetailReq [erpName=" + erpName + ", poNum=" + poNum + ", tableName=" + tableName + ", size="
				+ size + ", paginationParam=" + paginationParam + "]";
	}

	


	
}
