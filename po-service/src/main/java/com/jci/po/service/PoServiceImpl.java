/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.jci.po.azure.FlatFile;
import com.jci.po.azure.data.DataHelper;
import com.jci.po.azure.data.ResultSet;
import com.jci.po.azure.query.PaginationParam;
import com.jci.po.azure.query.ScrollingParam;
import com.jci.po.dto.req.BatchUpdateReq;
import com.jci.po.dto.req.PoDetailsReq;
import com.jci.po.dto.req.PoItemDetailReq;
import com.jci.po.dto.req.SegmentedDetailReq;
import com.jci.po.dto.res.BatchUpdateRes;
import com.jci.po.dto.res.PoItemDetailRes;
import com.jci.po.dto.res.SegmentedDetailRes;
import com.jci.po.entity.PoEntity;
import com.jci.po.repo.PoRepo;
import com.jci.po.utils.AzureUtils;
import com.jci.po.utils.CommonUtils;
import com.jci.po.utils.Constants;
import com.jci.po.utils.PrepareFlatFile;
import com.microsoft.azure.storage.StorageException;


/**
 * <p>
 * <strong> The Class PoServiceImpl.</strong>
 * <p>
 *
 * @author csonisk
 */
@Service
@RefreshScope
public class PoServiceImpl implements PoService{ // NO_UCD (unused code)

	/** The Constant LOG. */
 private static final Logger LOG = LoggerFactory.getLogger(PoServiceImpl.class);
	
	/** The repo. */
	@Autowired
	private PoRepo repo;
	
