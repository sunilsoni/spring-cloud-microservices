/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.entity;

import java.util.Date;

import com.microsoft.azure.storage.table.TableServiceEntity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * The Class PoEntity.
 */
@Data
@Builder
public class PoEntity extends TableServiceEntity {

	/**
	 * Instantiates a new po entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 */
	@Tolerate
	public PoEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;
	}


	/** The order creation date. */
	private Date orderCreationDate;
   	
	/** The supplier delivery state. */
	private int supplierDeliveryState;

   /** The description. */
   private String description;
	
	/** The user name. */
	private String userName;
	
	/** The global id. */
	private String globalId;	
	
	/** The comment. */
	private String comment;	
	
	/** The supp type. */
	private String suppType;
	
	/** The region. */
	private String region;
	
	/** The plant. */
	private String plant;
	
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
		PoEntity other = (PoEntity) obj;
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
