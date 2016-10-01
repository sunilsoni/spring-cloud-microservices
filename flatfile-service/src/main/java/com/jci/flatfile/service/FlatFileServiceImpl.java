package com.jci.flatfile.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jci.flatfile.config.FlatFile;
import com.jci.flatfile.config.SSHConnection;
import com.jci.flatfile.repo.FlatFileRepo;
import com.jci.flatfile.utils.BatchUpdateReq;
import com.jci.flatfile.utils.CommonUtils;
import com.jci.flatfile.utils.Constants;
import com.jci.flatfile.utils.FlatFileRes;
import com.jci.flatfile.utils.ProcessErrorReq;
import com.jci.flatfile.utils.ProcessErrorRes;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableEntity;

@Service
public class FlatFileServiceImpl implements FlatFileService{

    private static final Logger LOG = LoggerFactory.getLogger(FlatFileServiceImpl.class);
    
    @Autowired
    private FlatFileRepo repo;
    
    /** The all erps. */
    @Value("${all.erp.names}")
    private String allErps;
    
    @Autowired
    private FlatFile config;
    
    @Override
    public String processPoFlatFiles() throws InvalidKeyException, URISyntaxException, StorageException {
    	LOG.info("### Starting  FlatFileServiceImpl.processPoFlatFile ####");
        String tempDir = System.getProperty("java.io.tmpdir");
        
        CommonUtils utils = new CommonUtils();
        TreeMap<String,HashMap<Integer,String>> mappingList = utils.getDestMapping(config.getPoMappingFileUrl());
        
        Map<String,List<String>> pkToSuccessList = new HashMap<>();
        Map<String,List<String>> pkToErrorList = new HashMap<>();
        
        ArrayList<String> files = new ArrayList<>();
        String[] erpArr  = allErps.split(",");
        
        List<File> tempFiles = new ArrayList<>();
        for (int i=0;i<erpArr.length;i++){
            String partitionKey = erpArr[i].toUpperCase();
            FlatFileRes res = repo.getPoFlatFileData(partitionKey,null);
            Map<String, String> rowKeyToPlantMap = res.getRowKeyToPlantMap();
            Map<String, String> rowKeyToSupptypeMap = res.getRowKeyToSupptypeMap();
            
            ArrayList<Map<String,List<HashMap<String, Object>>>> poList = res.getPoList();
            for (Map<String,List<HashMap<String, Object>>> poNumToItemListMap : poList) {
                
                for (Map.Entry<String,List<HashMap<String, Object>>> entry : poNumToItemListMap.entrySet()){
                    
                    String suppType = rowKeyToSupptypeMap.get(entry.getKey());
                    if(mappingList.containsKey(suppType)){
                        Map<String,List<String>> fileNameToRowsMap = utils.prepareSuppData(entry.getKey(), mappingList.get(suppType),entry.getValue(),rowKeyToPlantMap.get(entry.getKey()),Constants.MESSAGE_TYPE_PO);
                        
                        for (Map.Entry<String,List<String>> entry1 : fileNameToRowsMap.entrySet()){
                            File toFile = new File(tempDir+entry1.getKey());
                             try {
                                    FileUtils.writeLines(toFile,"UTF-8", entry1.getValue(),false);
                                    files.add(toFile.getAbsolutePath());
                                    tempFiles.add(toFile);
                                } catch (IOException e) {
                                    LOG.error("### Exception in   ####",e);
                                }
                        }
                    }
                }
            }
            
        SSHConnection conn =  new SSHConnection(config.getHostname(),config.getPort(),config.getUsername(), config.getPassword());
        List<List<String>> finalList = new ArrayList<>();  
        
        try {
            finalList = conn.sftpUpload(files, Constants.TARGET_DIR);
        } catch (ClassNotFoundException | IOException e) {
        	e.printStackTrace();
        }
        
        LOG.info("finalList===>"+finalList);
        
        if(finalList!=null ){
            if(finalList.get(0).size()>0){
                pkToSuccessList.put(erpArr[i], finalList.get(0));
            }
            if(finalList.get(1).size()>0){
                pkToErrorList.put(erpArr[i], finalList.get(1));
            }
            
            //Update status in PODETAILS Table
            updatePoStatus(pkToSuccessList,pkToErrorList,Constants.TABLE_PO_DETAILS,null,null,null); 
        }
            
           
        
        //Need to delete TEMP Files.
        CommonUtils.deleteTempFiles(tempFiles);
        }
        LOG.info("### Ending  FlatFileServiceImpl.processPoFlatFile ####");
        return "Success";
    }

