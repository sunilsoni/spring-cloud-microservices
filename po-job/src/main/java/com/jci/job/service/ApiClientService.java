package com.jci.job.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.StorageException;

public interface ApiClientService {
	
	String getPoDetails()  throws InvalidKeyException, URISyntaxException, StorageException ;
	String processFlatFile () throws InvalidKeyException, URISyntaxException, StorageException;
	
	String getItemDetails() ;
	String getSupplierDetails() ;

}
