/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.azure;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * <p>
 * <strong> The Class ScrollingParam  for managing UI Pagination.</strong>
 * <p>
 *
 * @author csonisk
 */
@Data
@Builder
public class ScrollingParam {
	
	/** The partition. */
	private String partition ;
	
	/** The start row key. */
	private String startRowKey ;
	
	/** The end row key. */
	private String endRowKey ;
	
	/** The row. */
	private String row ;
	
	/** The size. */
	private int size ;

	@Tolerate
	public ScrollingParam() {
		super();
	}
	
	
}
