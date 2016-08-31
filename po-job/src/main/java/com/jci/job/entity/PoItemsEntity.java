package com.jci.job.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microsoft.azure.storage.table.TableServiceEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoItemsEntity extends TableServiceEntity {

	public PoItemsEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;//orderNumber_lineID"_"requestID
	}

	public PoItemsEntity() {
	}

	private String orderNumber;
	private String jsonString;
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	@Override
	public String toString() {
		return "PoItemsEntity [orderNumber=" + orderNumber + ", jsonString=" + jsonString + "]";
	}

	
	
	
}
