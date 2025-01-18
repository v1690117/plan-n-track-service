package com.v1690117.pnt.service.service;

import com.v1690117.pnt.service.model.User;
import com.v1690117.pnt.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import static com.v1690117.pnt.service.Constants.PROFILE_PRODUCTION;

@Profile(PROFILE_PRODUCTION)
@RequiredArgsConstructor
@Service
public class ProductionUserService implements UserService {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof OAuth2User oAuth2User)) {
            throw new IllegalStateException("User is not authenticated");
        }
        var externalId = "" + oAuth2User.getAttribute("id");
        return userRepository.findByExternalId(externalId)
                .orElseThrow(() -> new IllegalStateException("User not found in database"));
    }
}
