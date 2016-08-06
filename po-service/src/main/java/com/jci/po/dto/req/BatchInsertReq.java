package com.jci.po.dto.req;

import java.util.List;
import java.util.HashMap;

import com.microsoft.azure.storage.table.TableEntity;

public class BatchInsertReq {
	
	private String erpName;
	private boolean isCountRequest;
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

	public boolean isCountRequest() {
		return isCountRequest;
	}

	public void setCountRequest(boolean isCountRequest) {
		this.isCountRequest = isCountRequest;
	}

	@Override
	public String toString() {
		return "BatchInsertRequest [erpName=" + erpName + ", isCountRequest=" + isCountRequest
				+ ", tableNameToEntityMap=" + tableNameToEntityMap + "]";
	}





}
