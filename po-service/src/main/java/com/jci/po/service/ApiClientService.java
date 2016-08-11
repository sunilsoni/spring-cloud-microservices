package com.jci.po.service;

import com.jci.po.dto.req.FlatFileReq;
import com.jci.po.dto.res.FlatFileRes;

public interface ApiClientService {
	
	FlatFileRes processFlatFile(FlatFileReq req) ;

}
