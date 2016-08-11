package com.jci.po.dto.req;

import java.util.List;

public class PoDetailsReq {
	
	private String erpName;
	private String userName;
	private String globalId;
	private String comment;
	
	private List<String> poNo;
	
	public String getErpName() {
		return erpName;
	}
	public void setErpName(String erpName) {
		this.erpName = erpName;
	}
	public List<String> getPoNo() {
		return poNo;
	}
	public void setPoNo(List<String> poNo) {
		this.poNo = poNo;
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
		return "PoDetailsReq [erpName=" + erpName + ", userName=" + userName + ", globalId=" + globalId + ", comment="
				+ comment + ", poNo=" + poNo + "]";
	}



}
