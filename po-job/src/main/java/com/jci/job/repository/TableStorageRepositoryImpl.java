package com.jci.job.repository;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jci.job.azure.AzureStorage;
import com.jci.job.azure.BatchInsertRequest;
import com.jci.job.azure.BatchInsertResponse;
import com.jci.job.entity.MiscDataEntity;
import com.jci.job.entity.PoEntity;
import com.jci.job.utils.Constants;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableEntity;
import com.microsoft.azure.storage.table.TableOperation;


@Repository
public class TableStorageRepositoryImpl implements TableStorageRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(TableStorageRepositoryImpl.class);
	private static int errorCount;
	private static int successCount;
	private static int intransitCount;
	static int counter=0;
	final int batchSize = 100;
	
	@Autowired
	private AzureStorage azureStorage;

	public void createTable(String tableName) throws InvalidKeyException, StorageException, URISyntaxException {
	    if (azureStorage.getTable(tableName).createIfNotExists()) {
	    	LOG.info("table is created : " + tableName);
	    }
	}

	@Override
	@Transactional
	public BatchInsertResponse batchInsert(BatchInsertRequest request){
		LOG.info("#### Starting TableStorageRepositoryImpl.batchInsert ###" );
		BatchInsertResponse response = new BatchInsertResponse();
		
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
			    	}
			    	
			    	batchOperation.insertOrReplace(entity);
			    	if (i % batchSize == 0) {
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
		 
		 response.setErrorMap(errorMap);
		 response.setSuccessMap(successMap);
		 
		//Insert MIsc data
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
			response.setError(true);
			e.printStackTrace();
		}
		LOG.info("#### Ending TableStorageRepositoryImpl.batchInsert ###" );
		return response;		
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
}
