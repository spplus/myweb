package qc.com.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * map排序.
 * 
 * @author jiangfx
 */
public class MapSort {
	
	/**
	 * 按键(key)排序.
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {
			public int compare(String key1, String key2) {
				return key1.compareTo(key2);  
			}});
		sortedMap.putAll(map);
		return sortedMap;
	}
}
