package com.jci.po.entity;

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
   	private String sourceErpName;
   	private String description;
	private String userName;
	private String globalId;
	
	private String comment;
	
	private String suppliers;
	
	
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
	
	public String getSourceErpName() {
		return sourceErpName;
	}
	
	public void setSourceErpName(String sourceErpName) {
		this.sourceErpName = sourceErpName;
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

	public String getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(String suppliers) {
		this.suppliers = suppliers;
	}

	@Override
	public String toString() {
		return "PoEntity [orderCreationDate=" + orderCreationDate + ", status=" + status + ", sourceErpName="
				+ sourceErpName + ", description=" + description + ", userName=" + userName + ", globalId=" + globalId
				+ ", comment=" + comment + ", suppliers=" + suppliers + "]";
	}

}
