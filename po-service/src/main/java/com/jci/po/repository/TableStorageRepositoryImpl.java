package com.jci.po.repository;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.cypher.internal.compiler.v2_2.ast.rewriters.getDegreeOptimizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jci.po.azure.AzureStorage;
import com.jci.po.azure.data.AzureRequest;
import com.jci.po.azure.data.DataUtil;
import com.jci.po.azure.query.PaginationParam;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.response.ResultSet;
import com.jci.po.entity.ItemEntity;
import com.jci.po.entity.PoEntity;
import com.jci.po.entity.PoItemsEntity;
import com.jci.po.entity.SupplierEntity;
import com.jci.po.utils.AzureUtils;
import com.jci.po.utils.Constants;
import com.jci.po.utils.ItemModelData;
import com.jci.po.utils.PoModelData;
import com.jci.po.utils.QueryBuilder;
import com.jci.po.utils.SupplierModelData;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultSegment;
import com.microsoft.azure.storage.StorageCredentials;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.Operators;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;


@Repository
public class TableStorageRepositoryImpl implements TableStorageRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(TableStorageRepositoryImpl.class);
	
	@Autowired
	private AzureStorage azureStorage;

   public void createTable(String tableName) throws InvalidKeyException, StorageException, URISyntaxException {
	    if (azureStorage.getTable(tableName).createIfNotExists()) {
	    	LOG.info("table is created : " + tableName);
	    }
	}

	@Override
	public void insertPo(PoEntity poEntity) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting TableStorageRepositoryImpl.insertPo ###" );
		//createTable(Constants.TABLE_PO_DETAILS);
	   // azureStorage.getTable(Constants.TABLE_PO_DETAILS).execute(TableOperation.insert(poEntity));
	    
	    //inserting dummy data:
	    String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SYMIX);
		
		//Inserting dummy data
		poEntity = null;
		for (int i=3712510;i<3713600;i++){
			PoItemsEntity  poItemsEntity = new PoItemsEntity(partitionKey, i+"");
			poItemsEntity = PoModelData.getDummyData(poItemsEntity)	;
			
			poItemsEntity.setOrderNumber(i+"");
			azureStorage.getTable(Constants.TABLE_PO_ITEM_DETAILS).execute(TableOperation.insert(poItemsEntity));
			
			
			//need to save above data
			poEntity = new PoEntity(partitionKey, i+"");
			poEntity.setDescription("(GE45RC375060)3/8x3/8x6  OAL: RH .060");
			poEntity.setOrderCreationDate(new Date());
			poEntity.setOrderNumber(i+"");
			
			poEntity.setSourceErpName(1);;
			poEntity.setStatus(1);
			azureStorage.getTable(Constants.TABLE_PO_DETAILS).execute(TableOperation.insert(poEntity));
		}
		
		
		for (int i=3712510;i<3713600;i++){
			ItemEntity entity1 = new ItemEntity();
			entity1 = ItemModelData.getDummyData(entity1);
			entity1.setRowKey(i+"");
		    azureStorage.getTable(Constants.TABLE_ITEM).execute(TableOperation.insert(entity1));
		   
		    SupplierEntity entity2 = new SupplierEntity();
		    entity2 = SupplierModelData.getDummyData(entity2);
		    entity2.setRowKey(i+"");
		    azureStorage.getTable(Constants.TABLE_SUPPLIER).execute(TableOperation.insert(entity2));
		    
		}
		
	    
	    
		//LOG.info("### Ending TableStorageRepositoryImpl.insertPo ###" );
	}

	@Override
	public List<PoEntity> selectPos() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting TableStorageRepositoryImpl.selectPos ###" );
		ScrollingParam param = new ScrollingParam();
		list(param,null);
		
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
	    TableOperation retrieveSmithJeff =   TableOperation.retrieve(partitionKey, rowKey, PoEntity.class);
		return cloudTable.execute(retrieveSmithJeff).getResultAsType();
	}
	  


	@Override
	public void insertPo(List<PoEntity> poEntityList) throws InvalidKeyException, URISyntaxException, StorageException {
		// Create a cloud table object for the table.
		CloudTable cloudTable = azureStorage.getTable(Constants.TABLE_PO_DETAILS);
		// Define a batch operation.
	    TableBatchOperation batchOperation = new TableBatchOperation();
		poEntityList.forEach(poEntity -> batchOperation.insertOrReplace(poEntity));
		cloudTable.execute(batchOperation);
	}

	
   public ResultSet list(ScrollingParam param,AzureRequest request) {
	   LOG.info("### Starting TableStorageRepositoryImpl.list ###" );
	   	ResultSet result=null;
		 
		request = new AzureRequest();
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
	
   @Override
	public ResultSet getSegmentedResultSet(ScrollingParam param,AzureRequest request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("#### Starting TableStorageRepositoryImpl.getSegmentedResultSet ###" );
		
		 LOG.info("request 1--- "+request);
		 LOG.info("param 1--- "+param);
		 
		ResultContinuation continuationToken = DataUtil.getContinuationToken(param);
		 LOG.info("continuationToken 1--- "+continuationToken);
		 
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
		 LOG.info("query--->"+query.getFilterString());
		 
	     //  TableQuery<DynamicTableEntity> query = TableQuery.from(DynamicTableEntity.class).where(combinedFilter);
			//ResultSegment<DynamicTableEntity> entities = table.executeSegmented(query, null);
		  CloudTable table = azureStorage.getTable(request.getTableName());
       
		// segmented query
		//ResultSegment<DynamicTableEntity> response = table.executeSegmented(myQuery, continuationToken) ;
       ResultSegment<DynamicTableEntity> response = table.executeSegmented(query, continuationToken) ;
		  
		//  ResultSegment<DynamicTableEntity> response = table.executeSegmented(new TableQuery<DynamicTableEntity>(), continuationToken) ;
       
       
		 LOG.info("response.getLength() --- "+response.getLength());
		 LOG.info("response.getHasMoreResults() --- "+response.getHasMoreResults());
		 LOG.info("response.getIsPageComplete() --- "+response.getIsPageComplete());
		 LOG.info("response.getPageSize() --- "+response.getPageSize());
		 LOG.info("response.getRemainingPageResults() --- "+response.getRemainingPageResults());
		 LOG.info("response.getLength() --- "+response.getRemainingPageResults());
		 
		 
		// next continuation token
		continuationToken = response.getContinuationToken() ;
		LOG.info("continuationToken 2--- "+continuationToken);
		 
		
		if(continuationToken != null) {
			pagination.setNextPartition(continuationToken.getNextPartitionKey());
			pagination.setNextRow(continuationToken.getNextRowKey());
		}
		
		HashMap<String, Object> datum;
		
		List<HashMap<String, Object>> series = new ArrayList<HashMap<String, Object>>();
		DynamicTableEntity row;
		EntityProperty ep;
		
		Iterator<DynamicTableEntity> rows = response.getResults().iterator() ;
		
		
		while(rows.hasNext()) {
			row = rows.next() ;
			HashMap<String, EntityProperty> map = row.getProperties();

			datum = new HashMap<String, Object>();
			for (String key : map.keySet()) {
				ep = map.get(key);
				datum.put(key, ep.getValueAsString());
			}
			series.add(datum);
		}
		LOG.info("series--->"+series);
		LOG.info("pagination--->"+pagination);
		
		LOG.info("#### Ending TableStorageRepositoryImpl.getSegmentedResultSet ###" );
		return new ResultSet(series,pagination) ;
}
   		
}
