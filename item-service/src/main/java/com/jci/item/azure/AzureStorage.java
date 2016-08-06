package com.jci.item.azure;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

public class AzureStorage {
	
  private static final Logger LOG = LoggerFactory.getLogger(AzureStorage.class);
  private static final String FORMAT = "DefaultEndpointsProtocol=%s;AccountName=%s;AccountKey=%s";

  private String protocol;
  private String accountName;
  private String accountKey;
  
  public AzureStorage(String protocol, String accountName, String accountKey) {
	this.protocol  = protocol;
	this.accountName  = accountName;
	this.accountKey  = accountKey;
  }

  public final String getStoregeConnectionString() {
	// LOG.info(" ### Starting Ending AzureStorage.getStoregeConnectionString ###");
    return String.format(FORMAT, protocol, accountName, accountKey);
  }
  
  public CloudTable getTable(String tableName) throws InvalidKeyException, URISyntaxException, StorageException {
	  //LOG.info(" ### Starting Ending AzureStorage.getTable ### "+tableName);
    return CloudStorageAccount.parse(getStoregeConnectionString()).createCloudTableClient().getTableReference(tableName);
  }
  
  public CloudTableClient getInstance() throws InvalidKeyException, URISyntaxException, StorageException {
    return CloudStorageAccount.parse(getStoregeConnectionString()).createCloudTableClient();
  }

}
