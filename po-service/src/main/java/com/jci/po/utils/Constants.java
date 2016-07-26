package com.jci.po.utils;

public class Constants {

	public static final String SYMIX_QUERY ="SELECT * FROM \"po\" \"poAlias\" INNER JOIN \"poitem\" \"poitemAlias\"  ON  \"poAlias\".\"po-num\" = \"poitemAlias\".\"po-num\"  INNER JOIN \"vendor\" \"vAlias\"  ON  \"poAlias\".\"vend-num\" = \"vAlias\".\"vend-num\"  INNER JOIN \"item\" \"iAlias\"  ON  \"poitemAlias\".\"item\" = \"iAlias\".\"item\"   INNER JOIN \"shipto\" \"sAlias\"  ON  \"poitemAlias\".\"drop-ship-no\" = \"sAlias\".\"drop-ship-no\" and \"poAlias\".\"drop-ship-no\" = \"sAlias\".\"drop-ship-no\" INNER JOIN \"po-div\" \"podAlias\"  ON  \"podAlias\".\"po-num\" = \"poAlias\".\"po-num\" INNER JOIN \"vendor-div\" \"vdAlias\"  ON  \"vdAlias\".\"vend-num\" = \"vAlias\".\"vend-num\"  INNER JOIN \"item-div\" \"idAlias\"  ON  \"idAlias\".\"item\" = \"iAlias\".\"item\" INNER JOIN \"vendaddr\" \"vaAlias\"  ON  \"vaAlias\".\"vend-num\" = \"vAlias\".\"vend-num\"   WHERE \"poAlias\".\"order-date\" >=  ? ";
	
	public static final String TABLE_PO_DETAILS ="PODETAILS";
	public static final String TABLE_PO_ITEM_DETAILS ="POITEMDETAILS";
	
	public static final String TABLE_ITEM ="ITEM";
	public static final String TABLE_SUPPLIER ="SUPPLIER";
	
	public static final String ERP_SYMIX ="SYMIX";
	public static final String ERP_MACPAC ="MACPAC";
	public static final String ERP_MAPICS ="MAPICS";
	
	public static final String JSON_TEMPLATE = "{ \"returning\" : \"%s\" }";
	public static final String JSON_OK = "OK";
	public static final String JSON_NG = "NG";//String.format(JSON_TEMPLATE, returning)));
	
}
