/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.azure;

import java.util.HashMap;
import java.util.List;

import lombok.Builder;
import lombok.Data;




/**
 * <p>
 * <strong>The Class ResultSet for managing Azure Table Data.</strong>
 * <p>
 *
 * @author csonisk
 */
@Data
public class ResultSet {
	
	/** The series. */
	private List<HashMap<String, Object>> series ;
	
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

}
