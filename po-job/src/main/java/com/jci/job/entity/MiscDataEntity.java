package com.jci.job.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class MiscDataEntity   extends TableServiceEntity {

	public MiscDataEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	public MiscDataEntity() {
		
	}
	
	private int poIntransitCount;//1
	private int poProcessedCount;//2
	private int poErrorCount;//3
	
	private int itemIntransitCount;//1
	private int itemProcessedCount;//2
	private int itemErrorCount;//3
	
	private int suppIntransitCount;//1
	private int suppProcessedCount;//2
	private int suppErrorCount;//3
	
	private String orderNumber;
	private String comment;
	

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

	public int getPoIntransitCount() {
		return poIntransitCount;
	}

	public void setPoIntransitCount(int poIntransitCount) {
		this.poIntransitCount = poIntransitCount;
	}

	public int getPoProcessedCount() {
		return poProcessedCount;
	}

	public void setPoProcessedCount(int poProcessedCount) {
		this.poProcessedCount = poProcessedCount;
	}

	public int getPoErrorCount() {
		return poErrorCount;
	}

	public void setPoErrorCount(int poErrorCount) {
		this.poErrorCount = poErrorCount;
	}

	public int getItemIntransitCount() {
		return itemIntransitCount;
	}

	public void setItemIntransitCount(int itemIntransitCount) {
		this.itemIntransitCount = itemIntransitCount;
	}

	public int getItemProcessedCount() {
		return itemProcessedCount;
	}

	public void setItemProcessedCount(int itemProcessedCount) {
		this.itemProcessedCount = itemProcessedCount;
	}

	public int getItemErrorCount() {
		return itemErrorCount;
	}

	public void setItemErrorCount(int itemErrorCount) {
		this.itemErrorCount = itemErrorCount;
	}

	public int getSuppIntransitCount() {
		return suppIntransitCount;
	}

	public void setSuppIntransitCount(int suppIntransitCount) {
		this.suppIntransitCount = suppIntransitCount;
	}

	public int getSuppProcessedCount() {
		return suppProcessedCount;
	}

	public void setSuppProcessedCount(int suppProcessedCount) {
		this.suppProcessedCount = suppProcessedCount;
	}

	public int getSuppErrorCount() {
		return suppErrorCount;
	}

	public void setSuppErrorCount(int suppErrorCount) {
		this.suppErrorCount = suppErrorCount;
	}

	@Override
	public String toString() {
		return "MiscDataEntity [poIntransitCount=" + poIntransitCount + ", poProcessedCount=" + poProcessedCount
				+ ", poErrorCount=" + poErrorCount + ", itemIntransitCount=" + itemIntransitCount
				+ ", itemProcessedCount=" + itemProcessedCount + ", itemErrorCount=" + itemErrorCount
				+ ", suppIntransitCount=" + suppIntransitCount + ", suppProcessedCount=" + suppProcessedCount
				+ ", suppErrorCount=" + suppErrorCount + ", orderNumber=" + orderNumber + ", comment=" + comment + "]";
	}

}
