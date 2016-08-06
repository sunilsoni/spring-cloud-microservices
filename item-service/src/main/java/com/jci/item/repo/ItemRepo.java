package com.jci.item.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.item.azure.data.DataHelper;
import com.jci.item.azure.data.ResultSet;
import com.jci.item.azure.query.ScrollingParam;
import com.microsoft.azure.storage.StorageException;


public interface ItemRepo {
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException;
}
