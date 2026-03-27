package com.momentum.workout.mapper;

import com.momentum.workout.dto.ExerciseDTO;
import com.momentum.workout.dto.SetDTO;
import com.momentum.workout.dto.WorkoutDTO;
import com.momentum.workout.entity.Exercise;
import com.momentum.workout.entity.Workout;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkoutMapper {

    public WorkoutDTO toDTO(Workout workout) {
        WorkoutDTO workoutDto = new WorkoutDTO();

        workoutDto.setUserId(workout.getUserId());

        List<ExerciseDTO> exercises = workout.getExercises()
                .stream()
                .map(this::exerciseToDTO)
                .toList();

        workoutDto.setExercises(exercises);
        return workoutDto;
    }

    private ExerciseDTO exerciseToDTO(Exercise exercise) {
        ExerciseDTO exerciseDTO = new ExerciseDTO();

        exerciseDTO.setExerciseId(exercise.getExternalExerciseId());
        exerciseDTO.setName(exercise.getExerciseName());

        exerciseDTO.setBodyParts(
                exercise.getBodyPart() != null
                        ? List.of(exercise.getBodyPart())
                        : List.of()
        );

        exerciseDTO.setEquipments(
                exercise.getEquipment() != null
                        ? List.of(exercise.getEquipment())
                        : List.of()
        );

        exerciseDTO.setInstructions(
                exercise.getInstructions() != null
                        ? exercise.getInstructions()
                        : List.of()
        );

        List<SetDTO> sets = exercise.getSets() != null
                ? exercise.getSets().stream().map(set -> {
            SetDTO setDTO = new SetDTO();
            setDTO.setReps(set.getReps());
            setDTO.setWeight(set.getWeight());
            return setDTO;
        }).toList()
                : List.of();

        exerciseDTO.setSets(sets);

        return exerciseDTO;
    }
}
