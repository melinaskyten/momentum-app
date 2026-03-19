package com.momentum.workout.dto;

import lombok.Data;
import java.util.List;

@Data
public class ExternalExerciseResponse {
    private boolean success;
    private List<ExternalExerciseDTO> data;
}
