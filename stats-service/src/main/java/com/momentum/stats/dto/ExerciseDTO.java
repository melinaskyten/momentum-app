package com.momentum.stats.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseDTO {

    private String exerciseId;
    private String name;
    private List<SetDTO> sets;

}
