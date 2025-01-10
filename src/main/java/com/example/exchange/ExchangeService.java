package com.example.exchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;
import java.util.Map;

@Service
public class ExchangeService {
    @Value("${exchangerate.api.key}") 
    private String apiKey;
    private final RestTemplate template = new RestTemplate();
    private final String url = "https://v6.exchangerate-api.com/v6/";
    private final String tag = "/latest/";

    public Map<String, Number> getExchangeRates(String base) {
        Map<String,Number> result=Collections.emptyMap();
        try{
            final String urlFinal = url + apiKey + tag + (base != null ? base : "USD");

            Map<String, Number> response = template.getForObject(urlFinal, Map.class);
            result= (Map<String, Number>) response.get("conversion_rates");
        }
        catch (HttpClientErrorException | HttpServerErrorException e){
            throw new RuntimeException("HTTP error occurred: " + e.getMessage(), e); 
        } 
        catch (ResourceAccessException e) { 
            throw new RuntimeException("Resource access error occurred: " + e.getMessage(), e); 
        } 
        catch (RestClientException e) {
            throw new RuntimeException("REST client error occurred: " + e.getMessage(), e); 
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid argument: " + e.getMessage(), e);
        }
        finally{
            return result;
        }
        
        
    }
}
