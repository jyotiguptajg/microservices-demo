package com.jyotig.webservices.limit_webservices.controller;

import com.jyotig.webservices.limit_webservices.config.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    private LimitConfiguration limitConfiguration;

    @GetMapping("/limits")
    String GetLimits() {
        return limitConfiguration.getMessage();
    }
}
