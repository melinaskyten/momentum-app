package com.momentum.stats.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class HealthController {

    @GetMapping("/health")
    public String health(){
        return "Stats service is running";
    }

}
