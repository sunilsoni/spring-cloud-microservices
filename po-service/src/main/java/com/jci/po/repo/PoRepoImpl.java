package com.jci.po.repo;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.po.azure.AzureStorage;
import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.DataUtil;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.PaginationParam;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.req.BatchUpdateReq;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.entity.MiscDataEntity;
import com.jci.po.entity.PoEntity;
import com.jci.po.utils.Constants;
import com.jci.po.utils.QueryBuilder;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultSegment;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;


@Repository
@RefreshScope
public class PoRepoImpl implements PoRepo { // NO_UCD (unused code)
	
	private static final Logger LOG = LoggerFactory.getLogger(PoRepoImpl.class);
    @Value("${all.erp.names}")
    private String allErps;
    
	final int batchSize = 15;
	
	@Autowired   
	private AzureStorage azureStorage;

	public void createTable(String tableName) throws InvalidKeyException, StorageException, URISyntaxException {
	    if (azureStorage.getTable(tableName).createIfNotExists()) {
	    	LOG.info("table is created : " + tableName);
	    }
	}
	
	@Override
	public HashMap<String, ArrayList<Integer>> getGraphData() throws InvalidKeyException, URISyntaxException, StorageException {
		
		String query = QueryBuilder.graphQuery(Constants.PARTITION_KEY_MISCDATA,allErps);
		LOG.info("query : " + query);
		
		TableQuery<MiscDataEntity> partitionQuery =  TableQuery.from(MiscDataEntity.class).where(query);
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		
		HashMap<String, ArrayList<Integer>> graphData = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> list = null;
		
	    for (MiscDataEntity entity : cloudTable.execute(partitionQuery)) {
	    	list = new ArrayList<Integer>();
	    	list.add(entity.getPoIntransitCount());
	    	list.add(entity.getPoProcessedCount());
	    	list.add(entity.getPoErrorCount());
	    	graphData.put(entity.getRowKey(), list);
	   }
		 return graphData;
	}
	
