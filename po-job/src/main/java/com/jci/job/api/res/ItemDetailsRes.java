package com.jci.job.api.res;

import java.util.List;

public class ItemDetailsRes {

	private int code;
	private String status;
	private String message;
	private String date;
	
	private List<Object> itemList;

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

	public List<Object> getItemList() {
		return itemList;
	}

	public void setItemList(List<Object> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "ItemDetailsRes [code=" + code + ", status=" + status + ", message=" + message + ", date=" + date
				+ ", itemList=" + itemList + "]";
	}

	
}
