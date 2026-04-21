package com.momentum.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatsResponse {

    private double totalWeightLifted;
    private String favoriteExercise;
    private int totalWorkouts;

}
