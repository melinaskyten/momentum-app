package com.momentum.workout.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseDTO {
    private String exerciseId;
    private String name;
    private List<String> bodyParts;
    private List<String> equipments;
    private List<String> instructions;

    private List<SetDTO> sets;
}
