package com.jci.job.api.res;

import java.util.Date;
import java.util.List;

public class PoDetailsRes {

	private int code;
	private String status;
	private String message;
	private String date;
	
	private List<PoDetails> poList;

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

	public List<PoDetails> getPoList() {
		return poList;
	}

	public void setPoList(List<PoDetails> poList) {
		this.poList = poList;
	}

	@Override
	public String toString() {
		return "PoDetailsRes [code=" + code + ", status=" + status + ", message=" + message + ", date=" + date
				+ ", poList=" + poList + "]";
	}

	
	
}
