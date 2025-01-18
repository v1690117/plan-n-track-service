package com.v1690117.pnt.service.service;

import com.v1690117.pnt.service.model.User;
import com.v1690117.pnt.service.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import static com.v1690117.pnt.service.Constants.PROFILE_NOT_PRODUCTION;

@Profile(PROFILE_NOT_PRODUCTION)
@RequiredArgsConstructor
@Service
public class DevelopmentUserService implements UserService {
    private final User user = new User()
            .setId(1L)
            .setExternalId("1")
            .setLogin("user")
            .setExternalSource("local");
    private final UserRepository userRepository;

    public User getCurrentUser() {
        return user;
    }

    @PostConstruct
    public void prepareTestUser() {
        if (userRepository.findByExternalId("1").isEmpty()) {
            userRepository.save(user);
        }
    }
}
