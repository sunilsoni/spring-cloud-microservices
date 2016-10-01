/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
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


/**
 * <p>
 * <strong> The Class PoRepoImpl.</strong>
 * <p>
 *
 * @author csonisk
 */
@Repository
@RefreshScope
public class PoRepoImpl implements PoRepo { // NO_UCD (unused code)
	
	/** The Constant LOG. */
 private static final Logger LOG = LoggerFactory.getLogger(PoRepoImpl.class);
	
    /** The all erps. */
    @Value("${all.erp.names}")
    private String allErps;
    
	/** The batch size. */
	final int batchSize = 10;
	
	/** The azure storage. */
	@Autowired   
	private AzureStorage azureStorage;

	/**
	 * Creates the table.
	 *
	 * @param tableName the table name
	 * @throws InvalidKeyException the invalid key exception
	 * @throws StorageException the storage exception
	 * @throws URISyntaxException the URI syntax exception
	 */
	public void createTable(String tableName) throws InvalidKeyException, StorageException, URISyntaxException {
	    if (azureStorage.getTable(tableName).createIfNotExists()) {
	    	LOG.info("table is created : " + tableName);
	    }
	}
	
	/* (non-Javadoc)
	 * @see com.jci.po.repo.PoRepo#getGraphData()
	 */
	@Override
	public HashMap<String, ArrayList<Integer>> getGraphData() throws InvalidKeyException, URISyntaxException, StorageException {
		String query = QueryBuilder.graphQuery(Constants.PARTITION_KEY_MISCDATA,allErps);
		TableQuery<MiscDataEntity> partitionQuery =  TableQuery.from(MiscDataEntity.class).where(query);
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		
		HashMap<String, ArrayList<Integer>> graphData = new HashMap<>();
		ArrayList<Integer> list = null;
		
	    for (MiscDataEntity entity : cloudTable.execute(partitionQuery)) {
	    	list = new ArrayList<>();
	    	list.add(entity.getIntransitCount());
	    	list.add(entity.getProcessedCount());
	    	list.add(entity.getErrorCount());
	    	graphData.put(entity.getRowKey(), list);
	   }
		 return graphData;
	}
	
