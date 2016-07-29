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
	private String orderNumber;

	/**
	 * *Order Creation Date Sequence No: 1
	 */
	private Date orderCreationDate;

   	private int status;
   
   	private int sourceErpName;
   
   	private String description;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
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
	
	public int getSourceErpName() {
		return sourceErpName;
	}
	
	public void setSourceErpName(int sourceErpName) {
		this.sourceErpName = sourceErpName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	   
	   

}
