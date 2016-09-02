package com.jci.po.utils;

public class Constants {

	/**
	 * Azure Table names 
	 */
	public static final String TABLE_PO_DETAILS ="DEVPODETAILS";
	public static final String TABLE_PO_ITEM_DETAILS ="DEVPOITEMDETAILS";
	//public static final String TABLE_ITEM ="DEVITEM";
	//public static final String TABLE_SUPPLIER ="DEVSUPPLIER";
	public static final String TABLE_MISC ="DEVMISCDATA";
	
	
	/**
	 * Azure table column fields
	 */
	static final String PARTITION_KEY = "PartitionKey";
	static final String STATUS_KEY = "Status";
	static final String ROWKEY = "RowKey";	
	static final String ORDER_NUMBER = "OrderNumber";
	
	/**
	 * PO Status type
	 */
//	public static final int  STATUS_IN_TRANSIT =1;
	public static final int  STATUS_SUCCESS =2;
	static final int  STATUS_ERROR =3;
	
	public static final String JSON_OK = "OK";
	
	
	public static final String PARTITION_KEY_MISCDATA ="STATUS_COUNT";
	public static final String JSON_STRING = "JsonString";
	public static final String STATUS = "Status";
}
