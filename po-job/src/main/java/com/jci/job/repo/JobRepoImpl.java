package com.jci.job.repo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.job.api.req.BatchUpdateReq;
import com.jci.job.api.res.BatchUpdateRes;
import com.jci.job.azure.AzureStorage;
import com.jci.job.azure.BatchInsertReq;
import com.jci.job.entity.ItemEntity;
import com.jci.job.entity.MiscDataEntity;
import com.jci.job.entity.PoEntity;
import com.jci.job.entity.SupplierEntity;
import com.jci.job.utils.Constants;
import com.jci.job.utils.QueryBuilder;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableEntity;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;


@Repository
public class JobRepoImpl implements JobRepo {
	
	private static final Logger LOG = LoggerFactory.getLogger(JobRepoImpl.class);
	private static int errorCount;
	private static int successCount;
	private static int intransitCount;
	static int counter=0;
	final int batchSize = 20;
	
	@Autowired
	private AzureStorage azureStorage;

	public void createTable(String tableName) throws InvalidKeyException, StorageException, URISyntaxException {
	    if (azureStorage.getTable(tableName).createIfNotExists()) {
	    	LOG.info("table is created : " + tableName);
	    }
	}

	@Override
	public List<String> batchInsert(BatchInsertReq request){
		LOG.info("#### Starting JobRepoImpl.batchInsert ###" +request);
		boolean isMiscData=false;
		String erpName = request.getErpName();
		LOG.error("erpName--->"+erpName);
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = request.getTableNameToEntityMap();
	
		List<String> poSuccessList =  new ArrayList<String>();
		CloudTable cloudTable=null;
		 
		 for (Map.Entry<String, List<TableEntity>> entry : tableNameToEntityMap.entrySet()){
		     try {
					cloudTable = azureStorage.getTable(entry.getKey());
				} catch (Exception e) {
					//errorMap.put(entry.getKey(), entry.getValue());
					LOG.error("### Exception in JobRepoImpl.batchInsert.getTable ###"+e);
					e.printStackTrace();
					continue;
				}
		     LOG.error("Table Name--->"+cloudTable.getName());
		  // Define a batch operation.
			    TableBatchOperation batchOperation = new TableBatchOperation();
			    List<TableEntity> value = entry.getValue();
			    //LOG.error("value.size()--->"+value.size());
			   // LOG.error("value.toString()--->"+value.toString());
			    
			    
			    for (int i = 0; i < value.size(); i++) {
			    	TableEntity entity = value.get(i) ;
			    	if(entity instanceof PoEntity ){
			    		counter= counter+1;
			    		isMiscData=true;
			    		poSuccessList.add(entity.getRowKey());
			    	}else if(entity instanceof ItemEntity || entity instanceof SupplierEntity){
			    		poSuccessList.add(entity.getRowKey());
			    	}
			    	batchOperation.insertOrReplace(entity);
			    	if (i!=0 && i % batchSize == 0) {
			    		try {
							cloudTable.execute(batchOperation);
							batchOperation.clear();
							intransitCount = intransitCount+counter;
							counter = 0;
						} catch (Exception e) {
							counter = 0;
							LOG.error("### Exception in JobRepoImpl.batchInsert.execute ###"+e);
							e.printStackTrace();
							continue;
						}
			    	 }
			    }
			    
			   // LOG.error("batchOperation.size()--->"+batchOperation.size());
			    if(batchOperation.size()>0){
			    	try {
						cloudTable.execute(batchOperation);
						//successMap.put(entry.getKey(), entry.getValue());
						intransitCount = intransitCount+counter;
						counter = 0;
					} catch (Exception e) {
						counter = 0;
						LOG.error("### Exception in JobRepoImpl.batchInsert.execute ###"+e);
						e.printStackTrace();
						continue;
					}
			    }
		 }	
		 
		 HashMap<String,List<String>> successMap = null;
		 if(poSuccessList.size()>0){
			 successMap =  new HashMap<String,List<String>>();;
			 successMap.put(Constants.TABLE_PO_DETAILS, poSuccessList);
			// response.setSuccessMap(successMap);
		 }
		 

		//Insert MIsc data
		 if(isMiscData){
			 MiscDataEntity miscEntity=null;
				try {
					miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					e.printStackTrace();
				}
				if(miscEntity!=null){
					miscEntity.setIntransitCount((miscEntity.getIntransitCount()+intransitCount));
				}else{
					miscEntity = new MiscDataEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
					miscEntity.setIntransitCount(intransitCount);
				}
				
				try {
					updateStatusCountEntity(miscEntity);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					e.printStackTrace();
				}
		 }
		
		LOG.info("#### Ending JobRepoImpl.batchInsert ###"+poSuccessList );
		return poSuccessList;		
	}
	
