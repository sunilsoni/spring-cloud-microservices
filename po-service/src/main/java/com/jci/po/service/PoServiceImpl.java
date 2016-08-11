package com.jci.po.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.PaginationParam;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.req.BatchInsertReq;
import com.jci.po.dto.req.BatchUpdateReq;
import com.jci.po.dto.req.FlatFileReq;
import com.jci.po.dto.req.PoDetailsReq;
import com.jci.po.dto.req.SegmentedDetailReq;
import com.jci.po.dto.res.BatchInsertResp;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.dto.res.FlatFileRes;
import com.jci.po.dto.res.SegmentedDetailRes;
import com.jci.po.entity.ItemEntity;
import com.jci.po.entity.PoEntity;
import com.jci.po.entity.PoItemsEntity;
import com.jci.po.entity.SupplierEntity;
import com.jci.po.repo.PoRepo;
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
	private PoRepo repo;
	
	@Autowired
	private ApiClientService apiService;
	
	 @Override
	 public String getLastPo() throws InvalidKeyException, URISyntaxException, StorageException {
		 /*LOG.info("### Starting Ending PoServiceImpl.getLastPo ### " );
		 
		 return repo.selectPos().stream()
        .sorted(Comparator.comparing(PoEntity::getTimestamp).reversed())
        .map(PoEntity::getOrderNumber).findFirst().orElseThrow(() -> new NullPointerException("No Data"));*/
		 return null;
	 }

	
	@Override
	public SegmentedDetailRes getSegmentedResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException  {
		LOG.info("### Starting Ending PoServiceImpl.getSegmentedResultSet ### " );
		PaginationParam paginationParam = request.getPaginationParam();
		
		ScrollingParam param  = new ScrollingParam();
		
		if(paginationParam!=null){
			param.setPartition(paginationParam.getNextPartition());
			param.setRow(paginationParam.getNextRow());
		}
		
		//For where condition
		param.setSize(request.getSize());
		
		DataHelper azureRequest = null;
		ResultSet resultSet = null;
		
		SegmentedDetailRes response = new SegmentedDetailRes(); 
		HashMap<String, ResultSet>  resultSetMap = new HashMap<String, ResultSet>();
		HashMap<String, ResultSet>  supplierMap = new HashMap<String, ResultSet>();
		HashMap<String, ResultSet>  itemMap = new HashMap<String, ResultSet>();
		HashMap<String, ResultSet>  errorMap = new HashMap<String, ResultSet>();
		
		if(request.isFirstRequest()){
			String[] erpArr  = Constants.ALL_ERP_NAMES.split(",");			
			for (int i=0;i<erpArr.length;i++){				
				azureRequest = new DataHelper();
				azureRequest.setErrorDataRequired(false);
				azureRequest.setErpName(erpArr[i]);
				azureRequest.setPartitionValue(AzureUtils.getPartitionKey(erpArr[i]));
				azureRequest.setTableName(Constants.TABLE_PO_DETAILS);
				
				resultSet = repo.getSegmentedResultSet(param, azureRequest);
				resultSetMap.put(erpArr[i], resultSet);
				
				azureRequest.setErrorDataRequired(false);
				azureRequest.setTableName(Constants.TABLE_SUPPLIER);
				resultSet = repo.getSegmentedResultSet(param, azureRequest);
				supplierMap.put(erpArr[i], resultSet);
				
				azureRequest.setTableName(Constants.TABLE_ITEM);
				resultSet = repo.getSegmentedResultSet(param, azureRequest);
				itemMap.put(erpArr[i], resultSet);
				
				azureRequest.setTableName(Constants.TABLE_PO_DETAILS); 
				azureRequest.setErrorDataRequired(true);
				resultSet = repo.getSegmentedResultSet(param, azureRequest);
				errorMap.put(erpArr[i], resultSet);
				
			}
			response.setGraphData(repo.getGraphData());
			response.setResultSet(resultSetMap);
			response.setErrorData(errorMap);
			
			response.setSupplierData(supplierMap);
			response.setItemData(itemMap);			
		}else{
			azureRequest = new DataHelper();
			azureRequest.setErpName(request.getErpName());
			azureRequest.setPartitionValue(request.getPartition());
			azureRequest.setTableName(request.getTableName());
			resultSet = repo.getSegmentedResultSet(param, azureRequest);
			resultSetMap.put(request.getErpName(), resultSet);			
			response.setResultSet(resultSetMap);
		}
		response.setMessage(Constants.JSON_OK);
		
		LOG.info("### Ending Ending PoServiceImpl.getSegmentedResultSet ### " );
		
		return response;
	}
	

	@Override
	public SegmentedDetailRes getErrorResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting Ending PoServiceImpl.getErrorResultSet ### " );
		PaginationParam paginationParam = request.getPaginationParam();
		
		ScrollingParam param  = new ScrollingParam();
		
		if(paginationParam!=null){
			param.setPartition(paginationParam.getNextPartition());
			param.setRow(paginationParam.getNextRow());
		}
		
		//For where condition
		param.setSize(request.getSize());
		
		DataHelper azureRequest = null;
		ResultSet resultSet = null;
		
		SegmentedDetailRes response = new SegmentedDetailRes(); 
		HashMap<String, ResultSet>  resultSetMap = new HashMap<String, ResultSet>();
		
		azureRequest = new DataHelper();
		azureRequest.setErrorDataRequired(true);
		azureRequest.setErpName(request.getErpName());
		azureRequest.setPartitionValue(request.getPartition());
		azureRequest.setTableName(request.getTableName());
		resultSet = repo.getSegmentedResultSet(param, azureRequest);
		
		resultSetMap.put(request.getErpName(), resultSet);			
		response.setErrorData(resultSetMap);
		response.setMessage(Constants.JSON_OK);
		
		LOG.info("### Ending Ending PoServiceImpl.getErrorResultSet ### " );
		
		return response;
	}

	@Override
	public BatchUpdateRes processErrorPos(PoDetailsReq request)  throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.processErrorPos ###" +request);
		
		BatchUpdateRes res = new BatchUpdateRes();
		String partitionKey = AzureUtils.getPartitionKey(request.getErpName());
		
		if(StringUtils.isBlank(partitionKey)){
			res.setError(true);
			res.setMessage("Invalid request parameters !");
			return res;
		}
		
		List<String> poList = request.getPoNo();
		if(poList==null || poList.size()<1){
			res.setError(true);
			res.setMessage("Invalid request parameters !");
			return res;
		}
		
		List<PoItemsEntity>  itemList = repo.getErrorPos(partitionKey,poList);
		LOG.info(" itemList--->"+ itemList);
		
		List<PoEntity>  poEntity = repo.getPoDetails(partitionKey,poList);
		
		HashMap<String,List<PoEntity>> tableNameToEntityMap = new HashMap<String,List<PoEntity>>();
		tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poEntity);
		
		/**
		 * Code to process e2open flat files:
		 */
		
		/*FlatFileReq flatFileReq = new FlatFileReq();
		FlatFileRes flatFileRes  = apiService.processFlatFile(flatFileReq);
		LOG.info("flatFileRes--->"+flatFileRes);*/
		
		BatchUpdateReq req = new  BatchUpdateReq ();
		
		//if data processing is success than update status success in db
		req.setSuccess(true);
		
		//if data processing is not successful than update status error in db
		//req.setSuccess(false);
		
		//Need to make it dynamic based on mapping file
		req.setDestination(Constants.DESTINATION_E2OPEN);
		
		
		req.setErpName(request.getErpName().toUpperCase());
		req.setTableNameToEntityMap(tableNameToEntityMap);
		req.setGlobalId(request.getGlobalId());
		req.setUserName(request.getUserName());
		
		req.setComment(request.getComment());
				
		BatchUpdateRes response =  repo.batchUpdate(req);
		response.setGraphData(repo.getGraphData());
		
		
		LOG.info("### Ending PoServiceImpl.processErrorPos ###"+response );
		return response;
	}


	
	
	public void insertSymixDummyData() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.insertSymixDummyData ###" );
		BatchInsertReq request  = new BatchInsertReq();

	    String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SYMIX);
		
		//Inserting dummy data
	    PoEntity poEntity = null;
	    HashMap<String, List<TableEntity>> tableNameToEntityMap = new HashMap<String, List<TableEntity>>();
	    List<TableEntity> tableList1 = new ArrayList<TableEntity>();
	    List<TableEntity> tableList2 = new ArrayList<TableEntity>();
	   
	    
		for (int i=3713511;i<3715011;i++){
			PoItemsEntity  poItemsEntity = new PoItemsEntity(partitionKey, i+"");
			poItemsEntity = PoModelData.getDummyData(poItemsEntity)	;
			
			poItemsEntity.setOrderNumber(i+"");

			
			
			//need to save above data
			poEntity = new PoEntity(partitionKey, i+"");
			poEntity.setDescription("(GE45RC375060)3/8x3/8x6  OAL: RH .060");
			poEntity.setOrderCreationDate(new Date());
			//poEntity.setOrderNumber(i+"");
			//poEntity.setSourceErpName(Constants.ERP_SYMIX);
			
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
		for (int i=433001;i<435003;i++){
			ItemEntity entity1 = new ItemEntity();
			entity1 = ItemModelData.getDummyData(entity1);
			entity1.setRowKey(i+"");
			//entity1.setSupplierId(i+"");
			tableList3.add(entity1);
		   
		    SupplierEntity entity2 = new SupplierEntity();
		    entity2 = SupplierModelData.getDummyData(entity2);
		    entity2.setRowKey(i+"");
		   // entity1.setSupplierId(i+"");
		    tableList4.add(entity2);
		}
		
		tableNameToEntityMap.put(Constants.TABLE_ITEM, tableList3);
		tableNameToEntityMap.put(Constants.TABLE_SUPPLIER, tableList4);
		
		request.setTableNameToEntityMap(tableNameToEntityMap);
		request.setErpName(Constants.ERP_SYMIX);
		
		BatchInsertResp response = repo.batchInsert(request);
		
		LOG.info("### Ending PoServiceImpl.insertSymixDummyData ###"  +response.getErrorMap());
	}

	public void insertSapDummyData() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.insertSapDummyData ###" );
		BatchInsertReq request  = new BatchInsertReq();

	    String partitionKey = AzureUtils.getPartitionKey(Constants.ERP_SAP);

	    //Inserting dummy data
	    PoEntity poEntity = null;
	    HashMap<String, List<TableEntity>> tableNameToEntityMap = new HashMap<String, List<TableEntity>>();
	    List<TableEntity> tableList1 = new ArrayList<TableEntity>();
	    List<TableEntity> tableList2 = new ArrayList<TableEntity>();
	   
	    
		for (int i=4714011;i<4714511;i++){
			PoItemsEntity  poItemsEntity = new PoItemsEntity(partitionKey, i+"");
			poItemsEntity = PoModelData.getDummyData(poItemsEntity)	;
			
			poItemsEntity.setOrderNumber(i+"");
			
			//need to save above data
			poEntity = new PoEntity(partitionKey, i+"");
			poEntity.setDescription("(GE45RC375060)3/8x3/8x6  OAL: RH .060");
			poEntity.setOrderCreationDate(new Date());
			//poEntity.setOrderNumber(i+"");
			
			//poEntity.setSourceErpName(Constants.ERP_SAP);
			
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
		for (int i=533201;i<533701;i++){
			ItemEntity entity1 = new ItemEntity(partitionKey, i+"");
			entity1 = ItemModelData.getDummyData(entity1);
			entity1.setRowKey(i+"");
			//entity1.setSupplierId(i+"");
			tableList3.add(entity1);
		   
		    SupplierEntity entity2 = new SupplierEntity(partitionKey, i+"");
		    entity2 = SupplierModelData.getDummyData(entity2);
		    entity2.setRowKey(i+"");
		    //entity1.setSupplierId(i+"");
		    tableList4.add(entity2);
		    
		    //LOG.info("getPartitionKey-->"+entity1.getPartitionKey());
		}
		tableNameToEntityMap.put(Constants.TABLE_ITEM, tableList3);
		tableNameToEntityMap.put(Constants.TABLE_SUPPLIER, tableList4);
		
		request.setTableNameToEntityMap(tableNameToEntityMap);
		request.setErpName(Constants.ERP_SAP);
		
		BatchInsertResp response = repo.batchInsert(request);
		
		LOG.info("### Ending PoServiceImpl.insertSapDummyData ###" +response);
	}

	@Override
	public void insertDummyData() throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.insertDummyData ###" );
		//insertSymixDummyData();
		insertSapDummyData();
		LOG.info("### Ending PoServiceImpl.insertDummyData ###" );
	}





}
