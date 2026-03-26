package com.momentum.workout.dto;

import lombok.Data;

@Data
public class ExternalExerciseSingleResponse {
    private boolean success;
    private ExternalExerciseDTO data;
}