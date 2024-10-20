package com.v1690117.pnt.service.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        var ctx = SecurityContextHolder.getContext();
        if (principal == null) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("name", principal.getAttribute("login"));
    }
}
