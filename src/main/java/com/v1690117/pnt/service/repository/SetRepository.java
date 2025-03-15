package com.v1690117.pnt.service.repository;

import com.v1690117.pnt.service.model.Exercise;
import com.v1690117.pnt.service.model.Set;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SetRepository extends CrudRepository<Set, Long> {
    List<Set> findAllByExercise(Exercise exercise);
}
