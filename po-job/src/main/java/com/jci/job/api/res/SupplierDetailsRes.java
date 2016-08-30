package com.jci.job.api.res;

import java.util.List;
import java.util.Map;

import com.jci.job.entity.SupplierEntity;

public class SupplierDetailsRes {

	private int code;
	private String status;
	private String message;
	private String date;
	
	private List<Object> supplierList;

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

	public List<Object> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<Object> supplierList) {
		this.supplierList = supplierList;
	}

	@Override
	public String toString() {
		return "SupplierDetailsRes [code=" + code + ", status=" + status + ", message=" + message + ", date=" + date
				+ ", supplierList=" + supplierList + "]";
	}
	
	
	
}
