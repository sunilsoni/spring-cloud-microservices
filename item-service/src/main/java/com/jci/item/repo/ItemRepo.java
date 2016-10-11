/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.item.repo;

import com.jci.azure.DataHelper;
import com.jci.azure.ResultSet;
import com.jci.azure.ScrollingParam;



/**
 * <p>
 *  * The Interface ItemRepo.
 * <p>
 *
 * @author csonisk
 */
public interface ItemRepo {
	
	ResultSet getSegmentedResultSet(ScrollingParam param, DataHelper request);
}
