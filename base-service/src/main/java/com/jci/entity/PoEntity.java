/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.entity;

import java.util.Date;

import com.microsoft.azure.storage.table.TableServiceEntity;





/**
 * The Class PoEntity.
 */
public class PoEntity extends TableServiceEntity {

	/**
	 * Instantiates a new po entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 */
	public PoEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey;
	}

	/**
	 * Instantiates a new po entity.
	 */
	public PoEntity() {
	}

	/** The order creation date. */
	private Date orderCreationDate;
   	
	/** The supplier delivery state. */
	private int supplierDeliveryState;

   /** The description. */
   private String description;
	
	/** The user name. */
	private String userName;
	
	/** The global id. */
	private String globalId;	
	
	/** The comment. */
	private String comment;	
	
	/** The supp type. */
	private String suppType;
	
	/** The region. */
	private String region;
	
	/** The plant. */
	private String plant;
	
	/** The json string. */
	private String jsonString;
	
	/**
	 * Gets the order creation date.
	 *
	 * @return the order creation date
	 */
	public Date getOrderCreationDate() {
		return orderCreationDate;
	}

	/**
	 * Sets the order creation date.
	 *
	 * @param orderCreationDate the new order creation date
	 */
	public void setOrderCreationDate(Date orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}


	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the global id.
	 *
	 * @return the global id
	 */
	public String getGlobalId() {
		return globalId;
	}

	/**
	 * Sets the global id.
	 *
	 * @param globalId the new global id
	 */
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
    

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
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
		PoEntity other = (PoEntity) obj;
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
