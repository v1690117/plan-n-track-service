package com.v1690117.pnt.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WorkoutDto {
    private Long id;
    private String title;
    private Date date;
}
