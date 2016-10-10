package com.jci.flatfile.utils;

import java.util.List;



/**
 * The Class ProcessErrorReq.
 */
public class ProcessErrorReq {
	
	/** The errors list. */
	private List<String> errorsList;
	
	/** The erp name. */
	private String erpName;
	
	/** The table name. */
	private String tableName;
	
	/** The global id. */
	private String globalId;
	
	/** The user name. */
	private String userName;
	
	/** The comment. */
	private String comment;
	
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
	 * Gets the errors list.
	 *
	 * @return the errors list
	 */
	public List<String> getErrorsList() {
		return errorsList;
	}

	/**
	 * Sets the errors list.
	 *
	 * @param errorsList the new errors list
	 */
	public void setErrorsList(List<String> errorsList) {
		this.errorsList = errorsList;
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
		return "ProcessErrorReq [errorsList=" + errorsList + ", erpName=" + erpName + ", tableName=" + tableName
				+ ", globalId=" + globalId + ", userName=" + userName + ", comment=" + comment + "]";
	}
	
	
}
