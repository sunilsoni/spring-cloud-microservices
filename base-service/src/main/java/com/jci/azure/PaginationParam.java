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
 * <strong>The Class PaginationParam for managing UI Pagination.</strong>
 * <p>
 *
 * @author csonisk
 */
@Data
@Builder
public class PaginationParam {
	
	/** The last partition. */
	private String lastPartition ;
	
	/** The last row. */
	private String lastRow ;
	
	/** The next partition. */
	private String nextPartition ;
	
	/** The next row. */
	private String nextRow ;

	@Tolerate
	public PaginationParam() {
		super();
	}

	
}
