package com.momentum.workout.dto.external;

import lombok.Data;

@Data
public class ExternalExerciseSingleResponse {
    private boolean success;
    private ExternalExerciseDTO data;
}