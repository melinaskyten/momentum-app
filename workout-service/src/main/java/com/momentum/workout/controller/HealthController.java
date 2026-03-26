package com.momentum.workout.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
public class HealthController {

    @GetMapping("/health")
    public String health (){
        return "Workout service is running";
    }
}
