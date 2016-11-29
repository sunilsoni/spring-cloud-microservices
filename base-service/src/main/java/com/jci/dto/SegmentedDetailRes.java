/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.dto;

import java.util.ArrayList;
import java.util.HashMap;

import com.jci.azure.ResultSet;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;




/**
 * <p>
 * <strong> The Class SegmentedDetailRes.</strong>
 * <p>
 *
 * @author csonisk
 */
@Data
@Builder
public class SegmentedDetailRes {

	/** The is error. */
	private boolean isError;
	
	/** The message. */
	private String message;//OK
	
	/** The result set. */
	private HashMap<String, ResultSet>  resultSet;
	
	/** The graph data. */
	private HashMap<String, ArrayList<Integer>> graphData;
	
	/** The supp data. */
	private HashMap<String, ResultSet>  suppData;
	
	/** The item data. */
	private HashMap<String, ResultSet>  itemData;
	
	/** The error data. */
	private HashMap<String, ResultSet>  errorData;
	
	/** The user data. */
	private HashMap<String, String>  userData;

	@Tolerate
	public SegmentedDetailRes() {
		super();
	}
	
	
}
