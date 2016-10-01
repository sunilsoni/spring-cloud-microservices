/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class FlatFileConfig.
 */
@Configuration
@RefreshScope
public class FlatFileConfig { // NO_UCD (unused code)

  	@Value("${flat.file.po.supplier.mapping.po.url}")
    String poMappingFileUrl;
  	
  	@Value("${flat.file.po.supplier.mapping.gr.url}")
    String grMappingFileUrl;
  	
  	@Value("${flat.file.po.supplier.mapping.item.url}")
    String itemMappingFileUrl;
  	
  	@Value("${flat.file.po.supplier.mapping.supp.url}")
    String suppMappingFileUrl;
    
  	@Value("${sftp.conn.hostname}")
  	private String hostname;
  	
  	@Value("${sftp.conn.port}")
  	private int port;
  	
  	@Value("${sftp.conn.username}")
  	private String username;
  	
  	@Value("${sftp.conn.password}")
  	private String password;
  	
  	
  	@Bean
	  public FlatFile flatFile() {
		  FlatFile ff = new FlatFile(poMappingFileUrl,  grMappingFileUrl,  itemMappingFileUrl,
					 suppMappingFileUrl,  hostname,  port,  username,  password);
		  return ff;
	  }
}
