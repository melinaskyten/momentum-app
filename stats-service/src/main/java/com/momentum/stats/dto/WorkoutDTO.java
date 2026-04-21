package com.momentum.stats.dto;

import lombok.Data;

import java.util.List;

@Data
public class WorkoutDTO {

    private Long userId;
    private List<ExerciseDTO> exercises;

}
