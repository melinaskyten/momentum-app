package com.momentum.workout.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkoutDTO {

    private Long id;
    private Long userId;
    private List<ExerciseDTO> exercises;
    private LocalDateTime date;
}
