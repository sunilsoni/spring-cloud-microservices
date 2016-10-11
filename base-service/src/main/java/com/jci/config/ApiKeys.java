/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.config;
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

	public ApiKeys(String poKey, String grKey, String itemKey, String suppKey) {
		super();
		this.poKey = poKey;
		this.grKey = grKey;
		this.itemKey = itemKey;
		this.suppKey = suppKey;
	}

	public String getPoKey() {
		return poKey;
	}

	public void setPoKey(String poKey) {
		this.poKey = poKey;
	}

	public String getGrKey() {
		return grKey;
	}

	public void setGrKey(String grKey) {
		this.grKey = grKey;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getSuppKey() {
		return suppKey;
	}

	public void setSuppKey(String suppKey) {
		this.suppKey = suppKey;
	} 
	  
	  
}
