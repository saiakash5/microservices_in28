package com.in28minutes.microservices.limitsservice.Controller;

import com.in28minutes.microservices.limitsservice.Bean.LimitConfiguration;
import com.in28minutes.microservices.limitsservice.Config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimtsFromConfiguration(){
        return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
    }
}
