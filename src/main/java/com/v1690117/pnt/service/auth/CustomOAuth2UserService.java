package com.v1690117.pnt.service.auth;

import com.v1690117.pnt.service.model.User;
import com.v1690117.pnt.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String externalId = "" + oAuth2User.getAttribute("id");
        String login = oAuth2User.getAttribute("login");
        if (!userRepository.existsByExternalId(externalId)) {
            userRepository.save(
                    new User()
                            .setExternalId(externalId)
                            .setLogin(login)
                            .setExternalSource("github")
            );
        }
        return oAuth2User;
    }
}
