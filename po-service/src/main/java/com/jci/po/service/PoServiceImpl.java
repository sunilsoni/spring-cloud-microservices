/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.po.service;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.azure.DataHelper;
import com.jci.azure.PaginationParam;
import com.jci.azure.ResultSet;
import com.jci.azure.ScrollingParam;
import com.jci.dto.BatchUpdateRes;
import com.jci.dto.SegmentedDetailReq;
import com.jci.dto.SegmentedDetailRes;
import com.jci.enums.ErrorEnum;
import com.jci.exception.ErrorService;
import com.jci.po.dto.req.PoDetailsReq;
import com.jci.po.dto.req.PoItemDetailReq;
import com.jci.po.dto.req.ProcessErrorReq;
import com.jci.po.dto.res.PoItemDetailRes;
import com.jci.po.dto.res.ProcessErrorRes;
import com.jci.po.exception.PoException;
import com.jci.po.repo.PoRepo;
import com.jci.utils.Constants;



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
    
	/** The client. */
	@Autowired
    FlatFileClientImpl client;
	
    /** The error service. */
    @Autowired
    private ErrorService errorService;
    
    
	
	/* (non-Javadoc)
	 * @see com.jci.po.service.PoService#getSegmentedResultSet(com.jci.po.dto.req.SegmentedDetailReq)
	 */
	@Override
	public SegmentedDetailRes getSegmentedResultSet(SegmentedDetailReq request)   {
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
				azureRequest.setPartitionValue(erpArr[i].toUpperCase());
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
		//response.setMessage(Constants.JSON_OK);
		
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
	public SegmentedDetailRes getErrorResultSet(SegmentedDetailReq request)  {
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
		//response.setMessage(Constants.JSON_OK);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.jci.po.service.PoService#processErrorPos(com.jci.po.dto.req.PoDetailsReq)
	 */
	@Override
	public BatchUpdateRes processErrorPos(PoDetailsReq request)   {
	    
	    LOG.info("request--->"+request);
		BatchUpdateRes res = new BatchUpdateRes();
		String partitionKey = request.getErpName().toUpperCase();
		BatchUpdateRes response =  new BatchUpdateRes();;
		
		if(StringUtils.isBlank(partitionKey)){
			res.setError(true);
			res.setMessage("Invalid request parameters !");
			return res;
		}
		
		ProcessErrorReq ffres = new ProcessErrorReq();
		ffres.setComment(request.getComment());
		ffres.setErpName(request.getErpName());
		ffres.setErrorsList(request.getPoNo());
		ffres.setGlobalId(request.getGlobalId());
		ffres.setTableName(Constants.TABLE_PO_DETAILS);
		ffres.setUserName(request.getUserName());
		
		ResponseEntity<ProcessErrorRes> ffResponse =null;
		ProcessErrorRes ffPos = null;
		try{
			ffResponse = client.processErrorPosFlatFiles(ffres);
			 ffPos = ffResponse.getBody();
		}catch(Exception e){
			throw errorService.createException(PoException.class, e, ErrorEnum.ERROR_FLATFILE_PO_ERROR_SERVICE_DOWN);
		}
		
		if(ffPos==null){
		    response.setError(true);
		    return response;
		}
		
		response.setSuccessList(ffPos.getSuccessList());
		response.setErrorList(ffPos.getErrorList());
		response.setGraphData(repo.getGraphData());
			
		return response;
	}

	/* (non-Javadoc)
	 * @see com.jci.po.service.PoService#getPoItemDetail(com.jci.po.dto.req.PoItemDetailReq)
	 */
	@Override
	public PoItemDetailRes getPoItemDetail(PoItemDetailReq request)  {
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
		azureRequest.setPartitionValue(request.getErpName().toUpperCase());
		azureRequest.setTableName(Constants.TABLE_PO_ITEM_DETAILS);
		ResultSet resultSet = repo.getPoItemDetail(param, azureRequest);
		PoItemDetailRes response = new PoItemDetailRes();
		response.setResultSet(resultSet);
		//response.setMessage(Constants.JSON_OK);
		return response;
	}
	
}
