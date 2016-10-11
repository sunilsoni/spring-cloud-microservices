/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * <p>
 * <strong> The ApiKeysConfig Class.</strong>
 * <p>
 *
 * @author csonisk
 */
@Configuration
@RefreshScope
public class ApiKeysConfig { // NO_UCD (unused code)

	  
  	/** The po key. */
	  @Value("${apigee.api.key.po}")
	private  String poKey;
	 
  	/** The gr key. */
	  @Value("${apigee.api.key.gr}")
	private  String grKey;
	  
  	/** The item key. */
	  @Value("${apigee.api.key.item}")
	private   String itemKey;
	  
  	/** The supp key. */
	  @Value("${apigee.api.key.supp}")
	private   String suppKey;
	  
  /**
   * Flat file.
   *
   * @return the api keys
   */
  @Bean
  public ApiKeys apiKeys() {
	  ApiKeys ff = new ApiKeys(poKey,grKey,itemKey,suppKey);
	  return ff;
  }
	  
}
