/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.dto.req;

import java.util.List;



/**
 * <p>
 * <strong> The Class PoDetailsReq.</strong>
 * <p>
 *
 * @author csonisk
 */
public class PoDetailsReq {
	
	/** The erp name. */
	private String erpName;
	
	/** The user name. */
	private String userName;
	
	/** The global id. */
	private String globalId;
	
	/** The comment. */
	private String comment;
	
	/** The po no. */
	private List<String> poNo;
	
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
	 * Gets the po no.
	 *
	 * @return the po no
	 */
	public List<String> getPoNo() {
		return poNo;
	}
	
	/**
	 * Sets the po no.
	 *
	 * @param poNo the new po no
	 */
	public void setPoNo(List<String> poNo) {
		this.poNo = poNo;
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
		return "PoDetailsReq [erpName=" + erpName + ", userName=" + userName + ", globalId=" + globalId + ", comment="
				+ comment + ", poNo=" + poNo + "]";
	}



}
