package com.momentum.workout.service;

import com.momentum.workout.dto.*;
import com.momentum.workout.entity.Exercise;
import com.momentum.workout.entity.Set;
import com.momentum.workout.entity.Workout;
import com.momentum.workout.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