    /** The all erps. */
    @Value("${all.erp.names}")
    private String allErps;
    
	
	/** The config. */
	@Autowired
	private FlatFile config;

	
	/* (non-Javadoc)
	 * @see com.jci.po.service.PoService#getSegmentedResultSet(com.jci.po.dto.req.SegmentedDetailReq)
	 */
	@Override
	public SegmentedDetailRes getSegmentedResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException  {
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
		HashMap<String, ResultSet>  resultSetMap = new HashMap<>();
		HashMap<String, ResultSet>  suppMap = new HashMap<>();
		HashMap<String, ResultSet>  itemMap = new HashMap<>();
		HashMap<String, ResultSet>  errorMap = new HashMap<>();
		
		if(request.isFirstRequest()){
			String[] erpArr  = allErps.split(",");			
			for (int i=0;i<erpArr.length;i++){				
				azureRequest = new DataHelper();
				azureRequest.setErrorDataRequired(false);
				azureRequest.setErpName(erpArr[i]);
				azureRequest.setPartitionValue(AzureUtils.getPoPartitionKey(erpArr[i]));
				azureRequest.setTableName(Constants.TABLE_PO_DETAILS);
				
				resultSet = repo.getSegmentedResultSet(param, azureRequest);
				resultSetMap.put(erpArr[i], resultSet);
				
				azureRequest.setTableName(Constants.TABLE_PO_DETAILS); 
				azureRequest.setErrorDataRequired(true);
				resultSet = repo.getSegmentedResultSet(param, azureRequest);
				errorMap.put(erpArr[i], resultSet);
				
			}
			response.setGraphData(repo.getGraphData());
			response.setResultSet(resultSetMap);
			response.setErrorData(errorMap);
			
			response.setSuppData(suppMap);
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
		
		//Remove this
		HashMap<String,String> userData = new HashMap<>();
		userData.put("UserName", "Sunil Soni");
		userData.put("GlobalId", "csonisk");
		userData.put("Role", "Admin");
		
		response.setUserData(userData);
		return response;
	}
	

	/* (non-Javadoc)
	 * @see com.jci.po.service.PoService#getErrorResultSet(com.jci.po.dto.req.SegmentedDetailReq)
	 */
	@Override
	public SegmentedDetailRes getErrorResultSet(SegmentedDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException {
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
		HashMap<String, ResultSet>  resultSetMap = new HashMap<>();
		
		azureRequest = new DataHelper();
		azureRequest.setErrorDataRequired(true);
		azureRequest.setErpName(request.getErpName());
		azureRequest.setPartitionValue(request.getPartition());
		azureRequest.setTableName(request.getTableName());
		resultSet = repo.getSegmentedResultSet(param, azureRequest);
		
		resultSetMap.put(request.getErpName(), resultSet);			
		response.setErrorData(resultSetMap);
		response.setMessage(Constants.JSON_OK);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.jci.po.service.PoService#processErrorPos(com.jci.po.dto.req.PoDetailsReq)
	 */
	@Override
	public BatchUpdateRes processErrorPos(PoDetailsReq request)  throws InvalidKeyException, URISyntaxException, StorageException {
		BatchUpdateRes res = new BatchUpdateRes();
		String partitionKey = AzureUtils.getPoPartitionKey(request.getErpName());
		
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
		BatchUpdateRes response = null;
		Map<String,List<HashMap<String, Object>>>  poNumToItemsMap = repo.getErrorPos(partitionKey,poList);
		CommonUtils utils = new CommonUtils();
		//Starting JAVA Code to process flat file
		HashMap<String,HashMap<Integer,String>>  suppNameToMapping = utils.getDestMapping(config.getPoUrl());
		
		PrepareFlatFile file = new PrepareFlatFile();
		Map<String,List<String>> fileNameToRowsMap = null;
		
		List<String> successList = new ArrayList<>();
		List<String> errorList = new ArrayList<>();
		Map<String,List<String>> pkToSuccessList = new HashMap<>();
		
		 /**
		  * Loop: No of  Supplier mapping files present in directory  
		  */
		for (Map.Entry<String,HashMap<Integer,String>> mapping : suppNameToMapping.entrySet()){
			fileNameToRowsMap = file.prepareSuppData(mapping.getValue(),poNumToItemsMap,config);
			
			/**
			 * Code to process  flat files for  suppliers
			 */
			//HashMap<String,File> nameToFileMap=new HashMap<>();
			for (Map.Entry<String,List<String>> entry : fileNameToRowsMap.entrySet()){
				File toFile = new File(entry.getKey());
				 try {
				    	FileUtils.writeLines(toFile,"UTF-8", entry.getValue(),false);
				    	//toFile = File.createTempFile(entry.getKey(), ".txt");
					} catch (IOException e) {
						LOG.error("### Exception in   ####",e);
						
					}
				    boolean isSuccess =  PrepareFlatFile.processFile(toFile,config.getE2openUrl());
					LOG.info(" isSuccess--->"+ isSuccess);
					if(isSuccess){
						successList.add(entry.getKey().split("\\.")[0]);
					}else{
						errorList.add(entry.getKey().split("\\.")[0]);
					}
			}
		}
		
		
		if(successList.size()>0){
			pkToSuccessList.put(request.getErpName(), successList);
		}

		//Update status in DB
		boolean isUpdated  = updateStatus(pkToSuccessList,request.getGlobalId(),request.getUserName(),request.getComment());		
		 response = new BatchUpdateRes();
		 response.setGraphData(repo.getGraphData());
		 if(isUpdated){
			 response.setSuccessList(successList);
		 }
		 response.setErrorList(errorList);
		return response;
	}

	/**
	 * Update status.
	 *
	 * @param pkToSuccessList the pk to success list
	 * @param globalId the global id
	 * @param userName the user name
	 * @param comment the comment
	 * @return true, if successful
	 */
	private boolean updateStatus(Map<String,List<String>> pkToSuccessList,String globalId,String userName,String comment) {
		boolean isUpdated=false;
		BatchUpdateReq updateReq =null;
		for (Map.Entry<String,List<String>> entry : pkToSuccessList.entrySet()){
			updateReq = new  BatchUpdateReq ();
			updateReq.setSuccess(true);
			updateReq.setErpName(entry.getKey().toUpperCase());
			 
			String partitionKey = CommonUtils.getPartitionKey(entry.getKey().toUpperCase());
			try {
				List<PoEntity> poEntity = repo.getPoDetails(partitionKey,entry.getValue());
				HashMap<String,List<PoEntity>> tableNameToEntityMap = new HashMap<>();
				tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poEntity);
				updateReq.setTableNameToEntityMap(tableNameToEntityMap);
				
				updateReq.setComment(comment);
				updateReq.setUserName(userName);
				updateReq.setGlobalId(globalId);
				repo.batchUpdate(updateReq);
				isUpdated=true;
			} catch (InvalidKeyException | URISyntaxException | StorageException e) {
				LOG.error("### Exception in   ####",e);
				
			}
		}
		return isUpdated;
	} 
	
	/* (non-Javadoc)
	 * @see com.jci.po.service.PoService#getPoItemDetail(com.jci.po.dto.req.PoItemDetailReq)
	 */
	@Override
	public PoItemDetailRes getPoItemDetail(PoItemDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException {
		PaginationParam paginationParam = request.getPaginationParam();
		ScrollingParam param  = new ScrollingParam();
		
		if(paginationParam!=null){
			param.setPartition(paginationParam.getNextPartition());
			param.setRow(paginationParam.getNextRow());
		}
		
		//For where condition
		param.setSize(request.getSize());
		
		DataHelper azureRequest = new DataHelper();
		azureRequest.setErpName(request.getErpName());
		azureRequest.setPoNum(request.getPoNum());
		azureRequest.setPartitionValue(AzureUtils.getPoItemPartitionKey(request.getErpName()));
		azureRequest.setTableName(Constants.TABLE_PO_ITEM_DETAILS);
		ResultSet resultSet = repo.getPoItemDetail(param, azureRequest);
		PoItemDetailRes response = new PoItemDetailRes();
		response.setResultSet(resultSet);
		response.setMessage(Constants.JSON_OK);
		return response;
	}
	
}
