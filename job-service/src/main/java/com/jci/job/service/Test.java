package com.jci.job.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		 Map<Integer,Integer> map = new HashMap();
		 
		 map.put(1, 1);
		 map.put(2, 1);
		 map.put(3, 1);
		 map.put(4, 1);
		 map.put(5, 1);
		 map.put(6, 1);
		 map.put(7, 1);
		 System.out.println("map1--->"+map);
		 remove(map);
		 System.out.println("map2--->"+map);
	}

	public static int remove(Map map){
		Iterator<Map.Entry<Integer,Integer>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry<Integer,Integer> entry = iter.next();
		    if(entry.getValue()==1){
		        iter.remove();
		    }
		}
		return 1;
	}
	
}
