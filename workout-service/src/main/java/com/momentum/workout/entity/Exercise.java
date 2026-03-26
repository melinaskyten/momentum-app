package com.momentum.workout.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;

    private String externalExerciseId;
    private String exerciseName;
    private String bodyPart;
    private String equipment;

    @OneToMany (mappedBy = "exercise", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Set> sets;

    @ManyToOne
    @JoinColumn (name = "workout_id")
    @JsonBackReference
    private Workout workout;

}
