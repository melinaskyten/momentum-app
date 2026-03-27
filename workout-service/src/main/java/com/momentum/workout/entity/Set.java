package com.momentum.workout.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Set {

    @Id
    @GeneratedValue
    private Long id;

    private int reps;
    private double weight;

    @ManyToOne
    @JoinColumn (name = "exercise_id")
    @JsonBackReference
    private Exercise exercise;

}
