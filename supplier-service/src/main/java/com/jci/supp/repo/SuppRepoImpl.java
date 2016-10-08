/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.supp.repo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.azure.DataHelper;
import com.jci.azure.DataUtil;
import com.jci.azure.PaginationParam;
import com.jci.azure.ResultSet;
import com.jci.azure.ScrollingParam;
import com.jci.config.AzureStorage;
import com.jci.utils.Constants;
import com.jci.utils.QueryBuilder;
import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultSegment;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableQuery;

/**
 * <p>
 * <strong>The Class SuppRepoImpl.</strong>
 * <p>
 *
 * @author csonisk
 */
@Repository
public class SuppRepoImpl implements SuppRepo { // NO_UCD (unused code)
	
	/** The Constant LOG. */
 private static final Logger LOG = LoggerFactory.getLogger(SuppRepoImpl.class);
	
	/** The azure storage. */
	@Autowired
	private AzureStorage azureStorage;


	/* (non-Javadoc)
	 * @see com.jci.supp.repo.SuppRepo#getSegmentedResultSet(com.jci.supp.azure.query.ScrollingParam, com.jci.supp.azure.data.DataHelper)
	 */
	//Final 
   @Override
	public ResultSet getSegmentedResultSet(ScrollingParam param,DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException  {
		ResultContinuation continuationToken = DataUtil.getContinuationToken(param);
		PaginationParam pagination = new PaginationParam();
		if(continuationToken != null) {
			pagination.setLastPartition(param.getPartition());
			pagination.setLastRow(param.getRow());
		}
		 
		// Create the query
		String whereCondition = QueryBuilder.partitionWhereCondition(request.getPartitionValue());
		 if(StringUtils.isBlank(whereCondition) ){
			 return null;
		 }
		 TableQuery<DynamicTableEntity> query = TableQuery.from(DynamicTableEntity.class).where(whereCondition).take(param.getSize());
		  CloudTable table = azureStorage.getTable(request.getTableName());
       ResultSegment<DynamicTableEntity> response = table.executeSegmented(query, continuationToken) ;
       
		// next continuation token
		continuationToken = response.getContinuationToken() ;
		if(continuationToken != null) {
			pagination.setNextPartition(continuationToken.getNextPartitionKey());
			pagination.setNextRow(continuationToken.getNextRowKey());
		}
		HashMap<String, Object> hashmap;
		ObjectMapper mapper = new ObjectMapper(); 
		List<HashMap<String, Object>> series = new ArrayList<>();
		DynamicTableEntity row;
		EntityProperty ep;
		TypeReference<HashMap<String,Object>> typeRef  = new TypeReference<HashMap<String,Object>>() {};
		
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		LOG.error("hasNext--->"+rows.hasNext());
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			hashmap = new HashMap<>();
			for (String key : map.keySet()) {
				ep = map.get(key);
				if(Constants.JSON_STRING.equalsIgnoreCase(key)){					
					try {
						hashmap = mapper.readValue(ep.getValueAsString(),typeRef);
						hashmap.put("id", row.getRowKey());
						series.add(hashmap);
					} catch (IOException e) {
						
						LOG.error("### Exception in   ####",e);
					}				
				}	
				
			}
		}
		LOG.error("hasNext--->"+series);
		
		return new ResultSet(series,pagination) ;
   }
 
}
