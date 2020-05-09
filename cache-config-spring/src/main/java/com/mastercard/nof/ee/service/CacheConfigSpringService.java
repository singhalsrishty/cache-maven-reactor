package com.mastercard.nof.ee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mastercard.nof.ee.model.RateFile;

@Service
public class CacheConfigSpringService {
	private List<RateFile> rates;
	
	@CachePut("rates")
	public void populateRateCacheObject(List<RateFile> rates) {
		this.rates = rates;
	}
	
	@Cacheable(value = "rates")
	public String lookupRate(String sourceCurrency, String destinationCurrency) {
		System.out.println("Entered Cacheable Method: ");
		List<RateFile> lookedUpRates = this.rates.stream().filter(rate -> rates.stream().anyMatch(comparedRate -> comparedRate.getSourceCurrency().equals(sourceCurrency) && 
					comparedRate.getDestinationCurrency().equals(destinationCurrency))).collect(Collectors.toList());
		return lookedUpRates.isEmpty() ? null : lookedUpRates.get(0).getBuyRate();
	}
	
	@CacheEvict(value = "rates", allEntries = true)
	public void clearCache() {
		this.rates = new ArrayList<RateFile>();
	}

	public List<RateFile> getAllRates() {
		return this.rates;
	}
	
}
