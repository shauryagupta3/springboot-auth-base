package com.spring.auth.service;

import com.spring.auth.dto.SignupDto;
import com.spring.auth.entity.User;
import com.spring.auth.exception.InvalidJwtException;
import com.spring.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user;
    }

    public UserDetails signUp(SignupDto data) throws InvalidJwtException {
        if (userRepository.findByUsername(data.getUsername()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getUsername(), encryptedPassword, data.getRole());
        return userRepository.save(newUser);
    }
}
