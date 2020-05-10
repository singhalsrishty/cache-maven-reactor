package com.mastercard.nof.ee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mastercard.nof.ee.model.RateFile;

@Service
@CacheConfig(cacheNames = "rates")
public class CacheConfigEhcacheService {
	
private List<RateFile> rates;
	
	/**
	 * This method is used for cache population on start-up
	 * @param rates
	 */
	@CachePut("rates")
	public void populateRateCacheObject(List<RateFile> rates) {
		this.rates = rates;
	}
	
	/**
	 * This method looks up rate from downstream, here it is list stored in cache itself
	 * Cacheable annotation prevents method execution, if method is called for same set 
	 * of inputs - sourceCurrency, destinationCurrency next time
	 * 
	 * @param sourceCurrency
	 * @param destinationCurrency
	 * @return
	 */
	@Cacheable(value = "rates")
	public String lookupRate(String sourceCurrency, String destinationCurrency) {
		System.out.println("Entered Cacheable Method: ");
		Optional<RateFile> firstRateFound = this.rates.stream()
				.filter(rate -> (rate.getSourceCurrency().equals(sourceCurrency)
						&& rate.getDestinationCurrency().equals(destinationCurrency)))
				.findFirst();
		return firstRateFound.isPresent() ? firstRateFound.get().getBuyRate() : null;
	}
	
	/**
	 * This method clear whole cache
	 */
	@CacheEvict(value = "rates", allEntries = true)
	public void clearCache() {
		this.rates = new ArrayList<RateFile>();
	}

	/**
	 * A method to check if cache population happened 
	 * 	using populateRateCacheObject method
	 * @return
	 */
	public List<RateFile> getAllRates() {
		return this.rates;
	}

}
