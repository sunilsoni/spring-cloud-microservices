package com.jci.flatfile.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * The Class FlatFileRes.
 */
public class FlatFileRes {

	/** The row list. */
	private List<HashMap<String, Object>> rowList;
    
    /** The row key to plant map. */
    private Map<String, String> rowKeyToPlantMap;
    
    /** The po list. */
    private ArrayList<Map<String,List<HashMap<String, Object>>>> poList;
    
    /** The plant to rows map. */
    private Map<String,List<HashMap<String, Object>>> plantToRowsMap ;
    
    /** The plant to supptype map. */
    private Map<String, String> plantToSupptypeMap;
    
    /** The row key to supptype map. */
    private Map<String, String> rowKeyToSupptypeMap;
    
    /**
     * Gets the row list.
     *
     * @return the row list
     */
    public List<HashMap<String, Object>> getRowList() {
		return rowList;
	}
	
	/**
	 * Sets the row list.
	 *
	 * @param rowList the row list
	 */
	public void setRowList(List<HashMap<String, Object>> rowList) {
		this.rowList = rowList;
	}
	
	/**
	 * Gets the plant to supptype map.
	 *
	 * @return the plant to supptype map
	 */
	public Map<String, String> getPlantToSupptypeMap() {
		return plantToSupptypeMap;
	}
	
	/**
	 * Sets the plant to supptype map.
	 *
	 * @param plantToSupptypeMap the plant to supptype map
	 */
	public void setPlantToSupptypeMap(Map<String, String> plantToSupptypeMap) {
		this.plantToSupptypeMap = plantToSupptypeMap;
	}
	
	/**
	 * Gets the plant to rows map.
	 *
	 * @return the plant to rows map
	 */
	public Map<String, List<HashMap<String, Object>>> getPlantToRowsMap() {
		return plantToRowsMap;
	}
	
	/**
	 * Sets the plant to rows map.
	 *
	 * @param plantToRowsMap the plant to rows map
	 */
	public void setPlantToRowsMap(Map<String, List<HashMap<String, Object>>> plantToRowsMap) {
		this.plantToRowsMap = plantToRowsMap;
	}
	
	/**
	 * Gets the row key to plant map.
	 *
	 * @return the row key to plant map
	 */
	public Map<String, String> getRowKeyToPlantMap() {
        return rowKeyToPlantMap;
    }
    
    /**
     * Sets the row key to plant map.
     *
     * @param rowKeyToPlantMap the row key to plant map
     */
    public void setRowKeyToPlantMap(Map<String, String> rowKeyToPlantMap) {
        this.rowKeyToPlantMap = rowKeyToPlantMap;
    }
    
    /**
     * Gets the po list.
     *
     * @return the po list
     */
    public ArrayList<Map<String, List<HashMap<String, Object>>>> getPoList() {
        return poList;
    }
    
    /**
     * Sets the po list.
     *
     * @param poList the po list
     */
    public void setPoList(ArrayList<Map<String, List<HashMap<String, Object>>>> poList) {
        this.poList = poList;
    }
    
    /**
     * Instantiates a new flat file res.
     */
    public FlatFileRes(){
        
    }
    
    
    /**
     * Instantiates a new flat file res.
     *
     * @param rowKeyToPlantMap the row key to plant map
     * @param rowKeyToSupptypeMap the row key to supptype map
     * @param poList the po list
     */
    public FlatFileRes(Map<String, String> rowKeyToPlantMap, Map<String, String> rowKeyToSupptypeMap,
            ArrayList<Map<String, List<HashMap<String, Object>>>> poList) {
        super();
        this.rowKeyToPlantMap = rowKeyToPlantMap;
        this.rowKeyToSupptypeMap = rowKeyToSupptypeMap;
        this.poList = poList;
    }
    
    
    /**
     * Gets the row key to supptype map.
     *
     * @return the row key to supptype map
     */
    public Map<String, String> getRowKeyToSupptypeMap() {
        return rowKeyToSupptypeMap;
    }
    
    /**
     * Sets the row key to supptype map.
     *
     * @param rowKeyToSupptypeMap the row key to supptype map
     */
    public void setRowKeyToSupptypeMap(Map<String, String> rowKeyToSupptypeMap) {
        this.rowKeyToSupptypeMap = rowKeyToSupptypeMap;
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlatFileRes [rowList=" + rowList + ", rowKeyToPlantMap=" + rowKeyToPlantMap + ", poList=" + poList
				+ ", plantToRowsMap=" + plantToRowsMap + ", plantToSupptypeMap=" + plantToSupptypeMap
				+ ", rowKeyToSupptypeMap=" + rowKeyToSupptypeMap + "]";
	}
    
    


    
}
