/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.item.azure;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

public class AzureStorage {
	
  private static final String FORMAT = "DefaultEndpointsProtocol=%s;AccountName=%s;AccountKey=%s";

  private String protocol;
  private String accountName;
  
  /** The account key. */
  private String accountKey;
  
  AzureStorage(String protocol, String accountName, String accountKey) {
	this.protocol  = protocol;
	this.accountName  = accountName;
	this.accountKey  = accountKey;
  }

  public final String getStoregeConnectionString() {
    return String.format(FORMAT, protocol, accountName, accountKey);
  }
  
  /**
   * Gets the table.
   *
   * @param tableName the table name
   * @return the table
   * @throws InvalidKeyException the invalid key exception
   * @throws URISyntaxException the URI syntax exception
   * @throws StorageException the storage exception
   */
  public CloudTable getTable(String tableName) throws InvalidKeyException, URISyntaxException, StorageException {
    return CloudStorageAccount.parse(getStoregeConnectionString()).createCloudTableClient().getTableReference(tableName);
  }
  
  public CloudTableClient getInstance() throws InvalidKeyException, URISyntaxException, StorageException {
    return CloudStorageAccount.parse(getStoregeConnectionString()).createCloudTableClient();
  }

}
