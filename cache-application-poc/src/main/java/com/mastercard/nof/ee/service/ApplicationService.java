package com.mastercard.nof.ee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mastercard.nof.ee.model.RateFile;

public class ApplicationService {
	
	@Autowired
	private CacheConfigSpringService springCacheService;
	
	public void populateRateCache(List<RateFile> rateFiles) {
		this.springCacheService.populateRateCacheObject(rateFiles);
	}
	
	public String lookupRate(String sourceCurrency, String destinationCurrency) {
		return this.springCacheService.lookupRate(sourceCurrency, destinationCurrency);
	}
	
	public void clearCache() {
		this.springCacheService.clearCache();
	}

	public List<RateFile> getAllRates() {
		return this.springCacheService.getAllRates();
	}
	
}
