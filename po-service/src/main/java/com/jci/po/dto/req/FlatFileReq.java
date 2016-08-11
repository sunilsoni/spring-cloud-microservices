package com.jci.po.dto.req;

import java.io.File;
import java.util.HashMap;

public class FlatFileReq {


	HashMap<String,File> nameToFileMap;

	public HashMap<String, File> getNameToFileMap() {
		return nameToFileMap;
	}

	public void setNameToFileMap(HashMap<String, File> nameToFileMap) {
		this.nameToFileMap = nameToFileMap;
	}

	@Override
	public String toString() {
		return "FlatFileReq [nameToFileMap=" + nameToFileMap + "]";
	}
	
	
}
