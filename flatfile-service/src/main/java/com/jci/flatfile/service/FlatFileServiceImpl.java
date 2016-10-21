package com.jci.flatfile.service;

import java.io.File;
import java.io.IOException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jci.config.FlatFile;
import com.jci.config.SSHConnection;
import com.jci.enums.ErrorEnum;
import com.jci.exception.ErrorService;
import com.jci.flatfile.exception.FlatFileException;
import com.jci.flatfile.repo.FlatFileRepo;
import com.jci.flatfile.utils.BatchUpdateReq;
import com.jci.flatfile.utils.FlatFileRes;
import com.jci.flatfile.utils.ProcessErrorReq;
import com.jci.flatfile.utils.ProcessErrorRes;
import com.jci.utils.CommonUtils;
import com.jci.utils.Constants;
import com.microsoft.azure.storage.table.TableEntity;
/**
 * The Class Flat FileServiceImpl.
 */
@Service
public class FlatFileServiceImpl implements FlatFileService{ // NO_UCD (unused code)

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(FlatFileServiceImpl.class);
    
    /** The repo. */
    @Autowired
    private FlatFileRepo repo;
    
    /** The all erps. */
    @Value("${all.erp.names}")
    private String allErps;
    
    /** The config. */
    @Autowired
    private FlatFile config;
    
    /** The git. */
    @Autowired
    private GitClientImpl git;
    
    /** The error service. */
    @Autowired
    private ErrorService errorService;
    
    @Value("${node.name:}")
    private String nodeName;
    
    /* (non-Javadoc)
     * @see com.jci.flatfile.service.FlatFileService#processPoFlatFiles()
     */
    @Override
    public String processPoFlatFiles() {
    	LOG.info("### Starting  FlatFileServiceImpl.processPoFlatFile ####");
        String tempDir = System.getProperty("java.io.tmpdir");
        CommonUtils utils = new CommonUtils();
        TreeMap<String,HashMap<Integer,String>> mappingList = null;        
    	try {
    		if("localhost".equals(nodeName)){
    			mappingList = utils.getDestMapping("C:/Apigee/micro-services/Work/backup-plan/supplier-collaboration-config-dev/po");
    		}else{
    			ResponseEntity<String>  gitRes = git.getPoJson();
    			mappingList = utils.getGitJsonMapping(Constants.SUPPLIER_TYPE_E2OPEN,gitRes.getBody());
    		}
    		
		} catch (Exception e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_READING_CONFIG_PO_FILE);
		}
        
        ArrayList<String> files = new ArrayList<>();
        String[] erpArr  = allErps.split(",");
        
        List<File> tempFiles = new ArrayList<>();
        for (int i=0;i<erpArr.length;i++){
            String partitionKey = erpArr[i].toUpperCase();
         //old   FlatFileRes res = repo.getPoFlatFileData(partitionKey,null);
            FlatFileRes res = repo.getCombinedData(partitionKey,Constants.TABLE_PO_DETAILS,null);
            Map<String, String> rowKeyToPlantMap = res.getRowKeyToPlantMap();
           // Map<String, String> rowKeyToSupptypeMap = res.getRowKeyToSupptypeMap();
            Map<String, String> plantToSupptypeMap = res.getPlantToSupptypeMap();
            
            Map<String,List<HashMap<String, Object>>> plantToRowsMap = res.getPlantToRowsMap();
            for (Map.Entry<String,List<HashMap<String, Object>>> entry : plantToRowsMap.entrySet()){
            	String suppType = plantToSupptypeMap.get(entry.getKey());
            	
            	if(mappingList.containsKey(suppType) && entry.getValue().size()>0){
            		List<HashMap<String, Object>> contentList = entry.getValue();
            		for (HashMap<String, Object> items : contentList){
            			Map<String,List<String>> fileNameToRowsMap = utils.prepareSuppData((String)items.get("orderNumber"), mappingList.get(suppType),items,entry.getKey(),Constants.MESSAGE_TYPE_PO);
                        for (Map.Entry<String,List<String>> entry1 : fileNameToRowsMap.entrySet()){
                            File toFile = new File(tempDir+entry1.getKey());
                             try {
                                   FileUtils.writeLines(toFile,"UTF-8", entry1.getValue(),false);
                                   files.add(toFile.getAbsolutePath());
                                   tempFiles.add(toFile);
                              }catch (IOException e) {
                                    LOG.error("### Exception in   ####",e);
                              }
                        }
            		}
                }
            }
            
        if(files.size()==0){
        	return "No Po files !";
        }
        SSHConnection conn =  new SSHConnection(config.getHostname(),config.getPort(),config.getUsername(), config.getPassword());
        List<List<String>> finalList = new ArrayList<>();  
        
        try {
            finalList = conn.sftpUpload(files, Constants.TARGET_DIR);
        } catch (ClassNotFoundException | IOException e) {
        	e.printStackTrace();
        }
        
        Map<String,List<String>> pkToSuccessList = new HashMap<>();
        Map<String,List<String>> pkToErrorList = new HashMap<>();
        
        if(finalList!=null ){
        	List<String> successStatus = new ArrayList<>();  
            if(finalList.get(0).size()>0){
            	List<String> list1 = finalList.get(0);
            	for (String val : list1) {
            		String[] splitVal = val.split("_");
            		String plantName = splitVal[4]; //Sunil: Need to verify this.
            		for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            			if(entry.getValue().equalsIgnoreCase(plantName)){
            				successStatus.add(entry.getKey());
            			}
            		}
            	}
            	
                pkToSuccessList.put(erpArr[i], successStatus);
            }
            if(finalList.get(1).size()>0){
            	List<String> errorStatus = new ArrayList<>();
            	List<String> list1 = finalList.get(0);
            	for (String val : list1) {
            		String[] splitVal = val.split("_");
            		String plantName = splitVal[4]; //Sunil: Need to verify this.
            		for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            			if(entry.getValue().equalsIgnoreCase(plantName)){
            				successStatus.add(entry.getKey());
            			}
            		}
            	}
                pkToErrorList.put(erpArr[i], errorStatus);
            }
            
