package com.jci.job.service;

import com.jci.job.api.res.ItemDetailsRes;
import com.jci.job.api.res.PoDetailsRes;
import com.jci.job.api.res.SupplierDetailsRes;

public interface ApiClientService {
	
	PoDetailsRes getPoDetails() ;
	ItemDetailsRes getItemDetails() ;
	SupplierDetailsRes getSupplierDetails() ;

}
