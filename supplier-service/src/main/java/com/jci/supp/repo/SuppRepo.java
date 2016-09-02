package com.jci.supp.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.supp.azure.data.DataHelper;
import com.jci.supp.azure.data.ResultSet;
import com.jci.supp.azure.query.ScrollingParam;
import com.microsoft.azure.storage.StorageException;


public interface SuppRepo {
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException;
}
