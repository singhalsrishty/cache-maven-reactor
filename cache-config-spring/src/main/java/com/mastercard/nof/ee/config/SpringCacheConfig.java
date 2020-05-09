package com.mastercard.nof.ee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mastercard.nof.ee.service.CacheConfigSpringService;

@Configuration
public class SpringCacheConfig {
	
	@Bean
	public CacheConfigSpringService cacheConfigSpringService() {
		return new CacheConfigSpringService();
	}
}
