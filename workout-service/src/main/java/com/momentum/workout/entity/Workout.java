package com.momentum.workout.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Workout {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private LocalDateTime date;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Exercise> exercises;

}
