/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.config;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * <strong> The ApiKeys Class.</strong>
 * <p>
 *
 * @author csonisk
 */
@Data
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


}
