package com.jci.flatfile.service;

import com.jci.flatfile.utils.ProcessErrorReq;
import com.jci.flatfile.utils.ProcessErrorRes;

/**
 * The Interface FlatFileService.
 */
public interface FlatFileService {

    /**
     * Process po flat files.
     *
     * @return the string
     */
    String processPoFlatFiles () ;
    
    /**
     * Process gr flat files.
     *
     * @return the string
     */
    String processGrFlatFiles () ;
    
    /**
     * Process supp flat files.
     *
     * @return the string
     */
    String processSuppFlatFiles () ;
    
    /**
     * Process item flat files.
     *
     * @return the string
     */
    String processItemFlatFiles () ;
    
    /**
     * Process error pos flat files.
     *
     * @param req the req
     * @return the process error res
     */
    ProcessErrorRes processErrorPosFlatFiles(ProcessErrorReq req);
    
    /**
     * Process error gr flat files.
     *
     * @param req the req
     * @return the process error res
     */
    ProcessErrorRes processErrorGrFlatFiles(ProcessErrorReq req);
    
    /**
     * Process error item flat files.
     *
     * @param req the req
     * @return the process error res
     */
    ProcessErrorRes processErrorItemFlatFiles(ProcessErrorReq req);
    
    /**
     * Process error supp flat files.
     *
     * @param req the req
     * @return the process error res
     */
    ProcessErrorRes processErrorSuppFlatFiles(ProcessErrorReq req);
}
