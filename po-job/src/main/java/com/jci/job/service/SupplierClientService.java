package com.jci.job.service;

import org.springframework.web.multipart.MultipartFile;

public interface SupplierClientService {

	
	String sendFlatFile(String filename, MultipartFile file) ;
}
