package com.jci.po.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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

@Service
@RefreshScope
public class PoServiceImpl implements PoService{

	private static final Logger LOG = LoggerFactory.getLogger(PoServiceImpl.class);
	
	@Autowired
	private PoRepo repo;
	
    @Value("${all.erp.names}")
    private String allErps;
    
    @Value("${all.supplier.names}")
    private String allSuppliers;
	
	@Autowired
	private ApiClientService apiService;
	
	@Autowired
	private FlatFile config;
	
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
		
		LOG.info("allErps---> "+allErps);
		
		if(request.isFirstRequest()){
			String[] erpArr  = allErps.split(",");			
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
		
		//Remove this
		HashMap userData = new HashMap();
		userData.put("UserName", "Sunil Soni");
		userData.put("GlobalId", "csonisk");
		userData.put("Role", "Admin");
		
		response.setUserData(userData);
		
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
		BatchUpdateRes response = null;
		Map<String,List<HashMap<String, Object>>>  poNumToItemsMap = repo.getErrorPos(partitionKey,poList);
		LOG.info(" poNumToItemsMap--->"+ poNumToItemsMap);
		
		//Starting JS poc Code
		/*TempRequest tempReq = new TempRequest();
		tempReq.setPoNumToItemsMap(poNumToItemsMap);
		TempResponse flatFileRes  = apiService.postSupplierData(tempReq);
		LOG.info(" flatFileRes--->"+ flatFileRes);		
		if(true){//Sunil: Remove below if condition 
			return response;
		}*/
		//Ending JS poc CODE
		
		
		
		CommonUtils utils = new CommonUtils();
		//Starting JAVA Code to process flat file
		HashMap<String,HashMap<Integer,String>>  supplierNameToMapping = utils.getDestMapping(config.getPoUrl());
		LOG.info(" supplierNameToMapping--->"+ supplierNameToMapping);
		
		PrepareFlatFile file = new PrepareFlatFile();
		Map<String,List<String>> fileNameToRowsMap = null;
		
		
		String supplierName=null;
		
		 /**
		  * Loop: No of  Supplier mapping files present in directory  
		  */
		for (Map.Entry<String,HashMap<Integer,String>> mapping : supplierNameToMapping.entrySet()){
			supplierName = mapping.getKey();
			
			fileNameToRowsMap = file.prepareSupplierData(mapping.getValue(),poNumToItemsMap,config);
			
			/**
			 * Code to process  flat files for  suppliers
			 */
			//HashMap<String,File> nameToFileMap=new HashMap<String,File>();
			for (Map.Entry<String,List<String>> entry : fileNameToRowsMap.entrySet()){
				File toFile = new File(entry.getKey());
				LOG.info(" getAbsolutePath--->"+ toFile.getAbsolutePath());
				 
				 try {
				    	FileUtils.writeLines(toFile,"UTF-8", entry.getValue(),false);
					} catch (IOException e) {
						e.printStackTrace();
						//finalRes.setError(true);
						//return finalRes;
					}
				// nameToFileMap.put(entry.getKey(), toFile);
				 
				 String mimeType= URLConnection.guessContentTypeFromName(toFile.getName());
				 LOG.info(" mimeType--->"+ mimeType);
				 
				 //start
				 try{
					 RestTemplate template = new RestTemplate();

					 MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<String, Object>();
					 requestMap.add("name", toFile.getName());
					 requestMap.add("filename", toFile.getName());
					 requestMap.set("Content-Type",mimeType);
					 requestMap.set("Content-Length",(int)toFile.length());			 
					 
					 InputStream input = new FileInputStream(toFile);
					 ByteArrayResource contentsAsResource = new ByteArrayResource(IOUtils.toByteArray(input)){
					             @Override
					             public String getFilename(){
					                 return entry.getKey();
					             }
					 };
					 requestMap.add("file", contentsAsResource);
					// map.add("file", res.getLines());
					 
					 String result = template.postForObject((config.getSupplierUrl()+"?filename="+toFile.getName()), requestMap, String.class);
					 
					 LOG.info(" result--->"+ result);
					 
				}catch(Exception e) {
					e.printStackTrace();
				}
				 //end
			}
			//Ending JAVA Code to process flat file
			
			/*FlatFileReq flatFileReq = new FlatFileReq();
			flatFileReq.setNameToFileMap(nameToFileMap);
			
			
			FlatFileRes flatFileRes  = apiService.processFlatFile(flatFileReq);
			LOG.info("flatFileRes--->"+flatFileRes);
			*/
			BatchUpdateReq req = new  BatchUpdateReq ();
			
			//if data processing is success than update status success in db
			req.setSuccess(true);//Sunil: Where are we setting this false? 
			
			//if data processing is not successful than update status error in db
			//req.setSuccess(false);
			
			//dynamic based on supplier mapping file name
			req.setDestination(supplierName);
			
			
			req.setErpName(request.getErpName().toUpperCase());
			
			List<PoEntity>  poEntity = repo.getPoDetails(partitionKey,poList);
			HashMap<String,List<PoEntity>> tableNameToEntityMap = new HashMap<String,List<PoEntity>>();
			tableNameToEntityMap.put(Constants.TABLE_PO_DETAILS, poEntity);
			
			
			req.setTableNameToEntityMap(tableNameToEntityMap);
			req.setGlobalId(request.getGlobalId());
			req.setUserName(request.getUserName());
			
			req.setComment(request.getComment());
					
			response =  repo.batchUpdate(req);
			response.setGraphData(repo.getGraphData());
		}
		
		LOG.info("### Ending PoServiceImpl.processErrorPos ###"+response );
		return response;
	}

	@Override
	public PoItemDetailRes getPoItemDetail(PoItemDetailReq request) throws InvalidKeyException, URISyntaxException, StorageException {
		LOG.info("### Starting PoServiceImpl.getPoItemDetail ###"+request );

		
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
		azureRequest.setPartitionValue(AzureUtils.getPartitionKey(request.getErpName()));
		
		azureRequest.setTableName(Constants.TABLE_PO_ITEM_DETAILS);
		
		ResultSet resultSet = repo.getPoItemDetail(param, azureRequest);
		
		PoItemDetailRes response = new PoItemDetailRes();
		
		response.setResultSet(resultSet);
		response.setMessage(Constants.JSON_OK);
		
		
		LOG.info("### Ending PoServiceImpl.getPoItemDetail ###"+response );
		return response;
	}
	
}
