/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

import java.util.List;

/**
 * The Class PoDetailsRes.
 */
public class PoDetailsRes {
	
	
	private String date;
	private String message;
	private String status;
	private Integer code;
	private List<PoDetails> poList;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public List<PoDetails> getPoList() {
		return poList;
	}
	public void setPoList(List<PoDetails> poList) {
		this.poList = poList;
	}
	@Override
	public String toString() {
		return "PoDetailsRes [date=" + date + ", message=" + message + ", status=" + status + ", code=" + code
				+ ", poList=" + poList + "]";
	}
	
	
}
