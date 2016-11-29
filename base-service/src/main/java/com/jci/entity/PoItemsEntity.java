/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
/**
 * The Class PoItemsEntity.
 */
@Data
//@Builder
public class PoItemsEntity extends TableServiceEntity {

	/**
	 * Instantiates a new po items entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 */
	@Tolerate
	public PoItemsEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;//orderNumber_lineID"_"requestID
	}


	/** The order number. */
	private String orderNumber;
	
	/** The json string. */
	private String jsonString;
	
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
		PoItemsEntity other = (PoItemsEntity) obj;
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
	
	
}
