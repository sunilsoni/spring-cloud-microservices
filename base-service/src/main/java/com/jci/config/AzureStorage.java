/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.config;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;



/**
 * <p>
 * <strong>Azure Account Details for managing connection.</strong>
 * <p>
 *
 * @author csonisk
 */
public class AzureStorage {
	
  /** The Constant FORMAT. */
  private static final String FORMAT = "DefaultEndpointsProtocol=%s;AccountName=%s;AccountKey=%s";

  /** The protocol. */
  private String protocol;
  
  /** The account name. */
  private String accountName;
  
  /** The account key. */
  private String accountKey;
  
  /**
   * Instantiates a new azure storage.
   *
   * @param protocol the protocol
   * @param accountName the account name
   * @param accountKey the account key
   */
  public AzureStorage(String protocol, String accountName, String accountKey) {
	this.protocol  = protocol;
	this.accountName  = accountName;
	this.accountKey  = accountKey;
  }

  /**
   * Gets the storege connection string.
   *
   * @return the storege connection string
   */
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
  
  /**
   * Gets the single instance of AzureStorage.
   *
   * @return single instance of AzureStorage
   * @throws InvalidKeyException the invalid key exception
   * @throws URISyntaxException the URI syntax exception
   * @throws StorageException the storage exception
   */
  public CloudTableClient getInstance() throws InvalidKeyException, URISyntaxException, StorageException {
    return CloudStorageAccount.parse(getStoregeConnectionString()).createCloudTableClient();
  }

}
