package com.jci.flatfile.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.jci.flatfile.utils.ProcessErrorReq;
import com.jci.flatfile.utils.ProcessErrorRes;
import com.microsoft.azure.storage.StorageException;

public interface FlatFileService {

    String processPoFlatFiles () throws InvalidKeyException, URISyntaxException, StorageException;
    String processGrFlatFiles () throws InvalidKeyException, URISyntaxException, StorageException;
    String processSuppFlatFiles () throws InvalidKeyException, URISyntaxException, StorageException;
    String processItemFlatFiles () throws InvalidKeyException, URISyntaxException, StorageException;
    
    ProcessErrorRes processErrorPosFlatFiles(ProcessErrorReq req);
    ProcessErrorRes processErrorGrFlatFiles(ProcessErrorReq req);
    ProcessErrorRes processErrorItemFlatFiles(ProcessErrorReq req);
    ProcessErrorRes processErrorSuppFlatFiles(ProcessErrorReq req);
}
