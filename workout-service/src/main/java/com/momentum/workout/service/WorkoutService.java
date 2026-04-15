package com.momentum.workout.service;

import com.momentum.workout.dto.*;
import com.momentum.workout.entity.Exercise;
import com.momentum.workout.entity.Set;
import com.momentum.workout.entity.Workout;
import com.momentum.workout.mapper.WorkoutMapper;
import com.momentum.workout.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final ExerciseService exerciseService;
    private final WorkoutRepository workoutRepository;

    @Transactional
    public Workout createWorkout(WorkoutDTO request) {
        Workout workout = new Workout();
        workout.setUserId(request.getUserId());
        workout.setDate(LocalDateTime.now());

        List<Exercise> exercises = new ArrayList<>();

        for (ExerciseDTO exerciseRequest : request.getExercises()) {

            ExternalExerciseDTO dto = exerciseService.getExerciseById(exerciseRequest.getExerciseId());

            if (dto == null) {
                throw new RuntimeException("Exercise not found: " + exerciseRequest.getExerciseId());
            }

            Exercise exercise = new Exercise();

            exercise.setExternalExerciseId(dto.getExerciseId());
            exercise.setExerciseName(dto.getName());

            exercise.setBodyPart(dto.getBodyParts() != null && !dto.getBodyParts().isEmpty()
                    ? dto.getBodyParts().get(0)
                    : null);

            exercise.setEquipment(dto.getEquipments() != null && !dto.getEquipments().isEmpty()
                    ? dto.getEquipments().get(0)
                    : null);

            exercise.setInstructions(dto.getInstructions());

            exercise.setWorkout(workout);

            List<Set> sets = new ArrayList<>();

            for (SetDTO setRequest : exerciseRequest.getSets()) {

                Set set = new Set();
                set.setReps(setRequest.getReps());
                set.setWeight(setRequest.getWeight());
                set.setExercise(exercise);

                sets.add(set);
            }

            exercise.setSets(sets);
            exercises.add(exercise);
        }
        workout.setExercises(exercises);

        return workoutRepository.save(workout);
    }

    @Transactional
    public Workout updateWorkout(Long workoutId, WorkoutDTO request) {
        Workout workout = workoutRepository
                .findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        Map<Long, Exercise> existingExercises = workout.getExercises()
                .stream()
                .collect(Collectors.toMap(Exercise::getId, e -> e));

        List<Exercise> updatedExercises = new ArrayList<>();

        for (ExerciseDTO exerciseDTO : request.getExercises()) {
            Exercise exercise;

            if (exerciseDTO.getExerciseId() != null && existingExercises.containsKey(exerciseDTO.getExerciseId())) {
                exercise = existingExercises.get(exerciseDTO.getId());
            } else {
                exercise = new Exercise();
                exercise.setWorkout(workout);
            }

            exercise.setExternalExerciseId(exerciseDTO.getExerciseId());
            exercise.setExerciseName(exerciseDTO.getName());
            exercise.setBodyPart(exerciseDTO.getBodyParts() != null && !exerciseDTO.getBodyParts().isEmpty()
                    ? exerciseDTO.getBodyParts().get(0) : null);
            exercise.setEquipment(exerciseDTO.getEquipments() != null && !exerciseDTO.getEquipments().isEmpty()
                    ? exerciseDTO.getEquipments().get(0) : null);
            exercise.setInstructions(exerciseDTO.getInstructions() != null
                    ? exerciseDTO.getInstructions() : List.of());

            Map<Long, Set> existingSets = exercise.getSets() != null
                    ? exercise.getSets().stream().collect(Collectors.toMap(Set::getId, s -> s))
                    : new HashMap<>();

            List<Set> updatedSets = new ArrayList<>();

            for (SetDTO setDTO : exerciseDTO.getSets()) {

                Set set;
                if (setDTO.getId() != null && existingSets.containsKey(setDTO.getId())) {
                    set = existingSets.get(setDTO.getId());
                } else {
                    set = new Set();
                    set.setExercise(exercise);
                }

                set.setReps(setDTO.getReps());
                set.setWeight(setDTO.getWeight());

                updatedSets.add(set);
            }

            exercise.setSets(updatedSets);
            updatedExercises.add(exercise);
        }

        workout.getExercises().clear();
        workout.getExercises().addAll(updatedExercises);

        return workoutRepository.save(workout);
    }

    public List<Workout> getWorkoutByUserId(Long userId) {
        return workoutRepository.findByUserId(userId);
    }

}
