package com.mastercard.nof.ee.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.nof.ee.model.RateBean;
import com.mastercard.nof.ee.service.ApplicationService;

@RestController
@RequestMapping(value = "/v1/cache")
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/rates")
	public void populateRateCache(@RequestBody Map<String, RateBean> rateBeansMap) {
		this.applicationService.populateRateCache(rateBeansMap);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/rates")
	public Map<String, RateBean> getPopulatedCache() {
		return this.applicationService.getAllRates();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/rate")
	public void updateRateCache(@RequestBody RateBean rateBean) {
		this.applicationService.updateRateCache(rateBean);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/rate")
	public String lookupRate(@RequestParam("sourceCur") String sourceCurrency,
			@RequestParam("desCur") String destinationCurrency) {
		return "Applicable Buy Rate is: " + 
			this.applicationService.lookupRate(sourceCurrency, destinationCurrency);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/clear")
	public void clearCache() {
		this.applicationService.clearCache();
	}

}
