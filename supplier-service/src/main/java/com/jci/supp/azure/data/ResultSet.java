/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.supp.azure.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jci.supp.azure.query.PaginationParam;


/**
 * The Class ResultSet.
 */
public class ResultSet {
	
	/** The series. */
	private List<HashMap<String, Object>> series ;
	
	/** The error data. */
	private List<HashMap<String, String>> errorData = new ArrayList<>() ;
	
	/** The pagination. */
	private PaginationParam pagination ;
	
	/**
	 * Instantiates a new result set.
	 */
	public ResultSet() {
		
	}
	
	/**
	 * Instantiates a new result set.
	 *
	 * @param series1 the series 1
	 * @param pagination1 the pagination 1
	 */
	public ResultSet(List<HashMap<String, Object>> series1, PaginationParam pagination1) {
		this.series = series1 ;
		this.pagination = pagination1 ;
	}
	
	
	
	/**
	 * Gets the series.
	 *
	 * @return the series
	 */
	public List<HashMap<String, Object>> getSeries() {
		return series;
	}

	/**
	 * Sets the series.
	 *
	 * @param series the series
	 */
	public void setSeries(List<HashMap<String, Object>> series) {
		this.series = series;
	}

	/**
	 * Gets the pagination.
	 *
	 * @return the pagination
	 */
	public PaginationParam getPagination() {
		return pagination;
	}

	/**
	 * Sets the pagination.
	 *
	 * @param pagination the new pagination
	 */
	public void setPagination(PaginationParam pagination) {
		this.pagination = pagination;
	}


	/**
	 * Gets the error data.
	 *
	 * @return the error data
	 */
	public List<HashMap<String, String>> getErrorData() {
		return errorData;
	}

	/**
	 * Sets the error data.
	 *
	 * @param errorData the error data
	 */
	public void setErrorData(List<HashMap<String, String>> errorData) {
		this.errorData = errorData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResultSet [series=" + series + ", errorData=" + errorData + ", pagination=" + pagination + "]";
	}

	
}
