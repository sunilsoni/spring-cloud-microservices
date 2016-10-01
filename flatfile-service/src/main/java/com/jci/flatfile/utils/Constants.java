/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.utils;

/**
 * The Class Constants.
 */
public class Constants {
	
	/** The Constant TABLE_PO_DETAILS. */
	//Azure Table names 
	public static final String TABLE_PO_DETAILS ="PODETAILS";
	
	public static final String TABLE_GR_DETAILS ="GRDETAILS";
	
	/** The Constant TABLE_PO_ITEM_DETAILS. */
	public static final String TABLE_PO_ITEM_DETAILS ="POITEMDETAILS";
	
	/** The Constant TABLE_ITEM. */
	public static final String TABLE_ITEM ="ITEM";
	
	/** The Constant TABLE_SUPPLIER. */
	public static final String TABLE_SUPPLIER ="SUPPLIER";
	
	/** The Constant TABLE_MISC. */
	public static final String TABLE_MISC ="MISCDATA";
	
	/** Azure table column fields. */
	static final String PARTITION_KEY = "PartitionKey";
	
	/** The Constant STATUS_KEY. */
	static final String STATUS_KEY = "SupplierDeliveryState";
	 
	/** The Constant ROWKEY. */
	static final String ROWKEY = "RowKey";	
	
	/** The Constant ORDER_NUMBER. */
	static final String ORDER_NUMBER = "OrderNumber";
	
	/** The Constant JSON_STRING. */
	public static final String JSON_STRING = "JsonString";
	
	public static final String PLANT = "Plant";
	public static final String SUPP_TYPE = "SuppType";
	
	/** The Constant PARTITION_KEY_MISCDATA. */
	public static final String PARTITION_KEY_MISCDATA ="STATUS_COUNT";
	
	/** The Constant STATUS_IN_TRANSIT. */
	static final int  STATUS_IN_TRANSIT =1;
	
	/** The Constant STATUS_SUCCESS. */
	public static final int  STATUS_SUCCESS =2;
	
	/** The Constant STATUS_ERROR. */
	public static final int  STATUS_ERROR =3;
	
	public static final String MESSAGE_TYPE_PO = "DiscreteOrder";
    public static final String MESSAGE_TYPE_GR = "Receipt";
    public static final String MESSAGE_TYPE_ITEM = "PurchasedItems";
    public static final String MESSAGE_TYPE_SUPP = "Supplier";
	    
    static final String SENDER_DUNS = "006092860"; 
    static final String RECEIVER_DUNS = "006092860";
	static final String VERSION = "1.0";
	static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";
	static final String DATE_FORMAT_SUPP = "yyyyMMddHH";
	static final String TIME_ZONE = "UTC";
	
	//flat.file.name.site.id=JUA
	public static final String TARGET_DIR = "/send";///send
}
