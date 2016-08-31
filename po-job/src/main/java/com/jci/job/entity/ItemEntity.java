package com.jci.job.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class ItemEntity extends TableServiceEntity {

	public ItemEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; //customerItemID_supplierID
	}

	public ItemEntity() {
	}

	private String jsonString;
	private int status;
	private String erpName;
	private String region;
	private String plant;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
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

	@Override
	public String toString() {
		return "ItemEntity [jsonString=" + jsonString + ", status=" + status + ", erpName=" + erpName + ", region="
				+ region + ", plant=" + plant + "]";
	}


}
