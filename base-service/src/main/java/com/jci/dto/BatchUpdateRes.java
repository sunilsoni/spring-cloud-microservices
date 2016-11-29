/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Builder;
import lombok.Data;




/**
 * <p>
 * <strong> The Class BatchUpdateRes.</strong>
 * <p>
 *
 * @author csonisk
 */
@Data
//@Builder
public class BatchUpdateRes {
	
	/** The error list. */
	private List<String> errorList;
	
	/** The success list. */
	private List<String> successList;
	
	/** The graph data. */
	private HashMap<String, ArrayList<Integer>> graphData;
	
	/** The is error. */
	private boolean isError;
	
	/** The message. */
	private String message;
	
}
