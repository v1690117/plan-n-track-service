package com.v1690117.pnt.service.repository;

import com.v1690117.pnt.service.model.Exercise;
import com.v1690117.pnt.service.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    List<Exercise> findAllByUser(User user);
}
