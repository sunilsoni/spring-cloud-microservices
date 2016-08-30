package com.jci.job.entity;

import java.util.Date;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class PoEntity extends TableServiceEntity {

	public PoEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;// po number
	}

	public PoEntity() {
	}

	/**
	 * #*Order Number Sequence No: 0
	 */
	//private String orderNumber;

	/**
	 * *Order Creation Date Sequence No: 1
	 */
	private Date orderCreationDate;
   	private int status;
   	private String erpName;
   	private String description;
	private String userName;
	private String globalId;	
	private String comment;	
	private String supplierType;
	private String destSuppliers;
	private String grNum ;	
	private boolean poACK ;
	private boolean asn ;
	
	private String region;
	private String plant;
	
	public Date getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(Date orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGlobalId() {
		return globalId;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getDestSuppliers() {
		return destSuppliers;
	}

	public void setDestSuppliers(String destSuppliers) {
		this.destSuppliers = destSuppliers;
	}

	public String getGrNum() {
		return grNum;
	}

	public void setGrNum(String grNum) {
		this.grNum = grNum;
	}

	public boolean isPoACK() {
		return poACK;
	}

	public void setPoACK(boolean poACK) {
		this.poACK = poACK;
	}

	public boolean isAsn() {
		return asn;
	}

	public void setAsn(boolean asn) {
		this.asn = asn;
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
		return "PoEntity [orderCreationDate=" + orderCreationDate + ", status=" + status + ", erpName=" + erpName
				+ ", description=" + description + ", userName=" + userName + ", globalId=" + globalId + ", comment="
				+ comment + ", supplierType=" + supplierType + ", destSuppliers=" + destSuppliers + ", grNum=" + grNum
				+ ", poACK=" + poACK + ", asn=" + asn + ", region=" + region + ", plant=" + plant + "]";
	}

	
	
	

}
