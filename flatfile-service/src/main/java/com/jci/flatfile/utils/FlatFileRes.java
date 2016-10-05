package com.jci.flatfile.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatFileRes {

	List<HashMap<String, Object>> rowList;
    private Map<String, String> rowKeyToPlantMap;
    private ArrayList<Map<String,List<HashMap<String, Object>>>> poList;
    private Map<String,List<HashMap<String, Object>>> plantToRowsMap ;
    Map<String, String> plantToSupptypeMap;
    Map<String, String> rowKeyToSupptypeMap;
    
    public List<HashMap<String, Object>> getRowList() {
		return rowList;
	}
	public void setRowList(List<HashMap<String, Object>> rowList) {
		this.rowList = rowList;
	}
	public Map<String, String> getPlantToSupptypeMap() {
		return plantToSupptypeMap;
	}
	public void setPlantToSupptypeMap(Map<String, String> plantToSupptypeMap) {
		this.plantToSupptypeMap = plantToSupptypeMap;
	}
	public Map<String, List<HashMap<String, Object>>> getPlantToRowsMap() {
		return plantToRowsMap;
	}
	public void setPlantToRowsMap(Map<String, List<HashMap<String, Object>>> plantToRowsMap) {
		this.plantToRowsMap = plantToRowsMap;
	}
	public Map<String, String> getRowKeyToPlantMap() {
        return rowKeyToPlantMap;
    }
    public void setRowKeyToPlantMap(Map<String, String> rowKeyToPlantMap) {
        this.rowKeyToPlantMap = rowKeyToPlantMap;
    }
    public ArrayList<Map<String, List<HashMap<String, Object>>>> getPoList() {
        return poList;
    }
    public void setPoList(ArrayList<Map<String, List<HashMap<String, Object>>>> poList) {
        this.poList = poList;
    }
    public FlatFileRes(){
        
    }
    
    
    public FlatFileRes(Map<String, String> rowKeyToPlantMap, Map<String, String> rowKeyToSupptypeMap,
            ArrayList<Map<String, List<HashMap<String, Object>>>> poList) {
        super();
        this.rowKeyToPlantMap = rowKeyToPlantMap;
        this.rowKeyToSupptypeMap = rowKeyToSupptypeMap;
        this.poList = poList;
    }
    
    
    public Map<String, String> getRowKeyToSupptypeMap() {
        return rowKeyToSupptypeMap;
    }
    public void setRowKeyToSupptypeMap(Map<String, String> rowKeyToSupptypeMap) {
        this.rowKeyToSupptypeMap = rowKeyToSupptypeMap;
    }
	@Override
	public String toString() {
		return "FlatFileRes [rowList=" + rowList + ", rowKeyToPlantMap=" + rowKeyToPlantMap + ", poList=" + poList
				+ ", plantToRowsMap=" + plantToRowsMap + ", plantToSupptypeMap=" + plantToSupptypeMap
				+ ", rowKeyToSupptypeMap=" + rowKeyToSupptypeMap + "]";
	}
    
    


    
}
