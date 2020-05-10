package com.mastercard.nof.ee.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import com.mastercard.nof.ee.model.RateFile;

/**
 * @author singhalsrishty
 *
 */
public class ApplicationService {
	
	@Autowired
	private CacheConfigSpringService springCacheService;
	
	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * Method to populate cache on startup
	 * endpoint details available in readMe
	 * @param rateFiles
	 */
	public void populateRateCache(List<RateFile> rateFiles) {
		this.springCacheService.populateRateCacheObject(rateFiles);
	}
	
	/**
	 * Method to check cache population done from 
	 * 	populateRateCache method
	 * @return
	 */
	public List<RateFile> getAllRates() {
		return this.springCacheService.getAllRates();
	}
	
	/**
	 * Method to look up rate, it calls service implementing 
	 * cache implementation service to return result
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @return
	 */
	public String lookupRate(String sourceCurrency, String destinationCurrency) {
		if(!Objects.isNull(cacheManager))
			System.out.println("Using cache manager: " + cacheManager.getClass().getName());
		return this.springCacheService.lookupRate(sourceCurrency, destinationCurrency);
	}
	
	/**
	 * Method to call cache implementing service for clearing
	 * 	cache
	 */
	public void clearCache() {
		this.springCacheService.clearCache();
	}

}
