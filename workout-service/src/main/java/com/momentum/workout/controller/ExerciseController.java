package com.momentum.workout.controller;


import com.momentum.workout.dto.ExternalExerciseDTO;
import com.momentum.workout.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExternalExerciseDTO> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/search")
    public List <ExternalExerciseDTO> getExercisesBySearch(@RequestParam String search) {
        return exerciseService.getExercisesBySearch(search);
    }

    @GetMapping("/{id}")
    public ExternalExerciseDTO getExerciseById(@PathVariable String id) {
        return exerciseService.getExerciseById(id);
    }

}
