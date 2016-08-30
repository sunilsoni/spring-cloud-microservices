package com.jci.job.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class ItemEntity extends TableServiceEntity {

	public ItemEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	public ItemEntity() {
	}

	private String itemJsonString;

	public String getItemJsonString() {
		return itemJsonString;
	}

	public void setItemJsonString(String itemJsonString) {
		this.itemJsonString = itemJsonString;
	}

	@Override
	public String toString() {
		return "ItemEntity [itemJsonString=" + itemJsonString + "]";
	}	
	
	
	
}