          //Update status in PODETAILS Table
            updateStatus(pkToSuccessList,pkToErrorList,Constants.TABLE_PO_DETAILS,null,null,null,false);
        }
        
        
        //Need to delete TEMP Files.
        CommonUtils.deleteTempFiles(tempFiles);
        }
        LOG.info("### Ending  FlatFileServiceImpl.processPoFlatFile ####");
        return "Success";
    }

    /* (non-Javadoc)
     * @see com.jci.flatfile.service.FlatFileService#processGrFlatFiles()
     */
    @Override
    public String processGrFlatFiles() {
    	LOG.info("####### Starting  FlatFileServiceImpl.processGrFlatFiles #########");
        String tempDir = System.getProperty("java.io.tmpdir");
        CommonUtils utils = new CommonUtils();
        TreeMap<String,HashMap<Integer,String>> mappingList = null;
        
        try {
        	if("localhost".equals(nodeName)){
    			mappingList = utils.getDestMapping("C:/Apigee/micro-services/Work/backup-plan/supplier-collaboration-config-dev/gr");
    		}else{
    			ResponseEntity<String>  gitRes = git.getGrJson();
    			mappingList = utils.getGitJsonMapping(Constants.SUPPLIER_TYPE_E2OPEN,gitRes.getBody());
    		}
		} catch (IOException e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_READING_CONFIG_GR_FILE);
		}

        ArrayList<String> files = new ArrayList<>();
        String[] erpArr  = allErps.split(",");
        
        List<File> tempFiles = new ArrayList<>();
        for (int i=0;i<erpArr.length;i++){
            String partitionKey = erpArr[i].toUpperCase();
            
            FlatFileRes res = repo.getCombinedData(partitionKey,Constants.TABLE_GR_DETAILS,null);
            
            Map<String, String> rowKeyToPlantMap = res.getRowKeyToPlantMap();
            Map<String, String> plantToSupptypeMap = res.getPlantToSupptypeMap();
            
            Map<String,List<HashMap<String, Object>>> plantToRowsMap = res.getPlantToRowsMap();
            for (Map.Entry<String,List<HashMap<String, Object>>> entry : plantToRowsMap.entrySet()){
            	String suppType = plantToSupptypeMap.get(entry.getKey());
            	
            	if(mappingList.containsKey(suppType) && entry.getValue().size()>0){
            		List<HashMap<String, Object>> contentList = entry.getValue();
            		for (HashMap<String, Object> items : contentList){
            			Map<String,List<String>> fileNameToRowsMap = utils.prepareSuppData((String)items.get("receiptID"), mappingList.get(suppType),items,entry.getKey(),Constants.MESSAGE_TYPE_GR);
                        for (Map.Entry<String,List<String>> entry1 : fileNameToRowsMap.entrySet()){
                            File toFile = new File(tempDir+entry1.getKey());
                             try {
                                   FileUtils.writeLines(toFile,"UTF-8", entry1.getValue(),false);
                                   files.add(toFile.getAbsolutePath());
                                   tempFiles.add(toFile);
                              }catch (IOException e) {
                                    LOG.error("### Exception in   ####",e);
                              }
                        }
            		}
                }
            }
        if(files.size()==0){
        	return "No GR files !";
        }
            
        SSHConnection conn =  new SSHConnection(config.getHostname(),config.getPort(),config.getUsername(), config.getPassword());
        List<List<String>> finalList = new ArrayList<>();  
        
        try {
            finalList = conn.sftpUpload(files, Constants.TARGET_DIR);
        } catch (ClassNotFoundException | IOException e) {
        	 LOG.error("### Exception in FlatFile SFTP Upload   ####",e);
        }
        
        LOG.info("processGrFlatFiles finalList--->"+finalList);
        
        Map<String,List<String>> pkToSuccessList = new HashMap<>();
        Map<String,List<String>> pkToErrorList = new HashMap<>();
        
        if(finalList!=null ){
        	List<String> successStatus = new ArrayList<>();  
            if(finalList.get(0).size()>0){
            	List<String> list1 = finalList.get(0);
            	for (String val : list1) {
            		String[] splitVal = val.split("_");
            		String plantName = splitVal[4]; //Sunil: Need to verify this.
            		for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            			if(entry.getValue().equalsIgnoreCase(plantName)){
            				successStatus.add(entry.getKey());
            			}
            		}
            	}
            	
                pkToSuccessList.put(erpArr[i], successStatus);
            }
            if(finalList.get(1).size()>0){
            	List<String> errorStatus = new ArrayList<>();
            	List<String> list1 = finalList.get(0);
            	for (String val : list1) {
            		String[] splitVal = val.split("_");
            		String plantName = splitVal[4]; //Sunil: Need to verify this.
            		for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            			if(entry.getValue().equalsIgnoreCase(plantName)){
            				successStatus.add(entry.getKey());
            			}
            		}
            	}
                pkToErrorList.put(erpArr[i], errorStatus);
            }
            
          //Update status in PODETAILS Table
            updateStatus(pkToSuccessList,pkToErrorList,Constants.TABLE_GR_DETAILS,null,null,null,false);
        }
        
        //Need to delete TEMP Files.
        CommonUtils.deleteTempFiles(tempFiles);
        }
        LOG.info("### Ending  FlatFileServiceImpl.processGrFlatFiles ####");
        return "Success";
    
    }

    /* (non-Javadoc)
     * @see com.jci.flatfile.service.FlatFileService#processSuppFlatFiles()
     */
    @Override
    public String processSuppFlatFiles() {
    	LOG.info("### Starting  FlatFileServiceImpl.processSuppFlatFiles ####");
        String tempDir = System.getProperty("java.io.tmpdir");
        
        CommonUtils utils = new CommonUtils();
        TreeMap<String,HashMap<Integer,String>> mappingList = null;
        
        try {
        	if("localhost".equals(nodeName)){
    			mappingList = utils.getDestMapping("C:/Apigee/micro-services/Work/backup-plan/supplier-collaboration-config-dev/supplier");
    		}else{
    			ResponseEntity<String>  gitRes = git.getSuppJson();
    			mappingList = utils.getGitJsonMapping(Constants.SUPPLIER_TYPE_E2OPEN,gitRes.getBody());
    		}
		} catch (IOException e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_READING_CONFIG_SUPP_FILE);
		}
        
        
        ArrayList<String> files = new ArrayList<>();
        String[] erpArr  = allErps.split(",");
        
        List<File> tempFiles = new ArrayList<>();
        for (int i=0;i<erpArr.length;i++){
            String partitionKey = erpArr[i].toUpperCase();
            
            FlatFileRes res = repo.getFlatFileData(partitionKey,Constants.TABLE_SUPPLIER);
            Map<String, String> rowKeyToPlantMap = res.getRowKeyToPlantMap();
            Map<String,List<HashMap<String, Object>>> plantToRowsMap = res.getPlantToRowsMap();
            Map<String, String> plantToSupptypeMap = res.getPlantToSupptypeMap();
            
            for (Map.Entry<String,List<HashMap<String, Object>>> entry : plantToRowsMap.entrySet()){
            	String suppType = plantToSupptypeMap.get(entry.getKey());
            	
            	if(mappingList.containsKey(suppType) && entry.getValue().size()>0){
            		List<HashMap<String, Object>> contentList = entry.getValue();
            		for (HashMap<String, Object> items : contentList){
            			Map<String,List<String>> fileNameToRowsMap = utils.prepareSuppData(null, mappingList.get(suppType),items,entry.getKey(),Constants.MESSAGE_TYPE_SUPP);
                        
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
            
        if(files.size()==0){
        	return "No files !";
        }
            
        SSHConnection conn =  new SSHConnection(config.getHostname(),config.getPort(),config.getUsername(), config.getPassword());
        List<List<String>> finalList = new ArrayList<>();
        
        try {
            finalList = conn.sftpUpload(files, Constants.TARGET_DIR);
        } catch (ClassNotFoundException | IOException e) {
        	e.printStackTrace();
        }
        LOG.info("processSuppFlatFiles finalList--->"+finalList);
        
        Map<String,List<String>> pkToSuccessList = new HashMap<>();
        Map<String,List<String>> pkToErrorList = new HashMap<>();
        
        if(finalList!=null ){
        	List<String> successStatus = new ArrayList<>();  
            if(finalList.get(0).size()>0){
            	List<String> list1 = finalList.get(0);
            	for (String val : list1) {
            		String[] splitVal = val.split("_");
            		String plantName = splitVal[4]; //Sunil: Need to verify this.
            		for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            			if(entry.getValue().equalsIgnoreCase(plantName)){
            				successStatus.add(entry.getKey());
            			}
            		}
            	}
            	
                pkToSuccessList.put(erpArr[i], successStatus);
            }
            if(finalList.get(1).size()>0){
            	List<String> errorStatus = new ArrayList<>();
            	List<String> list1 = finalList.get(0);
            	for (String val : list1) {
            		String[] splitVal = val.split("_");
            		String plantName = splitVal[4]; //Sunil: Need to verify this.
            		for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            			if(entry.getValue().equalsIgnoreCase(plantName)){
            				successStatus.add(entry.getKey());
            			}
            		}
            	}
                pkToErrorList.put(erpArr[i], errorStatus);
            }
            
          //Update status in PODETAILS Table
            updateStatus(pkToSuccessList,pkToErrorList,Constants.TABLE_SUPPLIER,null,null,null,false);
        }
        
        //Need to delete TEMP Files.
        CommonUtils.deleteTempFiles(tempFiles);
        }
        LOG.info("### Ending  FlatFileServiceImpl.processSuppFlatFiles ####");
        return "Success";
    
    }

    /* (non-Javadoc)
     * @see com.jci.flatfile.service.FlatFileService#processItemFlatFiles()
     */
    @Override
    public String processItemFlatFiles() {
    	LOG.info("### Starting  FlatFileServiceImpl.processItemFlatFiles ####");
        String tempDir = System.getProperty("java.io.tmpdir");
        
        CommonUtils utils = new CommonUtils();
        TreeMap<String,HashMap<Integer,String>> mappingList = null;

        try {
        	if("localhost".equals(nodeName)){
    			mappingList = utils.getDestMapping("C:/Apigee/micro-services/Work/backup-plan/supplier-collaboration-config-dev/item");
    		}else{
    			ResponseEntity<String>  gitRes = git.getItemJson();
    			mappingList = utils.getGitJsonMapping(Constants.SUPPLIER_TYPE_E2OPEN,gitRes.getBody());
    		}
		} catch (IOException e) {
			throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_READING_CONFIG_ITEM_FILE);
		}
        
        
        ArrayList<String> files = new ArrayList<>();
        String[] erpArr  = allErps.split(",");
        
        List<File> tempFiles = new ArrayList<>();
        for (int i=0;i<erpArr.length;i++){
            String partitionKey = erpArr[i].toUpperCase();
            
            FlatFileRes res = repo.getFlatFileData(partitionKey,Constants.TABLE_ITEM);
            
            Map<String, String> rowKeyToPlantMap = res.getRowKeyToPlantMap();
            Map<String,List<HashMap<String, Object>>> plantToRowsMap = res.getPlantToRowsMap();
            Map<String, String> plantToSupptypeMap = res.getPlantToSupptypeMap();
            
            for (Map.Entry<String,List<HashMap<String, Object>>> entry : plantToRowsMap.entrySet()){
                
            	String suppType = plantToSupptypeMap.get(entry.getKey());
                if(mappingList.containsKey(suppType) && entry.getValue().size()>0){
                	List<HashMap<String, Object>> contentList = entry.getValue();
            		for (HashMap<String, Object> items : contentList){
            			 Map<String,List<String>> fileNameToRowsMap = utils.prepareSuppData(null, mappingList.get(suppType),items,entry.getKey(),Constants.MESSAGE_TYPE_ITEM);
                         
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
            }
            
            if(files.size()==0){
            	return "No Item files !";
            }
            
        SSHConnection conn =  new SSHConnection(config.getHostname(),config.getPort(),config.getUsername(), config.getPassword());
        List<List<String>> finalList = new ArrayList<>();  
        
        try {
            finalList = conn.sftpUpload(files, Constants.TARGET_DIR);
        } catch (ClassNotFoundException | IOException e) {
        	e.printStackTrace();
        }
        
        LOG.info("processItemFlatFiles finalList===>"+finalList);
        
        if(finalList!=null ){
        	Map<String,List<String>> pkToSuccessList = new HashMap<>();
            Map<String,List<String>> pkToErrorList = new HashMap<>();
            
        	List<String> successStatus = new ArrayList<>();  
            if(finalList.get(0).size()>0){
            	List<String> list1 = finalList.get(0);
            	for (String val : list1) {
            		String[] splitVal = val.split("_");
            		String plantName = splitVal[4]; //Sunil: Need to verify this.
            		for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            			if(entry.getValue().equalsIgnoreCase(plantName)){
            				successStatus.add(entry.getKey());
            			}
            		}
            	}
            	
                pkToSuccessList.put(erpArr[i], successStatus);
            }
            if(finalList.get(1).size()>0){
            	List<String> errorStatus = new ArrayList<>();
            	List<String> list1 = finalList.get(0);
            	for (String val : list1) {
            		String[] splitVal = val.split("_");
            		String plantName = splitVal[4]; 
            		for (Map.Entry<String, String> entry : rowKeyToPlantMap.entrySet()) {
            			if(entry.getValue().equalsIgnoreCase(plantName)){
            				successStatus.add(entry.getKey());
            			}
            		}
            	}
                pkToErrorList.put(erpArr[i], errorStatus);
            }
            //Update status in PODETAILS Table
            updateStatus(pkToSuccessList,pkToErrorList,Constants.TABLE_ITEM,null,null,null,false);
        }
            
        //Need to delete TEMP Files.
        CommonUtils.deleteTempFiles(tempFiles);
        }
        LOG.info("### Ending  FlatFileServiceImpl.processItemFlatFiles ####");
        return "Success";
    
    }
    
    
