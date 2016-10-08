/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.config;


/**
 * The Class FlatFile.
 */
public class FlatFile {
	
  	/*private  String poMappingFileUrl;
  	private  String grMappingFileUrl;
  	private  String itemMappingFileUrl;
  	private  String suppMappingFileUrl;*/
  	
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
	FlatFile(String hostname, int port, String username, String password) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Gets the hostname.
	 *
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * Sets the hostname.
	 *
	 * @param hostname the new hostname
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlatFile [hostname=" + hostname + ", port=" + port + ", username=" + username + ", password=" + password
				+ "]";
	}
  	
  	
}
