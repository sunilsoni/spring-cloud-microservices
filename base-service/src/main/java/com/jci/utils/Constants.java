/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.utils;
/**
 * <p>
 * <strong>Define all  Common utilities methods here .</strong>
 * <p>
 *
 * @author csonisk
 */
public class Constants {
	
	/** Azure Table name */
	public static final String TABLE_PO_DETAILS ="PODETAILS";
	
	/** The Constant TABLE_GR_DETAILS. */
	public static final String TABLE_GR_DETAILS ="GRDETAILS";
	
	public static final String TABLE_GR_ITEM_DETAILS ="GRITEMDETAILS";
	
	/** The Constant TABLE_PO_ITEM_DETAILS. */
	public static final String TABLE_PO_ITEM_DETAILS ="POITEMDETAILS";
	
	/** The Constant TABLE_ITEM. */
	public static final String TABLE_ITEM ="ITEM";
	
	/** The Constant TABLE_SUPPLIER. */
	public static final String TABLE_SUPPLIER ="SUPPLIER";
	
	/** The Constant TABLE_MISC. */
	public static final String TABLE_MISC ="MISCDATA";
	
	/** Azure table column fields. */
	public static final String PARTITION_KEY = "PartitionKey";
	
	/** The Constant STATUS_KEY. */
	//static final String STATUS_KEY = "Status";
	 
	/** The Constant ROWKEY. */
	public static final String ROWKEY = "RowKey";	
	
	/** The Constant ORDER_NUMBER. */
	public static final String ORDER_NUMBER = "OrderNumber";
	
	/** The Constant JSON_STRING. */
	//public static final String JSON_STRING = "JsonString";
	
	/** The Constant PARTITION_KEY_MISCDATA. */
	public static final String PARTITION_KEY_MISCDATA ="STATUS_COUNT";
	
	/** The Constant STATUS_IN_TRANSIT. */
	public static final int  STATUS_IN_TRANSIT =1;
	
	/** The Constant STATUS_SUCCESS. */
	public static final int  STATUS_SUCCESS =2;
	
	/** The Constant STATUS_ERROR. */
	public static final int  STATUS_ERROR =3;
	
	
	/** The Constant STATUS_KEY. */
	public static final String STATUS_KEY = "SupplierDeliveryState";
	 
	
	/** The Constant JSON_STRING. */
	public static final String JSON_STRING = "JsonString";
	
	/** The Constant PLANT. */
	public static final String PLANT = "Plant";
	
	/** The Constant SUPP_TYPE. */
	public static final String SUPP_TYPE = "SuppType";
	
	
	/** The Constant MESSAGE_TYPE_PO. */
	public static final String MESSAGE_TYPE_PO = "DiscreteOrder";
    
    /** The Constant MESSAGE_TYPE_GR. */
    public static final String MESSAGE_TYPE_GR = "Receipt";
    
    /** The Constant MESSAGE_TYPE_ITEM. */
    public static final String MESSAGE_TYPE_ITEM = "PurchasedItems";
    
    /** The Constant MESSAGE_TYPE_SUPP. */
    public static final String MESSAGE_TYPE_SUPP = "Supplier";
	    
    /** The Constant SENDER_DUNS. */
    public  static final String SENDER_DUNS = "006092860"; 
    
    /** The Constant RECEIVER_DUNS. */
    public static final String RECEIVER_DUNS = "006092860";
	
	/** The Constant VERSION. */
    public static final String VERSION = "1.0";
	
	/** The Constant DATE_FORMAT. */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";
	
	/** The Constant DATE_FORMAT_SUPP. */
    public static final String DATE_FORMAT_SUPP = "yyyyMMddHH";
	
	/** The Constant TIME_ZONE. */
    public static final String TIME_ZONE = "UTC";
	
	/** The Constant SUPPLIER_TYPE_E2OPEN. */
	public static final String SUPPLIER_TYPE_E2OPEN = "e2open";
	
	/** The Constant JSON_OK. */
	public static final String JSON_OK = "OK";
	
	/** The Constant TARGET_DIR. */
	//flat.file.name.site.id=JUA
	public static final String TARGET_DIR = "/send";///send
}
