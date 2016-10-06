/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;



/**
 * The Class SuppEntity.
 */
public class SuppEntity extends TableServiceEntity {

	/**
	 * Instantiates a new supp entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 */
	public SuppEntity(String partitionKey, String rowKey) { // NO_UCD (unused code)
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; //supplierID
	}

	
	/**
	 * Instantiates a new supp entity.
	 */
	public SuppEntity() {
	}

	/** The json string. */
	private String jsonString;
	
	/** The supplier delivery state. */
	private int supplierDeliveryState;
	
	/** The user name. */
	private String userName;
	
	/** The global id. */
	private String globalId;	
	
	/** The comment. */
	private String comment;	
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * Gets the global id.
	 *
	 * @return the global id
	 */
	public String getGlobalId() {
		return globalId;
	}


	/**
	 * Sets the global id.
	 *
	 * @param globalId the new global id
	 */
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}


	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}


	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}


	/**
	 * Gets the supplier delivery state.
	 *
	 * @return the supplier delivery state
	 */
	public int getSupplierDeliveryState() {
        return supplierDeliveryState;
    }

    /**
     * Sets the supplier delivery state.
     *
     * @param supplierDeliveryState the new supplier delivery state
     */
    public void setSupplierDeliveryState(int supplierDeliveryState) {
        this.supplierDeliveryState = supplierDeliveryState;
    }

	/** The region. */
	private String region;
	
	/** The plant. */
	private String plant;
	
	/** The supp type. */
	private String suppType;

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


	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Gets the plant.
	 *
	 * @return the plant
	 */
	public String getPlant() {
		return plant;
	}

	/**
	 * Sets the plant.
	 *
	 * @param plant the new plant
	 */
	public void setPlant(String plant) {
		this.plant = plant;
	}

	/**
	 * Gets the supp type.
	 *
	 * @return the supp type
	 */
	public String getSuppType() {
		return suppType;
	}

	/**
	 * Sets the supp type.
	 *
	 * @param suppType the new supp type
	 */
	public void setSuppType(String suppType) {
		this.suppType = suppType;
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
		SuppEntity other = (SuppEntity) obj;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SuppEntity [jsonString=" + jsonString + ", supplierDeliveryState=" + supplierDeliveryState
				+ ", userName=" + userName + ", globalId=" + globalId + ", comment=" + comment + ", region=" + region
				+ ", plant=" + plant + ", suppType=" + suppType + "]";
	}
	

}
