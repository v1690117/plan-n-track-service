package com.v1690117.pnt.service.repository;

import com.v1690117.pnt.service.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByExternalId(String externalId);

    Optional<User> findByExternalId(String externalId);
}
