package com.jci.job.api.req;

import java.util.HashMap;
import java.util.List;

import com.jci.job.entity.PoEntity;

public class BatchUpdateReq {

	HashMap<String,List<PoEntity>> tableNameToEntityMap;
	private String erpName;
	private boolean isSuccess;
	
	private String userName;
	private String globalId;
	private String comment;
	
	
	public HashMap<String, List<PoEntity>> getTableNameToEntityMap() {
		return tableNameToEntityMap;
	}
	public void setTableNameToEntityMap(HashMap<String, List<PoEntity>> tableNameToEntityMap) {
		this.tableNameToEntityMap = tableNameToEntityMap;
	}
	public String getErpName() {
		return erpName;
	}
	public void setErpName(String erpName) {
		this.erpName = erpName;
	}
	
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGlobalId() {
		return globalId;
	}
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "BatchUpdateReq [tableNameToEntityMap=" + tableNameToEntityMap + ", erpName=" + erpName + ", isSuccess="
				+ isSuccess + ", userName=" + userName + ", globalId=" + globalId + ", comment=" + comment + "]";
	}
	
	
}
