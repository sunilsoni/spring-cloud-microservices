package com.jci.supplier.repo;

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
		String whereCondition = QueryBuilder.partitionWhereCondition(request.getPartitionValue());
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
		
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();

			hashmap = new HashMap<String, Object>();
			hashmap.put("id", row.getRowKey());
			for (String key : map.keySet()) {
				ep = map.get(key);
				hashmap.put(key, ep.getValueAsString());
			}
			series.add(hashmap);
		}
		return new ResultSet(series,pagination) ;
   }
 
}
