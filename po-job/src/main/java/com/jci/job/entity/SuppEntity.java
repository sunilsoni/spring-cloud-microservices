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
	
	/** The status. */
	private int status;
	
	/** The erp name. */
	private String erpName;
	
	/** The region. */
	private String region;
	
	/** The plant. */
	private String plant;


	/**
	 * Gets the erp name.
	 *
	 * @return the erp name
	 */
	public String getErpName() {
		return erpName;
	}

	/**
	 * Sets the erp name.
	 *
	 * @param erpName the new erp name
	 */
	public void setErpName(String erpName) {
		this.erpName = erpName;
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(int status) {
		this.status = status;
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
		return "SuppEntity [jsonString=" + jsonString + ", status=" + status + ", erpName=" + erpName + ", region="
				+ region + ", plant=" + plant + "]";
	}

	
}
