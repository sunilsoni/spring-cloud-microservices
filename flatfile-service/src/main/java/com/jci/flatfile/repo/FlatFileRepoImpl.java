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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jci.config.AzureStorage;
import com.jci.entity.GrEntity;
import com.jci.entity.GrItemsEntity;
import com.jci.entity.ItemEntity;
import com.jci.entity.MiscDataEntity;
import com.jci.entity.PoEntity;
import com.jci.entity.PoItemsEntity;
import com.jci.entity.SuppEntity;
import com.jci.enums.ErrorEnum;
import com.jci.exception.ErrorService;
import com.jci.flatfile.exception.FlatFileException;
import com.jci.flatfile.utils.BatchUpdateReq;
import com.jci.flatfile.utils.FlatFileRes;
import com.jci.utils.Constants;
import com.jci.utils.QueryBuilder;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableEntity;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;


/**
 * The Class FlatFileRepoImpl.
 */
@Repository
public class FlatFileRepoImpl implements FlatFileRepo { // NO_UCD (unused code)

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(FlatFileRepoImpl.class);
    
    /** The batch size. */
    final int batchSize = 20;
    
    /** The azure storage. */
    @Autowired
    private AzureStorage azureStorage;
    
    /** The error service. */
    @Autowired
    private ErrorService errorService;
    
