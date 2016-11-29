/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.config;

import lombok.Data;

/**
 * The Class FlatFile.
 */
@Data
public class FlatFile {
	
  	/** The hostname. */
	  private String hostname;
  	
	  /** The port. */
	  private int port;
  	
	  /** The username. */
	  private String username;
  	
	  /** The password. */
	  private String password;
	
	/**
	 * Instantiates a new flat file.
	 *
	 * @param hostname the hostname
	 * @param port the port
	 * @param username the username
	 * @param password the password
	 */
	public FlatFile(String hostname, int port, String username, String password) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
}
