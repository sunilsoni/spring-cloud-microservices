package com.jci.po.azure.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jci.po.azure.AzureStorage;
import com.jci.po.azure.query.PaginationParam;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.response.ResultSet;
import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultSegment;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.Operators;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;
@Repository
public class DataHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(DataHelper.class);
	
	@Autowired
	private AzureStorage azureStorage;
	
	private static final String PARTITION_KEY = "PartitionKey";
	private static final String STATUS_KEY = "Status";
	private static final String TIMESTAMP = "Timestamp";
	
	public ResultSet getSegmentedResultSet(TableQuery<DynamicTableEntity> myQuery,ScrollingParam param,AzureRequest request) throws Exception {
			
		return getSegmentedResultSet(request, myQuery, param) ;
	}
	
	public ResultSet getSegmentedResultSet( final AzureRequest request,TableQuery<DynamicTableEntity> myQuery,ScrollingParam param) throws Exception {
		LOG.info("#### Starting DataHelper.getSegmentedResultSet ###" + request);
		
		ResultContinuation continuationToken = DataUtil.getContinuationToken(param);
		PaginationParam pagination = new PaginationParam();
		
		if(continuationToken != null) {
			pagination.setLastPartition(param.getPartition());
			pagination.setLastRow(param.getRow());
		}
		 
		// Create the table client required to make calls to the table
       // CloudStorageAccount cloudStorageAccount = CloudStorageAccount.parse(request.getStorageConnectionString());
        //CloudTableClient tableClient = cloudStorageAccount.createCloudTableClient();
        
    
        // Create filters to limit the data
       String partitionFilter = TableQuery.generateFilterCondition(PARTITION_KEY, QueryComparisons.EQUAL, request.getPartitionValue());

        String timestampFilter = TableQuery.generateFilterCondition(TIMESTAMP, QueryComparisons.GREATER_THAN_OR_EQUAL,request.getTimestamp());

        String partitionAndTimestampFilter = TableQuery.combineFilters( partitionFilter, Operators.AND, timestampFilter);

        String counterFilter = TableQuery.generateFilterCondition(STATUS_KEY,QueryComparisons.EQUAL, request.getStatus());
		
		 // Combine all the filters
        String combinedFilter = TableQuery.combineFilters(partitionAndTimestampFilter, Operators.AND, counterFilter);
        
        
		// Create the query
        TableQuery<DynamicTableEntity> query = TableQuery.from(DynamicTableEntity.class).where(combinedFilter);
        LOG.info("query--->"+query.toString());
        
		//ResultSegment<DynamicTableEntity> entities = table.executeSegmented(query, null);
		
		//CloudTableClient client = Table.getInstance();
		
		 // Get the table reference using the table name
        LOG.info("getTableName--->"+request.getTableName());
        
        LOG.info("azureStorage--->"+azureStorage);
        
        
        CloudTable table = azureStorage.getTable(request.getTableName());
        //CloudTable table = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
        
		// segmented query
		//ResultSegment<DynamicTableEntity> response = table.executeSegmented(myQuery, continuationToken) ;
        ResultSegment<DynamicTableEntity> response = table.executeSegmented(query, continuationToken) ;
        LOG.info("response--->"+response.getLength());
        LOG.info("response--->"+response.getPageSize());
        LOG.info("response--->"+response.getResults());
        
        
		// next continuation token
		continuationToken = response.getContinuationToken() ;
		
		if(continuationToken != null) {
			pagination.setNextPartition(continuationToken.getNextPartitionKey());
			pagination.setNextRow(continuationToken.getNextRowKey());
		}
		
		HashMap<String, Object> datum;
		
		List<HashMap<String, Object>> series = new ArrayList<HashMap<String, Object>>();
		DynamicTableEntity row;
		EntityProperty ep;
		
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();

			datum = new HashMap<String, Object>();
			for (String key : map.keySet()) {
				ep = map.get(key);
				datum.put(key, ep.getValueAsString());
			}
			
			series.add(datum);
		}
		LOG.info("#### Ending DataHelper.getSegmentedResultSet ###" + series);
		return new ResultSet(series,pagination) ;
}
	
	
}
