package com.v1690117.pnt.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetDto {
    private Long id;
    private Long exerciseId;
    private ExerciseDto exercise;
    private String title;
    private Float load;
    private Integer reps;
    private Integer index;
    private Integer rest;
    private Boolean completed;
}
