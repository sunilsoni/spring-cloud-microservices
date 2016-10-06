/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.req;


/**
 * <p>
 * <strong> The Goods Receipt Class.</strong>
 * <p>
 *
 * @author csonisk
 */
public class GrBody {
	
	/** The receipt ID. */
	private String receiptID;
	
	/** The erp. */
	private String erp;
	
	/** The region. */
	private String region;
	
	/** The plant. */
	private String plant;
	
	/**
	 * Gets the receipt ID.
	 *
	 * @return the receipt ID
	 */
	public String getReceiptID() {
		return receiptID;
	}
	
	/**
	 * Sets the receipt ID.
	 *
	 * @param receiptID the new receipt ID
	 */
	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}
	
	/**
	 * Gets the erp.
	 *
	 * @return the erp
	 */
	public String getErp() {
		return erp;
	}
	
	/**
	 * Sets the erp.
	 *
	 * @param erp the new erp
	 */
	public void setErp(String erp) {
		this.erp = erp;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GrBody [receiptID=" + receiptID + ", erp=" + erp + ", region=" + region + ", plant=" + plant + "]";
	}
	


}
