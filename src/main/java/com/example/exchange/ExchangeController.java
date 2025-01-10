package com.example.exchange;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import java.util.Collections;
import java.util.Map;

@RestController 
@RequestMapping("/api")
public class ExchangeController {
    @Autowired
    private ExchangeService service;

    @GetMapping("/rates")
    public Map<String, Number> getRates(@RequestParam(value = "base", defaultValue = "USD") String base) {
        try {
            Map<String, Number> map = service.getExchangeRates(base);
            System.out.println(map);
            return map;
        } catch (RuntimeException e) {
            System.out.println(e + "RUN TIME ERROR");
            return Collections.emptyMap();
        }
    }

    @PostMapping("/convert")
    public Map<String, Object> convert(@RequestBody Map<String, Object> request) {
        try { 
            String from = (String) request.get("from"); 
            String to = (String) request.get("to"); 
            if (from == null || to == null) { 
                throw new IllegalArgumentException("Invalid 'from' or 'to' currency code provided."); 
            } 
            double amount = Double.parseDouble(request.get("amount").toString()); 
            Map<String, Number> rates = service.getExchangeRates(from);
            if (!rates.containsKey(to)) {
                throw new IllegalArgumentException("Invalid target currency code: " + to); 
            } 
            double rate = rates.get(to).doubleValue(); 
            double convertedAmount = amount * rate; 
            return Map.of( 
                "from", from, 
                "to", to, 
                "amount", amount, 
                "convertedAmount", convertedAmount ); 
            } 
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); 
                return Map.of( "error", "Invalid input", "message", e.getMessage() ); 
            } catch (RuntimeException e) {
                System.out.println(e.getMessage()); return Map.of( "error", "Conversion failed", "message", e.getMessage() ); 
            } 
        } 
    }