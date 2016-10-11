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
 * The Class FlatFileConfig.
 */
@Configuration
@RefreshScope
public class FlatFileConfig { // NO_UCD (unused code)

  	/** The hostname. */
	  @Value("${sftp.conn.hostname}")
  	private String hostname;
  	
  	/** The port. */
	  @Value("${sftp.conn.port}")
  	private int port;
  	
  	/** The username. */
	  @Value("${sftp.conn.username}")
  	private String username;
  	
  	/** The password. */
	  @Value("${sftp.conn.password}")
  	private String password;
  	
  	
  	/**
	   * Flat file.
	   *
	   * @return the flat file
	   */
	  @Bean
	  public FlatFile flatFile() {
		  FlatFile ff = new FlatFile(hostname,  port,  username,  password);
		  return ff;
	  }
}
