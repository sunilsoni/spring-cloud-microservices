package com.jci.job.api.res;

import java.util.List;

public class PoDetails {

	private String orderNumber;
	private String suppType;
	private boolean poACK;
	private boolean asn;
	private String grNumber;
	private String orderCreationDate;	
	private List<Object> itemList;


	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}




	public boolean isPoACK() {
		return poACK;
	}


	public void setPoACK(boolean poACK) {
		this.poACK = poACK;
	}


	public boolean isAsn() {
		return asn;
	}


	public void setAsn(boolean asn) {
		this.asn = asn;
	}


	public String getGrNumber() {
		return grNumber;
	}


	public void setGrNumber(String grNumber) {
		this.grNumber = grNumber;
	}


	public String getOrderCreationDate() {
		return orderCreationDate;
	}


	public void setOrderCreationDate(String orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}


	public List<Object> getItemList() {
		return itemList;
	}


	public void setItemList(List<Object> itemList) {
		this.itemList = itemList;
	}


	public String getSuppType() {
		return suppType;
	}


	public void setSuppType(String suppType) {
		this.suppType = suppType;
	}


	@Override
	public String toString() {
		return "PoDetails [orderNumber=" + orderNumber + ", suppType=" + suppType + ", poACK=" + poACK + ", asn=" + asn
				+ ", grNumber=" + grNumber + ", orderCreationDate=" + orderCreationDate + ", itemList=" + itemList
				+ "]";
	}


	
	
}
