package com.jci.item.repo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.item.azure.AzureStorage;
import com.jci.item.azure.data.DataHelper;
import com.jci.item.azure.data.DataUtil;
import com.jci.item.azure.data.ResultSet;
import com.jci.item.azure.query.PaginationParam;
import com.jci.item.azure.query.ScrollingParam;
import com.jci.item.utils.Constants;
import com.jci.item.utils.QueryBuilder;
import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultSegment;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableQuery;


@Repository
public class ItemRepoImpl implements ItemRepo {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemRepoImpl.class);
	static int counter=0;
	final int batchSize = 15;//Azure bad request need to solve
	
	@Autowired
	private AzureStorage azureStorage;


	//Final 
   @Override
   //@Transactional
	public ResultSet getSegmentedResultSet(ScrollingParam param,DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("#### Starting TableStorageRepositoryImpl.getSegmentedResultSet ###" );
		ResultContinuation continuationToken = DataUtil.getContinuationToken(param);
		PaginationParam pagination = new PaginationParam();
		if(continuationToken != null) {
			pagination.setLastPartition(param.getPartition());
			pagination.setLastRow(param.getRow());
		}
		 
		// Create the query
		String whereCondition = QueryBuilder.partitionWhereCondition("ITEM_SYMIX");
		 if(StringUtils.isBlank(whereCondition) ){
			 return null;
		 }
		
		 TableQuery<DynamicTableEntity> query = TableQuery.from(DynamicTableEntity.class).where(whereCondition).take(param.getSize());
		 CloudTable table = azureStorage.getTable(request.getTableName());
       
		// segmented query
       ResultSegment<DynamicTableEntity> response = table.executeSegmented(query, continuationToken) ;
       
		// next continuation token
		continuationToken = response.getContinuationToken() ;
		if(continuationToken != null) {
			pagination.setNextPartition(continuationToken.getNextPartitionKey());
			pagination.setNextRow(continuationToken.getNextRowKey());
		}
		
		HashMap<String, Object> hashmap;
		
		List<HashMap<String, Object>> series = new ArrayList<HashMap<String, Object>>();
		DynamicTableEntity row;
		EntityProperty ep;
		ObjectMapper mapper = new ObjectMapper(); 
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			LOG.info("map--->"+map);
			hashmap = new HashMap<String, Object>();
				
			
			
			for (String key : map.keySet()) {
				LOG.info("key--->"+key);
				
				ep = map.get(key);
				
				if(Constants.JSON_STRING.equalsIgnoreCase(key)){					
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
			
			//series.add(hashmap);
		}
		return new ResultSet(series,pagination) ;
   }
}
