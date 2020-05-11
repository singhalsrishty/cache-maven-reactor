package com.mastercard.nof.ee.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to provide generic implementation of 
 * cache load, update and lookup
 * @author Srishty, Singhal
 *
 * @param <T>
 */
@Service
@CacheConfig(cacheNames = "${application.cache.rates.name}")
public class SpringCacheService<T> {
	
	@Value("classpath:dbMap.json")
	Resource dbJsonFile;
	
	private Map<String, T> dbMap = new HashMap<String, T>();
	
	/**
	 *  This method is used to populate cache
	 *  from json file
	 */
	public void populateCache() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(dbJsonFile.getFile().getAbsolutePath()), dbMap);
		} catch (JsonGenerationException e) {
			System.out.println("JsonGenerationException");
		} catch (JsonMappingException e) {
			System.out.println("JsonMappingException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}
	
	/**
	 * This method is used for cache updation
	 * @param cacheObject
	 * @param params
	 */
	@CachePut(keyGenerator = "customLoadKeyGenerator")
	public void updateCache(T cacheObject, String...params) {
		String mapKey = String.join("-", params);
		dbMap.put(mapKey, cacheObject);
	}
	
	
	/**
	 * This method provides an implementation to look into cache, takes 
	 * cache name, key generator and params as input
	 * @param params
	 * @return
	 */
	@Cacheable()
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
