package com.momentum.workout.dto.external;

import lombok.Data;
import java.util.List;

@Data
public class ExternalExerciseResponse {
    private boolean success;
    private List<ExternalExerciseDTO> data;
}
