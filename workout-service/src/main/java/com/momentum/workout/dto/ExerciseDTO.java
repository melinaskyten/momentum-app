package com.momentum.workout.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseDTO {

    private String exerciseId;
    private List<SetDTO> sets;
}
