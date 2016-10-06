package com.jci.flatfile.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.flatfile.utils.ProcessErrorReq;
import com.jci.flatfile.utils.ProcessErrorRes;
import com.microsoft.azure.storage.StorageException;


/**
 * The Interface FlatFileService.
 */
public interface FlatFileService {

    /**
     * Process po flat files.
     *
     * @return the string
     * @throws InvalidKeyException the invalid key exception
     * @throws URISyntaxException the URI syntax exception
     * @throws StorageException the storage exception
     */
    String processPoFlatFiles () throws InvalidKeyException, URISyntaxException, StorageException;
    
    /**
     * Process gr flat files.
     *
     * @return the string
     * @throws InvalidKeyException the invalid key exception
     * @throws URISyntaxException the URI syntax exception
     * @throws StorageException the storage exception
     */
    String processGrFlatFiles () throws InvalidKeyException, URISyntaxException, StorageException;
    
    /**
     * Process supp flat files.
     *
     * @return the string
     * @throws InvalidKeyException the invalid key exception
     * @throws URISyntaxException the URI syntax exception
     * @throws StorageException the storage exception
     */
    String processSuppFlatFiles () throws InvalidKeyException, URISyntaxException, StorageException;
    
    /**
     * Process item flat files.
     *
     * @return the string
     * @throws InvalidKeyException the invalid key exception
     * @throws URISyntaxException the URI syntax exception
     * @throws StorageException the storage exception
     */
    String processItemFlatFiles () throws InvalidKeyException, URISyntaxException, StorageException;
    
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
