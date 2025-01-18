package com.v1690117.pnt.service.config;

import com.v1690117.pnt.service.auth.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static com.v1690117.pnt.service.Constants.PROFILE_NOT_PRODUCTION;

@Profile(PROFILE_NOT_PRODUCTION)
@Configuration
@EnableWebSecurity
public class DevelopmentWebSecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http, CustomOAuth2UserService customOAuth2UserService) throws Exception {
        return http.authorizeHttpRequests(a -> a.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
