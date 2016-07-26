package com.jci.po.azure.data;

import java.util.HashMap;

public interface DataResolver {
	
	HashMap<String,Object> resolve(HashMap<String,Object> map) throws Exception;
}
