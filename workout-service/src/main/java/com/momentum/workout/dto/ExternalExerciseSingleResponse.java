package com.momentum.workout.dto;

import lombok.Data;
import java.util.List;

@Data
public class ExternalExerciseSingleResponse {
    private boolean success;
    private ExternalExerciseDTO data;
}