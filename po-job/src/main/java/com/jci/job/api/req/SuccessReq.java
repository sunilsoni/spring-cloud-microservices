package com.jci.job.api.req;

import java.util.List;

public class SuccessReq {

	List<Object> poList;
	List<Object> itemList;
	List<Object> supplierList;
	List<Object> grList;

	public List<Object> getPoList() {
		return poList;
	}

	public void setPoList(List<Object> poList) {
		this.poList = poList;
	}

	public List<Object> getItemList() {
		return itemList;
	}

	public void setItemList(List<Object> itemList) {
		this.itemList = itemList;
	}

	public List<Object> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<Object> supplierList) {
		this.supplierList = supplierList;
	}

	
	public List<Object> getGrList() {
		return grList;
	}

	public void setGrList(List<Object> grList) {
		this.grList = grList;
	}

	@Override
	public String toString() {
		return "SuccessReq [poList=" + poList + ", itemList=" + itemList + ", supplierList=" + supplierList
				+ ", grList=" + grList + "]";
	}





	
}