	public MiscDataEntity getStatusCountEntity(String partitionKey, String rowKey) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
	    TableOperation entity =   TableOperation.retrieve(partitionKey, rowKey, MiscDataEntity.class);
		return cloudTable.execute(entity).getResultAsType();
	}
	
	public void updateStatusCountEntity(MiscDataEntity entity) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		
		TableOperation insert = TableOperation.insertOrReplace(entity);
		cloudTable.execute(insert);
	}
	
	@Override
	public Map<String,List<HashMap<String, Object>>> getPos(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("#### Starting JobRepoImpl.getPos ###" );
		
		String query = QueryBuilder.poQuery(partitionKey,poList);
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
	    LOG.info("#### Ending JobRepoImpl.getPos ###" );
		 return poNumToItemListMap;
	}
	
	@Override
	public List<PoEntity> getPoDetails(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("#### Starting JobRepoImpl.getPoDetails ###" );
		
		String query = QueryBuilder.processPosQuery(partitionKey,poList);
		LOG.info("query--->"+query);
		
		
		List<PoEntity> errorData = new ArrayList<PoEntity>();
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	errorData.add(entity);
	    }
	    LOG.info("#### Ending JobRepoImpl.getPoDetails ###" );
		 return errorData;
	}
	
	@Override
	public BatchUpdateRes batchUpdate(BatchUpdateReq request){		
		LOG.info("#### Starting PoRepoImpl.batchUpdate ###" +request);
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
			    	//counter= counter+1;
			    	
			    	entity.setGlobalId(request.getGlobalId());
			    	entity.setUserName(request.getUserName());
			    	entity.setComment(request.getComment());
			    	
			    	if(request.isSuccess()){//Means we are updating(success) status for pos which has been successfully processed to e2open
			    		entity.setStatus(Constants.STATUS_SUCCESS);
			    		successCount = successCount+1;
			    		successList.add(entity.getRowKey());
			    	}else{//Request is for error status update
			    		entity.setStatus(Constants.STATUS_ERROR);
			    		errorCount = errorCount+1;
			    		errorList.add(entity.getRowKey());
			    	}
			    	
			    	
			    	batchOperation.insertOrReplace(entity);
			    	if (i!=0 && (i % batchSize) == 0) {
			    		try {
							cloudTable.execute(batchOperation);
							batchOperation.clear();
							//counter = 0;
						} catch (Exception e) {
							response.setError(true);
							response.setMessage("The Application has encountered an error!");
							//counter = 0;
							if(request.isSuccess()){
					    		successCount = successCount-1;
					    	}else{
					    		errorCount = errorCount-1;
					    	}
							LOG.error("### Exception in PoRepoImpl.batchUpdate.execute ###"+e);
							e.printStackTrace();
							continue;
						}
			    	 }
			    }
			    
			    if(batchOperation.size()>0){
			    	try {
						cloudTable.execute(batchOperation);
					//	counter = 0;
					} catch (Exception e) {
						//errorList.add(entity.getRowKey());
						response.setError(true);
						response.setMessage("The Application has encountered an error!");
						//counter = 0;
						if(request.isSuccess()){
				    		successCount = successCount-1;
				    	}else{
				    		errorCount = errorCount-1;
				    	}
						LOG.error("### Exception in PoRepoImpl.batchUpdate.execute ###"+e);
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
		
		int totalCount=0;
		if(errorCount>0){
			  int sum1 = miscEntity.getErrorCount()+errorCount;
			  miscEntity.setErrorCount((sum1));
			  totalCount=errorCount;
		}
		if(successCount>0){
			 int sum1 = miscEntity.getProcessedCount()+successCount;
			 miscEntity.setProcessedCount((sum1));
			 totalCount=totalCount+successCount;
		}
		if(totalCount>0){
			int sum2 = miscEntity.getIntransitCount()-totalCount;
			miscEntity.setIntransitCount(sum2);
		}
		
		if(totalCount>0){
			try {
				updateStatusCountEntity(miscEntity);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				response.setError(true);
				response.setMessage("The Application has encountered an error!");
				e.printStackTrace();
			}
		}
		LOG.info("#### Ending PoRepoImpl.batchUpdate ###" );
		return response;
		
	}

   
	@Override
	public List<Map<String,List<HashMap<String, Object>>>> getFlatFileData(String partitionKey) throws InvalidKeyException, URISyntaxException, StorageException{
		
		String query = QueryBuilder.ffQuery(partitionKey);
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		Map<String,List<String>> pkToRowkeyList = new HashMap<String,List<String>>();
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
		
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	if(pkToRowkeyList.containsKey(entity.getPartitionKey())){
				List<String> list = pkToRowkeyList.get(entity.getPartitionKey());
				list.add(entity.getRowKey());
				pkToRowkeyList.put(entity.getPartitionKey(), list);
			}else{
				List<String> list = new ArrayList<String>();
				list.add(entity.getRowKey());
				pkToRowkeyList.put(entity.getPartitionKey(), list);
			}
	    }
	    
		List<Map<String,List<HashMap<String, Object>>>> list = new ArrayList<Map<String,List<HashMap<String, Object>>>>();
		for (Map.Entry<String,List<String>> entry : pkToRowkeyList.entrySet()){	
			list.add(getffPos(entry.getKey(),entry.getValue()));
		}
		return list;
	}
	
	public Map<String,List<HashMap<String, Object>>> getffPos(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
		String query = QueryBuilder.poQuery(partitionKey,poList);
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS);
		OperationContext opContext = new OperationContext();
		
		TableQuery<DynamicTableEntity> myQuery = TableQuery.from(DynamicTableEntity.class).where(query).take(1000);//Need to discuss this
		Iterator<DynamicTableEntity> rows = cloudTable.execute(myQuery, null, opContext).iterator();
		DynamicTableEntity row;
		EntityProperty ep;
		HashMap<String, Object> hashmap;
		ObjectMapper mapper = new ObjectMapper(); 
		Map<String,List<HashMap<String, Object>>> poNumToItemListMap = new HashMap<String,List<HashMap<String, Object>>>();
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			hashmap = new HashMap<String, Object>();
			
			for (String key : map.keySet()) {
				ep = map.get(key);
				if(Constants.JSON_STRING.equals(key)){
					TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
					try {
						hashmap = mapper.readValue(ep.getValueAsString(), typeRef);
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
			}
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
		 return poNumToItemListMap;
	}
	
	@Override
	public List<HashMap<String, Object>> getFlatFileData(String partitionKey,String tableName) throws InvalidKeyException, URISyntaxException, StorageException {
		String query = QueryBuilder.ffQuery(partitionKey);
		
		CloudTable cloudTable = azureStorage.getTable(tableName);
		OperationContext opContext = new OperationContext();
		
		TableQuery<DynamicTableEntity> myQuery = TableQuery.from(DynamicTableEntity.class).where(query).take(1000);
		Iterator<DynamicTableEntity> rows = cloudTable.execute(myQuery, null, opContext).iterator();
		DynamicTableEntity row;
		EntityProperty ep;
		HashMap<String, Object> hashmap;
		ObjectMapper mapper = new ObjectMapper(); 
		
		List<HashMap<String, Object>> list  = new ArrayList<HashMap<String, Object>>();
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();
			hashmap = new HashMap<String, Object>();
			
			for (String key : map.keySet()) {
				ep = map.get(key);
				if(Constants.JSON_STRING.equals(key)){
					TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
					try {
						hashmap = mapper.readValue(ep.getValueAsString(), typeRef);
						list.add(hashmap);
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
			}
		}		
		 return list;
	}
}
