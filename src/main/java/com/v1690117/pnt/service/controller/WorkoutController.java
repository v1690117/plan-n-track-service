package com.v1690117.pnt.service.controller;

import com.v1690117.pnt.service.dto.WorkoutDto;
import com.v1690117.pnt.service.mapper.WorkoutMapper;
import com.v1690117.pnt.service.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/workouts")
    public WorkoutDto newWorkout(@RequestBody WorkoutDto dto) {
        if (dto.getDate() == null) {
            dto.setDate(new Date());
        }
        return workoutMapper.map(
                repository.save(
                        workoutMapper.map(dto)
                )
        );
    }
}
