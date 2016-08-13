package com.jci.job.utils;

public class Constants {

	
	//Azure Table names 
	public static final String TABLE_PO_DETAILS ="PODETAILS";
	public static final String TABLE_PO_ITEM_DETAILS ="POITEMDETAILS";
	public static final String TABLE_ITEM ="ITEM";
	public static final String TABLE_SUPPLIER ="SUPPLIER";
	public static final String TABLE_MISC ="MISCDATA";
	
//	public static final String ERP_SYMIX ="SYMIX";
//	public static final String ERP_SAP ="SAP";
//	public static final String ERP_MAPICS ="MAPICS";
	
	public static final int ERP_INT_SYMIX =1;
	public static final int ERP_INT_SAP =2;
	
	public static final String JSON_OK = "OK";
	
	
	public static final String PARTITION_KEY_SYMIX ="SYMIX_PO";
	public static final String PARTITION_KEY_MISCDATA ="STATUS_COUNT";
	public static final String ROW_KEY_SYMIX_MISCDATA ="SYMIX";
	
	
	public static final int  STATUS_IN_TRANSIT =1;
	public static final int  STATUS_SUCCESS =2;
	public static final int  STATUS_ERROR =3;
	
	public static final String ERROR_MSG ="The application has encountered an error!";
	
	
	
}
