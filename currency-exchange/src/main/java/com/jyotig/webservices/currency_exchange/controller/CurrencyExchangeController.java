package com.jyotig.webservices.currency_exchange.controller;

import com.jyotig.webservices.currency_exchange.model.CurrencyExchange;
import com.jyotig.webservices.currency_exchange.repository.CurrencyExchangeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    private Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
    	log.info("Request is coming for exchange from {from} to {to}",from,to);
        CurrencyExchange response = currencyExchangeRepository.findByFromAndTo(from, to);
       // CurrencyExchange response = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(65));
        if (response == null) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }
        response.setEnvironment(environment.getProperty("local.server.port"));
        return response;
    }
}
