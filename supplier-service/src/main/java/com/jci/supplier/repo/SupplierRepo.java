package com.jci.supplier.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.supplier.azure.data.DataHelper;
import com.jci.supplier.azure.data.ResultSet;
import com.jci.supplier.azure.query.ScrollingParam;
import com.microsoft.azure.storage.StorageException;


public interface SupplierRepo {
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException;
}
