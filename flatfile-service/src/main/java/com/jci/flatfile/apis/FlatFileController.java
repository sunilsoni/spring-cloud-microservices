package com.jci.flatfile.apis;

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
/**
 * Rest Layer for processing flat files.
 */
@RestController
public class FlatFileController { // NO_UCD (unused code)

	 /** The service. */
 	@Autowired
	 FlatFileService service;
	 
	 /** The Constant LOG. */
 	private static final Logger LOG = LoggerFactory.getLogger(FlatFileController.class);
	 
	/**
	 * Process po flat files.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/processPoFlatFiles", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public String processPoFlatFiles() {
			Executor executor = Executors.newSingleThreadExecutor();
			executor.execute(new Runnable() { 
				public void run() {
					LOG.info("<==========Starting PO FlatFile New thread =========>"); 
					service.processPoFlatFiles();
				}
			});
		return "SUCCESS";
	}
	
	/**
	 * Process gr flat files.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/processGrFlatFiles",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public String processGrFlatFiles() {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() { 
			public void run() {
				LOG.info("<==========Starting GR FlatFile New thread =========>"); 
				service.processGrFlatFiles();
			}
		});
	return "SUCCESS";
}
	
	/**
	 * Process supp flat files.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/processSuppFlatFiles",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public String processSuppFlatFiles() {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() { 
			public void run() {
				LOG.info("<==========Starting SUPP FlatFile New thread =========>"); 
				service.processSuppFlatFiles();
			}
		});
	return "SUCCESS";
   }
	
	/**
	 * Process item flat files.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/processItemFlatFiles",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public String processItemFlatFiles() {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() { 
			public void run() {
				LOG.info("<==========Starting ITEM FlatFile New thread =========>"); 
				service.processItemFlatFiles();
			}
		});
	return "SUCCESS";
   }
	
	/**
	 * Process error pos flat files.
	 *
	 * @param req the req
	 * @return the process error res
	 */
	@RequestMapping(value = "/processErrorPosFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProcessErrorRes processErrorPosFlatFiles(@RequestBody ProcessErrorReq req)   {
		LOG.info("### Starting  Ending FlatFileController.processErrorPosFlatFiles ####"+req);
		return service.processErrorPosFlatFiles(req);
	}
	
	/**
	 * Process error gr flat files.
	 *
	 * @param req the req
	 * @return the process error res
	 */
	@RequestMapping(value = "/processErrorGrFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProcessErrorRes processErrorGrFlatFiles(@RequestBody ProcessErrorReq req) {
		return service.processErrorGrFlatFiles(req);
	}
	
	/**
	 * Process error item flat files.
	 *
	 * @param req the req
	 * @return the process error res
	 */
	@RequestMapping(value = "/processErrorItemFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProcessErrorRes processErrorItemFlatFiles(@RequestBody ProcessErrorReq req) {
		return service.processErrorItemFlatFiles(req);
	}
	
	/**
	 * Process error supp flat files.
	 *
	 * @param req the req
	 * @return the process error res
	 */
	@RequestMapping(value = "/processErrorSuppFlatFiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProcessErrorRes processErrorSuppFlatFiles(@RequestBody ProcessErrorReq req) {
		return service.processErrorSuppFlatFiles(req);
	}
	
}
