package com.v1690117.pnt.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetWithWorkoutDto {
    private Long id;
    private Float load;
    private Integer reps;
    private Integer index;
    private Integer rest;
    private Boolean completed;
    private WorkoutDto workout;
}
