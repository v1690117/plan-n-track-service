package com.v1690117.pnt.service.mapper;

import com.v1690117.pnt.service.dto.WorkoutDto;
import com.v1690117.pnt.service.model.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    WorkoutDto map(Workout workout);

    Workout map(WorkoutDto workoutDto);
}
