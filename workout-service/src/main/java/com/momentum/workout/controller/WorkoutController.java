package com.momentum.workout.controller;

import com.momentum.workout.dto.WorkoutDTO;
import com.momentum.workout.entity.Workout;
import com.momentum.workout.mapper.WorkoutMapper;
import com.momentum.workout.repository.WorkoutRepository;
import com.momentum.workout.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutMapper workoutMapper;
    private final WorkoutRepository workoutRepository;

    @PostMapping
    public WorkoutDTO createWorkout (@RequestBody WorkoutDTO workoutDto) {
        Workout savedWorkout = workoutService.createWorkout(workoutDto);
        return workoutMapper.toDTO(savedWorkout);
    }

    @GetMapping
    public List<WorkoutDTO> getWorkoutByUserId(@RequestParam Long userId) {
        return workoutService.getWorkoutByUserId(userId)
                .stream()
                .map(workoutMapper::toDTO)
                .toList();
    }

    @PutMapping ("/{id}")
    public WorkoutDTO updateWorkout (@PathVariable Long id,
                                     @RequestBody WorkoutDTO workoutDto) {
        return workoutMapper.toDTO(workoutService.updateWorkout(id, workoutDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout (@PathVariable Long id) {
        if (!workoutRepository.existsById(id)) {
            throw new RuntimeException("Workout not found: " + id);
        }
        workoutService.deleteWorkout(id);
    }

}
