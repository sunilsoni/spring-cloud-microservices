/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.req;

import java.util.HashMap;
import java.util.List;

import com.microsoft.azure.storage.table.TableEntity;


/**
 * <p>
 * <strong> The Batch Insert Request Class.</strong>
 * <p>
 *
 * @author csonisk
 */
public class BatchInsertReq {
	
	/** The erp name. */
	private String erpName;
	
	/** The table name to entity map. */
	private HashMap<String,List<TableEntity>> tableNameToEntityMap;
	
	
	/** The req. */
	private List<Object> req;
	
	/** The row key list. */
	private List<String> rowKeyList;
	

	/**
	 * Gets the row key list.
	 *
	 * @return the row key list
	 */
	public List<String> getRowKeyList() {
        return rowKeyList;
    }

    /**
     * Sets the row key list.
     *
     * @param rowKeyList the new row key list
     */
    public void setRowKeyList(List<String> rowKeyList) {
        this.rowKeyList = rowKeyList;
    }

    /**
     * Gets the req.
     *
     * @return the req
     */
    public List<Object> getReq() {
		return req;
	}

	/**
	 * Sets the req.
	 *
	 * @param req the new req
	 */
	public void setReq(List<Object> req) {
		this.req = req;
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

	/**
	 * Gets the table name to entity map.
	 *
	 * @return the table name to entity map
	 */
	public HashMap<String, List<TableEntity>> getTableNameToEntityMap() {
		return tableNameToEntityMap;
	}

	/**
	 * Sets the table name to entity map.
	 *
	 * @param tableNameToEntityMap the table name to entity map
	 */
	public void setTableNameToEntityMap(HashMap<String, List<TableEntity>> tableNameToEntityMap) {
		this.tableNameToEntityMap = tableNameToEntityMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
        return "BatchInsertReq [erpName=" + erpName + ", tableNameToEntityMap=" + tableNameToEntityMap + ", req=" + req
                + ", rowKeyList=" + rowKeyList + "]";
    }



}
