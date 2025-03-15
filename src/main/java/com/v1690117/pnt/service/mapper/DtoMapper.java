package com.v1690117.pnt.service.mapper;

import com.v1690117.pnt.service.dto.ExerciseDto;
import com.v1690117.pnt.service.dto.SetDto;
import com.v1690117.pnt.service.dto.SetWithWorkoutDto;
import com.v1690117.pnt.service.dto.WorkoutDto;
import com.v1690117.pnt.service.model.Exercise;
import com.v1690117.pnt.service.model.Set;
import com.v1690117.pnt.service.model.Workout;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoMapper {
    WorkoutDto map(Workout workout);

    ExerciseDto map(Exercise exercise);

    SetDto map(Set set);

    Set map(SetDto dto);

    Workout map(WorkoutDto workoutDto);

    List<WorkoutDto> mapWorkouts(List<Workout> workouts);

    Exercise map(ExerciseDto exerciseDto);

    default Long map(Date value) {
        return value == null ? null : value.getTime();
    }

    default Date map(Long value) {
        return value == null ? null : new Date(value);
    }

    List<SetDto> mapSets(List<Set> sets);

    List<SetWithWorkoutDto> mapSetWithWorkout(List<Set> sets);
}
