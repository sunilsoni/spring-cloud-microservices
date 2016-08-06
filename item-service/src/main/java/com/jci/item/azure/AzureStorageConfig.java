package com.jci.item.azure;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AzureStorageConfig {
  //private static final Logger LOG = LoggerFactory.getLogger(AzureStorageConfig.class);
	
  @Value("${azure.storage.connection.protocol}")
  private String protocol;

  @Value("${azure.storage.connection.accountname}")
  private String accountName;

  @Value("${azure.storage.connection.accountkey}")
  private String accountKey;
  
  @Bean
  public AzureStorage azureStorege() {
	AzureStorage as = new AzureStorage(protocol, accountName, accountKey);
    return as;
  }

}

