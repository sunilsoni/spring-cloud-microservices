/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.config;

public class FlatFile {
	
  	private  String poMappingFileUrl;
  	private  String grMappingFileUrl;
  	private  String itemMappingFileUrl;
  	private  String suppMappingFileUrl;
  	
  	private String hostname;
  	private int port;
  	private String username;
  	private String password;
  	


    public FlatFile(String poMappingFileUrl, String grMappingFileUrl, String itemMappingFileUrl,
			String suppMappingFileUrl, String hostname, int port, String username, String password) {
		super();
		this.poMappingFileUrl = poMappingFileUrl;
		this.grMappingFileUrl = grMappingFileUrl;
		this.itemMappingFileUrl = itemMappingFileUrl;
		this.suppMappingFileUrl = suppMappingFileUrl;
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
	}


	public String getPoMappingFileUrl() {
        return poMappingFileUrl;
    }


    public void setPoMappingFileUrl(String poMappingFileUrl) {
        this.poMappingFileUrl = poMappingFileUrl;
    }


    public String getGrMappingFileUrl() {
        return grMappingFileUrl;
    }


    public void setGrMappingFileUrl(String grMappingFileUrl) {
        this.grMappingFileUrl = grMappingFileUrl;
    }


    public String getItemMappingFileUrl() {
        return itemMappingFileUrl;
    }


    public void setItemMappingFileUrl(String itemMappingFileUrl) {
        this.itemMappingFileUrl = itemMappingFileUrl;
    }


    public String getSuppMappingFileUrl() {
        return suppMappingFileUrl;
    }


    public void setSuppMappingFileUrl(String suppMappingFileUrl) {
        this.suppMappingFileUrl = suppMappingFileUrl;
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
  	
    
  	
}
