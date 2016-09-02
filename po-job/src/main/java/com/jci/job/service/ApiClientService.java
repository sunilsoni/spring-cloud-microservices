package com.jci.job.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.StorageException;

public interface ApiClientService {
	
	String getPoDetails()  throws InvalidKeyException, URISyntaxException, StorageException ;
	String processPoFlatFile () throws InvalidKeyException, URISyntaxException, StorageException;
	
	String processSuppFlatFile () throws InvalidKeyException, URISyntaxException, StorageException;
	String processItemFlatFile () throws InvalidKeyException, URISyntaxException, StorageException;
	
	String getItemDetails() throws InvalidKeyException, URISyntaxException, StorageException ;
	String getSuppDetails() throws InvalidKeyException, URISyntaxException, StorageException  ;

}
