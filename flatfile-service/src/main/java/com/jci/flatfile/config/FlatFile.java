/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.config;

public class FlatFile {
	
  	/*private  String poMappingFileUrl;
  	private  String grMappingFileUrl;
  	private  String itemMappingFileUrl;
  	private  String suppMappingFileUrl;*/
  	
  	private String hostname;
  	private int port;
  	private String username;
  	private String password;
	public FlatFile(String hostname, int port, String username, String password) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "FlatFile [hostname=" + hostname + ", port=" + port + ", username=" + username + ", password=" + password
				+ "]";
	}
  	
  	
}
