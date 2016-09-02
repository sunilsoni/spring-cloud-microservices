package com.jci.item.azure.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jci.item.azure.query.PaginationParam;

public class ResultSet {
	
	private List<HashMap<String, Object>> series ;
	private List<HashMap<String, String>> errorData = new ArrayList<HashMap<String, String>>() ;
	private PaginationParam pagination ;
	
	public ResultSet() {
		
	}
	
	public ResultSet(List<HashMap<String, Object>> series1, PaginationParam pagination1) {
		this.series = series1 ;
		this.pagination = pagination1 ;
	}
	
	public List<HashMap<String, Object>> getSeries() {
		return series;
	}

	public void setSeries(List<HashMap<String, Object>> series) {
		this.series = series;
	}

	public PaginationParam getPagination() {
		return pagination;
	}

	public void setPagination(PaginationParam pagination) {
		this.pagination = pagination;
	}


	public List<HashMap<String, String>> getErrorData() {
		return errorData;
	}

	public void setErrorData(List<HashMap<String, String>> errorData) {
		this.errorData = errorData;
	}

	@Override
	public String toString() {
		return "ResultSet [series=" + series + ", errorData=" + errorData + ", pagination=" + pagination + "]";
	}

	
}
