package com.jci.po.utils;

import java.util.Date;

import com.jci.po.entity.PoItemsEntity;

public class PoModelData {

		public static PoItemsEntity getDummyData(PoItemsEntity entity){
			
		entity.setId(115);
		entity.setBuyerName("Fernando Magdaleno");
		entity.setCustomerAddress1("5757 N. Green Bay Avenue");
		entity.setCustomerAddress2("P.O. Box 591");
		entity.setCustomerAddress3("AddressLine3");
		entity.setCustomerAddress4("AddressLine4");
		entity.setCustomerAddress5("AddressLine5");
		entity.setcustomerAddressDescriptor("Milwaukee Office");
		entity.setCustomerCity("Milwaukee");
		entity.setCustomerState("Wisconsin ");
		entity.setCustomerContry("USA");
		entity.setCustomerDUNS("6092860");
		entity.setCustomerDescription("Johnson Controls, Inc. Building Efficiency");
		entity.setCustomerId("JCI-BE");
		entity.setCustomerItemDescription("PRODUCT LABEL 1");
		entity.setCustomerItemId("LBL48-4000");
		entity.setCustomerZip("53201");
		entity.setDataSource("Symix");
		entity.setDeliveryTerm("DEF");
		entity.setOrderCreationDate(new Date());// 6/7/2013 0:00
		entity.setOrderNumber("3714153");
		entity.setOrderStatus("A");
		entity.setPaymentTerms("100");
		entity.setRequestDate(new Date());// 6/7/2013 0:00
		entity.setRequestedShipDate(new Date());// 7/5/2013 0:00
		entity.setSupplierId("SH-DOYEN");
		entity.setSupplierAddressDescriptor("SH-DOYEN Office");
	
		entity.setShipToState("6880");
		entity.setSupplierAddress1(" Floor 7th, Building A");
		entity.setSupplierAddress2("No. 285 Changshou Road Shanghai, China");
		entity.setSupplierAddress3("AddressLine3");
		entity.setSupplierAddress4("AddressLine4");
		entity.setSupplierAddress5("AddressLine5");
		entity.setSupplierCity("Shanghai");
		entity.setSupplierState("Shanghai");
		entity.setSupplierCountry("China");
		entity.setSupplierZip("200001");
		entity.setBuyerCode("BuyerCode01");
		entity.setBuyerContact("BuyerContact01");
		entity.setBuyerEmail("buyer01@jci.com");
		entity.setSupplierItemDescription("4306320");
		entity.setBillTo("JCI");
		entity.setBillToAddressDescriptor("Milwaukee Financial");
		entity.setBillToAddress1("5757 N. Green Bay Avenue");
		entity.setBillToAddress2("P.O. Box 591  Milwaukee");
		entity.setBillToAddress3("WI 53201");
		entity.setBillToAddress4("AddressLine4");
		entity.setBillToAddress5("AddressLine5");
		entity.setBillToCity("Milwaukee");
		entity.setBillToState("Wisconsin");
		entity.setBillToCountry("United States");
		entity.setBillToZip("5757");
		entity.setLineId(1);
		entity.setCustomerItemId("YVAA-MAT1");
		entity.setSupplierItemId("YVAA-MAT1");
		entity.setAction("InsertOrUpdate");
		entity.setRequestQty(218);
		entity.setCarrier("FoxConn Carrier 5061");
		entity.setPromiseId(1);
		entity.setPromiseQty(218);
		entity.setPromiseDate(new Date());
		entity.setPromiseShipDate(new Date());
		entity.setInCoTerm("InCoTerm");
		return entity;
		
	}
}
