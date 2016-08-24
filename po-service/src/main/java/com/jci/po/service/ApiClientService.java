package com.jci.po.service;

import com.jci.po.dto.req.FlatFileReq;
import com.jci.po.dto.req.TempRequest;
import com.jci.po.dto.res.FlatFileRes;
import com.jci.po.dto.res.TempResponse;

public interface ApiClientService {
	
	FlatFileRes processFlatFile(FlatFileReq req) ;
	
	TempResponse postSupplierData(TempRequest req);

}
