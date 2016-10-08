/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.supp.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.azure.DataHelper;
import com.jci.azure.PaginationParam;
import com.jci.azure.ResultSet;
import com.jci.azure.ScrollingParam;
import com.jci.dto.SegmentedDetailReq;
import com.jci.dto.SegmentedDetailRes;
import com.jci.supp.repo.SuppRepo;
import com.jci.utils.Constants;
import com.microsoft.azure.storage.StorageException;


/**
 * <p>
 * <strong>The Class SuppServiceImpl.</strong>
 * <p>
 *
 * @author csonisk
 */
@Service
public class SuppServiceImpl implements SuppService{ // NO_UCD (unused code)

	/** The repo. */
 @Autowired
	private SuppRepo repo;
	
	/* (non-Javadoc)
	 * @see com.jci.supp.service.SuppService#getSupplierResultSet(com.jci.supp.dto.SegmentedDetailReq)
	 */
	@Override
	public SegmentedDetailRes getSupplierResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException  {
		PaginationParam paginationParam = request.getPaginationParam();
		
		ScrollingParam param  = new ScrollingParam();
		
		if(paginationParam!=null){
			param.setPartition(paginationParam.getNextPartition());
			param.setRow(paginationParam.getNextRow());
		}
		
		//For where condition
		param.setSize(request.getSize());
		
		DataHelper azureRequest = null;
		ResultSet resultSet = null;
		
		SegmentedDetailRes response = new SegmentedDetailRes(); 
		HashMap<String, ResultSet>  resultSetMap = new HashMap<>();
		
		azureRequest = new DataHelper();
		azureRequest.setErpName(request.getErpName());
		azureRequest.setPartitionValue(request.getPartition());
		azureRequest.setTableName(request.getTableName());
		resultSet = repo.getSegmentedResultSet(param, azureRequest);
		
		resultSetMap.put(request.getErpName(), resultSet);			
		response.setSuppData(resultSetMap);
		response.setMessage(Constants.JSON_OK);
		
		return response;
	}
}
