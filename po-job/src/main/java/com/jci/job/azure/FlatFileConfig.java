/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.azure;

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

	  /** The e 2 open. */
 @Value("${flat.file.destination.e2open.url}")
	  private  String e2open;
	
	  /** The po url. */
  	@Value("${flat.file.destination.po.folder.url}")
	  private  String poUrl;
	  
	  /** The item url. */
  	@Value("${flat.file.destination.item.folder.url}")
	  private  String itemUrl;
	  
	  /** The supp url. */
  	@Value("${flat.file.destination.supplier.folder.url}")
	  private  String suppUrl;
	  
	  /** The region url. */
  	@Value("${flat.file.destination.region.url}")
	  private  String regionUrl;
	 
	  /** The sender duns. */
  	@Value("${flat.file.name.sender.duns}")
	  private  String senderDuns;
	 
	  /** The receiver duns. */
  	@Value("${flat.file.name.receiver.duns}")
	  private  String receiverDuns;
	  
	  /** The message type. */
  	@Value("${flat.file.name.message.type}")
	  private   String messageType;
	  
	  /** The version. */
  	@Value("${flat.file.name.version}")
	  private   String version;
	  
	  /** The site id. */
  	@Value("${flat.file.name.site.id}")
	  private  String siteId;
	  
	  /** The date format. */
  	@Value("${flat.file.name.date.format}")
	  private  String dateFormat;
	  
	  /** The time zone. */
  	@Value("${flat.file.name.date.time.zone}")
	  private  String timeZone;

	  /**
  	 * Flat file.
  	 *
  	 * @return the flat file
  	 */
  	@Bean
	  public FlatFile flatFile() {
		  FlatFile ff = new FlatFile(e2open, poUrl,itemUrl,suppUrl, senderDuns,receiverDuns,messageType,version,siteId,dateFormat,timeZone,regionUrl);
		  return ff;
	  }
	  
	  
	  
}
