package com.jci.supp.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.supp.azure.data.DataHelper;
import com.jci.supp.azure.data.ResultSet;
import com.jci.supp.azure.query.PaginationParam;
import com.jci.supp.azure.query.ScrollingParam;
import com.jci.supp.dto.SegmentedDetailReq;
import com.jci.supp.dto.SegmentedDetailRes;
import com.jci.supp.repo.SuppRepo;
import com.jci.supp.utils.Constants;
import com.microsoft.azure.storage.StorageException;

@Service
public class SuppServiceImpl implements SuppService{ // NO_UCD (unused code)

	private static final Logger LOG = LoggerFactory.getLogger(SuppServiceImpl.class);
	
	@Autowired
	private SuppRepo repo;
	
	@Override
	public SegmentedDetailRes getSupplierResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("### Starting Ending PoServiceImpl.getSupplierResultSet ### " );
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
		HashMap<String, ResultSet>  resultSetMap = new HashMap<String, ResultSet>();
		
		azureRequest = new DataHelper();
		azureRequest.setErpName(request.getErpName());
		azureRequest.setPartitionValue(request.getPartition());
		azureRequest.setTableName(request.getTableName());
		resultSet = repo.getSegmentedResultSet(param, azureRequest);
		
		
		resultSetMap.put(request.getErpName(), resultSet);			
		response.setSupplierData(resultSetMap);
		response.setMessage(Constants.JSON_OK);
		
		LOG.info("### Ending Ending PoServiceImpl.getSupplierResultSet ### " );
		
		return response;
	}
}
