package com.jci.flatfile.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatFileRes {

    private Map<String, String> rowKeyToPlantMap;
    private List<HashMap<String, Object>> rowList;
    private Map<String, String> rowKeyToSupptypeMap;
    private ArrayList<Map<String,List<HashMap<String, Object>>>> poList;
    
    public Map<String, String> getRowKeyToPlantMap() {
        return rowKeyToPlantMap;
    }
    public void setRowKeyToPlantMap(Map<String, String> rowKeyToPlantMap) {
        this.rowKeyToPlantMap = rowKeyToPlantMap;
    }
    public List<HashMap<String, Object>> getList() {
        return rowList;
    }
    public void setList(List<HashMap<String, Object>> rowList) {
        this.rowList = rowList;
    }
    
    public List<HashMap<String, Object>> getRowList() {
        return rowList;
    }
    public void setRowList(List<HashMap<String, Object>> rowList) {
        this.rowList = rowList;
    }
    public ArrayList<Map<String, List<HashMap<String, Object>>>> getPoList() {
        return poList;
    }
    public void setPoList(ArrayList<Map<String, List<HashMap<String, Object>>>> poList) {
        this.poList = poList;
    }
    public FlatFileRes(){
        
    }
    
    public FlatFileRes(Map<String, String> rowKeyToPlantMap,Map<String, String> rowKeyToSupptypeMap, List<HashMap<String, Object>> rowList) {
        super();
        this.rowKeyToPlantMap = rowKeyToPlantMap;
        this.rowList = rowList;
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
        return "FlatFileRes [rowKeyToPlantMap=" + rowKeyToPlantMap + ", rowList=" + rowList + ", rowKeyToSupptypeMap="
                + rowKeyToSupptypeMap + ", poList=" + poList + "]";
    }
    
}
