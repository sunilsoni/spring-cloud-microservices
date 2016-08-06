package com.jci.po.utils;

import com.jci.po.entity.ItemEntity;

public class ItemModelData {

	public static ItemEntity getDummyData(ItemEntity entity){
		
		entity.setEnterpriseCode("JCI-BE");
		entity.setSiteName("5061");
		entity.setSupplierId("SH-DOYEN");
		entity.setCustomerItemId("YVAA-MAT1");
		entity.setCustomerItemDescription("风冷冷水机组物料1");
		entity.setSupplierItemId("YVAA-MAT1");
		entity.setSupplierItemDescription("风冷冷水机组物料1");
		entity.setPurchasingGroup("Engineering");
		entity.setCommodityCode("Hardware");
		entity.setClassificationCode("Automation");
		entity.setUomppo("EA");
		entity.setCriticalItem("No");
		entity.setLeadTimeDays(5);
		entity.setForcastGracePeriod(13);
		entity.setBlanketExpiryTolerance(8);
		entity.setOrderGracePeriod(10);
		entity.setRequestOrPromiseDateTolerance(2);
		entity.setCommitQuantityToleranceMin(1.0000);
		entity.setCommitQuantityToleranceMax(4.0000);
		entity.setMinToleranceRequestOrPromiseQuantity(30.0000);
		entity.setMaxToleranceRequestOrPromiseQuantity(33.0000);
		entity.setMinToleranceRequestOrShippedQuantity(36.0000);
		entity.setMaxToleranceRequestOrShippedQuantity(39.0000);
		entity.setMinToleranceRequestOrReceivedQuantity(42.0000);
		entity.setMaxToleranceRequestOrReceivedQuantity(45.0000);
		entity.setLateShipmentsTolerance(4);
		entity.setLateDeliveriesTolerance(5);
		entity.setCustomerMOQ(70.0000);
		entity.setSupplierShipmentSize(70.0000);
		entity.setPlanningBucketType("W");
		entity.setProductLine("Product Line 1");
		entity.setPlanningBuckets("1");
		entity.setSerialNumberFlag("Y");
		entity.setOldPartNumber("OLD-PART-123");
		
		return entity;
	}
}
