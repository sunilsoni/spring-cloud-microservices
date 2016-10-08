/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.supp.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.azure.DataHelper;
import com.jci.azure.ResultSet;
import com.jci.azure.ScrollingParam;
import com.microsoft.azure.storage.StorageException;



/**
 * <p>
 * <strong>The Class SuppRepo.</strong>
 * <p>
 *
 * @author csonisk
 */
public interface SuppRepo {
	
	/**
	 * Gets the segmented result set.
	 *
	 * @param param the param
	 * @param request the request
	 * @return the segmented result set
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException;
}
