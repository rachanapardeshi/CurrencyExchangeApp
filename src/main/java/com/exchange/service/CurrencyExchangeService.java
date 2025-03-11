
package com.exchange.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.exchange.model.CurrencyExchangeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;


@Service
public class CurrencyExchangeService {
   
	@Autowired
    private CacheManager cacheManager;
	
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CurrencyExchangeService.class);

    @Value("${exchange.api.key}")
    private String apiKey;

    @Cacheable(value = "exchangeRatesCache", key = "#baseCurrency + '-' + #targetCurrency")
    public CurrencyExchangeResponse getExchangeRate(String baseCurrency, String targetCurrency) {
        String url = "https://open.er-api.com/v6/latest/" + baseCurrency + "?apikey=" + apiKey;
                
        try {
            logger.info("Requesting exchange rate for base currency: {} to target currency: {}", baseCurrency, targetCurrency);

            Cache<String, Double> cache = cacheManager.getCache("exchangeRatesCache", String.class, Double.class);
             if (cache != null) {
                String cacheKey = baseCurrency + "-" + targetCurrency;
                if (cache.get(cacheKey) != null) {
                    logger.info("Cache hit for key: {}", cacheKey);
                } else {
                    logger.info("Cache miss for key: {}", cacheKey);
                }
            }
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
            
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String responseBody = response.body();
                
                logger.debug("Received response: {}", responseBody);

                ObjectMapper objectMapper = new ObjectMapper();
                CurrencyExchangeResponse exchangeResponse = objectMapper.readValue(responseBody, CurrencyExchangeResponse.class);

                logger.info("Exchange rate request successful..");
                logger.debug("Result: {}", exchangeResponse.getResult());
                logger.debug("Base Code: {}", exchangeResponse.getBase_code());
                logger.debug("Conversion Rates: {}", exchangeResponse.getRates());

                return exchangeResponse;
            } catch (IOException | InterruptedException e) {
                logger.error("Error while processing the response: {}", e.getMessage(), e);
                throw new RuntimeException("Error fetching exchange rate", e);
            }
            
        } catch (HttpClientErrorException.NotFound e) {
            logger.error("API endpoint not found. Please check the API URL: {}", url, e);
            throw new RuntimeException("Currency exchange data not found", e);
        } catch (HttpClientErrorException e) {
            logger.error("Client-side error occurred while fetching the exchange rate: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching exchange rate", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }
}
