package com.momentum.workout.dto;

import lombok.Data;

import java.util.List;

@Data
public class WorkoutDTO {

    private Long userId;
    private List<ExerciseDTO> exercises;
}
