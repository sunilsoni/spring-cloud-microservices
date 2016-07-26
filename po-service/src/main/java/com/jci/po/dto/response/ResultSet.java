package com.jci.po.dto.response;

import java.util.HashMap;
import java.util.List;

import com.jci.po.azure.query.PaginationParam;

public class ResultSet {
	
	List<HashMap<String, Object>> series ;
	PaginationParam pagination ;
	
	public ResultSet() {
		
	}
	
	public ResultSet(List<HashMap<String, Object>> series, PaginationParam pagination) {
		this.series = series ;
		this.pagination = pagination ;
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

	@Override
	public String toString() {
		return "ResultSet [series=" + series + ", pagination=" + pagination + "]";
	}
	
}
