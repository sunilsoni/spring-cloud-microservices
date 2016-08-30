package com.jci.po.utils;

public class Constants {

	/**
	 * Azure Table names 
	 */
	public static final String TABLE_PO_DETAILS ="PODETAILSTEST";
	public static final String TABLE_PO_ITEM_DETAILS ="POITEMDETAILSTEST";
	public static final String TABLE_ITEM ="ITEMTEST";
	public static final String TABLE_SUPPLIER ="SUPPLIERTEST";
	public static final String TABLE_MISC ="MISCDATA";
	
	
	/**
	 * Azure table column fields
	 */
	public static final String PARTITION_KEY = "PartitionKey";
	public static final String STATUS_KEY = "Status";
	public static final String TIMESTAMP = "Timestamp";
	public static final String ROWKEY = "RowKey";	
	public static final String ORDER_NUMBER = "OrderNumber";
	
	/**
	 * PO Status type
	 */
	public static final int  STATUS_IN_TRANSIT =1;
	public static final int  STATUS_SUCCESS =2;
	public static final int  STATUS_ERROR =3;
	
	//public static final String ERP_SYMIX ="SYMIX";
	//public static final String ERP_SAP ="SAP";
//	public static final String ERP_MAPICS ="MAPICS";
	
	//public static final int ERP_INT_SYMIX =1;
	//public static final int ERP_INT_SAP =2;
	
//	public static final String JSON_TEMPLATE = "{ \"returning\" : \"%s\" }";
	public static final String JSON_OK = "OK";
//	public static final String JSON_NG = "NG";//String.format(JSON_TEMPLATE, returning)));
	
	
	//public static final String PARTITION_KEY_SYMIX ="SYMIX_PO";
	public static final String PARTITION_KEY_MISCDATA ="STATUS_COUNT";
	//public static final String ROW_KEY_SYMIX_MISCDATA ="SYMIX";
	

	
	public static final int  MAX_ROW_SIZE =1000;
	public static final String ERROR_MSG ="The application has encountered an error!";
	
	//public static final String ALL_ERP_NAMES ="SYMIX,SAP";
	//public static final String ALL_ERP_NAMES ="SYMIX";
	
	//http://c201s009.cg.na.jci.com:15080/E2OPOC
	//public static final String E2OPEN_URL ="http://gtstaging.controls.johnsoncontrols.com/E2OPOC"; 
	
	//https://gtstaging.controls.johnsoncontrols.com/E2OPOC
	
	public static final int DESTINATION_E2OPEN =1;
	public static final int DESTINATION_EDI =2;

	
}
