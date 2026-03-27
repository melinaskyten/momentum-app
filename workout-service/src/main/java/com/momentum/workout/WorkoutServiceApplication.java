package com.momentum.workout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WorkoutServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkoutServiceApplication.class, args);
    }

}
