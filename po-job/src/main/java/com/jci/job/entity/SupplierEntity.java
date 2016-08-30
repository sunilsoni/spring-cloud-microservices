package com.jci.job.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class SupplierEntity extends TableServiceEntity {

	public SupplierEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	public SupplierEntity() {
	}

	private String supplierJsonString;

	public String getSupplierJsonString() {
		return supplierJsonString;
	}

	public void setSupplierJsonString(String supplierJsonString) {
		this.supplierJsonString = supplierJsonString;
	}

	@Override
	public String toString() {
		return "SupplierEntity [supplierJsonString=" + supplierJsonString + "]";
	}	
	
	
}
