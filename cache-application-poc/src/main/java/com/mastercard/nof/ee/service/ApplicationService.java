package com.mastercard.nof.ee.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import com.mastercard.nof.ee.model.RateBean;

/**
 * @author singhalsrishty
 *
 */
public class ApplicationService {
	
	@Autowired
	private SpringCacheService<RateBean> springCacheService;
	
	@Autowired
	private CacheManager cacheManager;
	
	
	/**
	 * Method to populate cache (preferably on startup)
	 * endpoint details available in readMe
	 */
	public void populateRateCache(List<RateBean> rateBeanList) {
		rateBeanList.stream().forEach(rateBean -> 
				this.springCacheService.updateCache(rateBean, rateBean.getSourceCurrency(), 
				rateBean.getDestinationCurrency()));
	}
	
	/**
	 * Method to update cache
	 * endpoint details available in readMe
	 * @param rateFiles
	 */
	public void updateRateCache(RateBean rateFile) {
		this.springCacheService.updateCache(rateFile, rateFile.getSourceCurrency(), 
				rateFile.getDestinationCurrency());
	}
	
	/**
	 * Method to look up rate, it calls service implementing 
	 * cache implementation service to return result
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @return
	 */
	public String lookupRate(String sourceCurrency, String destinationCurrency) 
			 {
		if(!Objects.isNull(cacheManager))
			System.out.println("Using cache manager: " + cacheManager.getClass().getName());
		RateBean rateBean = this.springCacheService.lookupCache(sourceCurrency, destinationCurrency).isPresent() ? 
				this.springCacheService.lookupCache(sourceCurrency, destinationCurrency).get() : null ;
		return Objects.isNull(rateBean) ? null : rateBean.getBuyRate();
	}
	
	/**
	 * Method to call cache implementing service for clearing
	 * 	cache
	 */
	public void clearCache() {
		this.springCacheService.clearCache();
	}

}
