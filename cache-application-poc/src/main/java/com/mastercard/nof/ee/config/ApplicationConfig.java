package com.mastercard.nof.ee.config;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.mastercard.nof.ee.model.RateBean;
import com.mastercard.nof.ee.service.ApplicationService;
import com.mastercard.nof.ee.service.SpringCacheService;

@Configuration
@EnableCaching
public class ApplicationConfig {
	
	@Value("${application.cache.rates.name}")
	private String ratesCache;
	
	@Profile("springcache")
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(ratesCache);
    }
	
	@Bean
	public SpringCacheService<RateBean> springCacheService() {
		return new SpringCacheService<RateBean>();
	}
	
	@Bean
	public ApplicationService applicationService() {
		return new ApplicationService();
	}
	
	@Component(value = "customLoadKeyGenerator")
	public class CustomLoadKeyGenerator implements KeyGenerator {

		@Override
		public Object generate(Object target, Method method, Object... params) {
			String key = null;
			if(!Objects.isNull(params) && params.length > 1) {
				String[] paramArr = 
						IntStream.range(1, params.length).mapToObj(i -> params[i]).toArray(String[]::new);
				key = String.join("-", paramArr);
			}
			return key;
		}
		
	}
	
}
