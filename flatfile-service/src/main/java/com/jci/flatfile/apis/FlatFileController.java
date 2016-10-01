package com.jci.flatfile.apis;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jci.flatfile.service.FlatFileService;
import com.jci.flatfile.utils.ProcessErrorReq;
import com.jci.flatfile.utils.ProcessErrorRes;
import com.microsoft.azure.storage.StorageException;

@RestController
public class FlatFileController {

	 @Autowired
	 FlatFileService service;
	 
	 private static final Logger LOG = LoggerFactory.getLogger(FlatFileController.class);
	 
	@RequestMapping(value = "/processPoFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processPoFlatFiles() {
		LOG.info("### Starting  FlatFileController.processPoFlatFiles ####");
			Executor executor = Executors.newSingleThreadExecutor();
			executor.execute(new Runnable() { 
				public void run() {
					LOG.info("<==========Starting PO FlatFile New thread =========>"); 
					try {
						service.processPoFlatFiles();
					} catch (InvalidKeyException | URISyntaxException | StorageException e) {
						LOG.error("### Exception in  FlatFileController.processPoFlatFiles ####");
					} 
				}
			});
		LOG.info("### Ending  FlatFileController.processPoFlatFiles ####");
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/processGrFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processGrFlatFiles() {
		LOG.info("### Starting  FlatFileController.processGrFlatFiles ####");
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() { 
			public void run() {
				LOG.info("<==========Starting GR FlatFile New thread =========>"); 
				try {
					service.processGrFlatFiles();
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					LOG.error("### Exception in  FlatFileController.processGrFlatFiles ####");
				} 
			}
		});
	LOG.info("### Ending  FlatFileController.processGrFlatFiles ####");
	return "SUCCESS";
}
	
	@RequestMapping(value = "/processSuppFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processSuppFlatFiles() {
		LOG.info("### Starting  FlatFileController.processSuppFlatFiles ####");
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() { 
			public void run() {
				LOG.info("<==========Starting SUPP FlatFile New thread =========>"); 
				try {
					service.processSuppFlatFiles();
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					LOG.error("### Exception in  FlatFileController.processSuppFlatFiles ####");
				} 
			}
		});
	LOG.info("### Ending  FlatFileController.processSuppFlatFiles ####");
	return "SUCCESS";
   }
	
	@RequestMapping(value = "/processItemFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String processItemFlatFiles() {
		LOG.info("### Starting  FlatFileController.processItemFlatFiles ####");
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() { 
			public void run() {
				LOG.info("<==========Starting ITEM FlatFile New thread =========>"); 
				try {
					service.processItemFlatFiles();
				} catch (InvalidKeyException | URISyntaxException | StorageException e) {
					LOG.error("### Exception in  FlatFileController.processItemFlatFiles ####");
				} 
			}
		});
	LOG.info("### Ending  FlatFileController.processItemFlatFiles ####");
	return "SUCCESS";
   }
	
	@RequestMapping(value = "/processErrorPosFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProcessErrorRes processErrorPosFlatFiles(@RequestBody ProcessErrorReq req)   {
		LOG.info("### Starting  Ending FlatFileController.processErrorPosFlatFiles ####"+req);
		try {
			service.processItemFlatFiles();
		} catch (InvalidKeyException | URISyntaxException | StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service.processErrorPosFlatFiles(req);
	}
	
	@RequestMapping(value = "/processErrorGrFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProcessErrorRes processErrorGrFlatFiles(@RequestBody ProcessErrorReq req) {
		return service.processErrorGrFlatFiles(req);
	}
	
	@RequestMapping(value = "/processErrorItemFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProcessErrorRes processErrorItemFlatFiles(@RequestBody ProcessErrorReq req) {
		return service.processErrorItemFlatFiles(req);
	}
	
	@RequestMapping(value = "/processErrorSuppFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProcessErrorRes processErrorSuppFlatFiles(@RequestBody ProcessErrorReq req) {
		return service.processErrorSuppFlatFiles(req);
	}
	
}
