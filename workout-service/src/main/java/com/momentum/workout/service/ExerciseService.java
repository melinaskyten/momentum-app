package com.momentum.workout.service;

import com.momentum.workout.client.ExerciseApiClient;
import com.momentum.workout.dto.external.ExternalExerciseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExerciseService {

    private final ExerciseApiClient exerciseApiClient;

    public List<ExternalExerciseDTO> getAllExercises() {
        return exerciseApiClient.getExercises();
    }

    @Cacheable (value = "exerciseBySearch", key = "#query")
    public List<ExternalExerciseDTO> getExercisesBySearch(String query) {
        return exerciseApiClient.getExercisesBySearch(query);
    }

    @Cacheable (value = "exerciseById", key = "#id")
    public ExternalExerciseDTO getExerciseById(String id) {
        return exerciseApiClient.getExerciseById(id);
    }

    @CacheEvict (value = "exerciseBySearch", allEntries = true)
    public void evictCacheBySearch(String query) {}

    @CacheEvict (value = "exerciseById", allEntries = true)
    public void evictCacheById(String id) {}
}