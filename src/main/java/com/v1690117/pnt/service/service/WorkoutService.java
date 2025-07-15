package com.v1690117.pnt.service.service;

import com.v1690117.pnt.service.mapper.DtoMapper;
import com.v1690117.pnt.service.model.Set;
import com.v1690117.pnt.service.model.Workout;
import com.v1690117.pnt.service.repository.ExerciseRepository;
import com.v1690117.pnt.service.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkoutService {
    private final WorkoutRepository repository;
    private final UserService currentUserService;
    private final ExerciseRepository exerciseRepository;
    private final DtoMapper mapper;

    public List<Workout> findWorkoutsByUser() {
        return repository.findAllByUser(currentUserService.getCurrentUser());
    }

    public Workout getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Workout create(Workout wo) {
        if (wo.getDate() == null) {
            wo.setDate(new Date());
        }
        wo.setUser(currentUserService.getCurrentUser());
        return repository.save(wo);
    }

    public List<Set> getSets(Long id) {
        return getById(id).getSets();
    }

    public void addSets(Long id, List<Set> newSets) {
        var wo = repository.findById(id).orElseThrow();
        for (var newSet : newSets) {
            newSet.setCompleted(false);
            if (newSet.getExerciseId() != null) { // todo this is temp check. then this field must be required.
                var exercise = exerciseRepository.findById(newSet.getExerciseId()).orElseThrow();
                newSet.setExercise(exercise);
                newSet.setTitle(exercise.getTitle());
            }
            if (wo.getSets() == null) {
                wo.setSets(new ArrayList<>());
            }
            if (newSet.getReps() == null && !wo.getSets().isEmpty()) {
                var latestSet = wo.getSets().get(wo.getSets().size() - 1);
                newSet.setReps(latestSet.getReps());
                newSet.setLoad(latestSet.getLoad());
                newSet.setRest(latestSet.getRest());
            }
            wo.getSets().add(newSet);
            newSet.setWorkout(wo);
        }
        repository.save(wo);
    }

    public void delete(Long id) {
        var user = currentUserService.getCurrentUser();
        var workout = getById(id);
        if (!workout.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Operation not permitted!");
        }
        repository.deleteById(id);
    }

    public Long clone(Long id) {
        var cloning = getById(id);

        var newWoDto = mapper.map(cloning);
        newWoDto.setId(null);
        newWoDto.setDate(new Date().getTime());

        var newWo = create(mapper.map(newWoDto));

        addSets(
                newWo.getId(),
                cloning.getSets().stream()
                        .map(mapper::map)
                        .map(mapper::map)
                        .peek(s -> s.setId(null))
                        .collect(Collectors.toList())
        );

        return newWo.getId();
    }
}
