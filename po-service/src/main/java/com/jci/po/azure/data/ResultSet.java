package com.jci.po.azure.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jci.po.azure.query.PaginationParam;

public class ResultSet {
	
	List<HashMap<String, Object>> series ;
	List<HashMap<String, String>> errorData ;
	PaginationParam pagination ;
	HashMap<String, ArrayList<Integer>> graphData ; 
	
	public ResultSet() {
		
	}
	
	public ResultSet(List<HashMap<String, Object>> series,List<HashMap<String, String>> errorData,HashMap<String, ArrayList<Integer>> graphData , PaginationParam pagination) {
		this.series = series ;
		this.pagination = pagination ;
		this.errorData = errorData ;
		this.graphData = graphData ;
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

	public HashMap<String, ArrayList<Integer>> getGraphData() {
		return graphData;
	}

	public void setGraphData(HashMap<String, ArrayList<Integer>> graphData) {
		this.graphData = graphData;
	}

	public List<HashMap<String, String>> getErrorData() {
		return errorData;
	}

	public void setErrorData(List<HashMap<String, String>> errorData) {
		this.errorData = errorData;
	}

	@Override
	public String toString() {
		return "ResultSet [series=" + series + ", errorData=" + errorData + ", pagination=" + pagination
				+ ", graphData=" + graphData + "]";
	}

}
