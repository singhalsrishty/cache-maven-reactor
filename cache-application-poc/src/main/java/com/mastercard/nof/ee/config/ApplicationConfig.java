package com.mastercard.nof.ee.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.mastercard.nof.ee.service.ApplicationService;

@Configuration
@EnableCaching
@Import({SpringCacheConfig.class})
public class ApplicationConfig {
	
	@Profile("springcache")
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("rates");
    }
	
	@Bean
	public ApplicationService applicationService() {
		return new ApplicationService();
	}
	
	

}
