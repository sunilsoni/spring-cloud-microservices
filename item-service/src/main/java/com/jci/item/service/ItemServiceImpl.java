package com.jci.item.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.item.azure.data.DataHelper;
import com.jci.item.azure.data.ResultSet;
import com.jci.item.azure.query.PaginationParam;
import com.jci.item.azure.query.ScrollingParam;
import com.jci.item.dto.SegmentedDetailReq;
import com.jci.item.dto.SegmentedDetailRes;
import com.jci.item.repo.ItemRepo;
import com.jci.item.utils.Constants;
import com.microsoft.azure.storage.StorageException;

@Service
public class ItemServiceImpl implements ItemService{

	private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	@Autowired
	private ItemRepo repo;
	
	
	@Override
	public SegmentedDetailRes getItemResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("### Starting Ending ItemServiceImpl.getItemResultSet ### " );
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
		response.setItemData(resultSetMap);
		response.setMessage(Constants.JSON_OK);
		
		LOG.info("### Ending Ending ItemServiceImpl.getItemResultSet ### " );
		
		return response;
	}
}
