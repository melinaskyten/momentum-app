package com.momentum.workout.mapper;

import com.momentum.workout.dto.ExerciseDTO;
import com.momentum.workout.dto.SetDTO;
import com.momentum.workout.dto.WorkoutDTO;
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
                .map(exercise -> {
                    ExerciseDTO exerciseDTO = new ExerciseDTO();
                    exerciseDTO.setExerciseId(exercise.getExternalExerciseId());

                    List<SetDTO> sets = exercise.getSets().stream().map(set -> {
                        SetDTO setDTO = new SetDTO();
                        setDTO.setReps(set.getReps());
                        setDTO.setWeight(set.getWeight());
                        return setDTO;
                    }).toList();

                    exerciseDTO.setSets(sets);
                    return exerciseDTO;
                }).toList();

        workoutDto.setExercises(exercises);
        return workoutDto;

    }
}
