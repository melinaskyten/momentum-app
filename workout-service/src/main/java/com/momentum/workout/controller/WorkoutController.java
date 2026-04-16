package com.momentum.workout.controller;

import com.momentum.workout.dto.WorkoutDTO;
import com.momentum.workout.entity.Workout;
import com.momentum.workout.mapper.WorkoutMapper;
import com.momentum.workout.repository.WorkoutRepository;
import com.momentum.workout.service.WorkoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutMapper workoutMapper;
    private final WorkoutRepository workoutRepository;

    @PostMapping
    public WorkoutDTO createWorkout (@RequestBody WorkoutDTO workoutDto,
                                     HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Workout savedWorkout = workoutService.createWorkout(workoutDto, userId);
        return workoutMapper.toDTO(savedWorkout);
    }

    @GetMapping
    public List<WorkoutDTO> getWorkoutByUserId(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        return workoutService.getWorkoutByUserId(userId)
                .stream()
                .map(workoutMapper::toDTO)
                .toList();
    }

    @PutMapping ("/{workoutId}")
    public WorkoutDTO updateWorkout (@PathVariable Long workoutId,
                                     @RequestBody WorkoutDTO workoutDto,
                                     HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return workoutMapper.toDTO(workoutService.updateWorkout(workoutId, workoutDto, userId));
    }

    @DeleteMapping("/{workoutId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout (@PathVariable Long workoutId,
                               HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        workoutService.deleteWorkout(workoutId, userId);
    }

}
