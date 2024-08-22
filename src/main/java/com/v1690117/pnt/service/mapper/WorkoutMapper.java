package com.v1690117.pnt.service.mapper;

import com.v1690117.pnt.service.dto.SetDto;
import com.v1690117.pnt.service.dto.WorkoutDto;
import com.v1690117.pnt.service.model.Set;
import com.v1690117.pnt.service.model.Workout;
import org.mapstruct.Mapper;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    WorkoutDto map(Workout workout);

    SetDto map(Set set);

    Set map(SetDto dto);

    Workout map(WorkoutDto workoutDto);

    default Long map(Date value) {
        return value == null ? null : value.getTime();
    }

    default Date map(Long value) {
        return value == null ? null : new Date(value);
    }
}