/**
 * Update status.
 *
 * @param pkToSuccessList the pk to success list
 * @param pkToErrorList the pk to error list
 * @param tableName the table name
 * @param globalId the global id
 * @param userName the user name
 * @param comment the comment
 * @param isErrorReq the is error req
 */
//Table status update
    @SuppressWarnings("unchecked")
	private synchronized void updateStatus(Map<String,List<String>> pkToSuccessList,Map<String,List<String>> pkToErrorList,String tableName,String globalId,String userName,String comment,boolean isErrorReq) {
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
            	
            	updateReq.setErrorReq(isErrorReq);
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
                updateReq.setErrorReq(isErrorReq);
                repo.batchUpdate(updateReq);
            } catch (Exception e) {
                LOG.error("### Exception in  FlatFileServiceImpl.updateStatus ####",e);
            }
        }
        LOG.info("### Ending in FlatFileServiceImpl.updateStatus ###");
    }

	/* (non-Javadoc)
	 * @see com.jci.flatfile.service.FlatFileService#processErrorPosFlatFiles(com.jci.flatfile.utils.ProcessErrorReq)
	 */
	@Override
	public ProcessErrorRes processErrorPosFlatFiles(ProcessErrorReq req){
		LOG.info("### Starting  FlatFileServiceImpl.processErrorPosFlatFiles ####"+req);
		 String tempDir = System.getProperty("java.io.tmpdir");
	        
	        CommonUtils utils = new CommonUtils();
	        TreeMap<String,HashMap<Integer,String>> mappingList = null;
	        
	        try {
	        	if("localhost".equals(nodeName)){
	    			mappingList = utils.getDestMapping("C:/Apigee/micro-services/Work/backup-plan/supplier-collaboration-config-dev/po");
	    		}else{
	    			ResponseEntity<String>  gitRes = git.getPoJson();
					mappingList = utils.getGitJsonMapping(Constants.SUPPLIER_TYPE_E2OPEN,gitRes.getBody());
	    		}
			} catch (IOException e) {
				throw errorService.createException(FlatFileException.class, e, ErrorEnum.ERROR_READING_CONFIG_PO_FILE);
			}
	        
	        Map<String,List<String>> pkToSuccessList = new HashMap<>();
	        Map<String,List<String>> pkToErrorList = new HashMap<>();
	        
	        ArrayList<String> files = new ArrayList<>();
	        List<File> tempFiles = new ArrayList<>();
	        
           // FlatFileRes res = repo.getPoFlatFileData(req.getErpName().toUpperCase(),req.getErrorsList());
	        FlatFileRes res = repo.getCombinedData(req.getErpName().toUpperCase(),Constants.TABLE_PO_DETAILS,req.getErrorsList());
            Map<String, String> rowKeyToPlantMap = res.getRowKeyToPlantMap();
           // Map<String, String> rowKeyToSupptypeMap = res.getRowKeyToSupptypeMap();
            Map<String, String> plantToSupptypeMap = res.getPlantToSupptypeMap();
            Map<String,List<HashMap<String, Object>>> plantToRowsMap = res.getPlantToRowsMap();
			
            Map<String, String> rowKeyToSupptypeMap = res.getRowKeyToSupptypeMap();
            
            ArrayList<Map<String,List<HashMap<String, Object>>>> poList = res.getPoList();
            for (Map<String,List<HashMap<String, Object>>> poNumToItemListMap : poList) {
                
                for (Map.Entry<String,List<HashMap<String, Object>>> entry : poNumToItemListMap.entrySet()){
                    String suppType = rowKeyToSupptypeMap.get(entry.getKey());
                    List<HashMap<String, Object>> contentList = entry.getValue();
            		for (HashMap<String, Object> items : contentList){
            			if(mappingList.containsKey(suppType)){
                            Map<String,List<String>> fileNameToRowsMap = utils.prepareSuppData(entry.getKey(), mappingList.get(suppType),items,rowKeyToPlantMap.get(entry.getKey()),Constants.MESSAGE_TYPE_PO);
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
            }
	            
	        SSHConnection conn =  new SSHConnection(config.getHostname(),config.getPort(),config.getUsername(), config.getPassword());
	        List<List<String>> finalList = new ArrayList<>();  
	        
	        try {
	            finalList = conn.sftpUpload(files, Constants.TARGET_DIR);
	        } catch (ClassNotFoundException | IOException e) {
	        	e.printStackTrace();
	        }
	        
	         
	        LOG.info("processErrorPosFlatFiles finalList===>"+finalList);
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
		        updateStatus(pkToSuccessList,pkToErrorList,Constants.TABLE_PO_DETAILS,req.getGlobalId(),req.getUserName(),req.getComment(),true);
	        }
	            
	        //Need to delete TEMP Files.
	        CommonUtils.deleteTempFiles(tempFiles);
	        LOG.info("### Ending  FlatFileServiceImpl.processErrorPosFlatFiles ####"+re);
		return re;
	}

	/* (non-Javadoc)
	 * @see com.jci.flatfile.service.FlatFileService#processErrorGrFlatFiles(com.jci.flatfile.utils.ProcessErrorReq)
	 */
	@Override
	public ProcessErrorRes processErrorGrFlatFiles(ProcessErrorReq req){
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jci.flatfile.service.FlatFileService#processErrorItemFlatFiles(com.jci.flatfile.utils.ProcessErrorReq)
	 */
	@Override
	public ProcessErrorRes processErrorItemFlatFiles(ProcessErrorReq req){
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jci.flatfile.service.FlatFileService#processErrorSuppFlatFiles(com.jci.flatfile.utils.ProcessErrorReq)
	 */
	@Override
	public ProcessErrorRes processErrorSuppFlatFiles(ProcessErrorReq req){
		return null;
	}
    
}
