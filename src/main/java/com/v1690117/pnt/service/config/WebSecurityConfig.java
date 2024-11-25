package com.v1690117.pnt.service.config;

import com.v1690117.pnt.service.auth.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http, CustomOAuth2UserService customOAuth2UserService) throws Exception {
        return http.authorizeHttpRequests(
                        a -> a.requestMatchers("/login", "/login.html", "/error", "/webjars/**").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .logout(l -> l
//                        .logoutUrl("/logout")
                                .logoutSuccessUrl("/").permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
//                .csrf(c -> c
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                )
                .oauth2Login(o -> o
                        .userInfoEndpoint(u -> u.userService(customOAuth2UserService))
                        .loginPage("/login") // Указываем URL страницы логина
                        .defaultSuccessUrl("/", true)
                )
                .build();
    }
}
