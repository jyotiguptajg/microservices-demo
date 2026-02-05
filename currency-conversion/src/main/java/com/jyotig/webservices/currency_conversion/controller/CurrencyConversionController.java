package com.jyotig.webservices.currency_conversion.controller;

import com.jyotig.webservices.currency_conversion.config.CurrencyExchangeProxy;
import com.jyotig.webservices.currency_conversion.model.CurrencyConversion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	private Logger log = LoggerFactory.getLogger(CurrencyConversionController.class);
    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    CurrencyConversion getCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable Double quantity) {
        ResponseEntity<CurrencyConversion> currencyConversionEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/" + from + "/to/" + to, CurrencyConversion.class);
        CurrencyConversion response =  currencyConversionEntity.getBody();
        return new CurrencyConversion(response.getId(), from,to, quantity, response.getConversionMultiple(), quantity * response.getConversionMultiple(), response.getEnvironment()+" -rest template");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    CurrencyConversion getCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable Double quantity) {
        log.info("Request is coming from {} to {}", from ,to);
    	CurrencyConversion response = currencyExchangeProxy.getExchangeValue(from, to);
        return new CurrencyConversion(response.getId(), from,to, quantity, response.getConversionMultiple(), quantity * response.getConversionMultiple(), response.getEnvironment()+" -feign");
    }
}
