package com.v1690117.pnt.service.controller;

import com.v1690117.pnt.service.dto.ExerciseDto;
import com.v1690117.pnt.service.mapper.DtoMapper;
import com.v1690117.pnt.service.repository.ExerciseRepository;
import com.v1690117.pnt.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExerciseController {
    private final ExerciseRepository repository;
    private final DtoMapper mapper;
    private final UserService currentUserService;

    @GetMapping("/exercises")
    public List<ExerciseDto> getAll() {
        var user = currentUserService.getCurrentUser();
        List<ExerciseDto> re = new ArrayList<>();
        for (var exercise : repository.findAllByUser(user)) {
            re.add(mapper.map(exercise));
        }
        return re;
    }

    @GetMapping("/exercises/{id}")
    public ExerciseDto getExercise(@PathVariable Long id) {
        // todo by id and user?
        return repository.findById(id).map(mapper::map).orElseThrow();
    }

    @PostMapping("/exercises")
    public ExerciseDto newExercise(@RequestBody ExerciseDto dto) {
        // todo check if such exercise exist for current user
        var wo = mapper.map(dto);
        wo.setUser(currentUserService.getCurrentUser());
        return mapper.map(
                repository.save(
                        wo
                )
        );
    }

    @DeleteMapping("/exercises/{id}")
    public void deleteExercise(@PathVariable Long id) {
        var user = currentUserService.getCurrentUser();
        var workout = repository.findById(id).orElseThrow();
        if (!workout.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Operation not permitted!");
        }
        repository.deleteById(id);
    }
}
