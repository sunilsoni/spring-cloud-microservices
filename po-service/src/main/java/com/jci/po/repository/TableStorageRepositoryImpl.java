package com.jci.po.repository;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jci.po.azure.AzureStorage;
import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.DataUtil;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.PaginationParam;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.request.BatchInsertRequest;
import com.jci.po.dto.response.BatchInsertResponse;
import com.jci.po.entity.ItemEntity;
import com.jci.po.entity.MiscDataEntity;
import com.jci.po.entity.PoEntity;
import com.jci.po.entity.PoItemsEntity;
import com.jci.po.entity.SupplierEntity;
import com.jci.po.utils.AzureUtils;
import com.jci.po.utils.Constants;
import com.jci.po.utils.ItemModelData;
import com.jci.po.utils.PoModelData;
import com.jci.po.utils.QueryBuilder;
import com.jci.po.utils.SupplierModelData;
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
public class TableStorageRepositoryImpl implements TableStorageRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(TableStorageRepositoryImpl.class);
	private static int errorCount;
	private static int successCount;
	private static int intransitCount;
	static int counter=0;
	final int batchSize = 100;
	
	@Autowired
	private AzureStorage azureStorage;

	//Final
	public void createTable(String tableName) throws InvalidKeyException, StorageException, URISyntaxException {
	    if (azureStorage.getTable(tableName).createIfNotExists()) {
	    	LOG.info("table is created : " + tableName);
	    }
	}
	//Final
	public HashMap<String, ArrayList<Integer>> getGraphData() throws InvalidKeyException, URISyntaxException, StorageException {
		TableQuery<MiscDataEntity> partitionQuery =  TableQuery.from(MiscDataEntity.class).where(QueryBuilder.partitionWhereCondition(Constants.PARTITION_KEY_MISCDATA,null,null));
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
	
	//Final
	public List<HashMap<String, String>> getErrorData(String partitionKey) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("#### Ending TableStorageRepositoryImpl.getErrorData ###" );
		List<HashMap<String, String>> errorData = new ArrayList<HashMap<String, String>>();
		TableQuery<PoEntity> partitionQuery =  TableQuery.from(PoEntity.class).where(QueryBuilder.errorDataQuery(partitionKey));
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
	    for (PoEntity entity : cloudTable.execute(partitionQuery)) {
	    	HashMap<String, String> map = new HashMap<String, String>();
	    	map.put("Status",String.valueOf(entity.getStatus()));
	    	map.put("Description",String.valueOf(entity.getDescription()));
	    	map.put("OrderNumber",String.valueOf(entity.getOrderNumber()));
	    	map.put("SourceErpName",String.valueOf(entity.getSourceErpName()));
	    	errorData.add(map);
	   }
	    LOG.info("#### Ending TableStorageRepositoryImpl.getErrorData ###" );
		 return errorData;
	}
		
	
	
	@Override
	@Transactional
	public void insertPo(PoEntity poEntity) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting TableStorageRepositoryImpl.insertPo ###" );
		//createTable(Constants.TABLE_PO_DETAILS);
	   // azureStorage.getTable(Constants.TABLE_PO_DETAILS).execute(TableOperation.insert(poEntity));
	    
	    //inserting dummy data:
	    String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SYMIX);
		
		//Inserting dummy data
		poEntity = null;
		
		LOG.info(" Starting insert PoItemsEntity PoEntity ");
		for (int i=3712510;i<3713600;i++){
			PoItemsEntity  poItemsEntity = new PoItemsEntity(partitionKey, i+"");
			poItemsEntity = PoModelData.getDummyData(poItemsEntity)	;
			
			poItemsEntity.setOrderNumber(i+"");
			azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS).execute(TableOperation.insertOrReplace(poItemsEntity));
			
			//need to save above data
			poEntity = new PoEntity(partitionKey, i+"");
			poEntity.setDescription("(GE45RC375060)3/8x3/8x6  OAL: RH .060");
			poEntity.setOrderCreationDate(new Date());
			poEntity.setOrderNumber(i+"");
			
			poEntity.setSourceErpName(1);
			
			
			if(i%2==0){
				poEntity.setStatus(Constants.STATUS_IN_TRANSIT);
				intransitCount = intransitCount+1;
			}else if(i%3==0){
				poEntity.setStatus(Constants.STATUS_SUCCESS);
				successCount = successCount+1;
			}else{
				poEntity.setStatus(Constants.STATUS_ERROR);
				errorCount = errorCount+1;
			}
			
			azureStorage.getTable(Constants.TABLE_PO_DETAILS).execute(TableOperation.insertOrReplace(poEntity));
		}
		LOG.info(" Ending insert PoItemsEntity PoEntity ");
		
		LOG.info(" Starting  insert ItemEntity SupplierEntity ");
		for (int i=430000;i<431000;i++){
			ItemEntity entity1 = new ItemEntity();
			entity1 = ItemModelData.getDummyData(entity1);
			entity1.setRowKey(i+"");
			entity1.setSupplierId(i+"");
			
		    azureStorage.getTable(Constants.TABLE_ITEM).execute(TableOperation.insertOrReplace(entity1));
		   
		    SupplierEntity entity2 = new SupplierEntity();
		    entity2 = SupplierModelData.getDummyData(entity2);
		    entity2.setRowKey(i+"");
		    entity1.setSupplierId(i+"");
		    
		    azureStorage.getTable(Constants.TABLE_SUPPLIER).execute(TableOperation.insertOrReplace(entity2));
		}
		LOG.info(" Ending  insert ItemEntity SupplierEntity ");
		
		LOG.info(" Starting  insert MiscDataEntity ");
		
		
		  
		//make this dynamic
		MiscDataEntity miscEntity = getStatusCountEntity(Constants.PARTITION_KEY_MISCDATA,Constants.ROW_KEY_SYMIX_MISCDATA);
		miscEntity.setIntransitCount(( miscEntity.getIntransitCount()+intransitCount));
		miscEntity.setProcessedCount(( miscEntity.getIntransitCount()+successCount));
		miscEntity.setErrorCount(( miscEntity.getIntransitCount()+errorCount));
		
		updateStatusCountEntity(miscEntity);
		LOG.info(" Ending  insert MiscDataEntity ");
		//LOG.info("### Ending TableStorageRepositoryImpl.insertPo ###" );
	}

	@Override
	public List<PoEntity> selectPos() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting TableStorageRepositoryImpl.selectPos ###" );
		ScrollingParam param = new ScrollingParam();
		//list(param,null);
		
		//TableQuery<PoEntity>  query = TableQuery.from(PoEntity.class);
		
		//Iterable<PoEntity> entities = azureStorage.getTable(Constants.TABLE_PO_DETAILS).execute(query);
		
		//LOG.info("entities--->"+entities );
	    //return StreamSupport.stream(entities.spliterator(), false).collect(Collectors.toList());
		 return null;
	}

	@Override
	public List<PoEntity> getPoItemsByPoNo(String orderNumber) throws InvalidKeyException, URISyntaxException, StorageException {
	//	TableQuery<PoEntity>  query = TableQuery.from(PoEntity.class);
	//	String partitionFilter = TableQuery.generateFilterCondition(TableConstants.PARTITION_KEY,QueryComparisons.EQUAL,member.getPartitionKey());
		//TableQuery<PoEntity> partitionQuery =  TableQuery.from(Constants.TABLE_PO_DETAILS, PoEntity.class).where(partitionFilter);
		return null;
	}
	
	@Override
	public PoEntity getPoDetailsByPoNo(String partitionKey, String rowKey) throws InvalidKeyException, URISyntaxException, StorageException {
		// Create a cloud table object for the table.
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
	    TableOperation entity =   TableOperation.retrieve(partitionKey, rowKey, PoEntity.class);
		return cloudTable.execute(entity).getResultAsType();
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
	@Transactional
	public void insertPo(List<PoEntity> poEntityList) throws InvalidKeyException, URISyntaxException, StorageException {
		// Create a cloud table object for the table.
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
		// Define a batch operation.
	    TableBatchOperation batchOperation = new TableBatchOperation();
		poEntityList.forEach(poEntity -> batchOperation.insertOrReplace(poEntity));
		cloudTable.execute(batchOperation);
		
		PoEntity entity = poEntityList.get(0);
		
		MiscDataEntity miscEntity = getStatusCountEntity(entity.getPartitionKey(),Constants.ROW_KEY_SYMIX_MISCDATA);
		miscEntity.setIntransitCount(( miscEntity.getIntransitCount()+poEntityList.size()));
		updateStatusCountEntity(miscEntity);
	}
	
	

   public ResultSet list(ScrollingParam param,DataHelper request) {
	   LOG.info("### Starting TableStorageRepositoryImpl.list ###" );
	   	ResultSet result=null;
		 
		request = new DataHelper();
		request.setPartitionValue("SYMIX_PO_2016");
		request.setStatus(1);
		//request.setStorageConnectionString(storageConnectionString);
		request.setTableName(Constants.TABLE_PO_DETAILS);
		request.setTimestamp(new Date());
		
		try {
			 result = getSegmentedResultSet(param,request);
			 LOG.info("result--->"+result);
			 LOG.info("param---1 "+param);
			 
			 PaginationParam pagination = result.getPagination();
			 LOG.info("pagination---1 "+pagination);
			 
			 param.setPartition( pagination.getNextPartition());
			 param.setRow(pagination.getNextRow());
			 
			 LOG.info("param---2 "+param);
			 
			 
			 result = getSegmentedResultSet(param,request);
			 LOG.info("result---2"+result);
			 pagination = result.getPagination();
			 LOG.info("pagination---2 "+pagination);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOG.info("### Ending TableStorageRepositoryImpl.list ###" +result);
		return result ;
	}
	
	//Final 
   @Override
   @Transactional
	public ResultSet getSegmentedResultSet(ScrollingParam param,DataHelper request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("#### Starting TableStorageRepositoryImpl.getSegmentedResultSet ###" );
		ResultContinuation continuationToken = DataUtil.getContinuationToken(param);
		PaginationParam pagination = new PaginationParam();
		if(continuationToken != null) {
			pagination.setLastPartition(param.getPartition());
			pagination.setLastRow(param.getRow());
		}
		 
		// Create the query
		String whereCondition = QueryBuilder.partitionWhereCondition(request.getPartitionValue(), param.getStartRowKey(), param.getEndRowKey());
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
			for (String key : map.keySet()) {
				ep = map.get(key);
				hashmap.put(key, ep.getValueAsString());
			}
			series.add(hashmap);
		}
		
		LOG.info("#### Ending TableStorageRepositoryImpl.getSegmentedResultSet ###" );
		return new ResultSet(series,getErrorData(request.getPartitionValue()),getGraphData(),pagination) ;
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
			    		/*
			    		//Sunil:delete this code start
			    		if(((PoEntity) entity).getStatus()==1){
			    			intransitCount = intransitCount+1;
			    		}else if(((PoEntity) entity).getStatus()==2){
			    			successCount = successCount+1;
			    		}else{
			    			errorCount = errorCount+1;
			    		}*/
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
	
}
