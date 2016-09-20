/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.dto.res;


/**
 * <p>
 * <strong> The Class PoDetails.</strong>
 * <p>
 *
 * @author csonisk
 */
class PoDetails {

	/** The key. */
	private String key;
	
	/** The po no. */
	private String poNo;
	
	/** The description. */
	private String description;
	
	/** The status. */
	private int status;
	
	/** The source erp. */
	private String sourceErp;
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Gets the po no.
	 *
	 * @return the po no
	 */
	public String getPoNo() {
		return poNo;
	}
	
	/**
	 * Sets the po no.
	 *
	 * @param poNo the new po no
	 */
	public void setPoNo(String poNo) {
		this.poNo = poNo;
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * Gets the source erp.
	 *
	 * @return the source erp
	 */
	public String getSourceErp() {
		return sourceErp;
	}
	
	/**
	 * Sets the source erp.
	 *
	 * @param sourceErp the new source erp
	 */
	public void setSourceErp(String sourceErp) {
		this.sourceErp = sourceErp;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoDetails [key=" + key + ", poNo=" + poNo + ", description=" + description + ", status=" + status
				+ ", sourceErp=" + sourceErp + "]";
	}
	
	
}
