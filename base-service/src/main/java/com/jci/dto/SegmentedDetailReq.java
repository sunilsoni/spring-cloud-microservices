/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.dto;

import com.jci.azure.PaginationParam;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;



/**
 * <p>
 * <strong> The Class SegmentedDetailReq.</strong>
 * <p>
 *
 * @author csonisk
 */
@Data
@Builder
public class SegmentedDetailReq {

	/** The is first request. */
	private boolean isFirstRequest;
	
	/** The pagination param. */
	private PaginationParam paginationParam;
	
	/** The partition. */
	private String partition;
	
	/** The table name. */
	private String tableName;
	
	/** The erp name. */
	private String erpName;
	
	/** The size. */
	private int size;

	@Tolerate
	public SegmentedDetailReq() {
		super();
	}

	
}
