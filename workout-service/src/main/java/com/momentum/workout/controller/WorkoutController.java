package com.momentum.workout.controller;

import com.momentum.workout.dto.WorkoutDTO;
import com.momentum.workout.entity.Workout;
import com.momentum.workout.mapper.WorkoutMapper;
import com.momentum.workout.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutMapper workoutMapper;

    @PostMapping("/create")
    public WorkoutDTO createWorkout (@RequestBody WorkoutDTO workoutDto) {
        Workout savedWorkout = workoutService.createWorkout(workoutDto);
        return workoutMapper.toDTO(savedWorkout);
    }
}
