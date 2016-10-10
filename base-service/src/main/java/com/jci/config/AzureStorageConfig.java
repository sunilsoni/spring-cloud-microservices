/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p>
 * <strong>The Class Azure Storage Configuration.</strong>
 * <p>
 *
 * @author csonisk
 */
@Configuration
public class AzureStorageConfig { // NO_UCD (unused code)
	
  /** The protocol. */
 @Value("${azure.storage.connection.protocol}")
  private String protocol;

  /** The account name. */
  @Value("${azure.storage.connection.accountname}")
  private String accountName;

  /** The account key. */
  @Value("${azure.storage.connection.accountkey}")
  private String accountKey;
  
  /**
   * Azure storege.
   *
   * @return the azure storage
   */
  @Bean
  public AzureStorage azureStorege() {
	AzureStorage as = new AzureStorage(protocol, accountName, accountKey);
    return as;
  }

}

