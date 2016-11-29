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

import com.jci.config.AzureStorage;
import com.jci.dto.BatchUpdateRes;
import com.jci.dto.GrDetails;
import com.jci.entity.MiscDataEntity;
import com.jci.entity.PoEntity;
import com.jci.enums.ErrorEnum;
import com.jci.exception.ErrorService;
import com.jci.job.api.req.BatchInsertReq;
import com.jci.job.api.req.BatchUpdateReq;
import com.jci.job.exception.JobException;
import com.jci.utils.CommonUtils;
import com.jci.utils.Constants;
import com.jci.utils.QueryBuilder;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableEntity;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;

/**
 * <p>
 * <strong> The Job Repoository implementation class ..</strong>
 * <p>
 *
 * @author csonisk
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
	
    /** The error service. */
    @Autowired
    private ErrorService errorService;

	@Override
	public boolean createTable(String tableName) {
		boolean isSuccess=false;
	    try {
			if (azureStorage.getTable(tableName).createIfNotExists()) {
				LOG.info("table is created : " + tableName);
				 isSuccess=true;
			}
		} catch (InvalidKeyException | StorageException | URISyntaxException e) {
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_TABLE_CREATION);
		}
	    return isSuccess;
	}

	@Override
	public synchronized List<Object> batchInsert(BatchInsertReq request){
		LOG.info("### Starting in JobRepoImpl.batchInsert ###");
		
		String erpName = request.getErpName();
		HashMap<String,List<TableEntity>> tableNameToEntityMap = request.getTableNameToEntityMap();
		CloudTable cloudTable=null;
		  
		String partitionKey = erpName.toUpperCase();
		//List<String> rowKeys = request.getRowKeyList();
		
		Map<String,List<String>> tableNameToRowkeyListMap =  request.getTableNameToRowkeyListMap();
		List<Map<String,Integer>> rowKeyData=null;
		 for (Map.Entry<String, List<TableEntity>> entry : tableNameToEntityMap.entrySet()){
			 String tableName=null;
			 List<TableEntity> value = null;
			 int valueSize =0;
		     try {
					cloudTable = azureStorage.getTable(entry.getKey());
					tableName=entry.getKey();
					value = entry.getValue();
					valueSize = value==null ? 0 : value.size();
					
					LOG.info("tableName-->"+tableName);
					if(request.isDummyGrData() && Constants.TABLE_PO_DETAILS.equals(tableName)){
						rowKeyData = null;
					}else{
						rowKeyData = CommonUtils.getNewRowkeys(partitionKey,tableName, tableNameToRowkeyListMap.get(tableName),cloudTable);
					}
				} catch (Exception e) {
					LOG.error("### Exception in JobRepoImpl.batchInsert.getTable ###"+e);
					continue;
				}
			    TableBatchOperation batchOperation = new TableBatchOperation();
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
			    
			  //Insert MIsc data
			    MiscDataEntity miscEntity=null;
				try {
					miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
				} catch (Exception e) {
					LOG.error("### Exception in JobRepoImpl.addMiscEntity ####",e);
				}
				
				if(miscEntity==null){
					miscEntity = new MiscDataEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
				}
			    
				 if(rowKeyData!=null){
						miscEntity = CommonUtils.getMiscEntity(miscEntity,tableName,rowKeyData);
						 LOG.info("miscEntity--->"+miscEntity);
						 
						if(miscEntity!=null){
							updateStatusCountEntity(miscEntity);
						}
				 }else if(request.isDummyGrData() && Constants.TABLE_PO_DETAILS.equals(tableName)){
					 miscEntity = CommonUtils.getMiscEntity(miscEntity,valueSize);
					 if(miscEntity!=null){
						updateStatusCountEntity(miscEntity);
					 }
				 }
		 }	
		
		 LOG.info("### Ending in JobRepoImpl.batchInsert ###");
		return request.getReq();
	}
	
	
	public MiscDataEntity getStatusCountEntity(String partitionKey, String rowKey) {
		CloudTable cloudTable;
		MiscDataEntity misc= null;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_MISCDATA_TABLE_NOT_FOUND);
		}
	    TableOperation entity =   TableOperation.retrieve(partitionKey, rowKey, MiscDataEntity.class);
		try {
			misc =  cloudTable.execute(entity).getResultAsType();
		} catch (StorageException e) {
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_MISCDATA_ENTITY_NOT_FOUND);
		}
		
		return misc;
	}
	
	public void updateStatusCountEntity(MiscDataEntity entity) {
		CloudTable cloudTable;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_MISCDATA_TABLE_NOT_FOUND);
		}
		
		TableOperation insert = TableOperation.insertOrMerge(entity);
		try {
			cloudTable.execute(insert);
		} catch (StorageException e) {
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_MISCDATA_INSERT_DATA);
		}
	}
	
	@Override
	public List<PoEntity> getPoDetails(String partitionKey, List<String> poList) {
		String query = QueryBuilder.processPosQuery(partitionKey,poList);
		List<PoEntity> errorData = new ArrayList<>();
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
		
		CloudTable cloudTable;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_PO_TABLE_NOT_FOUND);
		}
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	errorData.add(entity);
	    }
		 return errorData;
	}
	
	@Override
	public synchronized BatchUpdateRes batchUpdate(BatchUpdateReq request){		
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
					throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,entry.getKey());
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
	
	private void updateMiscEntity(String erpName,String tableName,int successCount,int errorCount){
		MiscDataEntity miscEntity=null;
		
		try {
			miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		} catch (Exception e) {
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
			updateStatusCountEntity(miscEntity);
		}
	}
	
	Map<String,Integer> poMap=null;
	@Override
	public Map<String,Integer> getGrQtyMap(List<GrDetails> grList) {
		poMap = new HashMap<>();
		prepareGrQtyDetails(grList); 
		return poMap;
	}
	
	private void prepareGrQtyDetails(List<GrDetails> grList){
		CloudTable cloudTable = null;
		
		if(grList.size()>batchSize){
			String query = QueryBuilder.getGrQtyQuery(grList.subList(0, batchSize));
			
		    //PO exist?
		    TableQuery<PoEntity> poQuery =  TableQuery.from(PoEntity.class).where(query);
		    try {
				cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_PO_TABLE_NOT_FOUND);
			}
		    
		    for (PoEntity entity : cloudTable.execute(poQuery)) {
		    	poMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
		    }
		    prepareGrQtyDetails(grList.subList(batchSize,grList.size()));
		}else if(grList.size()>0){
			String query = QueryBuilder.getGrQtyQuery(grList);
		  //PO exist?
		    TableQuery<PoEntity> poQuery =  TableQuery.from(PoEntity.class).where(query);
		    try {
				cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				throw errorService.createException(JobException.class, e, ErrorEnum.ERROR_PO_TABLE_NOT_FOUND);
			}
		    
		    for (PoEntity entity : cloudTable.execute(poQuery)) {
		    	poMap.put(entity.getRowKey(),entity.getSupplierDeliveryState());
		    }
		}
	}
	
}
