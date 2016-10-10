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
import com.jci.azure.DataHelper;
import com.jci.azure.DataUtil;
import com.jci.azure.PaginationParam;
import com.jci.azure.ResultSet;
import com.jci.azure.ScrollingParam;
import com.jci.config.AzureStorage;
import com.jci.entity.MiscDataEntity;
import com.jci.entity.PoEntity;
import com.jci.enums.ErrorEnum;
import com.jci.exception.ErrorService;
import com.jci.po.exception.PoException;
import com.jci.utils.Constants;
import com.jci.utils.QueryBuilder;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultSegment;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
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

    /** The error service. */
    @Autowired
    private ErrorService errorService;
    
	/* (non-Javadoc)
	 * @see com.jci.po.repo.PoRepo#getGraphData()
	 */
	@Override
	public HashMap<String, ArrayList<Integer>> getGraphData()  {
		String query = QueryBuilder.graphQuery(Constants.PARTITION_KEY_MISCDATA,allErps);
		TableQuery<MiscDataEntity> partitionQuery =  TableQuery.from(MiscDataEntity.class).where(query);
		CloudTable cloudTable;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(PoException.class, e, ErrorEnum.ERROR_MISCDATA_TABLE_NOT_FOUND);
		}
		
		HashMap<String, ArrayList<Integer>> graphData = new HashMap<>();
		ArrayList<Integer> list = null;
		
	    for (MiscDataEntity entity : cloudTable.execute(partitionQuery)) {
	    	list = new ArrayList<>();
	    	list.add(entity.getPoIntransitCount());
	    	list.add(entity.getPoProcessedCount());
	    	list.add(entity.getPoErrorCount());
	    	graphData.put(entity.getRowKey(), list);
	   }
		 return graphData;
	}
	
	

	/**
	 * Gets the error data.
	 *
	 * @param partitionKey the partition key
	 * @return the error data
	 */
	public List<HashMap<String, String>> getErrorData(String partitionKey)  {
		List<HashMap<String, String>> errorData = new ArrayList<>();
		String query = QueryBuilder.errorQuery(partitionKey,allErps);
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		
		//multiplePartitionWhereCondition
		CloudTable cloudTable;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(PoException.class, e, ErrorEnum.ERROR_PO_TABLE_NOT_FOUND);
		}
		
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	HashMap<String, String> map = new HashMap<>();
	    	map.put("Status",String.valueOf(entity.getSupplierDeliveryState()));
	    	map.put("Description",String.valueOf(entity.getDescription()));
	    	map.put("OrderNumber",String.valueOf(entity.getRowKey()));
	    	map.put("SourceErpName",String.valueOf(entity.getPartitionKey()));
	    	errorData.add(map);
	   }
		 return errorData;
	}
	
	/* (non-Javadoc)
	 * @see com.jci.po.repo.PoRepo#getErrorPos(java.lang.String, java.util.List)
	 */
	@Override
	public Map<String,List<HashMap<String, Object>>> getErrorPos(String partitionKey, List<String> poList)  {
		String query = QueryBuilder.getErrorPosQuery(partitionKey,poList);
		CloudTable cloudTable;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(PoException.class, e, ErrorEnum.ERROR_POITEM_TABLE_NOT_FOUND);
		}
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
			if(poNumToItemListMap.containsKey(row.getRowKey().split("-")[0])){
				List<HashMap<String, Object>> list =poNumToItemListMap.get(row.getRowKey().split("-")[0]);
	    		list.add(hashmap);
	    		poNumToItemListMap.put(row.getRowKey().split("-")[0], list);
	    	}else{
	    		List<HashMap<String, Object>> list = new  ArrayList<>();
	    		list.add(hashmap);
	    		poNumToItemListMap.put(row.getRowKey().split("-")[0], list);
	    	}
		}		
		 return poNumToItemListMap;
	}
	
	/* (non-Javadoc)
	 * @see com.jci.po.repo.PoRepo#getPoDetails(java.lang.String, java.util.List)
	 */
	@Override
	public List<PoEntity> getPoDetails(String partitionKey, List<String> poList)  {
		String query = QueryBuilder.processPosQuery(partitionKey,poList);
		List<PoEntity> errorData = new ArrayList<>();
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		
		CloudTable cloudTable;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(PoException.class, e, ErrorEnum.ERROR_PO_TABLE_NOT_FOUND);
		}
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	errorData.add(entity);
	    }
		 return errorData;
	}
	
   /* (non-Javadoc)
    * @see com.jci.po.repo.PoRepo#getSegmentedResultSet(com.jci.po.azure.query.ScrollingParam, com.jci.po.azure.data.DataHelper)
    */
   @Override
	public ResultSet getSegmentedResultSet(ScrollingParam param,DataHelper request)   {
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
		 CloudTable table;
		try {
			table = azureStorage.getTable(request.getTableName());
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(PoException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,request.getTableName());
		}
		 
		// segmented query
        ResultSegment<DynamicTableEntity> response=null;
		try {
			response = table.executeSegmented(query, continuationToken);
		} catch (StorageException e) {
			throw errorService.createException(PoException.class, e, ErrorEnum.ERROR_PO_SEGMENTED_QUERY, query);
		}
       
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
				if(key.equals(Constants.STATUS_KEY)){
					hashmap.put("Status", ep.getValueAsString());
				}
			}
			series.add(hashmap);
		}
		return new ResultSet(series,pagination) ;
   }
   
	/* (non-Javadoc)
	 * @see com.jci.po.repo.PoRepo#getPoItemDetail(com.jci.po.azure.query.ScrollingParam, com.jci.po.azure.data.DataHelper)
	 */
	@Override
	public ResultSet getPoItemDetail(ScrollingParam param,DataHelper request)	 {
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
		CloudTable table;
		
		try {
			table = azureStorage.getTable(request.getTableName());
		} catch (InvalidKeyException | URISyntaxException | StorageException e1) {
			throw errorService.createException(PoException.class, e1, ErrorEnum.ERROR_TABLE_NOT_FOUND,request.getTableName());
		}
       
		// segmented query
       ResultSegment<DynamicTableEntity> response=null;
		try {
			response = table.executeSegmented(query, continuationToken);
		} catch (StorageException e1) {
			throw errorService.createException(PoException.class, e1, ErrorEnum.ERROR_POITEM_SEGMENTED_QUERY,query);
		}
       
		// next continuation token
		continuationToken = response.getContinuationToken() ;
		if(continuationToken != null) {
			pagination.setNextPartition(continuationToken.getNextPartitionKey());
			pagination.setNextRow(continuationToken.getNextRowKey());
		}
		    
		HashMap<String, Object> hashmap;
		
		List<HashMap<String, Object>> series = new ArrayList<>();
		DynamicTableEntity row;
		ObjectMapper mapper = new ObjectMapper(); 
		TypeReference<HashMap<String,Object>> typeRef  = new TypeReference<HashMap<String,Object>>() {};
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			hashmap = new HashMap<>();
			String jsonString = map.get(Constants.JSON_STRING).getValueAsString();
            if(!StringUtils.isBlank(jsonString)){
                try {
                    hashmap = mapper.readValue(jsonString, typeRef);
                    hashmap.put("id", row.getRowKey());  
                } catch (IOException e) {
                    LOG.error("### Exception in   ####",e);                        
                } 
            }
			series.add(hashmap);
		}
		return new ResultSet(series,pagination) ;
	}
}
