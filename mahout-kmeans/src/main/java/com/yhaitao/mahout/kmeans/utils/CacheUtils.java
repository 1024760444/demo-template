package com.yhaitao.mahout.kmeans.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存工具。
 * @author yhaitao
 *
 */
public class CacheUtils {
	/**
	 * 分词序号队列。
	 * 分词编号-分词
	 */
	private final static Map<Integer, String> INDEX_TERM = new HashMap<Integer, String>();
	
	/**
	 * 
	 * @param superId
	 * @param id
	 * @param termFreq
	 */
	public static void cache(int superId, int id, Map<String, Double> termFreq) {
		
	}
}
