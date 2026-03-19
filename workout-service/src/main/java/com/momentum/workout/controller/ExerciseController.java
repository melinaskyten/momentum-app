package com.momentum.workout.controller;


import com.momentum.workout.client.ExerciseApiClient;
import com.momentum.workout.dto.ExternalExerciseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseApiClient exerciseApiClient;

    @GetMapping
    public List<ExternalExerciseDTO> getAllExercises() {
        return exerciseApiClient.getExercises();
    }


}
