package com.spring.auth.service;

import com.spring.auth.entity.User;
import com.spring.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class BaseService {

    protected final UserRepository userRepository;

    public User CurrentUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final User user = (User) auth.getPrincipal();
        return userRepository.findById(user.getId()).get();
    }
}
