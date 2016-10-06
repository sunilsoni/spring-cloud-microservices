/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.api.res;

/**
 * <p>
 * <strong> The Goods Receipt Details Class.</strong>
 * <p>
 *
 * @author csonisk
 */
public class GrDetails {
	
	
	/** The receipt ID. */
	private String receiptID;
	
	/** The receipt date. */
	private String receiptDate;
	
	/** The erp. */
	private String erp;
	
	/** The plant. */
	private String plant;
	
	/** The region. */
	private String region;
	
	/** The supplier type. */
	private String supplierType;
	
	/** The gr. */
	private Object gr;
	
	
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
	 * Gets the receipt date.
	 *
	 * @return the receipt date
	 */
	public String getReceiptDate() {
		return receiptDate;
	}
	
	/**
	 * Sets the receipt date.
	 *
	 * @param receiptDate the new receipt date
	 */
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
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
	 * Gets the supplier type.
	 *
	 * @return the supplier type
	 */
	public String getSupplierType() {
		return supplierType;
	}
	
	/**
	 * Sets the supplier type.
	 *
	 * @param supplierType the new supplier type
	 */
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	
	/**
	 * Gets the gr.
	 *
	 * @return the gr
	 */
	public Object getGr() {
		return gr;
	}
	
	/**
	 * Sets the gr.
	 *
	 * @param gr the new gr
	 */
	public void setGr(Object gr) {
		this.gr = gr;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GrDetails [receiptID=" + receiptID + ", receiptDate=" + receiptDate + ", erp=" + erp + ", plant="
				+ plant + ", region=" + region + ", supplierType=" + supplierType + ", gr=" + gr + "]";
	}
	
	
	
}
