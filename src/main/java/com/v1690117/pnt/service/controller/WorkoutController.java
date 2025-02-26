package com.v1690117.pnt.service.controller;

import com.v1690117.pnt.service.dto.SetDto;
import com.v1690117.pnt.service.dto.WorkoutDto;
import com.v1690117.pnt.service.mapper.DtoMapper;
import com.v1690117.pnt.service.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class WorkoutController {
    private final WorkoutService workoutService;
    private final DtoMapper mapper;

    @GetMapping("/workouts")
    public List<WorkoutDto> getAll() {
        return mapper.mapWorkouts(workoutService.findWorkoutsByUser());
    }

    @GetMapping("/workouts/{id}")
    public WorkoutDto getWorkout(@PathVariable Long id) {
        // todo by id and user?
        return mapper.map(
                workoutService.getById(id)
        );
    }

    @PostMapping("/workouts")
    public WorkoutDto newWorkout(@RequestBody WorkoutDto dto) {
        return mapper.map(
                workoutService.create(
                        mapper.map(dto)
                )
        );
    }

    @PostMapping("/workouts/{id}")
    public Long cloneWorkout(@PathVariable Long id) {
        return workoutService.clone(id);
    }

    // todo get rid of this?
    @GetMapping("/workouts/{id}/sets")
    public List<SetDto> getSets(@PathVariable Long id) {
        // todo check user
        return mapper.mapSets(
                workoutService.getSets(id)
        );
    }

    @PostMapping("/workouts/{id}/sets")
    public void addSet(@PathVariable Long id, @RequestBody SetDto set) {
        // todo check user
        workoutService.addSets(
                id,
                List.of(
                        mapper.map(set)
                )
        );
    }

    @DeleteMapping("/workouts/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.delete(id);
    }
}