	public MiscDataEntity getStatusCountEntity(String partitionKey, String rowKey) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
	    TableOperation entity =   TableOperation.retrieve(partitionKey, rowKey, MiscDataEntity.class);
		return cloudTable.execute(entity).getResultAsType();
	}
	
	public void updateStatusCountEntity(MiscDataEntity entity) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("entity-->"+entity);
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		
		TableOperation insert = TableOperation.insertOrReplace(entity);
		cloudTable.execute(insert);
	}

	public List<HashMap<String, String>> getErrorData(String partitionKey) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("#### Starting PoRepoImpl.getErrorData ###" );
		List<HashMap<String, String>> errorData = new ArrayList<HashMap<String, String>>();
		
		String query = QueryBuilder.errorQuery(partitionKey,allErps);
		LOG.info("query-->"+query);
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		
		//multiplePartitionWhereCondition
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	HashMap<String, String> map = new HashMap<String, String>();
	    	map.put("Status",String.valueOf(entity.getStatus()));
	    	map.put("Description",String.valueOf(entity.getDescription()));
	    	map.put("OrderNumber",String.valueOf(entity.getRowKey()));
	    	map.put("SourceErpName",String.valueOf(entity.getErpName()));
	    	errorData.add(map);
	   }
	    LOG.info("#### Ending PoRepoImpl.getErrorData ###" );
		 return errorData;
	}
	
	@Override
	public Map<String,List<HashMap<String, Object>>> getErrorPos(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("#### Starting PoRepoImpl.getErrorPos ###" );
		
		String query = QueryBuilder.getErrorPosQuery(partitionKey,poList);
		LOG.info("query--->"+query);
		
		//List<PoItemsEntity> errorData = new ArrayList<PoItemsEntity>();
		//TableQuery<PoItemsEntity> partitionQuery =  TableQuery.from(PoItemsEntity.class).where(query);
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS);
		
		OperationContext opContext = new OperationContext();
		
		TableQuery<DynamicTableEntity> myQuery = TableQuery.from(DynamicTableEntity.class).where(query).take(1000);//Need to discuss this
		
		Iterator<DynamicTableEntity> rows = cloudTable.execute(myQuery, null, opContext).iterator();
		DynamicTableEntity row;
		EntityProperty ep;
		HashMap<String, Object> hashmap;
		//List<HashMap<String, Object>> series = new ArrayList<HashMap<String, Object>>();
		
		Map<String,List<HashMap<String, Object>>> poNumToItemListMap = new HashMap<String,List<HashMap<String, Object>>>();
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			
			//row.getRowKey().split("_")[0]
			hashmap = new HashMap<String, Object>();
			for (String key : map.keySet()) {
				ep = map.get(key);
				hashmap.put(key, ep.getValueAsString());
			}
			//series.add(hashmap);
			
			if(poNumToItemListMap.containsKey(row.getRowKey().split("_")[0])){
				List<HashMap<String, Object>> list =poNumToItemListMap.get(row.getRowKey().split("_")[0]);
	    		list.add(hashmap);
	    		poNumToItemListMap.put(row.getRowKey().split("_")[0], list);
	    	}else{
	    		List<HashMap<String, Object>> list = new  ArrayList<HashMap<String, Object>>();
	    		list.add(hashmap);
	    		poNumToItemListMap.put(row.getRowKey().split("_")[0], list);
	    	}
			
		}		
	    LOG.info("#### Ending PoRepoImpl.getErrorPos ###" );
		 return poNumToItemListMap;
	}
	
	@Override
	public List<PoEntity> getPoDetails(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("#### Starting PoRepoImpl.getErrorPos ###" );
		
		String query = QueryBuilder.processPosQuery(partitionKey,poList);
		LOG.info("query--->"+query);
		
		
		List<PoEntity> errorData = new ArrayList<PoEntity>();
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	errorData.add(entity);
	    }
	    LOG.info("#### Ending PoRepoImpl.getErrorPos ###" );
		 return errorData;
	}
	
   @Override
	public ResultSet getSegmentedResultSet(ScrollingParam param,DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("#### Starting PoRepoImpl.getSegmentedResultSet ###" );
		ResultContinuation continuationToken = DataUtil.getContinuationToken(param);
		PaginationParam pagination = new PaginationParam();
		if(continuationToken != null) {
			pagination.setLastPartition(param.getPartition());
			pagination.setLastRow(param.getRow());
		}
		 
		// Create the query
		String whereCondition = null;
		 if(request.isErrorDataRequired()){
			 whereCondition = QueryBuilder.errorDataQuery(request.getPartitionValue());
		 }else{
			 whereCondition = QueryBuilder.partitionWhereCondition(request.getPartitionValue());
		 }
		 
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
			hashmap.put("OrderNumber", row.getRowKey());
			for (String key : map.keySet()) {
				ep = map.get(key);				
				if(key.equals(Constants.STATUS)){
					hashmap.put(key, ep.getValueAsString());
				}
			}
			series.add(hashmap);
		}
		//LOG.info("#### Ending TableStorageRepositoryImpl.getSegmentedResultSet ###" );
		//ResultSet(series,getErrorData(request.getPartitionValue()),pagination)
		
		return new ResultSet(series,pagination) ;
   }
   
	public BatchUpdateRes batchUpdate(BatchUpdateReq request){
		BatchUpdateRes response = new BatchUpdateRes();
		
		String erpName = request.getErpName();
		HashMap<String,List<PoEntity>> tableNameToEntityMap = request.getTableNameToEntityMap();
		
		 List<String> errorList = new ArrayList<String>();
		 List<String> successList = new ArrayList<String>();
		
		 CloudTable cloudTable=null;
		 PoEntity entity = null;
		  int successCount=0;
		 
		 for (Map.Entry<String, List<PoEntity>> entry : tableNameToEntityMap.entrySet()){
		     try {
					cloudTable = azureStorage.getTable(entry.getKey());
				} catch (Exception e) {
					LOG.error("### Exception in PoRepoImpl.batchUpdate.getTable ###"+e);
					e.printStackTrace();
					response.setError(true);
					response.setMessage("The Application has encountered an error! Table  does not exist !");
					continue;
				}
		     
		  // Define a batch operation.
			    TableBatchOperation batchOperation = new TableBatchOperation();
			    List<PoEntity> value = entry.getValue();
			    
			    for (int i = 0; i < value.size(); i++) {
			    	entity = value.get(i) ;
			    	
			    	entity.setGlobalId(request.getGlobalId());
			    	entity.setUserName(request.getUserName());
			    	entity.setComment(request.getComment());
			    	
		    		entity.setStatus(Constants.STATUS_SUCCESS);
		    		successCount = successCount+1;
		    		successList.add(entity.getRowKey());
			    	
			    	batchOperation.insertOrReplace(entity);
			    	if (i!=0 && (i % batchSize) == 0) {
			    		try {
							cloudTable.execute(batchOperation);
							batchOperation.clear();
						} catch (Exception e) {
							response.setError(true);
							response.setMessage("The Application has encountered an error!");
					    	successCount = successCount-1;
							LOG.error("### Exception in PoRepoImpl.batchUpdate.execute ###"+e);
							e.printStackTrace();
							continue;
						}
			    	 }
			    }
			    
			    if(batchOperation.size()>0){
			    	try {
						cloudTable.execute(batchOperation);
					} catch (Exception e) {
						response.setError(true);
						response.setMessage("The Application has encountered an error!");
				    	successCount = successCount-1;
						LOG.error("### Exception in PoRepoImpl.batchUpdate.execute ###"+e);
						e.printStackTrace();
						continue;
					}
			    }
		 }	
		 response.setErrorList(errorList);
		 response.setSuccessList(successList);
		 
		//Insert MIsc data: need to make sure only for podetails
		MiscDataEntity miscEntity=null;
		try {
			miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in PoRepoImpl.batchUpdate ####",e);
			response.setError(true);
			response.setMessage("The Application has encountered an error!");
			e.printStackTrace();
		}
		
		if(successCount>0){
			 int sum1 = miscEntity.getPoProcessedCount()+successCount;
			 miscEntity.setPoProcessedCount(sum1);
			 int sum2 = miscEntity.getPoErrorCount()-successCount;
			 miscEntity.setPoErrorCount(sum2);
			 
			 try {
					updateStatusCountEntity(miscEntity);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					LOG.error("### Exception in PoRepoImpl.batchUpdate ####",e);
					response.setError(true);
					response.setMessage("The Application has encountered an error!");
					e.printStackTrace();
				}
		}
		return response;
		
	}//Ending batchUpdate

	@Override
	public ResultSet getPoItemDetail(ScrollingParam param,DataHelper request)	throws InvalidKeyException, URISyntaxException, StorageException {
		ResultContinuation continuationToken = DataUtil.getContinuationToken(param);
		PaginationParam pagination = new PaginationParam();
		if(continuationToken != null) {
			pagination.setLastPartition(param.getPartition());
			pagination.setLastRow(param.getRow());
		}
		 
		// Create the query
		String  whereCondition = QueryBuilder.poItemDetailQuery(request);
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
		TypeReference<HashMap<String,Object>> typeRef  = new TypeReference<HashMap<String,Object>>() {};
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			hashmap = new HashMap<String, Object>();
			
			for (String key : map.keySet()) {
				ep = map.get(key);
				if(Constants.JSON_STRING.equals(key)){
					try {
						hashmap = mapper.readValue(ep.getValueAsString(), typeRef);
						hashmap.put("id", row.getRowKey());  
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
			}
			series.add(hashmap);
		}

		
		return new ResultSet(series,pagination) ;
	}
}