	/**
	 * Gets the status count entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 * @return the status count entity
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	public MiscDataEntity getStatusCountEntity(String partitionKey, String rowKey) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
	    TableOperation entity =   TableOperation.retrieve(partitionKey, rowKey, MiscDataEntity.class);
		return cloudTable.execute(entity).getResultAsType();
	}
	
	/**
	 * Update status count entity.
	 *
	 * @param entity the entity
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	public void updateStatusCountEntity(MiscDataEntity entity) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		TableOperation insert = TableOperation.insertOrMerge(entity);
		cloudTable.execute(insert);
	}

	/**
	 * Gets the error data.
	 *
	 * @param partitionKey the partition key
	 * @return the error data
	 * @throws InvalidKeyException the invalid key exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws StorageException the storage exception
	 */
	public List<HashMap<String, String>> getErrorData(String partitionKey) throws InvalidKeyException, URISyntaxException, StorageException {
		List<HashMap<String, String>> errorData = new ArrayList<>();
		String query = QueryBuilder.errorQuery(partitionKey,allErps);
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		
		//multiplePartitionWhereCondition
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	HashMap<String, String> map = new HashMap<>();
	    	map.put("Status",String.valueOf(entity.getSupplierDeliveryState()));
	    	map.put("Description",String.valueOf(entity.getDescription()));
	    	map.put("OrderNumber",String.valueOf(entity.getRowKey()));
	    	map.put("SourceErpName",String.valueOf(entity.getErpName()));
	    	errorData.add(map);
	   }
		 return errorData;
	}
	
	/* (non-Javadoc)
	 * @see com.jci.po.repo.PoRepo#getErrorPos(java.lang.String, java.util.List)
	 */
	@Override
	public Map<String,List<HashMap<String, Object>>> getErrorPos(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
		String query = QueryBuilder.getErrorPosQuery(partitionKey,poList);
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS);
		OperationContext opContext = new OperationContext();
		TableQuery<DynamicTableEntity> myQuery = TableQuery.from(DynamicTableEntity.class).where(query).take(1000);//Need to discuss this
		
		Iterator<DynamicTableEntity> rows = cloudTable.execute(myQuery, null, opContext).iterator();
		DynamicTableEntity row;
		EntityProperty ep;
		HashMap<String, Object> hashmap;
		Map<String,List<HashMap<String, Object>>> poNumToItemListMap = new HashMap<>();
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			hashmap = new HashMap<>();
			for (String key : map.keySet()) {
				ep = map.get(key);
				hashmap.put(key, ep.getValueAsString());
			}
			if(poNumToItemListMap.containsKey(row.getRowKey().split("_")[0])){
				List<HashMap<String, Object>> list =poNumToItemListMap.get(row.getRowKey().split("_")[0]);
	    		list.add(hashmap);
	    		poNumToItemListMap.put(row.getRowKey().split("_")[0], list);
	    	}else{
	    		List<HashMap<String, Object>> list = new  ArrayList<>();
	    		list.add(hashmap);
	    		poNumToItemListMap.put(row.getRowKey().split("_")[0], list);
	    	}
		}		
		 return poNumToItemListMap;
	}
	
	/* (non-Javadoc)
	 * @see com.jci.po.repo.PoRepo#getPoDetails(java.lang.String, java.util.List)
	 */
	@Override
	public List<PoEntity> getPoDetails(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
		String query = QueryBuilder.processPosQuery(partitionKey,poList);
		List<PoEntity> errorData = new ArrayList<>();
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	errorData.add(entity);
	    }
		 return errorData;
	}
	
   /* (non-Javadoc)
    * @see com.jci.po.repo.PoRepo#getSegmentedResultSet(com.jci.po.azure.query.ScrollingParam, com.jci.po.azure.data.DataHelper)
    */
   @Override
	public ResultSet getSegmentedResultSet(ScrollingParam param,DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException  {
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
		List<HashMap<String, Object>> series = new ArrayList<>();
		DynamicTableEntity row;
		EntityProperty ep;
		
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			hashmap = new HashMap<>();
			hashmap.put("id", row.getRowKey());  
			hashmap.put("OrderNumber", row.getRowKey());
			for (String key : map.keySet()) {
				ep = map.get(key);				
				if(key.equals(Constants.STATUS)){
					hashmap.put("Status", ep.getValueAsString());
				}
			}
			series.add(hashmap);
		}
		return new ResultSet(series,pagination) ;
   }
   
	/* (non-Javadoc)
	 * @see com.jci.po.repo.PoRepo#batchUpdate(com.jci.po.dto.req.BatchUpdateReq)
	 */
	public BatchUpdateRes batchUpdate(BatchUpdateReq request){
		BatchUpdateRes response = new BatchUpdateRes();
		String erpName = request.getErpName();
		HashMap<String,List<PoEntity>> tableNameToEntityMap = request.getTableNameToEntityMap();
		
		List<String> errorList = new ArrayList<>();
		List<String> successList = new ArrayList<>();
		
		 CloudTable cloudTable=null;
		 PoEntity entity = null;
		 int successCount=0;
		 
		 for (Map.Entry<String, List<PoEntity>> entry : tableNameToEntityMap.entrySet()){
		     try {
					cloudTable = azureStorage.getTable(entry.getKey());
				} catch (Exception e) {
					LOG.error("### Exception in PoRepoImpl.batchUpdate.getTable ###"+e);
					
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
			    	
		    		entity.setSupplierDeliveryState(Constants.STATUS_SUCCESS);
		    		successCount = successCount+1;
		    		successList.add(entity.getRowKey());
			    	
			    	batchOperation.insertOrMerge(entity);
			    	if (i!=0 && (i % batchSize) == 0) {
			    		try {
							cloudTable.execute(batchOperation);
							batchOperation.clear();
						} catch (Exception e) {
							response.setError(true);
							response.setMessage("The Application has encountered an error!");
					    	successCount = successCount-1;
							LOG.error("### Exception in PoRepoImpl.batchUpdate.execute ###"+e);
							
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
			
		}
		
		if(successCount>0){
			 int sum1 = miscEntity.getProcessedCount()+successCount;
			 miscEntity.setProcessedCount(sum1);
			 int sum2 = miscEntity.getErrorCount()-successCount;
			 miscEntity.setErrorCount(sum2);
			 
			 try {
					updateStatusCountEntity(miscEntity);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					LOG.error("### Exception in PoRepoImpl.batchUpdate ####",e);
					response.setError(true);
					response.setMessage("The Application has encountered an error!");
					
				}
		}
		return response;
		
	}//Ending batchUpdate

	/* (non-Javadoc)
	 * @see com.jci.po.repo.PoRepo#getPoItemDetail(com.jci.po.azure.query.ScrollingParam, com.jci.po.azure.data.DataHelper)
	 */
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
		
		List<HashMap<String, Object>> series = new ArrayList<>();
		DynamicTableEntity row;
		EntityProperty ep;
		ObjectMapper mapper = new ObjectMapper(); 
		TypeReference<HashMap<String,Object>> typeRef  = new TypeReference<HashMap<String,Object>>() {};
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			hashmap = new HashMap<>();
			
			for (String key : map.keySet()) {
				ep = map.get(key);
				if(Constants.JSON_STRING.equals(key)){
					try {
						hashmap = mapper.readValue(ep.getValueAsString(), typeRef);
						hashmap.put("id", row.getRowKey());  
					} catch (IOException e) {
						LOG.error("### Exception in   ####",e);
					} 
				}
			}
			series.add(hashmap);
		}
		return new ResultSet(series,pagination) ;
	}
}
