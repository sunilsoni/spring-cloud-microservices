/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.flatfile.repo;

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
import com.jci.flatfile.config.AzureStorage;
import com.jci.flatfile.entity.GrEntity;
import com.jci.flatfile.entity.ItemEntity;
import com.jci.flatfile.entity.MiscDataEntity;
import com.jci.flatfile.entity.PoEntity;
import com.jci.flatfile.entity.SuppEntity;
import com.jci.flatfile.utils.BatchUpdateReq;
import com.jci.flatfile.utils.Constants;
import com.jci.flatfile.utils.FlatFileRes;
import com.jci.flatfile.utils.QueryBuilder;
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
public class FlatFileRepoImpl implements FlatFileRepo {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(FlatFileRepoImpl.class);
    
    /** The batch size. */
    final int batchSize = 20;
    
    /** The azure storage. */
    @Autowired
    private AzureStorage azureStorage;
    
    @Override
    public FlatFileRes getPoFlatFileData(String partitionKey, List<String> poList)  throws InvalidKeyException, URISyntaxException, StorageException {
    	LOG.info("### Starting in FlatFileRepoImpl.getPoFlatFileData ###"+poList);
    	String query = null;
    	if(poList!=null){
    		query = QueryBuilder.poQuery(partitionKey,poList);
    	}else{
    		query = QueryBuilder.ffQuery(partitionKey);
    	}
    	
    	LOG.info("query--->"+query);

    	TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
        Map<String,List<String>> pkToRowkeyList = new HashMap<>();
        CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
        
        Map<String, String> rowKeyToPlantMap = new  HashMap<>();
        Map<String, String> rowKeyToSupptypeMap  = new  HashMap<>();
        
        for (PoEntity entity : cloudTable.execute(partitionQuery)) {
        	 LOG.info("entity--->"+entity);
        	 LOG.info("pkToRowkeyList--->"+pkToRowkeyList);
        	 LOG.info("pk--->"+pkToRowkeyList.containsKey(entity.getPartitionKey()));
            if(pkToRowkeyList.containsKey(entity.getPartitionKey())){
                List<String> list = pkToRowkeyList.get(entity.getPartitionKey());
                list.add(entity.getRowKey());
                pkToRowkeyList.put(entity.getPartitionKey(), list);
            }else{
                List<String> list = new ArrayList<>();
                list.add(entity.getRowKey());
                pkToRowkeyList.put(entity.getPartitionKey(), list);
            }
            rowKeyToPlantMap.put(entity.getRowKey(), entity.getPlant());
            rowKeyToSupptypeMap.put(entity.getRowKey(), entity.getSuppType());
        }
        LOG.info("pkToRowkeyList--->"+pkToRowkeyList);
        
        ArrayList<Map<String,List<HashMap<String, Object>>>> list = new ArrayList<>();
        for (Map.Entry<String,List<String>> entry : pkToRowkeyList.entrySet()){ 
            list.add(getPos(entry.getKey(),entry.getValue()));
        }
        LOG.info("### Ending in FlatFileRepoImpl.getPoFlatFileData ###");
        return new FlatFileRes(rowKeyToPlantMap,rowKeyToSupptypeMap,list);
       
    }
    
    
    private Map<String,List<HashMap<String, Object>>> getPos(String partitionKey, List<String> poList) throws InvalidKeyException, URISyntaxException, StorageException {
        String query = QueryBuilder.poItemQuery(partitionKey,poList);
        CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS);
        OperationContext opContext = new OperationContext();
        
        TableQuery<DynamicTableEntity> myQuery = TableQuery.from(DynamicTableEntity.class).where(query).take(1000);//Need to discuss this
        Iterator<DynamicTableEntity> rows = cloudTable.execute(myQuery, null, opContext).iterator();
        DynamicTableEntity row;
        EntityProperty ep;
        HashMap<String, Object> hashmap;
        ObjectMapper mapper = new ObjectMapper(); 
        Map<String,List<HashMap<String, Object>>> poNumToItemListMap = new HashMap<>();
        