    @Override
    public String processGrFlatFiles() throws InvalidKeyException, URISyntaxException, StorageException {
    	LOG.info("### Starting  FlatFileServiceImpl.processGrFlatFiles ####");
    	
    	
    	LOG.info("### Ending  FlatFileServiceImpl.processGrFlatFiles ####");
        return "Success";
    }

    @Override
    public String processSuppFlatFiles() throws InvalidKeyException, URISyntaxException, StorageException {
    	LOG.info("### Starting  FlatFileServiceImpl.processSuppFlatFiles ####");
        String tempDir = System.getProperty("java.io.tmpdir");
        
        CommonUtils utils = new CommonUtils();
        TreeMap<String,HashMap<Integer,String>> mappingList = utils.getDestMapping(config.getSuppMappingFileUrl());
        
        Map<String,List<String>> pkToSuccessList = new HashMap<>();
        Map<String,List<String>> pkToErrorList = new HashMap<>();
        
        ArrayList<String> files = new ArrayList<>();
        String[] erpArr  = allErps.split(",");
        
        List<File> tempFiles = new ArrayList<>();
        for (int i=0;i<erpArr.length;i++){
            String partitionKey = erpArr[i].toUpperCase();
            
            FlatFileRes res = repo.getFlatFileData(partitionKey,Constants.TABLE_SUPPLIER);
            
            
            //plant name to total records map
            
            Map<String, String> rowKeyToPlantMap = res.getRowKeyToPlantMap();
            Map<String, String> rowKeyToSupptypeMap = res.getRowKeyToSupptypeMap();
            List<HashMap<String, Object>> rowList = res.getList();
            
            Map<String, List<HashMap<String, Object>>> plantToRowsMap = new HashMap<>();
            
            List<HashMap<String, Object>> list = null;
            
            for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            	
            	if(plantToRowsMap.containsKey(entry.getValue())){
            		list = plantToRowsMap.get(entry.getValue());
            		
                	for (HashMap<String, Object> rowkeyToVal : rowList) {
                     	
                     	if(rowkeyToVal.containsValue(entry.getKey())){
                     		list.add(rowkeyToVal);
                     	}
                     }
                	 plantToRowsMap.put(entry.getValue(),list);
            	}else{
            		list = new ArrayList<>();
            		for (HashMap<String, Object> rowkeyToVal : rowList) {
                     	
                     	if(rowkeyToVal.containsValue(entry.getKey())){
                     		list.add(rowkeyToVal);
                     	}
                     }
            		plantToRowsMap.put(entry.getValue(),list);
            	}
            }
            
                
            for (Map.Entry<String,List<HashMap<String, Object>>> entry : plantToRowsMap.entrySet()){
                
                String suppType = rowKeyToSupptypeMap.get(entry.getKey());
                if(mappingList.containsKey(suppType)){
                    Map<String,List<String>> fileNameToRowsMap = utils.prepareSuppData(null, mappingList.get(suppType),entry.getValue(),rowKeyToPlantMap.get(entry.getKey()),Constants.MESSAGE_TYPE_SUPP);
                    
                    for (Map.Entry<String,List<String>> entry1 : fileNameToRowsMap.entrySet()){
                        File toFile = new File(tempDir+entry1.getKey());
                         try {
                                FileUtils.writeLines(toFile,"UTF-8", entry1.getValue(),false);
                                files.add(toFile.getAbsolutePath());
                                tempFiles.add(toFile);
                            } catch (IOException e) {
                                LOG.error("### Exception in   ####",e);
                            }
                    }
                }
            }
            
        SSHConnection conn =  new SSHConnection(config.getHostname(),config.getPort(),config.getUsername(), config.getPassword());
        List<List<String>> finalList = new ArrayList<>();  
        
        try {
            finalList = conn.sftpUpload(files, Constants.TARGET_DIR);
        } catch (ClassNotFoundException | IOException e) {
        	e.printStackTrace();
        }
        
        LOG.info("finalList===>"+finalList);
        
        if(finalList!=null ){
            if(finalList.get(0).size()>0){
                pkToSuccessList.put(erpArr[i], finalList.get(0));
            }
            if(finalList.get(1).size()>0){
                pkToErrorList.put(erpArr[i], finalList.get(1));
            }
            
          //Update status in PODETAILS Table
            updatePoStatus(pkToSuccessList,pkToErrorList,Constants.TABLE_SUPPLIER,null,null,null);
        }
            
              
        
        //Need to delete TEMP Files.
        CommonUtils.deleteTempFiles(tempFiles);
        }
        LOG.info("### Ending  FlatFileServiceImpl.processSuppFlatFiles ####");
        return "Success";
    
    }

    @Override
    public String processItemFlatFiles() throws InvalidKeyException, URISyntaxException, StorageException {
    	LOG.info("### Starting  FlatFileServiceImpl.processItemFlatFiles ####");
        String tempDir = System.getProperty("java.io.tmpdir");
        
        CommonUtils utils = new CommonUtils();
        TreeMap<String,HashMap<Integer,String>> mappingList = utils.getDestMapping(config.getItemMappingFileUrl());
        
        Map<String,List<String>> pkToSuccessList = new HashMap<>();
        Map<String,List<String>> pkToErrorList = new HashMap<>();
        
        ArrayList<String> files = new ArrayList<>();
        String[] erpArr  = allErps.split(",");
        
        List<File> tempFiles = new ArrayList<>();
        for (int i=0;i<erpArr.length;i++){
            String partitionKey = erpArr[i].toUpperCase();
            
            FlatFileRes res = repo.getFlatFileData(partitionKey,Constants.TABLE_ITEM);
            
            
            //plant name to total records map
            
            Map<String, String> rowKeyToPlantMap = res.getRowKeyToPlantMap();
            Map<String, String> rowKeyToSupptypeMap = res.getRowKeyToSupptypeMap();
            List<HashMap<String, Object>> rowList = res.getList();
            
            Map<String, List<HashMap<String, Object>>> plantToRowsMap = new HashMap<>();
            
            List<HashMap<String, Object>> list = null;
            
            for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            	
            	if(plantToRowsMap.containsKey(entry.getValue())){
            		list = plantToRowsMap.get(entry.getValue());
            		
                	for (HashMap<String, Object> rowkeyToVal : rowList) {
                     	
                     	if(rowkeyToVal.containsValue(entry.getKey())){
                     		list.add(rowkeyToVal);
                     	}
                     }
                	 plantToRowsMap.put(entry.getValue(),list);
            	}else{
            		list = new ArrayList<>();
            		for (HashMap<String, Object> rowkeyToVal : rowList) {
                     	
                     	if(rowkeyToVal.containsValue(entry.getKey())){
                     		list.add(rowkeyToVal);
                     	}
                     }
            		plantToRowsMap.put(entry.getValue(),list);
            	}
            }
            
                
            for (Map.Entry<String,List<HashMap<String, Object>>> entry : plantToRowsMap.entrySet()){
                
                String suppType = rowKeyToSupptypeMap.get(entry.getKey());
                if(mappingList.containsKey(suppType)){
                    Map<String,List<String>> fileNameToRowsMap = utils.prepareSuppData(null, mappingList.get(suppType),entry.getValue(),rowKeyToPlantMap.get(entry.getKey()),Constants.MESSAGE_TYPE_ITEM);
                    
                    for (Map.Entry<String,List<String>> entry1 : fileNameToRowsMap.entrySet()){
                        File toFile = new File(tempDir+entry1.getKey());
                         try {
                                FileUtils.writeLines(toFile,"UTF-8", entry1.getValue(),false);
                                files.add(toFile.getAbsolutePath());
                                tempFiles.add(toFile);
                            } catch (IOException e) {
                                LOG.error("### Exception in   FlatFileServiceImpl.processItemFlatFiles  ####",e);
                            }
                    }
                }
            }
            
        SSHConnection conn =  new SSHConnection(config.getHostname(),config.getPort(),config.getUsername(), config.getPassword());
        List<List<String>> finalList = new ArrayList<>();  
        
        try {
            finalList = conn.sftpUpload(files, Constants.TARGET_DIR);
        } catch (ClassNotFoundException | IOException e) {
        	e.printStackTrace();
        }
        
        LOG.info("finalList===>"+finalList);
        
        if(finalList!=null ){
            if(finalList.get(0).size()>0){
                pkToSuccessList.put(erpArr[i], finalList.get(0));
            }
            if(finalList.get(1).size()>0){
                pkToErrorList.put(erpArr[i], finalList.get(1));
            }
            
            //Update status in PODETAILS Table
            updatePoStatus(pkToSuccessList,pkToErrorList,Constants.TABLE_ITEM,null,null,null);
        }
            
        //Need to delete TEMP Files.
        CommonUtils.deleteTempFiles(tempFiles);
        }
        LOG.info("### Ending  FlatFileServiceImpl.processItemFlatFiles ####");
        return "Success";
    
    }
    
    
