/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.repo;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jci.job.api.req.BatchInsertReq;
import com.jci.job.api.req.BatchUpdateReq;
import com.jci.job.api.res.BatchUpdateRes;
import com.jci.job.azure.AzureStorage;
import com.jci.job.entity.MiscDataEntity;
import com.jci.job.entity.PoEntity;
import com.jci.job.utils.CommonUtils;
import com.jci.job.utils.Constants;
import com.jci.job.utils.QueryBuilder;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableEntity;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;



/**
 * The Class JobRepoImpl.
 */
@Repository
public class JobRepoImpl implements JobRepo { // NO_UCD (unused code)
	
	/** The Constant LOG. */
 private static final Logger LOG = LoggerFactory.getLogger(JobRepoImpl.class);
	
	/** The batch size. */
	final int batchSize = 20;
	
	/** The azure storage. */
	@Autowired
	private AzureStorage azureStorage;

	/* (non-Javadoc)
	 * @see com.jci.job.repo.JobRepo#createTable(java.lang.String)
	 */
	@Override
	public boolean createTable(String tableName) throws InvalidKeyException, StorageException, URISyntaxException {
		boolean isSuccess=false;
	    if (azureStorage.getTable(tableName).createIfNotExists()) {
	    	LOG.info("table is created : " + tableName);
	    	 isSuccess=true;
	    }
	    return isSuccess;
	}

