package com.v1690117.pnt.service.repository;

import com.v1690117.pnt.service.model.User;
import com.v1690117.pnt.service.model.Workout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {
    List<Workout> findAllByUser(User user);
}
