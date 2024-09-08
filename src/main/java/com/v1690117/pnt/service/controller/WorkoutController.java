package com.v1690117.pnt.service.controller;

import com.v1690117.pnt.service.dto.SetDto;
import com.v1690117.pnt.service.dto.WorkoutDto;
import com.v1690117.pnt.service.mapper.WorkoutMapper;
import com.v1690117.pnt.service.model.Workout;
import com.v1690117.pnt.service.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class WorkoutController {
    private final WorkoutRepository repository;
    private final WorkoutMapper workoutMapper;

    @GetMapping("/workouts")
    public List<WorkoutDto> getAll() {
        List<WorkoutDto> re = new ArrayList<>();
        for (var workout : repository.findAll()) {
            re.add(workoutMapper.map(workout));
        }
        return re;
    }

    @GetMapping("/workouts/{id}")
    public WorkoutDto getWorkout(@PathVariable Long id) {
        return repository.findById(id).map(workoutMapper::map).orElseThrow();
    }

    @PostMapping("/workouts")
    public WorkoutDto newWorkout(@RequestBody WorkoutDto dto) {
        if (dto.getDate() == null) {
            dto.setDate(new Date().getTime());
        }
        return workoutMapper.map(
                repository.save(
                        workoutMapper.map(dto)
                )
        );
    }

    @GetMapping("/workouts/{id}/sets")
    public List<SetDto> getSets(@PathVariable Long id) {
        var wo = repository.findById(id);
        List<SetDto> res = new ArrayList<>();
        wo.map(Workout::getSets).ifPresent(sets -> sets.forEach(
                set -> res.add(workoutMapper.map(set))
        ));
        return res;
    }

    @PostMapping("/workouts/{id}/sets")
    public void addSet(@PathVariable Long id, @RequestBody SetDto set) {
        var wo = repository.findById(id).orElseThrow();
        var newSet = workoutMapper.map(set);
        newSet.setCompleted(false);
        if (wo.getSets().size() != 0) {
            var latestSet = wo.getSets().get(wo.getSets().size() - 1);
            newSet.setReps(latestSet.getReps());
            newSet.setLoad(latestSet.getLoad());
            newSet.setRest(latestSet.getRest());
        }
        wo.getSets().add(newSet);
        newSet.setWorkout(wo);
        repository.save(wo);
    }
}
