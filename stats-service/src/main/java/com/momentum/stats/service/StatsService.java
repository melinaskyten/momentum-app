package com.momentum.stats.service;

import com.momentum.stats.client.WorkoutClient;
import com.momentum.stats.dto.ExerciseDTO;
import com.momentum.stats.dto.StatsResponse;
import com.momentum.stats.dto.WorkoutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final WorkoutClient workoutClient;

    public StatsResponse getStats(String token) {
        List<WorkoutDTO> workouts = workoutClient.getWorkoutsByUserId(token);

        double totalWeightLifted = workouts.stream()
                .flatMap(workout -> workout.getExercises().stream())
                .flatMap(exercise -> exercise.getSets().stream())
                .mapToDouble(set -> set.getReps() * set.getWeight())
                .sum();

        String favoriteExercise = workouts.stream()
                .flatMap(workout -> workout.getExercises().stream())
                .collect(Collectors.groupingBy(ExerciseDTO::getName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No favorite yet");

        int totalWorkouts = workouts.size();

        return new StatsResponse(totalWeightLifted, favoriteExercise, totalWorkouts);
    }

}
