package com.jci.job.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microsoft.azure.storage.table.TableServiceEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoItemsEntity extends TableServiceEntity {

	public PoItemsEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;
	}

	public PoItemsEntity() {
	}

	private String orderNumber;
	private String itemJsonString;
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getItemJsonString() {
		return itemJsonString;
	}

	public void setItemJsonString(String itemJsonString) {
		this.itemJsonString = itemJsonString;
	}

	@Override
	public String toString() {
		return "PoItemsEntity [orderNumber=" + orderNumber + ", itemJsonString=" + itemJsonString + "]";
	}

	
	
}
