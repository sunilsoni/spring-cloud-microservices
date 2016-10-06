package com.jci.po.dto.req;

import java.util.List;

public class ProcessErrorReq {
	
	private List<String> errorsList;
	private String erpName;
	
	private String tableName;
	private String globalId;
	private String userName;
	private String comment;
	
	public String getErpName() {
		return erpName;
	}

	public void setErpName(String erpName) {
		this.erpName = erpName;
	}

	public List<String> getErrorsList() {
		return errorsList;
	}

	public void setErrorsList(List<String> errorsList) {
		this.errorsList = errorsList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getGlobalId() {
		return globalId;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ProcessErrorReq [errorsList=" + errorsList + ", erpName=" + erpName + ", tableName=" + tableName
				+ ", globalId=" + globalId + ", userName=" + userName + ", comment=" + comment + "]";
	}
	
	
}
