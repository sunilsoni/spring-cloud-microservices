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
 * The Class BatchInsertReq.
 */
public class BatchInsertReq {
	
	/** The erp name. */
	private String erpName;
	
	/** The table name to entity map. */
	private HashMap<String,List<TableEntity>> tableNameToEntityMap;

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
		return "BatchInsertRequest [erpName=" + erpName + ", tableNameToEntityMap=" + tableNameToEntityMap + "]";
	}



}
