package com.jci.po.repo;

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

import com.jci.po.azure.AzureStorage;
import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.DataUtil;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.PaginationParam;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.req.BatchInsertReq;
import com.jci.po.dto.req.BatchUpdateReq;
import com.jci.po.dto.res.BatchInsertResp;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.entity.MiscDataEntity;
import com.jci.po.entity.PoEntity;
import com.jci.po.entity.PoItemsEntity;
import com.jci.po.utils.Constants;
import com.jci.po.utils.QueryBuilder;
import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultSegment;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableEntity;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;


@Repository
@RefreshScope
public class PoRepoImpl implements PoRepo {
	
	private static final Logger LOG = LoggerFactory.getLogger(PoRepoImpl.class);
    @Value("${all.erp.names}")
    private String allErps;
    
	private static int errorCount;
	private static int successCount;
	private static int intransitCount;
	static int counter=0;
	final int batchSize = 15;//Azure bad request need to solve
	
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
	    	list.add(entity.getIntransitCount());
	    	list.add(entity.getProcessedCount());
	    	list.add(entity.getErrorCount());
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

	//Final
	public List<HashMap<String, String>> getErrorData(String partitionKey) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("#### Ending TableStorageRepositoryImpl.getErrorData ###" );
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
	    	map.put("SourceErpName",String.valueOf(entity.getSourceErpName()));
	    	errorData.add(map);
	   }
	    LOG.info("#### Ending TableStorageRepositoryImpl.getErrorData ###" );
		 return errorData;
	}
	
	@Override
	public List<PoItemsEntity> getErrorPos(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("#### Ending TableStorageRepositoryImpl.getErrorPos ###" );
		
		String query = QueryBuilder.processPosQuery(partitionKey,poList);
		LOG.info("query--->"+query);
		
		
		List<PoItemsEntity> errorData = new ArrayList<PoItemsEntity>();
		TableQuery<PoItemsEntity> partitionQuery =  TableQuery.from(PoItemsEntity.class).where(query);
		
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS);
	    for (PoItemsEntity entity : cloudTable.execute(partitionQuery)) {
	    	errorData.add(entity);
	    }
	    LOG.info("#### Ending TableStorageRepositoryImpl.getErrorPos ###" );
		 return errorData;
	}
	
	@Override
	public List<PoEntity> getPoDetails(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("#### Ending TableStorageRepositoryImpl.getErrorPos ###" );
		
		String query = QueryBuilder.processPosQuery(partitionKey,poList);
		LOG.info("query--->"+query);
		
		
		List<PoEntity> errorData = new ArrayList<PoEntity>();
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	errorData.add(entity);
	    }
	    LOG.info("#### Ending TableStorageRepositoryImpl.getErrorPos ###" );
		 return errorData;
	}
	
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
				hashmap.put(key, ep.getValueAsString());
			}
			series.add(hashmap);
		}
		//LOG.info("#### Ending TableStorageRepositoryImpl.getSegmentedResultSet ###" );
		//ResultSet(series,getErrorData(request.getPartitionValue()),pagination)
		
		return new ResultSet(series,pagination) ;
   }
   
	@Override
	public BatchInsertResp batchInsert(BatchInsertReq request){
		LOG.info("#### Starting TableStorageRepositoryImpl.batchInsert ###" );
		BatchInsertResp response = new BatchInsertResp();
		
		String erpName = request.getErpName();
		HashMap<String,List<TableEntity>> tableNameToEntityMap = request.getTableNameToEntityMap();
		
		 HashMap<String,List<TableEntity>> errorMap = new HashMap<String,List<TableEntity>>();
		 HashMap<String,List<TableEntity>> successMap =  new HashMap<String,List<TableEntity>>();;
		
		 CloudTable cloudTable=null;
		 
		 for (Map.Entry<String, List<TableEntity>> entry : tableNameToEntityMap.entrySet()){
		     try {
					cloudTable = azureStorage.getTable(entry.getKey());
					
				} catch (Exception e) {
					errorMap.put(entry.getKey(), entry.getValue());
					LOG.error("### Exception in TableStorageRepositoryImpl.batchInsert.getTable ###"+e);
					e.printStackTrace();
					response.setError(true);
					continue;
				}
		     
		  // Define a batch operation.
			    TableBatchOperation batchOperation = new TableBatchOperation();
			    List<TableEntity> value = entry.getValue();
			    
			    for (int i = 0; i < value.size(); i++) {
			    	TableEntity entity = value.get(i) ;
			    	if(entity instanceof PoEntity){
			    		counter= counter+1;
			    		
			    		//Sunil:delete this code start
			    		if(((PoEntity) entity).getStatus()==1){
			    			intransitCount = intransitCount+1;
			    		}else if(((PoEntity) entity).getStatus()==2){
			    			successCount = successCount+1;
			    		}else{
			    			errorCount = errorCount+1;
			    		}//end
			    	}
			    	
			    	//LOG.info("entity-->"+entity);
			    	batchOperation.insertOrReplace(entity);
			    	if (i!=0 && i % batchSize == 0) {
			    		try {
							cloudTable.execute(batchOperation);
							batchOperation.clear();
							successMap.put(entry.getKey(), entry.getValue());
							intransitCount = intransitCount+counter;
							counter = 0;
						} catch (Exception e) {
							errorMap.put(entry.getKey(), entry.getValue());
							response.setError(true);
							counter = 0;
							LOG.error("### Exception in TableStorageRepositoryImpl.batchInsert.execute ###"+e);
							e.printStackTrace();
							continue;
						}
			    	 }
			    }
			    
			    if(batchOperation.size()>0){
			    	try {
						cloudTable.execute(batchOperation);
						successMap.put(entry.getKey(), entry.getValue());
						intransitCount = intransitCount+counter;
						counter = 0;
					} catch (Exception e) {
						errorMap.put(entry.getKey(), entry.getValue());
						response.setError(true);
						counter = 0;
						LOG.error("### Exception in TableStorageRepositoryImpl.batchInsert.execute ###"+e);
						e.printStackTrace();
						continue;
					}
			    }
		 }	
		 
		 response.setErrorMap(errorMap);
		 response.setSuccessMap(successMap);
		 
		 if(intransitCount<1){//Sunil: make sure: while updating its working fine
			 return response;	
		 }
		//Insert MIsc data: need to make sure only for podetails
		MiscDataEntity miscEntity=null;
		try {
			miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			e.printStackTrace();
		}
		if(miscEntity!=null){
			miscEntity.setIntransitCount((miscEntity.getIntransitCount()+intransitCount));
			
			//Sunil:delete this code start
			miscEntity.setErrorCount((miscEntity.getErrorCount()+errorCount));
			miscEntity.setProcessedCount((miscEntity.getProcessedCount()+successCount));
			//end
		}else{
			miscEntity = new MiscDataEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
			miscEntity.setIntransitCount(intransitCount);
		}
		
		try {
			updateStatusCountEntity(miscEntity);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			e.printStackTrace();
		}
		LOG.info("#### Ending TableStorageRepositoryImpl.batchInsert ###" );
		return response;		
	}
	
	public BatchUpdateRes batchUpdate(BatchUpdateReq request){
		
		LOG.info("#### Starting TableStorageRepositoryImpl.batchInsert ###" +request);
		BatchUpdateRes response = new BatchUpdateRes();
		
		String erpName = request.getErpName();
		HashMap<String,List<PoEntity>> tableNameToEntityMap = request.getTableNameToEntityMap();
		
		 List<String> errorList = new ArrayList<String>();
		 List<String> successList = new ArrayList<String>();
		
		 CloudTable cloudTable=null;
		 PoEntity entity = null;
		 for (Map.Entry<String, List<PoEntity>> entry : tableNameToEntityMap.entrySet()){
		     try {
					cloudTable = azureStorage.getTable(entry.getKey());
				} catch (Exception e) {
					LOG.error("### Exception in TableStorageRepositoryImpl.batchUpdate.getTable ###"+e);
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
			    	counter= counter+1;
			    	
			    	entity.setGlobalId(request.getGlobalId());
			    	entity.setUserName(request.getUserName());
			    	entity.setComment(request.getComment());
			    	
			    	//Need to maintain destination mapping file and make this dynamic
			    	if(request.getDestination()==1){
			    		entity.setE2openProcessed(true);
			    	}else if(request.getDestination()==2){
			    		entity.setEdiProcessed(true);
			    	}
			    	
			    	if(request.isSuccess()){//Means we are updating(success) status for pos which has been successfully processed to e2opne
			    		entity.setStatus(Constants.STATUS_SUCCESS);
			    		successCount = successCount+1;
			    		successList.add(entity.getRowKey());
			    	}else{
			    		entity.setStatus(Constants.STATUS_ERROR);
			    		errorCount = errorCount+1;
			    		errorList.add(entity.getRowKey());
			    	}
			    	
			    	
			    	batchOperation.insertOrReplace(entity);
			    	if (i!=0 && (i % batchSize) == 0) {
			    		try {
							cloudTable.execute(batchOperation);
							batchOperation.clear();
							counter = 0;
						} catch (Exception e) {
							response.setError(true);
							response.setMessage("The Application has encountered an error!");
							counter = 0;
							if(request.isSuccess()){
					    		successCount = successCount-1;
					    	}else{
					    		errorCount = errorCount-1;
					    	}
							LOG.error("### Exception in TableStorageRepositoryImpl.batchUpdate.execute ###"+e);
							e.printStackTrace();
							continue;
						}
			    	 }
			    }
			    
			    LOG.info("batchOperation.size()-->"+batchOperation.size());
			    
			    if(batchOperation.size()>0){
			    	
			    	try {
						cloudTable.execute(batchOperation);
						counter = 0;
					} catch (Exception e) {
						//errorList.add(entity.getRowKey());
						response.setError(true);
						response.setMessage("The Application has encountered an error!");
						counter = 0;
						if(request.isSuccess()){
				    		successCount = successCount-1;
				    	}else{
				    		errorCount = errorCount-1;
				    	}
						LOG.error("### Exception in TableStorageRepositoryImpl.batchUpdate.execute ###"+e);
						e.printStackTrace();
						continue;
					}
			    }
		 }	
		 
		 response.setErrorList(errorList);
		 response.setSuccessList(successList);
		 
		  LOG.info("errorCount-->"+errorCount);
		  LOG.info("successCount-->"+successCount);
		  
		//Insert MIsc data: need to make sure only for podetails
		MiscDataEntity miscEntity=null;
		try {
			miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage("The Application has encountered an error!");
			e.printStackTrace();
		}
		
		if(errorCount>0){
			  int sum1 = miscEntity.getErrorCount()+errorCount;
			  miscEntity.setErrorCount((sum1));
			  int sum2 = miscEntity.getIntransitCount()-errorCount;
			  miscEntity.setIntransitCount(sum2);
		}
		
		if(successCount>0){
			 int sum1 = miscEntity.getProcessedCount()+successCount;
			 int sum2 = miscEntity.getErrorCount()-successCount;
			 miscEntity.setProcessedCount((sum1));
			 miscEntity.setErrorCount((sum2));
		}
		
		try {
			updateStatusCountEntity(miscEntity);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			response.setError(true);
			response.setMessage("The Application has encountered an error!");
			e.printStackTrace();
		}
		LOG.info("#### Ending TableStorageRepositoryImpl.batchUpdate ###" );
		return response;
		
	}
}
