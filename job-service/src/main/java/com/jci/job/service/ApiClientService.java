/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.StorageException;


/**
 * The Interface ApiClientService.
 */
public interface ApiClientService {
	
	String getPoDetails(String plant, String erp, String region)  throws InvalidKeyException, URISyntaxException, StorageException ;
	
	String getGrDetails(String plant, String erp, String region)  throws InvalidKeyException, URISyntaxException, StorageException ;
	
	String getItemDetails(String plant, String erp, String region) throws InvalidKeyException, URISyntaxException, StorageException ;
	
	String getSuppDetails(String plant, String erp, String region) throws InvalidKeyException, URISyntaxException, StorageException  ;
	
	/**
	 * Process po flat file.
	 */
	void processPoFlatFile () ;
	
	/**
	 * Process gr flat file.
	 */
	void processGrFlatFile () ;
	
	/**
	 * Process supp flat file.
	 */
	void processSuppFlatFile () ;
	
	/**
	 * Process item flat file.
	 */
	void processItemFlatFile () ;

}
