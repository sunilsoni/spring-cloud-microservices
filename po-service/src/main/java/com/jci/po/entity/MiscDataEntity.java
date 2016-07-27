package com.jci.po.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class MiscDataEntity   extends TableServiceEntity {

	public MiscDataEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	public MiscDataEntity() {
		
	}
	
	private int intransitCount;//1
	private int processedCount;//2
	private int errorCount;//3
	
	private String orderNumber;
	private String comment;
	
	public int getIntransitCount() {
		return intransitCount;
	}

	public void setIntransitCount(int intransitCount) {
		this.intransitCount = intransitCount;
	}

	public int getProcessedCount() {
		return processedCount;
	}

	public void setProcessedCount(int processedCount) {
		this.processedCount = processedCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "MiscDataEntity [intransitCount=" + intransitCount + ", processedCount=" + processedCount
				+ ", errorCount=" + errorCount + ", orderNumber=" + orderNumber + ", comment=" + comment + "]";
	}
	
	
}
