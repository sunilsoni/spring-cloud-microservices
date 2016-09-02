package com.jci.job.api.res;

import java.util.List;

public class SuppDetailsRes {

	private int code;
	private String status;
	private String message;
	private String date;
	
	private List<Object> suppList;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Object> getSuppList() {
		return suppList;
	}

	public void setSuppList(List<Object> suppList) {
		this.suppList = suppList;
	}

	@Override
	public String toString() {
		return "SuppDetailsRes [code=" + code + ", status=" + status + ", message=" + message + ", date=" + date
				+ ", suppList=" + suppList + "]";
	}

}
