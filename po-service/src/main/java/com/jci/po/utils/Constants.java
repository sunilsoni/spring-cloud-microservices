/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.utils;


/**
 * The Class Constants.
 */
public class Constants {

	/** Azure Table names. */
	public static final String TABLE_PO_DETAILS ="DEVPODETAILS";
	
	/** The Constant TABLE_PO_ITEM_DETAILS. */
	public static final String TABLE_PO_ITEM_DETAILS ="DEVPOITEMDETAILS";
	//public static final String TABLE_ITEM ="DEVITEM";
	/** The Constant TABLE_MISC. */
	//public static final String TABLE_SUPPLIER ="DEVSUPPLIER";
	public static final String TABLE_MISC ="DEVMISCDATA";
	
	
	/** Azure table column fields. */
	static final String PARTITION_KEY = "PartitionKey";
	
	/** The Constant STATUS_KEY. */
	static final String STATUS_KEY = "Status";
	
	/** The Constant ROWKEY. */
	static final String ROWKEY = "RowKey";	
	
	/** The Constant ORDER_NUMBER. */
	static final String ORDER_NUMBER = "OrderNumber";
	
	/** PO Status type. */
//	public static final int  STATUS_IN_TRANSIT =1;
	public static final int  STATUS_SUCCESS =2;
	
	/** The Constant STATUS_ERROR. */
	static final int  STATUS_ERROR =3;
	
	/** The Constant JSON_OK. */
	public static final String JSON_OK = "OK";
	
	/** The Constant PARTITION_KEY_MISCDATA. */
	public static final String PARTITION_KEY_MISCDATA ="STATUS_COUNT";
	
	/** The Constant JSON_STRING. */
	public static final String JSON_STRING = "JsonString";
	
	/** The Constant STATUS. */
	public static final String STATUS = "Status";
}