	/* (non-Javadoc)
	 * @see com.jci.job.repo.JobRepo#batchInsert(com.jci.job.api.req.BatchInsertReq)
	 */
	@Override
	public List<Object> batchInsert(BatchInsertReq request){ 
		String tableName=null;
		
		String erpName = request.getErpName();
		HashMap<String,List<TableEntity>> tableNameToEntityMap = request.getTableNameToEntityMap();
	
		CloudTable cloudTable=null;
		  
		String partitionKey = erpName.toUpperCase();
		List<String> rowKeys = request.getRowKeyList();
		List<Map<String,Integer>> rowKeyData=null;
		 for (Map.Entry<String, List<TableEntity>> entry : tableNameToEntityMap.entrySet()){
		     try {
					cloudTable = azureStorage.getTable(entry.getKey());
					tableName=entry.getKey();
					rowKeyData = CommonUtils.getNewRowkeys(partitionKey,tableName, rowKeys,cloudTable);
					//LOG.info("rowKeyData 1-->"+rowKeyData);
					
				} catch (Exception e) {
					LOG.error("### Exception in JobRepoImpl.batchInsert.getTable ###"+e);
					continue;
				}
			    TableBatchOperation batchOperation = new TableBatchOperation();
			    List<TableEntity> value = entry.getValue();
			    for (int i = 0; i < value.size(); i++) {
			    	TableEntity entity = value.get(i) ;
			    	batchOperation.insertOrMerge(entity);
			    	if (i!=0 && i % batchSize == 0) {
			    		try {
							cloudTable.execute(batchOperation);
							batchOperation.clear();
						} catch (Exception e) {
							LOG.error("### Exception in JobRepoImpl.batchInsert.execute ###"+e);
							continue;
						}
			    	 }
			    }
			    
			    if(batchOperation.size()>0){
			    	try {
						cloudTable.execute(batchOperation);
					} catch (Exception e) {
						LOG.error("### Exception in JobRepoImpl.batchInsert.execute ###"+e);
						continue;
					}
			    }
		 }	
		//Insert MIsc data
		 if(rowKeyData!=null){
			 MiscDataEntity miscEntity=null;
				try {
					miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					LOG.error("### Exception in JobRepoImpl.addMiscEntity ####",e);
				}
				
				if(miscEntity==null){
					miscEntity = new MiscDataEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
				}
				
				miscEntity = CommonUtils.getMiscEntity(miscEntity,tableName,rowKeyData);
				try {
					updateStatusCountEntity(miscEntity);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					LOG.error("### Exception in JobRepoImpl.addMiscEntity ####",e);
				}
		 }
		return request.getReq();
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
	
	/* (non-Javadoc)
	 * @see com.jci.job.repo.JobRepo#getPoDetails(java.lang.String, java.util.List)
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
	 * @see com.jci.job.repo.JobRepo#batchUpdate(com.jci.job.api.req.BatchUpdateReq)
	 */
	@Override
	public BatchUpdateRes batchUpdate(BatchUpdateReq request){		
		int errorCount=0;
		int successCount=0;
		BatchUpdateRes response = new BatchUpdateRes();
		
		String erpName = request.getErpName();
		HashMap<String,List<PoEntity>> tableNameToEntityMap = request.getTableNameToEntityMap();
		
		 List<String> errorList = new ArrayList<>();
		 List<String> successList = new ArrayList<>();
		
		 CloudTable cloudTable=null;
		 PoEntity entity = null;
		 String tableName=null;
		 
		 for (Map.Entry<String, List<PoEntity>> entry : tableNameToEntityMap.entrySet()){
		     try {
					cloudTable = azureStorage.getTable(entry.getKey());
					tableName=entry.getKey();
				} catch (Exception e) {
					LOG.error("### Exception in JobRepoImpl.batchUpdate.getTable ###"+e);
					
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
			    		entity.setSupplierDeliveryState(Constants.STATUS_SUCCESS);
			    		successCount = successCount+1;
			    		successList.add(entity.getRowKey());
			    	}else{//Request is for error status update
			    		entity.setSupplierDeliveryState(Constants.STATUS_ERROR);
			    		errorCount = errorCount+1;
			    		errorList.add(entity.getRowKey());
			    	}
			    	
			    	batchOperation.insertOrMerge(entity);
			    	if (i!=0 && (i % batchSize) == 0) {
			    		try {
							cloudTable.execute(batchOperation);
							batchOperation.clear();
						} catch (Exception e) {
							response.setError(true);
							response.setMessage("The Application has encountered an error!");
							if(request.isSuccess()){
					    		successCount = successCount-1;
					    	}else{
					    		errorCount = errorCount-1;
					    	}
							LOG.error("### Exception in JobRepoImpl.batchUpdate.execute ###"+e);
							
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
						if(request.isSuccess()){
				    		successCount = successCount-1;
				    	}else{
				    		errorCount = errorCount-1;
				    	}
						LOG.error("### Exception in JobRepoImpl.batchUpdate.execute ###"+e);
						continue;
					}
			    }
		 }	
		 response.setErrorList(errorList);
		 response.setSuccessList(successList);
		 updateMiscEntity(erpName,tableName,successCount,errorCount);
		return response;
		
	}//batchUpdate
	
	/**
	 * Update misc entity.
	 *
	 * @param erpName the erp name
	 * @param tableName the table name
	 * @param successCount the success count
	 * @param errorCount the error count
	 */
	private void updateMiscEntity(String erpName,String tableName,int successCount,int errorCount){
		MiscDataEntity miscEntity=null;
		
		try {
			miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in JobRepoImpl.batchInsert ####",e);
			
		}
		
		if(miscEntity==null){
			miscEntity = new MiscDataEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		}
		
		int totalCount=0;
		if(Constants.TABLE_PO_DETAILS.equals(tableName)){
			if(errorCount>0){
				int sum1 = miscEntity.getPoErrorCount()+errorCount;
				miscEntity.setPoErrorCount((sum1));
				totalCount=errorCount;
			}
			if(successCount>0){
				int sum1 = miscEntity.getPoProcessedCount()+successCount;
				miscEntity.setPoProcessedCount((sum1));
				totalCount=totalCount+successCount;
			}
			if(totalCount>0){
				int sum2 = miscEntity.getPoIntransitCount()-totalCount;
				miscEntity.setPoIntransitCount(sum2);
			}
		}else if (Constants.TABLE_SUPPLIER.equals(tableName)){
			if(errorCount>0){
				 int sum1 = miscEntity.getSuppErrorCount()+errorCount;
				 miscEntity.setSuppErrorCount((sum1));
				 totalCount=errorCount;
			}
			if(successCount>0){
				 int sum1 = miscEntity.getSuppProcessedCount()+successCount;
				 miscEntity.setSuppProcessedCount((sum1));
				 totalCount=totalCount+successCount;
			}
			if(totalCount>0){
				int sum2 = miscEntity.getSuppIntransitCount()-totalCount;
				miscEntity.setSuppIntransitCount(sum2);
			}
			 
		}else if(Constants.TABLE_ITEM.equals(tableName)){			 
			if(errorCount>0){
				 int sum1 = miscEntity.getItemErrorCount()+errorCount;
				 miscEntity.setItemErrorCount((sum1));
				 totalCount=errorCount;
			}
			if(successCount>0){
				 int sum1 = miscEntity.getItemProcessedCount()+successCount;
				 miscEntity.setItemProcessedCount((sum1));
				 totalCount=totalCount+successCount;
			}
			if(totalCount>0){
				int sum2 = miscEntity.getItemIntransitCount()-totalCount;
				miscEntity.setItemIntransitCount(sum2);
			}
		}
		 
		if(totalCount>0){
			try {
				updateStatusCountEntity(miscEntity);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				LOG.error("### Exception in JobRepoImpl.updateMiscEntity ####",e);
			}
		}
	}
}
