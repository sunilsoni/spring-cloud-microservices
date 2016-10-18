/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microsoft.azure.storage.table.TableServiceEntity;
/**
 * The Class PoItemsEntity.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrItemsEntity extends TableServiceEntity {

	/**
	 * Instantiates a new po items entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 */
	public GrItemsEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;//receiptID
	}

	/**
	 * Instantiates a new po items entity.
	 */
	public GrItemsEntity() {
	}

	/** The json string. */
	private String jsonString;

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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partitionKey == null) ? 0 : partitionKey.hashCode());
		result = prime * result + ((rowKey == null) ? 0 : rowKey.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrItemsEntity other = (GrItemsEntity) obj;
		if (partitionKey == null) {
			if (other.partitionKey != null)
				return false;
		} else if (!partitionKey.equals(other.partitionKey))
			return false;
		if (rowKey == null) {
			if (other.rowKey != null)
				return false;
		} else if (!rowKey.equals(other.rowKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrItemsEntity [jsonString=" + jsonString + "]";
	}

	
}
