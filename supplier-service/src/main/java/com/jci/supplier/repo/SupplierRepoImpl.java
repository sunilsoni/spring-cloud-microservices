package com.jci.supplier.repo;

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
import com.jci.supplier.azure.AzureStorage;
import com.jci.supplier.azure.data.DataHelper;
import com.jci.supplier.azure.data.DataUtil;
import com.jci.supplier.azure.data.ResultSet;
import com.jci.supplier.azure.query.PaginationParam;
import com.jci.supplier.azure.query.ScrollingParam;
import com.jci.supplier.utils.QueryBuilder;
import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultSegment;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableQuery;

@Repository
public class SupplierRepoImpl implements SupplierRepo {
	
	private static final Logger LOG = LoggerFactory.getLogger(SupplierRepoImpl.class);
	
	@Autowired
	private AzureStorage azureStorage;


	//Final 
   @Override
	public ResultSet getSegmentedResultSet(ScrollingParam param,DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("#### Starting TableStorageRepositoryImpl.getSegmentedResultSet ###" );
		ResultContinuation continuationToken = DataUtil.getContinuationToken(param);
		PaginationParam pagination = new PaginationParam();
		if(continuationToken != null) {
			pagination.setLastPartition(param.getPartition());
			pagination.setLastRow(param.getRow());
		}
		 
		// Create the query
		String whereCondition = QueryBuilder.partitionWhereCondition("SUPPLIER_SYMIX ");
		 if(StringUtils.isBlank(whereCondition) ){
			 return null;
		 }
		 LOG.info("whereCondition--->"+whereCondition);
		 TableQuery<DynamicTableEntity> query = TableQuery.from(DynamicTableEntity.class).where(whereCondition).take(param.getSize());
		  CloudTable table = azureStorage.getTable(request.getTableName());
		  LOG.info("table--->"+table.getName());
		// segmented query
		  
		  LOG.info("query getFilterString--->"+query.getFilterString());
       ResultSegment<DynamicTableEntity> response = table.executeSegmented(query, continuationToken) ;
       
		// next continuation token
		continuationToken = response.getContinuationToken() ;
		if(continuationToken != null) {
			pagination.setNextPartition(continuationToken.getNextPartitionKey());
			pagination.setNextRow(continuationToken.getNextRowKey());
		}
		 LOG.info("response--->"+response);
		HashMap<String, Object> hashmap;
		ObjectMapper mapper = new ObjectMapper(); 
		List<HashMap<String, Object>> series = new ArrayList<HashMap<String, Object>>();
		DynamicTableEntity row;
		EntityProperty ep;
		
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		LOG.info("rows.hasNext()--->"+rows.hasNext());
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			LOG.info("map--->"+map);
			hashmap = new HashMap<String, Object>();
			hashmap.put("id", row.getRowKey());
			for (String key : map.keySet()) {
				ep = map.get(key);
				LOG.info("key--->"+key);
				hashmap.put(key, ep.getValueAsString());
				if("SupplierJsonString".equalsIgnoreCase(key)){					
					try {
						hashmap = mapper.readValue(ep.getValueAsString(), new TypeReference<HashMap<String, Object>>(){});
						//hashmap.put(key, ep.getValueAsString());
						
						hashmap.put("id", row.getRowKey());	
						series.add(hashmap);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					
				}	
				
			}
		//	series.add(hashmap);
		}
		return new ResultSet(series,pagination) ;
   }
 
}