        while(rows.hasNext()) {
            row = rows.next() ;
            HashMap<String, EntityProperty> map = row.getProperties();
            hashmap = new HashMap<>();
            
            for (String key : map.keySet()) {
                ep = map.get(key);
                if(Constants.JSON_STRING.equals(key)){
                    TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
                    try {
                        hashmap = mapper.readValue(ep.getValueAsString(), typeRef);
                    } catch (IOException e) {
                        LOG.error("### Exception in   ####",e);                        
                    } 
                }
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
        LOG.info("poNumToItemListMap--->"+poNumToItemListMap);
         return poNumToItemListMap;
    }

    @Override
    public FlatFileRes getFlatFileData(String partitionKey, String tableName)  throws InvalidKeyException, URISyntaxException, StorageException {
        String query = QueryBuilder.ffQuery(partitionKey);
        
        CloudTable cloudTable = azureStorage.getTable(tableName);
        OperationContext opContext = new OperationContext();
        
        TableQuery<DynamicTableEntity> myQuery = TableQuery.from(DynamicTableEntity.class).where(query).take(1000);
        Iterator<DynamicTableEntity> rows = cloudTable.execute(myQuery, null, opContext).iterator();
        DynamicTableEntity row;
        EntityProperty ep;
        HashMap<String, Object> hashmap;
        ObjectMapper mapper = new ObjectMapper(); 
        
        List<HashMap<String, Object>> list  = new ArrayList<>();
        Map<String, String> rowKeyToPlantMap = new  HashMap<>();
        
       // Map<String, List<HashMap<String, Object>>> rowKeyToRowsMap = new  HashMap<>();
        Map<String, String> rowKeyToSupptypeMap  = new  HashMap<>();
       
      //  Map<String,List<HashMap<String, Object>>> plantToMap  = new  HashMap<>();
        TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
        while(rows.hasNext()) {
            row = rows.next() ;
            HashMap<String, EntityProperty> map = row.getProperties();
            hashmap = new HashMap<>();
            for (String key : map.keySet()) {
                ep = map.get(key);
                if(Constants.JSON_STRING.equals(key)){
                    
                    try {
                        hashmap = mapper.readValue(ep.getValueAsString(), typeRef);
                        list.add(hashmap);
                    } catch (IOException e) {
                        LOG.error("### Exception in   ####",e);
                    }
                }else if(Constants.PLANT.equals(key)){
                    rowKeyToPlantMap.put(row.getRowKey(), ep.getValueAsString());
                	
                }else if(Constants.SUPP_TYPE.equals(key)){
                    rowKeyToSupptypeMap.put(row.getRowKey(), ep.getValueAsString());
                }
            }
        }       
         return new  FlatFileRes(rowKeyToPlantMap, rowKeyToSupptypeMap,list);
    }
    
    
    @Override
	public void batchUpdate(BatchUpdateReq request){
    	LOG.info("### Starting in FlatFileRepoImpl.batchUpdate ###");
		int errorCount=0;
		int successCount=0;
		
		HashMap<String,List<TableEntity>> tableNameToEntityMap = request.getTableNameToEntityMap();
		
		 CloudTable cloudTable=null;
		 TableEntity entity = null;
		 
		 for (Map.Entry<String, List<TableEntity>> entry : tableNameToEntityMap.entrySet()){
		     try {
					cloudTable = azureStorage.getTable(entry.getKey());
				} catch (Exception e) {
					LOG.error("### Exception in FlatFileRepoImpl.batchUpdate.getTable ###"+e);
					continue;
				}
		     
		  // Define a batch operation.
			    TableBatchOperation batchOperation = new TableBatchOperation();
			    
			    
			    List<TableEntity> value = entry.getValue();
			    
			    for (int i = 0; i < value.size(); i++) {
			    	entity = value.get(i) ;
			    	
			    	if(entity instanceof PoEntity){
			    		PoEntity en = (PoEntity) entity;
			    		if(request.isSuccess()){
				    		en.setSupplierDeliveryState(Constants.STATUS_SUCCESS);
				    		successCount = successCount+1;
				    	}else{//Request is for error status update
				    		en.setSupplierDeliveryState(Constants.STATUS_ERROR);
				    		errorCount = errorCount+1;
				    	}
			    		if(request.getUserName()!=null){
			    			en.setComment(request.getComment());
			    			en.setUserName(request.getUserName());
			    			en.setGlobalId(request.getGlobalId());
		            	}
			    		batchOperation.insertOrMerge(en);
			    		
			    		 LOG.info("successCount-->"+successCount);
			    		 LOG.info("errorCount-->"+errorCount);
			    	}else if(entity instanceof SuppEntity){
			    		SuppEntity en = (SuppEntity) entity;
			    		if(request.isSuccess()){
				    		en.setSupplierDeliveryState(Constants.STATUS_SUCCESS);
				    		successCount = successCount+1;
				    	}else{//Request is for error status update
				    		en.setSupplierDeliveryState(Constants.STATUS_ERROR);
				    		errorCount = errorCount+1;
				    	}
			    		if(request.getUserName()!=null){
			    			en.setComment(request.getComment());
			    			en.setUserName(request.getUserName());
			    			en.setGlobalId(request.getGlobalId());
		            	}
			    		batchOperation.insertOrMerge(en);
			    	}else if(entity instanceof ItemEntity){
			    		ItemEntity en = (ItemEntity) entity;
			    		if(request.isSuccess()){
				    		en.setSupplierDeliveryState(Constants.STATUS_SUCCESS);
				    		successCount = successCount+1;
				    	}else{//Request is for error status update
				    		en.setSupplierDeliveryState(Constants.STATUS_ERROR);
				    		errorCount = errorCount+1;
				    	}
			    		if(request.getUserName()!=null){
			    			en.setComment(request.getComment());
			    			en.setUserName(request.getUserName());
			    			en.setGlobalId(request.getGlobalId());
		            	}
			    		batchOperation.insertOrMerge(en);
			    	}else if(entity instanceof GrEntity){
			    		GrEntity en = (GrEntity) entity;
			    		if(request.isSuccess()){
				    		en.setSupplierDeliveryState(Constants.STATUS_SUCCESS);
				    		successCount = successCount+1;
				    	}else{//Request is for error status update
				    		en.setSupplierDeliveryState(Constants.STATUS_ERROR);
				    		errorCount = errorCount+1;
				    	}
			    		if(request.getUserName()!=null){
			    			en.setComment(request.getComment());
			    			en.setUserName(request.getUserName());
			    			en.setGlobalId(request.getGlobalId());
		            	}
			    		batchOperation.insertOrMerge(en);
			    	}
			    	
			    	
			    	if (i!=0 && (i % batchSize) == 0) {
			    		try {
							cloudTable.execute(batchOperation);
							batchOperation.clear();
						} catch (Exception e) {
							LOG.error("### Exception in FlatFileRepoImpl.batchUpdate.execute ###"+e);
							continue;
						}
			    	 }
			    }
			    
			    if(batchOperation.size()>0){
			    	try {
						cloudTable.execute(batchOperation);
					} catch (Exception e) {
						continue;
					}
			    }
			    LOG.info("successCount-->"+successCount);
			    LOG.info("errorCount-->"+errorCount);
			    
			    updateMiscEntity(request.getErpName(),entry.getKey(),successCount,errorCount);
		 }	
		 
		 
		 LOG.info("### Ending in FlatFileRepoImpl.batchUpdate ###");
	} 
    
    List<Object> poFinal = null;
	@Override
	public List<Object> getPoDetails(String partitionKey, List<String> poList,String tableName) throws InvalidKeyException, URISyntaxException, StorageException {
		poFinal = new ArrayList<>();
		preparePoDetails( partitionKey,  poList,tableName); 
		return poFinal;
	}
	
	private void preparePoDetails(String partitionKey, List<String> poList,String tableName) throws InvalidKeyException, URISyntaxException, StorageException{
		if(poList.size()>batchSize){
			String query = QueryBuilder.processPosQuery(partitionKey, poList.subList(0, batchSize));
			
			if(Constants.TABLE_PO_DETAILS.equals(tableName)){
				TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
				
				CloudTable cloudTable = azureStorage.getTable(tableName);
			    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_GR_DETAILS.equals(tableName)){
				TableQuery<GrEntity> partitionQuery =  TableQuery.from(GrEntity.class).where(query);
				
				CloudTable cloudTable = azureStorage.getTable(tableName);
			    for (GrEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_ITEM.equals(tableName)){
				TableQuery<ItemEntity> partitionQuery =  TableQuery.from(ItemEntity.class).where(query);
				
				CloudTable cloudTable = azureStorage.getTable(tableName);
			    for (ItemEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_SUPPLIER.equals(tableName)){
				TableQuery<SuppEntity> partitionQuery =  TableQuery.from(SuppEntity.class).where(query);
				
				CloudTable cloudTable = azureStorage.getTable(tableName);
			    for (SuppEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}
			
		    preparePoDetails(partitionKey,poList.subList(batchSize,poList.size()),tableName);
		}else{
			String query = QueryBuilder.processPosQuery(partitionKey,poList);
		    if(Constants.TABLE_PO_DETAILS.equals(tableName)){
				TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
				
				CloudTable cloudTable = azureStorage.getTable(tableName);
			    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_GR_DETAILS.equals(tableName)){
				TableQuery<GrEntity> partitionQuery =  TableQuery.from(GrEntity.class).where(query);
				
				CloudTable cloudTable = azureStorage.getTable(tableName);
			    for (GrEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_ITEM.equals(tableName)){
				TableQuery<ItemEntity> partitionQuery =  TableQuery.from(ItemEntity.class).where(query);
				
				CloudTable cloudTable = azureStorage.getTable(tableName);
			    for (ItemEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_SUPPLIER.equals(tableName)){
				TableQuery<SuppEntity> partitionQuery =  TableQuery.from(SuppEntity.class).where(query);
				
				CloudTable cloudTable = azureStorage.getTable(tableName);
			    for (SuppEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}
		}
	}
	
	private synchronized void updateMiscEntity(String erpName,String tableName,int successCount,int errorCount){
		MiscDataEntity miscEntity=null;
		LOG.error("tableName-->"+tableName);
		try {
			miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			LOG.error("### Exception in JobRepoImpl.batchInsert ####",e);
			
		}
		
		if(miscEntity==null){
			miscEntity = new MiscDataEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		}
		LOG.error("miscEntity-->"+miscEntity);
		int totalCount=0;
		int sum1 = 0;
		if(Constants.TABLE_PO_DETAILS.equals(tableName)){
			if(errorCount>0){
				if( miscEntity.getPoErrorCount()==null){
					sum1 = errorCount;
				}else{
					sum1 = miscEntity.getPoErrorCount()+errorCount;
				}
				
				miscEntity.setPoErrorCount((sum1));
				totalCount=errorCount;
			}
			if(successCount>0){
				
				if(miscEntity.getPoProcessedCount()==null){
					sum1 = successCount;
				}else{
					sum1 = miscEntity.getPoProcessedCount()+successCount;
				}
				miscEntity.setPoProcessedCount((sum1));
				totalCount=totalCount+successCount;
			}
			if(totalCount>0){
				int sum2 = miscEntity.getPoErrorCount()-totalCount;
				miscEntity.setPoErrorCount(sum2);
			}
			LOG.error("miscEntity2-->"+miscEntity);
		}else if (Constants.TABLE_SUPPLIER.equals(tableName)){
			if(errorCount>0){
				if(miscEntity.getSuppErrorCount()==null){
					sum1 = errorCount;
				}else{
					sum1 = miscEntity.getSuppErrorCount()+errorCount;
				}
				 miscEntity.setSuppErrorCount((sum1));
				 totalCount=errorCount;
			}
			if(successCount>0){
				if(miscEntity.getSuppProcessedCount()==null){
					sum1 =  successCount;
				}else{
					sum1 =  miscEntity.getSuppProcessedCount()+successCount;
				}
				 miscEntity.setSuppProcessedCount((sum1));
				 totalCount=totalCount+successCount;
			}
			if(totalCount>0){
				int sum2 = miscEntity.getSuppErrorCount()-totalCount;
				miscEntity.setSuppErrorCount(sum2);
			}
			 
		}else if(Constants.TABLE_ITEM.equals(tableName)){			 
			if(errorCount>0){
				if(miscEntity.getItemErrorCount()==null){
					sum1 = errorCount;
				}else{
					sum1 = miscEntity.getItemErrorCount()+errorCount;
				}
				 miscEntity.setItemErrorCount((sum1));
				 totalCount=errorCount;
			}
			if(successCount>0){
				if(miscEntity.getSuppProcessedCount()==null){
					sum1 = successCount;
				}else{
					sum1 = miscEntity.getItemProcessedCount()+successCount;
				}
				 miscEntity.setItemProcessedCount((sum1));
				 totalCount=totalCount+successCount;
			}
			if(totalCount>0){
				int sum2 = miscEntity.getItemErrorCount()-totalCount;
				miscEntity.setItemErrorCount(sum2);
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
	
	public void updateStatusCountEntity(MiscDataEntity entity) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		
		TableOperation insert = TableOperation.insertOrMerge(entity);
		cloudTable.execute(insert);
	}
	public MiscDataEntity getStatusCountEntity(String partitionKey, String rowKey) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
	    TableOperation entity =   TableOperation.retrieve(partitionKey, rowKey, MiscDataEntity.class);
		return cloudTable.execute(entity).getResultAsType();
	}
}
