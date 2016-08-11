package com.jci.supplier.utils;

public class Constants {

	public static final String SYMIX_QUERY ="SELECT * FROM \"po\" \"poAlias\" INNER JOIN \"poitem\" \"poitemAlias\"  ON  \"poAlias\".\"po-num\" = \"poitemAlias\".\"po-num\"  INNER JOIN \"vendor\" \"vAlias\"  ON  \"poAlias\".\"vend-num\" = \"vAlias\".\"vend-num\"  INNER JOIN \"item\" \"iAlias\"  ON  \"poitemAlias\".\"item\" = \"iAlias\".\"item\"   INNER JOIN \"shipto\" \"sAlias\"  ON  \"poitemAlias\".\"drop-ship-no\" = \"sAlias\".\"drop-ship-no\" and \"poAlias\".\"drop-ship-no\" = \"sAlias\".\"drop-ship-no\" INNER JOIN \"po-div\" \"podAlias\"  ON  \"podAlias\".\"po-num\" = \"poAlias\".\"po-num\" INNER JOIN \"vendor-div\" \"vdAlias\"  ON  \"vdAlias\".\"vend-num\" = \"vAlias\".\"vend-num\"  INNER JOIN \"item-div\" \"idAlias\"  ON  \"idAlias\".\"item\" = \"iAlias\".\"item\" INNER JOIN \"vendaddr\" \"vaAlias\"  ON  \"vaAlias\".\"vend-num\" = \"vAlias\".\"vend-num\"   WHERE \"poAlias\".\"order-date\" >=  ? ";
	
	//Azure Table names 
	public static final String TABLE_PO_DETAILS ="PODETAILS";
	public static final String TABLE_PO_ITEM_DETAILS ="POITEMDETAILS";
	
	public static final String TABLE_ITEM ="ITEM";
	public static final String TABLE_SUPPLIER ="SUPPLIER";
	
	public static final String TABLE_MISC ="MISCDATA";
	
	public static final String ERP_SYMIX ="SYMIX";
	public static final String ERP_SAP ="SAP";
//	public static final String ERP_MAPICS ="MAPICS";
	
	public static final int ERP_INT_SYMIX =1;
	public static final int ERP_INT_SAP =2;
	
//	public static final String JSON_TEMPLATE = "{ \"returning\" : \"%s\" }";
	public static final String JSON_OK = "OK";
//	public static final String JSON_NG = "NG";//String.format(JSON_TEMPLATE, returning)));
	
	
	public static final String PARTITION_KEY_SYMIX ="SYMIX_PO";
	public static final String PARTITION_KEY_MISCDATA ="STATUS_COUNT";
	public static final String ROW_KEY_SYMIX_MISCDATA ="SYMIX";
	
	
	public static final int  STATUS_IN_TRANSIT =1;
	public static final int  STATUS_SUCCESS =2;
	public static final int  STATUS_ERROR =3;
	
	public static final String ERROR_MSG ="The application has encountered an error!";
	
	//public static final String ALL_ERP_NAMES ="SYMIX,SAP";
	public static final String ALL_ERP_NAMES ="SYMIX";
	
}
