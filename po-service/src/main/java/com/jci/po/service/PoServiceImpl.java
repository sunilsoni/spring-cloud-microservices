package com.jci.po.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.PaginationParam;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.request.BatchInsertRequest;
import com.jci.po.dto.request.PoDetailsRequest;
import com.jci.po.dto.request.SegmentedDetailRequest;
import com.jci.po.dto.response.BatchInsertResponse;
import com.jci.po.dto.response.PoDetails;
import com.jci.po.dto.response.PoDetailsResponse;
import com.jci.po.dto.response.SegmentedDetailResponse;
import com.jci.po.entity.ItemEntity;
import com.jci.po.entity.PoEntity;
import com.jci.po.entity.PoItemsEntity;
import com.jci.po.entity.SupplierEntity;
import com.jci.po.repository.TableStorageRepository;
import com.jci.po.utils.AzureUtils;
import com.jci.po.utils.Constants;
import com.jci.po.utils.ItemModelData;
import com.jci.po.utils.PoModelData;
import com.jci.po.utils.SupplierModelData;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableEntity;

@Service
public class PoServiceImpl implements PoService{

	private static final Logger LOG = LoggerFactory.getLogger(PoServiceImpl.class);
	
	@Autowired
	private TableStorageRepository repo;
	
	 
	@Override
	public void addPo(PoEntity poEntity) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.addPo ### " + poEntity);
		
		repo.insertPo(poEntity);
		