//PO Table status update
    @SuppressWarnings("unchecked")
	private synchronized void updatePoStatus(Map<String,List<String>> pkToSuccessList,Map<String,List<String>> pkToErrorList,String tableName,String globalId,String userName,String comment) {
    	LOG.info("### Starting in FlatFileServiceImpl.updateStatus ###");
        BatchUpdateReq updateReq =null;
        for (Map.Entry<String,List<String>> entry : pkToSuccessList.entrySet()){
            updateReq = new  BatchUpdateReq ();
            updateReq.setSuccess(true);
            updateReq.setErpName(entry.getKey().toUpperCase());
            HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
            try {
            	if(Constants.TABLE_PO_DETAILS.equals(tableName)){
            		List<TableEntity> poEntity = (List<TableEntity>)(List<?>) repo.getPoDetails(entry.getKey().toUpperCase(),entry.getValue(),tableName);
    				tableNameToEntityMap.put(tableName, poEntity);
    			}else if(Constants.TABLE_GR_DETAILS.equals(tableName)){
    				List<TableEntity> poEntity = (List<TableEntity>)(List<?>) repo.getPoDetails(entry.getKey().toUpperCase(),entry.getValue(),tableName);
    				tableNameToEntityMap.put(tableName, poEntity);
    			}else if(Constants.TABLE_ITEM.equals(tableName)){
    				List<TableEntity> poEntity = (List<TableEntity>)(List<?>) repo.getPoDetails(entry.getKey().toUpperCase(),entry.getValue(),tableName);
    				tableNameToEntityMap.put(tableName, poEntity);
    			}else if(Constants.TABLE_SUPPLIER.equals(tableName)){    				
    				List<TableEntity> poEntity = (List<TableEntity>)(List<?>) repo.getPoDetails(entry.getKey().toUpperCase(),entry.getValue(),tableName);
    				tableNameToEntityMap.put(tableName, poEntity);
    			}
            	updateReq.setTableNameToEntityMap(tableNameToEntityMap);
            	
            	if(globalId!=null){
            		updateReq.setComment(comment);
    				updateReq.setUserName(userName);
    				updateReq.setGlobalId(globalId);
            	}
                repo.batchUpdate(updateReq);    
            } catch (Exception e) {
                LOG.error("### Exception in  FlatFileServiceImpl.updateStatus ####",e);                
            }
        }
        
        for (Map.Entry<String,List<String>> entry : pkToErrorList.entrySet()){
            updateReq = new  BatchUpdateReq ();
            updateReq.setSuccess(false);
            updateReq.setErpName(entry.getKey().toUpperCase());
            HashMap<String,List<TableEntity>> tableNameToEntityMap = new HashMap<>();
            try {
            	
            	if(Constants.TABLE_PO_DETAILS.equals(tableName)){
            		List<TableEntity> poEntity = (List<TableEntity>)(List<?>) repo.getPoDetails(entry.getKey().toUpperCase(),entry.getValue(),tableName);
    				tableNameToEntityMap.put(tableName, poEntity);
    			}else if(Constants.TABLE_GR_DETAILS.equals(tableName)){
    				List<TableEntity> poEntity = (List<TableEntity>)(List<?>) repo.getPoDetails(entry.getKey().toUpperCase(),entry.getValue(),tableName);
    				tableNameToEntityMap.put(tableName, poEntity);
    			}else if(Constants.TABLE_ITEM.equals(tableName)){
    				List<TableEntity> poEntity = (List<TableEntity>)(List<?>) repo.getPoDetails(entry.getKey().toUpperCase(),entry.getValue(),tableName);
    				tableNameToEntityMap.put(tableName, poEntity);
    			}else if(Constants.TABLE_SUPPLIER.equals(tableName)){    				
    				List<TableEntity> poEntity = (List<TableEntity>)(List<?>) repo.getPoDetails(entry.getKey().toUpperCase(),entry.getValue(),tableName);
    				tableNameToEntityMap.put(tableName, poEntity);
    			}
                updateReq.setTableNameToEntityMap(tableNameToEntityMap);
                if(globalId!=null){
            		updateReq.setComment(comment);
    				updateReq.setUserName(userName);
    				updateReq.setGlobalId(globalId);
            	}
                repo.batchUpdate(updateReq);
            } catch (Exception e) {
                LOG.error("### Exception in  FlatFileServiceImpl.updateStatus ####",e);
            }
        }
        LOG.info("### Ending in FlatFileServiceImpl.updateStatus ###");
    }

	@Override
	public ProcessErrorRes processErrorPosFlatFiles(ProcessErrorReq req){
		LOG.info("### Starting  FlatFileServiceImpl.processErrorPosFlatFiles ####"+req);
		 String tempDir = System.getProperty("java.io.tmpdir");
	        
	        CommonUtils utils = new CommonUtils();
	        TreeMap<String,HashMap<Integer,String>> mappingList = utils.getDestMapping(config.getPoMappingFileUrl());
	        
	        Map<String,List<String>> pkToSuccessList = new HashMap<>();
	        Map<String,List<String>> pkToErrorList = new HashMap<>();
	        
	        ArrayList<String> files = new ArrayList<>();
	        List<File> tempFiles = new ArrayList<>();
	        
            FlatFileRes res=null;
			try {
				res = repo.getPoFlatFileData(req.getErpName().toUpperCase(),req.getErrorsList());
			} catch (InvalidKeyException | URISyntaxException | StorageException e1) {
				LOG.error("### Exception in   ####",e1);
			}
			
			LOG.info("res===>"+res);
            Map<String, String> rowKeyToPlantMap = res.getRowKeyToPlantMap();
            Map<String, String> rowKeyToSupptypeMap = res.getRowKeyToSupptypeMap();
            
            ArrayList<Map<String,List<HashMap<String, Object>>>> poList = res.getPoList();
            for (Map<String,List<HashMap<String, Object>>> poNumToItemListMap : poList) {
                
                for (Map.Entry<String,List<HashMap<String, Object>>> entry : poNumToItemListMap.entrySet()){
                    
                    String suppType = rowKeyToSupptypeMap.get(entry.getKey());
                    if(mappingList.containsKey(suppType)){
                        Map<String,List<String>> fileNameToRowsMap = utils.prepareSuppData(entry.getKey(), mappingList.get(suppType),entry.getValue(),rowKeyToPlantMap.get(entry.getKey()),Constants.MESSAGE_TYPE_PO);
                        
                        for (Map.Entry<String,List<String>> entry1 : fileNameToRowsMap.entrySet()){
                            File toFile = new File(tempDir+entry1.getKey());
                             try {
                                    FileUtils.writeLines(toFile,"UTF-8", entry1.getValue(),false);
                                    files.add(toFile.getAbsolutePath());
                                    tempFiles.add(toFile);
                                } catch (IOException e) {
                                    LOG.error("### Exception in   ####",e);
                                }
                        }
                    }
                }
            }
	            
	        SSHConnection conn =  new SSHConnection(config.getHostname(),config.getPort(),config.getUsername(), config.getPassword());
	        List<List<String>> finalList = new ArrayList<>();  
	        
	        try {
	            finalList = conn.sftpUpload(files, Constants.TARGET_DIR);
	        } catch (ClassNotFoundException | IOException e) {
	        	e.printStackTrace();
	        }
	        
	        LOG.info("finalList===>"+finalList);
	        ProcessErrorRes re = new ProcessErrorRes();
	        
	        if(finalList!=null ){
	            if(finalList.get(0).size()>0){
	            	List<String> lst = finalList.get(0);
	                pkToSuccessList.put(req.getErpName(),lst);
	                
	                List<String> lst1=new ArrayList<>();
	                for(int i=0;i<lst.size();i++){  
	                	lst1.add(lst.get(i).split("\\.")[0]);
	                }
	                re.setSuccessList(lst1);
	            }
	            if(finalList.get(1).size()>0){
	            	List<String> lst = finalList.get(1);
	                pkToErrorList.put(req.getErpName(),lst);
	                
	                List<String> lst1=new ArrayList<>();
	                for(int i=0;i<lst.size();i++){  
	                	lst1.add(lst.get(i).split("\\.")[0]);
	                }
	                re.setErrorList(lst1);
	            }
	        
	            //Update status in PODETAILS Table
		        updatePoStatus(pkToSuccessList,pkToErrorList,Constants.TABLE_PO_DETAILS,req.getGlobalId(),req.getUserName(),req.getComment());
	        }
	            
	        //Need to delete TEMP Files.
	        CommonUtils.deleteTempFiles(tempFiles);
	        LOG.info("### Ending  FlatFileServiceImpl.processErrorPosFlatFiles ####"+re);
		return re;
	}

	@Override
	public ProcessErrorRes processErrorGrFlatFiles(ProcessErrorReq req){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessErrorRes processErrorItemFlatFiles(ProcessErrorReq req){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessErrorRes processErrorSuppFlatFiles(ProcessErrorReq req){
		// TODO Auto-generated method stub
		return null;
	}
    
}
