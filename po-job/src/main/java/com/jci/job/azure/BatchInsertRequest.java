package com.jci.job.azure;

import java.util.HashMap;
import java.util.List;

import com.microsoft.azure.storage.table.TableEntity;

public class BatchInsertRequest {
	
	private String erpName;
	private HashMap<String,List<TableEntity>> tableNameToEntityMap;

	public String getErpName() {
		return erpName;
	}

	public void setErpName(String erpName) {
		this.erpName = erpName;
	}

	public HashMap<String, List<TableEntity>> getTableNameToEntityMap() {
		return tableNameToEntityMap;
	}

	public void setTableNameToEntityMap(HashMap<String, List<TableEntity>> tableNameToEntityMap) {
		this.tableNameToEntityMap = tableNameToEntityMap;
	}

	@Override
	public String toString() {
		return "BatchInsertRequest [erpName=" + erpName + ", tableNameToEntityMap=" + tableNameToEntityMap + "]";
	}



}