		LOG.info("### Ending PoServiceImpl.addPo ### " + poEntity);
	}

	@Override
	public PoDetailsResponse getPos(PoDetailsRequest request) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting Entity PoServiceImpl.getPos ### "+request );
		
		List<PoEntity> pos = repo.selectPos();
		
		/*List<String> pos = repo.selectPos().stream()
		        .sorted(Comparator.comparing(PoEntity::getTimestamp).reversed())
		        .limit(20).map(PoEntity::getOrderNumber).collect(Collectors.toList());
		
		LOG.info("pos-->"+pos);*/
		
		PoDetailsResponse response = new PoDetailsResponse();
		
		if(true){
			return response;
		}
		
		List<PoDetails> list = new ArrayList<PoDetails>();
		
		for (PoEntity po : pos) {
			PoDetails details = new PoDetails();  
			details.setDescription(po.getDescription());
			details.setKey(po.getPartitionKey());
			details.setPoNo(po.getOrderNumber());
			details.setSourceErp(po.getSourceErpName()+"");
			details.setStatus(po.getStatus());
			list.add(details);
		}
		response.setPoDetails(list);
		LOG.info("### Ending Entity PoServiceImpl.getPos ### "+request );
		return response;
	}

	@Override
	public int getPoSize() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.getPoSize ### " );

		
		LOG.info("### Ending PoServiceImpl.getPoSize ### " );
		return 0;
	}

	 @Override
	 public String getLastPo() throws InvalidKeyException, URISyntaxException, StorageException {
		 LOG.info("### Starting Ending PoServiceImpl.getLastPo ### " );
		 
		 return repo.selectPos().stream()
        .sorted(Comparator.comparing(PoEntity::getTimestamp).reversed())
        .map(PoEntity::getOrderNumber).findFirst().orElseThrow(() -> new NullPointerException("No Data"));
	 }

	@Override
	public List<PoEntity> getPos(int size) throws InvalidKeyException, URISyntaxException, StorageException {
		List<PoEntity> pos = repo.selectPos().stream()
			        .sorted(Comparator.comparing(PoEntity::getTimestamp).reversed())
			        .limit(20)
			        .collect(Collectors.toList());
		
			    /*if (pos.size() < size) {
			      int supplementSize = size - pos.size();
			      List<Integer> pos1 = IntStream.range(0, supplementSize)
			          .boxed()
			          .map(i -> 0)
			          .collect(Collectors.toList());
			      pos1.addAll(pos);
			      return pos1;
			    }
			    return pos;*/
		return pos;
	}

	@Override
	public PoEntity getPoDetailsByPoNo(String partitionKey, String rowKey)
			throws InvalidKeyException, URISyntaxException, StorageException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public SegmentedDetailResponse getSegmentedResultSet(SegmentedDetailRequest request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("### Starting Ending PoServiceImpl.getSegmentedResultSet ### " );
		PaginationParam paginationParam = request.getPaginationParam();
		
		ScrollingParam param  = new ScrollingParam();
		
		if(paginationParam!=null){
			param.setPartition(paginationParam.getNextPartition());
			param.setRow(paginationParam.getNextRow());
		}
		
		//For where condition
		param.setStartRowKey(request.getStartRowKey());
		param.setEndRowKey(request.getEndRowKey());
		param.setSize(request.getSize());
		
		DataHelper azureRequest = new DataHelper();
		azureRequest.setErpName(request.getErpName());
		azureRequest.setPartitionValue(request.getPartition());
		azureRequest.setTableName(request.getTableName());
		//azureRequest.setTimestamp(timestamp);
		ResultSet resultSet = repo.getSegmentedResultSet(param, azureRequest);
		
		SegmentedDetailResponse response = new SegmentedDetailResponse(); 
		response.setResultSet(resultSet);
		response.setMessage(Constants.JSON_OK);
		
		LOG.info("### Ending Ending PoServiceImpl.getSegmentedResultSet ### " );
		
		return response;
	}
	
	public void insertSymixDummyData() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.insertSymixDummyData ###" );
		BatchInsertRequest request  = new BatchInsertRequest();

	    String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SYMIX);
		
		//Inserting dummy data
	    PoEntity poEntity = null;
	    HashMap<String, List<TableEntity>> tableNameToEntityMap = new HashMap<String, List<TableEntity>>();
	    List<TableEntity> tableList1 = new ArrayList<TableEntity>();
	    List<TableEntity> tableList2 = new ArrayList<TableEntity>();
	   
	    
		for (int i=3713511;i<3714013;i++){
			PoItemsEntity  poItemsEntity = new PoItemsEntity(partitionKey, i+"");
			poItemsEntity = PoModelData.getDummyData(poItemsEntity)	;
			
			poItemsEntity.setOrderNumber(i+"");

			
			
			//need to save above data
			poEntity = new PoEntity(partitionKey, i+"");
			poEntity.setDescription("(GE45RC375060)3/8x3/8x6  OAL: RH .060");
			poEntity.setOrderCreationDate(new Date());
			poEntity.setOrderNumber(i+"");
			
			//tableList1.setSourceErpName(1);
			
			
			if(i%2==0){
				poEntity.setStatus(Constants.STATUS_IN_TRANSIT);
			}else if(i%3==0){
				poEntity.setStatus(Constants.STATUS_SUCCESS);
			}else{
				poEntity.setStatus(Constants.STATUS_ERROR);
			}
			
			tableList1.add(poItemsEntity);
			tableList2.add(poEntity);
			
			//azureStorage.getTable(Constants.TABLE_PO_DETAILS).execute(TableOperation.insertOrReplace(poEntity));
		}
		tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, tableList2);
		tableNameToEntityMap.put(Constants.TABLE_PO_ITEM_DETAILS, tableList1);
		
		 List<TableEntity> tableList3 = new ArrayList<TableEntity>();
		 List<TableEntity> tableList4 = new ArrayList<TableEntity>();
		for (int i=433001;i<433503;i++){
			ItemEntity entity1 = new ItemEntity();
			entity1 = ItemModelData.getDummyData(entity1);
			entity1.setRowKey(i+"");
			entity1.setSupplierId(i+"");
			tableList3.add(entity1);
		   
		    SupplierEntity entity2 = new SupplierEntity();
		    entity2 = SupplierModelData.getDummyData(entity2);
		    entity2.setRowKey(i+"");
		    entity1.setSupplierId(i+"");
		    tableList4.add(entity2);
		}
		
		tableNameToEntityMap.put(Constants.TABLE_ITEM, tableList3);
		tableNameToEntityMap.put(Constants.TABLE_SUPPLIER, tableList4);
		
		request.setTableNameToEntityMap(tableNameToEntityMap);
		request.setErpName(Constants.ERP_SYMIX);
		
		BatchInsertResponse response = repo.batchInsert(request);
		
		LOG.info("### Ending PoServiceImpl.insertSymixDummyData ###"  +response.getErrorMap());
	}

	public void insertSapDummyData() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.insertSapDummyData ###" );
		BatchInsertRequest request  = new BatchInsertRequest();

	    String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SAP);
		
		//Inserting dummy data
	    PoEntity poEntity = null;
	    HashMap<String, List<TableEntity>> tableNameToEntityMap = new HashMap<String, List<TableEntity>>();
	    List<TableEntity> tableList1 = new ArrayList<TableEntity>();
	    List<TableEntity> tableList2 = new ArrayList<TableEntity>();
	   
	    
		for (int i=4712510;i<4714510;i++){
			PoItemsEntity  poItemsEntity = new PoItemsEntity(partitionKey, i+"");
			poItemsEntity = PoModelData.getDummyData(poItemsEntity)	;
			
			poItemsEntity.setOrderNumber(i+"");

			
			
			//need to save above data
			poEntity = new PoEntity(partitionKey, i+"");
			poEntity.setDescription("(GE45RC375060)3/8x3/8x6  OAL: RH .060");
			poEntity.setOrderCreationDate(new Date());
			poEntity.setOrderNumber(i+"");
			
			//tableList1.setSourceErpName(1);
			
			
			if(i%2==0){
				poEntity.setStatus(Constants.STATUS_IN_TRANSIT);
			}else if(i%3==0){
				poEntity.setStatus(Constants.STATUS_SUCCESS);
			}else{
				poEntity.setStatus(Constants.STATUS_ERROR);
			}
			
			tableList1.add(poItemsEntity);
			tableList2.add(poEntity);
			
		}
		tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, tableList2);
		tableNameToEntityMap.put(Constants.TABLE_PO_ITEM_DETAILS, tableList1);
		
		 List<TableEntity> tableList3 = new ArrayList<TableEntity>();
		 List<TableEntity> tableList4 = new ArrayList<TableEntity>();
		for (int i=530000;i<534000;i++){
			ItemEntity entity1 = new ItemEntity(partitionKey, i+"");
			entity1 = ItemModelData.getDummyData(entity1);
			entity1.setRowKey(i+"");
			entity1.setSupplierId(i+"");
			tableList3.add(entity1);
		   
		    SupplierEntity entity2 = new SupplierEntity(partitionKey, i+"");
		    entity2 = SupplierModelData.getDummyData(entity2);
		    entity2.setRowKey(i+"");
		    entity1.setSupplierId(i+"");
		    tableList4.add(entity2);
		}
		
		tableNameToEntityMap.put(Constants.TABLE_ITEM, tableList3);
		tableNameToEntityMap.put(Constants.TABLE_SUPPLIER, tableList4);
		
		request.setTableNameToEntityMap(tableNameToEntityMap);
		request.setErpName(Constants.ERP_SAP);
		
		BatchInsertResponse response = repo.batchInsert(request);
		
		LOG.info("### Ending PoServiceImpl.insertSapDummyData ###" +response.getErrorMap());
	}

	@Override
	public void insertDummyData() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.insertDummyData ###" );
		insertSymixDummyData();
		insertSapDummyData();
		LOG.info("### Ending PoServiceImpl.insertDummyData ###" );
	}



}
