/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.utils;

import java.util.HashMap;
import java.util.List;

import com.microsoft.azure.storage.table.TableEntity;





/**
 * The Class BatchUpdateReq.
 */
public class BatchUpdateReq {

	/** The table name to entity map. */
	private  HashMap<String,List<TableEntity>> tableNameToEntityMap;
	
	/** The erp name. */
	private String erpName;
	
	/** The is success. */
	private boolean isSuccess;
	
	/** The user name. */
	private String userName;
	
	/** The global id. */
	private String globalId;
	
	/** The comment. */
	private String comment;
	
	/** The is error req. */
	private boolean isErrorReq ;
	
	/**
	 * Checks if is error req.
	 *
	 * @return true, if is error req
	 */
	public boolean isErrorReq() {
		return isErrorReq;
	}

	/**
	 * Sets the error req.
	 *
	 * @param isErrorReq the new error req
	 */
	public void setErrorReq(boolean isErrorReq) {
		this.isErrorReq = isErrorReq;
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
	 * Checks if is success.
	 *
	 * @return true, if is success
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	
	/**
	 * Sets the success.
	 *
	 * @param isSuccess the new success
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the global id.
	 *
	 * @return the global id
	 */
	public String getGlobalId() {
		return globalId;
	}
	
	/**
	 * Sets the global id.
	 *
	 * @param globalId the new global id
	 */
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
	
	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BatchUpdateReq [tableNameToEntityMap=" + tableNameToEntityMap + ", erpName=" + erpName + ", isSuccess="
				+ isSuccess + ", userName=" + userName + ", globalId=" + globalId + ", comment=" + comment
				+ ", isErrorReq=" + isErrorReq + "]";
	}
	
	
}
