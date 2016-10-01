/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.entity;

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
	public SuppEntity(String partitionKey, String rowKey) {
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
	

}
