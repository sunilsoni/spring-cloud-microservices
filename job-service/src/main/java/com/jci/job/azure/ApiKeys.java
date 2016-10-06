/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.azure;

/**
 * <p>
 * <strong> The ApiKeys Class.</strong>
 * <p>
 *
 * @author csonisk
 */
public class ApiKeys {
	
	  
	  /** The po key. */
	  private  String poKey; 
  	
	  /** The gr key. */
	  private  String grKey; 
  	
	  /** The item key. */
	  private  String itemKey; 
  	
	  /** The supp key. */
	  private  String suppKey; 
	  
	  /**
  	 * Instantiates a new api keys.
  	 *
  	 * @param poKey the po key
  	 * @param grKey the gr key
  	 * @param itemKey the item key
  	 * @param suppKey the supp key
  	 */
  	ApiKeys(String poKey,String grKey, String itemKey, String suppKey) {
		this.poKey  = poKey;
		this.grKey  = grKey;
		this.itemKey  = itemKey;
		this.suppKey  = suppKey;
	  }


    /**
     * Gets the po key.
     *
     * @return the po key
     */
    public String getPoKey() {
        return poKey;
    }

    /**
     * Sets the po key.
     *
     * @param poKey the new po key
     */
    public void setPoKey(String poKey) {
        this.poKey = poKey;
    }

    /**
     * Gets the gr key.
     *
     * @return the gr key
     */
    public String getGrKey() {
        return grKey;
    }

    /**
     * Sets the gr key.
     *
     * @param grKey the new gr key
     */
    public void setGrKey(String grKey) {
        this.grKey = grKey;
    }

    /**
     * Gets the item key.
     *
     * @return the item key
     */
    public String getItemKey() {
        return itemKey;
    }

    /**
     * Sets the item key.
     *
     * @param itemKey the new item key
     */
    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    /**
     * Gets the supp key.
     *
     * @return the supp key
     */
    public String getSuppKey() {
        return suppKey;
    }

    /**
     * Sets the supp key.
     *
     * @param suppKey the new supp key
     */
    public void setSuppKey(String suppKey) {
        this.suppKey = suppKey;
    }

	  
		  
}
