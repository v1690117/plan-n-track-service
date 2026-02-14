package com.v1690117.pnt.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sets_seq")
    @SequenceGenerator(name = "sets_seq", sequenceName = "sets_seq", allocationSize = 1)
    private Long id;
    private String title;
    private Float load;
    private Integer reps;
    private Integer index;
    private Integer rest;
    private Boolean completed;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id")
//    @JoinColumn(name = "exercise_id", nullable = false) // todo this is temp
    private Exercise exercise;
    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @Transient
    private Long exerciseId; // todo how to pass this correctly

    public Long getExerciseId() {
        return exerciseId == null ? exercise == null ? null : exercise.getId() : exerciseId;
    }
}
