/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microsoft.azure.storage.table.TableServiceEntity;

/**
 * The Class PoItemsEntity.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoItemsEntity extends TableServiceEntity {

	/**
	 * Instantiates a new po items entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 */
	public PoItemsEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;//orderNumber_lineID"_"requestID
	}

	/**
	 * Instantiates a new po items entity.
	 */
	public PoItemsEntity() {
	}

	/** The order number. */
	private String orderNumber;
	
	/** The json string. */
	private String jsonString;
	
	/**
	 * Gets the order number.
	 *
	 * @return the order number
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Sets the order number.
	 *
	 * @param orderNumber the new order number
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * Gets the json string.
	 *
	 * @return the json string
	 */
	public String getJsonString() {
		return jsonString;
	}

	/**
	 * Sets the json string.
	 *
	 * @param jsonString the new json string
	 */
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoItemsEntity [orderNumber=" + orderNumber + ", jsonString=" + jsonString + "]";
	}

	
	
	
}
