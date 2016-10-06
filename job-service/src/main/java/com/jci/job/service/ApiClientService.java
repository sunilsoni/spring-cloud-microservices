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
	
	/**
	 * Gets the po details.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the po details
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	String getPoDetails(String plant, String erp, String region)  throws InvalidKeyException, URISyntaxException, StorageException ;
	
	/**
	 * Gets the gr details.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the gr details
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	String getGrDetails(String plant, String erp, String region)  throws InvalidKeyException, URISyntaxException, StorageException ;
	
	/**
	 * Gets the item details.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the item details
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	String getItemDetails(String plant, String erp, String region) throws InvalidKeyException, URISyntaxException, StorageException ;
	
	/**
	 * Gets the supp details.
	 *
	 * @param plant the plant
	 * @param erp the erp
	 * @param region the region
	 * @return the supp details
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
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
