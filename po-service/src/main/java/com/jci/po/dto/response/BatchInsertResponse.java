package com.jci.po.dto.response;

import java.util.HashMap;
import java.util.List;

import com.microsoft.azure.storage.table.TableEntity;

public class BatchInsertResponse {
	
	private boolean isError;
	private String msg;
	
	private  HashMap<String,List<TableEntity>> errorMap;
	private  HashMap<String,List<TableEntity>> successMap;
	
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public HashMap<String, List<TableEntity>> getErrorMap() {
		return errorMap;
	}
	public void setErrorMap(HashMap<String, List<TableEntity>> errorMap) {
		this.errorMap = errorMap;
	}
	public HashMap<String, List<TableEntity>> getSuccessMap() {
		return successMap;
	}
	public void setSuccessMap(HashMap<String, List<TableEntity>> successMap) {
		this.successMap = successMap;
	}
	@Override
	public String toString() {
		return "BatchInsertResponse [isError=" + isError + ", msg=" + msg + ", errorMap=" + errorMap + ", successMap="
				+ successMap + "]";
	}
	
	
	
	

}
