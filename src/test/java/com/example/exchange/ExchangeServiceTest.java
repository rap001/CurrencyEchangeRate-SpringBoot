package com.example.exchange;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExchangeServiceTest {
    @Value("${exchangerate.api.key}") 
    private String apiKey;
    @Test
    public void testGetExchangeRatesSuccess() {
        ExchangeService exchangeService = new ExchangeService();
        // Set private fields using ReflectionTestUtils
        ReflectionTestUtils.setField(exchangeService, "apiKey", apiKey);
        RestTemplate mockRestTemplate = new RestTemplate() {
            @Override
            public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
                return (T) Collections.singletonMap("conversion_rates", Collections.singletonMap("USD", 1.0));
            }
        };
        ReflectionTestUtils.setField(exchangeService, "template", mockRestTemplate);

        Map<String, Number> result = exchangeService.getExchangeRates("USD");

        // Perform assertions
        assertNotNull(result);
        assertEquals(1.0, result.get("USD"));
    }
    @Test
    public void testGetExchangeRatesFail() {
        ExchangeService exchangeService = new ExchangeService();
        // Set private fields using ReflectionTestUtils
        ReflectionTestUtils.setField(exchangeService, "apiKey", apiKey);
        RestTemplate mockRestTemplate = new RestTemplate() {
            @Override
            public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
                return (T) Collections.singletonMap("conversion_rates", Collections.emptyMap());
            }
        };
        ReflectionTestUtils.setField(exchangeService, "template", mockRestTemplate);

        Map<String, Number> result = exchangeService.getExchangeRates("INVALID");

        // Perform assertions
        assertTrue(result.isEmpty());
    }
}

