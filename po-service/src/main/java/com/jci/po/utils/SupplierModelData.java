package com.jci.po.utils;

import com.jci.po.entity.SupplierEntity;

public class SupplierModelData {

	public static SupplierEntity getDummyData(SupplierEntity entity){
		
		String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SYMIX);
		entity.setPartitionKey(partitionKey);
		entity.setRowKey("123");
		
		entity.setEnterpriseCode("JCI-BE");
		entity.setSupplierId("SH-DOYEN");
		entity.setSupplierDescription("上海大洋机电设备工程有限公司");// Shanghai Ocean
														// Electrical and
														// Mechanical Equipment
														// Engineering Co., Ltd.
		entity.setSupplierAddressDescriptor("SH-DOYEN Office");
		entity.setSupplierAddress1("Floor 7th, Building A");
		entity.setSupplierAddress2("No. 285 Changshou Road");
		entity.setSupplierAddress3("Shanghai, China");
		entity.setSupplierAddress4("AddressLine4");
		entity.setSupplierAddress5("AddressLine5");
		entity.setSupplierCity("Shanghai");
		entity.setSupplierState("Shanghai");
		entity.setSupplierCountry("China");
		entity.setSupplierZip("200001");
		entity.setSupplierStatus("Active");
		entity.setTransitLeadTime(3);
		
		return entity;
	}
}
