package com.jyotig.webservices.currency_conversion.config;

import com.jyotig.webservices.currency_conversion.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange",url="localhost:8000") //application name of service to call
@FeignClient(name = "currency-exchange") //application name of service to call with eureka server
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion getExchangeValue(@PathVariable String from, @PathVariable String to);
}