    /* (non-Javadoc)
     * @see com.jci.flatfile.repo.FlatFileRepo#getPoFlatFileData(java.lang.String, java.util.List)
     */
    @Override
    public FlatFileRes getPoFlatFileData(String partitionKey, List<String> poList)  {
    	LOG.info("### Starting in FlatFileRepoImpl.getPoFlatFileData ###"+poList);
    	String query = null;
    	if(poList!=null){
    		query = QueryBuilder.poQuery(partitionKey,poList);
    	}else{
    		query = QueryBuilder.ffQuery(partitionKey);
    	}
    	
    	TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
        Map<String,List<String>> pkToRowkeyList = new HashMap<>();
        CloudTable cloudTable = null;
        
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_PO_TABLE_NOT_FOUND);
		}
        
        Map<String, String> rowKeyToPlantMap = new  HashMap<>();
        Map<String, String> rowKeyToSupptypeMap  = new  HashMap<>();
        
        for (PoEntity entity : cloudTable.execute(partitionQuery)) {
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
        ArrayList<Map<String,List<HashMap<String, Object>>>> list = new ArrayList<>();
        for (Map.Entry<String,List<String>> entry : pkToRowkeyList.entrySet()){ 
            list.add(getPos(entry.getKey(),entry.getValue()));
        }
        LOG.info("### Ending in FlatFileRepoImpl.getPoFlatFileData ###");
        return new FlatFileRes(rowKeyToPlantMap,rowKeyToSupptypeMap,list);
       
    }
    
    
    /**
     * Gets the pos.
     *
     * @param partitionKey the partition key
     * @param poList the po list
     * @return the pos
     */
    private Map<String,List<HashMap<String, Object>>> getPos(String partitionKey, List<String> poList) {
        String query = QueryBuilder.poItemQuery(partitionKey,poList);
        CloudTable cloudTable=null;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS);
		} catch (InvalidKeyException | URISyntaxException | StorageException e1) {
			throw errorService.createException(FlatFileException.class, e1, ErrorEnum.ERROR_POITEM_TABLE_NOT_FOUND);
		}
		
        OperationContext opContext = new OperationContext();
        
        TableQuery<DynamicTableEntity> myQuery = TableQuery.from(DynamicTableEntity.class).where(query).take(1000);//Need to discuss this
        Iterator<DynamicTableEntity> rows = cloudTable.execute(myQuery, null, opContext).iterator();
        DynamicTableEntity row;
       // EntityProperty ep;
        HashMap<String, Object> hashmap;
        ObjectMapper mapper = new ObjectMapper(); 
        Map<String,List<HashMap<String, Object>>> poNumToItemListMap = new HashMap<>();
        TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
        
        while(rows.hasNext()) {
            row = rows.next() ;
            HashMap<String, EntityProperty> map = row.getProperties();
            hashmap = new HashMap<>();
            
            String jsonString = map.get(Constants.JSON_STRING).getValueAsString();
            if(!StringUtils.isBlank(jsonString)){
                try {
                    hashmap = mapper.readValue(jsonString, typeRef);
                } catch (IOException e) {
                    LOG.error("### Exception in   ####",e);                        
                } 
            }
            
            String poNum = map.get(Constants.ORDER_NUMBER).getValueAsString();
            if(poNumToItemListMap.containsKey(poNum)){
                List<HashMap<String, Object>> list =poNumToItemListMap.get(poNum);
                list.add(hashmap);
                poNumToItemListMap.put(poNum, list);
            }else{
                List<HashMap<String, Object>> list = new  ArrayList<>();
                list.add(hashmap);
                poNumToItemListMap.put(poNum, list);
            }
        }       
         return poNumToItemListMap;
    }

    /* (non-Javadoc)
     * @see com.jci.flatfile.repo.FlatFileRepo#getFlatFileData(java.lang.String, java.lang.String)
     */
    @Override
    public FlatFileRes getFlatFileData(String partitionKey, String tableName)  {
        String query = QueryBuilder.ffQuery(partitionKey);
        
        CloudTable cloudTable;
		try {
			cloudTable = azureStorage.getTable(tableName);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
		}
		
        OperationContext opContext = new OperationContext();
        
        TableQuery<DynamicTableEntity> myQuery = TableQuery.from(DynamicTableEntity.class).where(query).take(1000);
        Iterator<DynamicTableEntity> rows = cloudTable.execute(myQuery, null, opContext).iterator();
        DynamicTableEntity row;
        HashMap<String, Object> hashmap;
        ObjectMapper mapper = new ObjectMapper(); 
        
        List<HashMap<String, Object>> list  = new ArrayList<>();
        Map<String, String> rowKeyToPlantMap = new  HashMap<>();
        
        Map<String,List<HashMap<String, Object>>> plantToRowsMap  = new  HashMap<>();
        TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
        Map<String, String> plantToSupptypeMap = new HashMap<>();
        
        while(rows.hasNext()) {
            row = rows.next() ;
            
            HashMap<String, EntityProperty> map = row.getProperties();
            hashmap = new HashMap<>();
           
            String plant = map.get(Constants.PLANT).getValueAsString();
            if(plantToRowsMap.containsKey(plant)){
            	list = plantToRowsMap.get(plant);
            	try {
                    hashmap = mapper.readValue(map.get(Constants.JSON_STRING).getValueAsString(), typeRef);
                    list.add(hashmap);
                } catch (IOException e) {
                    LOG.error("### Exception in FlatFileRepoImpl.getFlatFileData  ####",e);
                }
            	plantToRowsMap.put(plant, list);
            }else{
            	  list  = new ArrayList<>();
            	  try {
                      hashmap = mapper.readValue(map.get(Constants.JSON_STRING).getValueAsString(), typeRef);
                      list.add(hashmap);
                  } catch (IOException e) {
                      LOG.error("### Exception in FlatFileRepoImpl.getFlatFileData  ####",e);
                  }
            	  plantToRowsMap.put(plant, list);
            	  plantToSupptypeMap.put(plant, map.get(Constants.SUPP_TYPE).getValueAsString());
            }            
            rowKeyToPlantMap.put(row.getRowKey(), plant);
        }     
        
        FlatFileRes res = new  FlatFileRes();
        res.setRowKeyToPlantMap(rowKeyToPlantMap);
        res.setPlantToRowsMap(plantToRowsMap);
        res.setPlantToSupptypeMap(plantToSupptypeMap);
        return res;
    }
    
    @Override
    public FlatFileRes  getCombinedData(String partitionKey,String tableName, List<String> rowKeyList)  {
    	String query = null;
    	if(rowKeyList!=null){
    		query = QueryBuilder.poQuery(partitionKey,rowKeyList); 
    	}else{
    		query = QueryBuilder.ffQuery(partitionKey);
    	}
    	
        
        CloudTable cloudTable;
		try {
			cloudTable = azureStorage.getTable(tableName);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
		}
		
        OperationContext opContext = new OperationContext();
        
        TableQuery<DynamicTableEntity> myQuery = TableQuery.from(DynamicTableEntity.class).where(query).take(1000);
        Iterator<DynamicTableEntity> rows = cloudTable.execute(myQuery, null, opContext).iterator();
        DynamicTableEntity row;
        HashMap<String, Object> newHashmap;
        ObjectMapper mapper = new ObjectMapper(); 
        
        List<Map<String,HashMap<String, Object>>> rowKeyToItemsMapList  = new ArrayList<>();
        
        Map<String,List<Map<String,HashMap<String, Object>>>> plantToRowsMap  = new  HashMap<>();
        TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
        Map<String, String> plantToSupptypeMap = new HashMap<>();
        
        Map<String, String> rowKeyToPkMap = new HashMap<>();
        Map<String, String> rowKeyToPlantMap = new  HashMap<>();
        while(rows.hasNext()) {
            row = rows.next() ;
            
            HashMap<String, EntityProperty> map = row.getProperties();
            newHashmap = new HashMap<>();
            Map<String,HashMap<String, Object>> rowKeyToItemsMap = new HashMap<>();
            
            String plant = map.get(Constants.PLANT).getValueAsString();
            rowKeyToPkMap.put(row.getRowKey(),row.getPartitionKey());
            
            if(plantToRowsMap.containsKey(plant)){
            	rowKeyToItemsMapList = plantToRowsMap.get(plant);
            	try {
            		newHashmap = mapper.readValue(map.get(Constants.JSON_STRING).getValueAsString(), typeRef);
            		rowKeyToItemsMap.put(row.getRowKey(), newHashmap);
            		
            		rowKeyToItemsMapList.add(rowKeyToItemsMap);
                } catch (IOException e) {
                    LOG.error("### Exception in FlatFileRepoImpl.getCombinedData  ####",e);
                }
            	plantToRowsMap.put(plant, rowKeyToItemsMapList);
            }else{
            	rowKeyToItemsMapList  = new ArrayList<>();
            	  try {
            		  newHashmap = mapper.readValue(map.get(Constants.JSON_STRING).getValueAsString(), typeRef);
            		  rowKeyToItemsMap.put(row.getRowKey(), newHashmap);
            		  rowKeyToItemsMapList.add(rowKeyToItemsMap);
                  } catch (IOException e) {
                      LOG.error("### Exception in FlatFileRepoImpl.getCombinedData  ####",e);
                  }
            	  plantToRowsMap.put(plant, rowKeyToItemsMapList);
            	  plantToSupptypeMap.put(plant, map.get(Constants.SUPP_TYPE).getValueAsString());
            }   
            rowKeyToPlantMap.put(row.getRowKey(), plant);
        }   
        
        Map<String,List<HashMap<String, Object>>> plantToRowsMapFinal  = new  HashMap<>();
        Map<String,HashMap<String, Object>> rowKeyToDetailsMap = null;
        
        rowKeyToDetailsMap = prepareData(rowKeyToPkMap,tableName);
        
        for (Map.Entry<String,List<Map<String,HashMap<String, Object>>>> rowKeyToItemsMapList1 : plantToRowsMap.entrySet()){	
        	String plant = rowKeyToItemsMapList1.getKey();
        	List<HashMap<String, Object>> finalList  = new ArrayList<>();
        	for (Map<String,HashMap<String, Object>> rowKeyToItemsMap1 : rowKeyToItemsMapList1.getValue()){
        		for (Map.Entry<String,HashMap<String, Object>> items : rowKeyToItemsMap1.entrySet()){
        			//String rowKey = items.getKey();
        			HashMap<String, Object> oldMap =  items.getValue();
        			oldMap.putAll(rowKeyToDetailsMap.get(items.getKey()));
        			finalList.add(oldMap);
        		}
        	}
        	plantToRowsMapFinal.put(plant, finalList);
    	}
        
        FlatFileRes res = new  FlatFileRes();
        res.setRowKeyToPlantMap(rowKeyToPlantMap);
        res.setPlantToRowsMap(plantToRowsMapFinal);
        res.setPlantToSupptypeMap(plantToSupptypeMap);
        if(rowKeyToDetailsMap!=null){
        	rowKeyToDetailsMap.clear();
        }
        return res;
    }
    
    
    /* (non-Javadoc)
     * @see com.jci.flatfile.repo.FlatFileRepo#batchUpdate(com.jci.flatfile.utils.BatchUpdateReq)
     */
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
					throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,entry.getKey());
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
			    		if(request.getGlobalId()!=null){
			    			en.setComment(request.getComment());
			    			en.setUserName(request.getUserName());
			    			en.setGlobalId(request.getGlobalId());
		            	}
			    		batchOperation.insertOrMerge(en);
			    	}else if(entity instanceof SuppEntity){
			    		SuppEntity en = (SuppEntity) entity;
			    		if(request.isSuccess()){
				    		en.setSupplierDeliveryState(Constants.STATUS_SUCCESS);
				    		successCount = successCount+1;
				    	}else{//Request is for error status update
				    		en.setSupplierDeliveryState(Constants.STATUS_ERROR);
				    		errorCount = errorCount+1;
				    	}
			    		if(request.getGlobalId()!=null){
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
			    		if(request.getGlobalId()!=null){
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
			    		if(request.getGlobalId()!=null){
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
			    if(successCount>0 || errorCount>0){
			    	updateMiscEntity(request.getErpName(),entry.getKey(),successCount,errorCount,request.isErrorReq());
			    }
		 }	
		 LOG.info("### Ending in FlatFileRepoImpl.batchUpdate ###");
	} 
    
    /** The po final. */
    List<Object> poFinal = null;
	
	/* (non-Javadoc)
	 * @see com.jci.flatfile.repo.FlatFileRepo#getPoDetails(java.lang.String, java.util.List, java.lang.String)
	 */
	@Override
	public List<Object> getPoDetails(String partitionKey, List<String> poList,String tableName) {
		poFinal = new ArrayList<>();
		preparePoDetails( partitionKey,  poList,tableName); 
		return poFinal;
	}
	
	/**
	 * Prepare po details.
	 *
	 * @param partitionKey the partition key
	 * @param poList the po list
	 * @param tableName the table name
	 */
	private void preparePoDetails(String partitionKey, List<String> poList,String tableName){
		CloudTable cloudTable=null;
		if(poList.size()>batchSize){
			String query = QueryBuilder.processPosQuery(partitionKey, poList.subList(0, batchSize));
			
			if(Constants.TABLE_PO_DETAILS.equals(tableName)){
				TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
				
				try {
					cloudTable = azureStorage.getTable(tableName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
				}
			    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_GR_DETAILS.equals(tableName)){
				TableQuery<GrEntity> partitionQuery =  TableQuery.from(GrEntity.class).where(query);
				
				try {
					cloudTable = azureStorage.getTable(tableName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
				}
			    for (GrEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_ITEM.equals(tableName)){
				TableQuery<ItemEntity> partitionQuery =  TableQuery.from(ItemEntity.class).where(query);
				
				try {
					cloudTable = azureStorage.getTable(tableName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
				}
			    for (ItemEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_SUPPLIER.equals(tableName)){
				TableQuery<SuppEntity> partitionQuery =  TableQuery.from(SuppEntity.class).where(query);
				try {
					cloudTable = azureStorage.getTable(tableName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
				}
			    for (SuppEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}
		    preparePoDetails(partitionKey,poList.subList(batchSize,poList.size()),tableName);
		}else if(poList.size()>0){
			String query = QueryBuilder.processPosQuery(partitionKey,poList);
		    if(Constants.TABLE_PO_DETAILS.equals(tableName)){
				TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(query);
				try {
					cloudTable = azureStorage.getTable(tableName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
				}
			    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_GR_DETAILS.equals(tableName)){
				TableQuery<GrEntity> partitionQuery =  TableQuery.from(GrEntity.class).where(query);
				try {
					cloudTable = azureStorage.getTable(tableName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
				}
			    for (GrEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_ITEM.equals(tableName)){
				TableQuery<ItemEntity> partitionQuery =  TableQuery.from(ItemEntity.class).where(query);
				try {
					cloudTable = azureStorage.getTable(tableName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
				}
			    for (ItemEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}else if(Constants.TABLE_SUPPLIER.equals(tableName)){
				TableQuery<SuppEntity> partitionQuery =  TableQuery.from(SuppEntity.class).where(query);
				try {
					cloudTable = azureStorage.getTable(tableName);
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_TABLE_NOT_FOUND,tableName);
				}
			    for (SuppEntity entity : cloudTable.execute(partitionQuery)) {
			    	poFinal.add(entity);
			    }
			}
		}
	}
	
	/**
	 * Update misc entity.
	 *
	 * @param erpName the erp name
	 * @param tableName the table name
	 * @param successCount the success count
	 * @param errorCount the error count
	 * @param isErrorReq the is error req
	 */
	private synchronized void updateMiscEntity(String erpName,String tableName,int successCount,int errorCount,boolean isErrorReq){
		MiscDataEntity miscEntity=null;
		
		try {
			miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		} catch (StorageException e1) {
			LOG.error("### Exception in  updateMiscEntity  ####",e1);
		}
		
		if(miscEntity==null){
			miscEntity = new MiscDataEntity(Constants.PARTITION_KEY_MISCDATA,erpName);
		}
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
				int sum2 = 0;
				if(isErrorReq){
					sum2 = miscEntity.getPoErrorCount()-totalCount;
					miscEntity.setPoErrorCount(sum2);
				}else{
					sum2 = miscEntity.getPoIntransitCount()-totalCount;
					miscEntity.setPoIntransitCount(sum2);
				}
			}
		}else if (Constants.TABLE_GR_DETAILS.equals(tableName)){
			if(errorCount>0){
				if( miscEntity.getGrErrorCount()==null){
					sum1 = errorCount;
				}else{
					sum1 = miscEntity.getGrErrorCount()+errorCount;
				}
				
				miscEntity.setGrErrorCount((sum1));
				totalCount=errorCount;
			}
			if(successCount>0){
				
				if(miscEntity.getGrProcessedCount()==null){
					sum1 = successCount;
				}else{
					sum1 = miscEntity.getGrProcessedCount()+successCount;
				}
				miscEntity.setGrProcessedCount((sum1));
				totalCount=totalCount+successCount;
			}
			if(totalCount>0){
				int sum2 = 0;
				if(isErrorReq){
					sum2 = miscEntity.getGrErrorCount()-totalCount;
					miscEntity.setGrErrorCount(sum2);
				}else{
					sum2 = miscEntity.getGrIntransitCount()-totalCount;
					miscEntity.setGrIntransitCount(sum2);
				}
			}
		
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
				int sum2 = 0;
				if(isErrorReq){
					sum2 = miscEntity.getSuppErrorCount()-totalCount;
					miscEntity.setSuppErrorCount(sum2);
				}else{
					sum2 = miscEntity.getSuppIntransitCount()-totalCount;
					miscEntity.setSuppIntransitCount(sum2);
				}
				
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
				if(miscEntity.getItemProcessedCount()==null){
					sum1 = successCount;
				}else{
					sum1 = miscEntity.getItemProcessedCount()+successCount;
				}
				 miscEntity.setItemProcessedCount((sum1));
				 totalCount=totalCount+successCount;
			}
			if(totalCount>0){
				int sum2 = 0;
				if(isErrorReq){
					sum2 = miscEntity.getItemErrorCount()-totalCount;
					miscEntity.setItemErrorCount(sum2);
				}else{
					sum2 = miscEntity.getItemIntransitCount()-totalCount;
					miscEntity.setItemIntransitCount(sum2);
				}
			}
		}
		 
		if(totalCount>0){
		  updateStatusCountEntity(miscEntity);
		}
	}
	
	/**
	 * Update status count entity.
	 *
	 * @param entity the entity
	 */
	public void updateStatusCountEntity(MiscDataEntity entity) {
		CloudTable cloudTable=null;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_MISCDATA_TABLE_NOT_FOUND);
		}
		
		TableOperation insert = TableOperation.insertOrMerge(entity);
		try {
			cloudTable.execute(insert);
		} catch (StorageException e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_MISCDATA_INSERT_DATA);
		}
	}
	
	/**
	 * Gets the status count entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 * @return the status count entity
	 * @throws StorageException the storage exception
	 */
	public MiscDataEntity getStatusCountEntity(String partitionKey, String rowKey) throws StorageException {
		CloudTable cloudTable=null;
		try {
			cloudTable = azureStorage.getTable(Constants.TABLE_MISC);
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_MISCDATA_TABLE_NOT_FOUND);
		}
	    TableOperation entity =   TableOperation.retrieve(partitionKey, rowKey, MiscDataEntity.class);
		return cloudTable.execute(entity).getResultAsType();
	}
	
	Map<String,HashMap<String, Object>> rowKeyToDetailsMap = null;
	private Map<String,HashMap<String, Object>> prepareData(Map<String, String> rowKeyToPkMap,String tableName) {
		rowKeyToDetailsMap = new HashMap<>();
		if(Constants.TABLE_PO_DETAILS.equals(tableName)){
			preparePoDetails(rowKeyToPkMap); 
		}else if(Constants.TABLE_GR_DETAILS.equals(tableName)){
			prepareGrDetails(rowKeyToPkMap); 
		}
		
		return rowKeyToDetailsMap;
	}
	
	private void prepareGrDetails(Map<String, String> rowKeyToPkMap){
		CloudTable cloudTable = null;
		ObjectMapper mapper = new ObjectMapper(); 
		HashMap<String, Object> newHashmap;
		TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
		 LOG.error("rowKeyToPkMap-->"+rowKeyToPkMap);
		 LOG.error("size-->"+rowKeyToPkMap.size());
		 
		if(rowKeyToPkMap.size()>batchSize){
			String query = QueryBuilder.getCombinedDataQuery(rowKeyToPkMap);
			 LOG.error("query-->"+query);
		    TableQuery<GrItemsEntity> poQuery =  TableQuery.from(GrItemsEntity.class).where(query);
		    LOG.error("poQuery-->"+poQuery.getFilterString());
		    try {
				cloudTable = azureStorage.getTable(Constants.TABLE_GR_ITEM_DETAILS);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_GR_ITEM_TABLE_NOT_FOUND);
			}
		    
		    for (GrItemsEntity entity : cloudTable.execute(poQuery)) {
		    	try {
					newHashmap = mapper.readValue(entity.getJsonString(), typeRef);
					rowKeyToDetailsMap.put(entity.getRowKey(), newHashmap);
				} catch (IOException e) {
					LOG.error("### Exception in FlatFileRepoImpl.prepareGrDetails ###"+e);
				}
		    }
		    prepareGrDetails(rowKeyToPkMap);
		}else if(rowKeyToPkMap.size()>0){
			String query = QueryBuilder.getCombinedDataQuery(rowKeyToPkMap);
		    TableQuery<GrItemsEntity> poQuery =  TableQuery.from(GrItemsEntity.class).where(query);
		    try {
				cloudTable = azureStorage.getTable(Constants.TABLE_GR_ITEM_DETAILS);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_GR_ITEM_TABLE_NOT_FOUND);
			}
		    
		    for (GrItemsEntity entity : cloudTable.execute(poQuery)) {
		    	try {
					newHashmap = mapper.readValue(entity.getJsonString(), typeRef);
					rowKeyToDetailsMap.put(entity.getRowKey(), newHashmap);
				} catch (IOException e) {
					LOG.error("### Exception in FlatFileRepoImpl.prepareGrDetails ###"+e);
				}
		    	
		    }
		}
	}
	
	private void preparePoDetails(Map<String, String> rowKeyToPkMap){
		CloudTable cloudTable = null;
		ObjectMapper mapper = new ObjectMapper(); 
		HashMap<String, Object> newHashmap;
		TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
		 
		if(rowKeyToPkMap.size()>batchSize){
			String query = QueryBuilder.getCombinedDataQuery(rowKeyToPkMap);
			
		    TableQuery<PoItemsEntity> poQuery =  TableQuery.from(PoItemsEntity.class).where(query);
		    try {
				cloudTable = azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_POITEM_TABLE_NOT_FOUND);
			}
		    
		    for (PoItemsEntity entity : cloudTable.execute(poQuery)) {
		    	try {
					newHashmap = mapper.readValue(entity.getJsonString(), typeRef);
					rowKeyToDetailsMap.put(entity.getRowKey(), newHashmap);
				} catch (IOException e) {
					LOG.error("### Exception in FlatFileRepoImpl.preparePoDetails ###"+e);
				}
		    }
		    preparePoDetails(rowKeyToPkMap);
		}else if(rowKeyToPkMap.size()>0){
			String query = QueryBuilder.getCombinedDataQuery(rowKeyToPkMap);
		    TableQuery<PoItemsEntity> poQuery =  TableQuery.from(PoItemsEntity.class).where(query);
		    try {
				cloudTable = azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS);
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_POITEM_TABLE_NOT_FOUND);
			}
		    
		    for (PoItemsEntity entity : cloudTable.execute(poQuery)) {
		    	try {
					newHashmap = mapper.readValue(entity.getJsonString(), typeRef);
					rowKeyToDetailsMap.put(entity.getRowKey(), newHashmap);
				} catch (IOException e) {
					LOG.error("### Exception in FlatFileRepoImpl.preparePoDetails ###"+e);
				}
		    	
		    }
		}
	}
}
