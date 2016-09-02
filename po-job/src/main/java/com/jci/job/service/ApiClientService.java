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
	 * @return the po details
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	String getPoDetails()  throws InvalidKeyException, URISyntaxException, StorageException ;
	
	/**
	 * Process po flat file.
	 *
	 * @return the string
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	String processPoFlatFile () throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * Process supp flat file.
	 *
	 * @return the string
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	String processSuppFlatFile () throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * Process item flat file.
	 *
	 * @return the string
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	String processItemFlatFile () throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * Gets the item details.
	 *
	 * @return the item details
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	String getItemDetails() throws InvalidKeyException, URISyntaxException, StorageException ;
	
	/**
	 * Gets the supp details.
	 *
	 * @return the supp details
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	String getSuppDetails() throws InvalidKeyException, URISyntaxException, StorageException  ;

}
