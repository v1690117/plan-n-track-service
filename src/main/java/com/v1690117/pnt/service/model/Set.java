package com.v1690117.pnt.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
