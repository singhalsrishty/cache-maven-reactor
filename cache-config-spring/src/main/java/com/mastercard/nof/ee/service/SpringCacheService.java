package com.mastercard.nof.ee.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * Class to provide generic implementation of 
 * cache load, update and lookup
 * @author Srishty, Singhal
 *
 * @param <T>
 */
@Service
@CacheConfig(cacheNames = "${application.cache.name}")
public class SpringCacheService<T> {
	
	@Value("classpath:dbMap.json")
	Resource dbJsonFile;
	
	private Map<String, T> dbMap = new HashMap<String, T>();
	
	/**
	 *  This method is used to populate cache
	 *  from json file
	 */
	public void populateCache(Map<String, T> cacheList) {
		for(Entry<String, T> entry: cacheList.entrySet()) {
			dbMap.put(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * This method is used for cache updation
	 * @param cacheObject
	 * @param params
	 */
	@CachePut(keyGenerator = "customLoadKeyGenerator")
	public T updateCache(T cacheObject, String...params) {
		String mapKey = String.join("-", params);
		dbMap.put(mapKey, cacheObject);
		return cacheObject;
	}
	
	
	/**
	 * This method provides an implementation to look into cache, takes 
	 * cache name, key generator and params as input
	 * @param params
	 * @return
	 */
	@Cacheable(keyGenerator = "customLookupKeyGenerator")
	public T lookupCache(String...params) {
		System.out.println("Entered Cacheable Method: ");
		
		String mapKey = null;
		if(!Objects.isNull(params) && params.length > 0) {
			String[] paramArr = 
					IntStream.range(0, params.length).mapToObj(i -> params[i]).toArray(String[]::new);
			mapKey = String.join("-", paramArr);
		}
		return dbMap.get(mapKey);
	}
	
	/**
	 * This method clears whole cache
	 */
	@CacheEvict(allEntries = true)
	public void clearCache() {
		this.dbMap = new HashMap<String, T>();
	}

	
	/**
	 * Method to check if cache population is success
	 * @return
	 */
	public Map<String, T> getCache() {
		return this.dbMap;
	}
	
}
