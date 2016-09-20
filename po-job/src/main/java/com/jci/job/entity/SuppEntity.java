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
	
	private String suppType;

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErpName() {
		return erpName;
	}

	public void setErpName(String erpName) {
		this.erpName = erpName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getSuppType() {
		return suppType;
	}

	public void setSuppType(String suppType) {
		this.suppType = suppType;
	}

	@Override
	public String toString() {
		return "SuppEntity [jsonString=" + jsonString + ", status=" + status + ", erpName=" + erpName + ", region="
				+ region + ", plant=" + plant + ", suppType=" + suppType + "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partitionKey == null) ? 0 : partitionKey.hashCode());
		result = prime * result + ((rowKey == null) ? 0 : rowKey.hashCode());
		return result;
	}

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
