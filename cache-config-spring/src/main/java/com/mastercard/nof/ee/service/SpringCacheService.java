package com.mastercard.nof.ee.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
	
	/**
	 * This method is used for cache updation
	 * @param cacheObject
	 * @param params
	 */
	@CachePut(keyGenerator = "customLoadKeyGenerator")
	public Optional<T> updateCache(T cacheObject, String...params) {
		return Optional.of(cacheObject);
	}
	
	
	/**
	 * This method provides an implementation to look into cache, takes 
	 * cache name, key generator and params as input
	 * @param params
	 * @return
	 */
	@Cacheable(keyGenerator = "customLookupKeyGenerator")
	public Optional<T> lookupCache(String...params) {
		System.out.println("Entered Cacheable Method: ");
		
		return Optional.empty();
	}
	
	/**
	 * This method clears whole cache
	 */
	@CacheEvict(allEntries = true)
	public void clearCache() {
		
	}

}
