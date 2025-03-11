package com.exchange.config;

import java.time.Duration;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

	@Bean(name = "customCacheManager")
    public CacheManager cacheManager() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("exchangeRatesCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Double.class,
                        ResourcePoolsBuilder.heap(1000))  
                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(30))))
                .build();
        cacheManager.init();
     
        return cacheManager;
    }
}

