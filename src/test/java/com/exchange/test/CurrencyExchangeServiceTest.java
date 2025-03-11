/*
 * package com.exchange.test;
 * 
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertNotNull; import static
 * org.junit.jupiter.api.Assertions.assertThrows; import static
 * org.mockito.ArgumentMatchers.any; import static
 * org.mockito.ArgumentMatchers.eq; import static org.mockito.Mockito.mock;
 * import static org.mockito.Mockito.times; import static
 * org.mockito.Mockito.verify; import static org.mockito.Mockito.when;
 * 
 * import java.io.IOException; import java.net.http.HttpClient; import
 * java.net.http.HttpRequest; import java.net.http.HttpResponse; import
 * java.util.Map;
 * 
 * import org.ehcache.Cache; import org.ehcache.CacheManager; import
 * org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks;
 * import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;
 * import org.slf4j.Logger; import org.springframework.http.HttpStatus; import
 * org.springframework.web.client.HttpClientErrorException;
 * 
 * import com.exchange.model.CurrencyExchangeResponse; import
 * com.exchange.service.CurrencyExchangeService;
 * 
 * @ExtendWith(MockitoExtension.class) public class CurrencyExchangeServiceTest
 * {
 * 
 * @Mock private CacheManager cacheManager;
 * 
 * @Mock private HttpClient httpClient;
 * 
 * @Mock private Logger logger;
 * 
 * @InjectMocks private CurrencyExchangeService currencyExchangeService;
 * 
 * private String baseCurrency = "USD"; private String targetCurrency = "EUR";
 * private String apiKey = "8e5f00f38c25456c9791198099c301d9"; private String
 * url =
 * "https://open.er-api.com/v6/latest/USD?apikey=8e5f00f38c25456c9791198099c301d9";
 * 
 * @Mock private Cache<String, Double> cache;
 * 
 * 
 * @BeforeEach void setUp() { when(cacheManager.getCache("exchangeRatesCache",
 * String.class, Double.class)).thenReturn(cache); }
 * 
 * @Test void testGetExchangeRateCacheHit() throws IOException,
 * InterruptedException { CurrencyExchangeResponse exchangeResponse = new
 * CurrencyExchangeResponse(); exchangeResponse.setBase_code("USD");
 * exchangeResponse.setRates(Map.of("EUR", 0.84));
 * 
 * 
 * when(cache.get(baseCurrency + "-" + targetCurrency)).thenReturn(0.84);
 * 
 * CurrencyExchangeResponse result =
 * currencyExchangeService.getExchangeRate(baseCurrency, targetCurrency);
 * 
 * assertNotNull(result); assertEquals("USD", result.getBase_code()); //
 * assertEquals(result.getRates().get("EUR"), result.getRates().get("EUR"));
 * verify(cache, times(1)).get(baseCurrency + "-" + targetCurrency); }
 * 
 * @Test void testGetExchangeRateCacheMiss() throws IOException,
 * InterruptedException { CurrencyExchangeResponse exchangeResponse = new
 * CurrencyExchangeResponse(); exchangeResponse.setBase_code("USD");
 * exchangeResponse.setRates(Map.of("EUR", 0.92));
 * 
 * 
 * when(cache.get(baseCurrency + "-" + targetCurrency)).thenReturn(null);
 * 
 * HttpResponse<String> httpResponse = mock(HttpResponse.class);
 * when(httpResponse.body()).thenReturn(
 * "{\"base_code\":\"USD\",\"rates\":{\"EUR\":0.92}}");
 * when(httpClient.send(any(HttpRequest.class),
 * eq(HttpResponse.BodyHandlers.ofString()))).thenReturn(httpResponse);
 * 
 * CurrencyExchangeResponse result =
 * currencyExchangeService.getExchangeRate(baseCurrency, targetCurrency);
 * 
 * assertNotNull(result); assertEquals("USD", result.getBase_code());
 * assertEquals(result.getRates().get("EUR"), result.getRates().get("EUR"));
 * verify(cache, times(1)).get(baseCurrency + "-" + targetCurrency);
 * verify(httpClient, times(1)).send(any(HttpRequest.class),
 * eq(HttpResponse.BodyHandlers.ofString())); }
 * 
 * @Test void testGetExchangeRateApiError() throws IOException,
 * InterruptedException {
 * 
 * HttpClientErrorException exception = new
 * HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bad Request");
 * when(httpClient.send(any(HttpRequest.class),
 * eq(HttpResponse.BodyHandlers.ofString()))).thenThrow(exception);
 * 
 * RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
 * currencyExchangeService.getExchangeRate(baseCurrency, targetCurrency); });
 * 
 * assertEquals("Error fetching exchange rate", thrown.getMessage()); }
 * 
 * 
 * 
 * @Test void testGetExchangeRateNotFound() throws IOException,
 * InterruptedException {
 * 
 * HttpClientErrorException exception = new
 * HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found");
 * 
 * 
 * when(httpClient.send(any(HttpRequest.class),
 * eq(HttpResponse.BodyHandlers.ofString()))).thenThrow(exception);
 * 
 * 
 * RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
 * currencyExchangeService.getExchangeRate(baseCurrency, targetCurrency); });
 * 
 * // Assert that the exception message matches the expected message
 * assertEquals("Currency exchange data not found", thrown.getMessage()); }
 * 
 * @Test void testUnexpectedError() throws IOException, InterruptedException {
 * 
 * when(httpClient.send(any(HttpRequest.class),
 * eq(HttpResponse.BodyHandlers.ofString()))).thenThrow(new
 * RuntimeException("Unexpected error"));
 * 
 * RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
 * currencyExchangeService.getExchangeRate(baseCurrency, targetCurrency); });
 * 
 * assertEquals("Unexpected error occurred", thrown.getMessage()); } }
 */