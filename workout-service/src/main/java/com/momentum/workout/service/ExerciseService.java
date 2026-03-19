package com.momentum.workout.service;

import com.momentum.workout.client.ExerciseApiClient;
import com.momentum.workout.dto.ExternalExerciseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExerciseService {

    private final ExerciseApiClient exerciseApiClient;

    public List<ExternalExerciseDTO> getAllExercises() {
        return exerciseApiClient.getExercises();
    }

    public List<ExternalExerciseDTO> getExercisesBySearch(String query) {
        return exerciseApiClient.getExercisesBySearch(query);
    }
}
