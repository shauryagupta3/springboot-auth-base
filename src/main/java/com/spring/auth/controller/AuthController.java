package com.spring.auth.controller;

import com.spring.auth.config.auth.TokenProvider;
import com.spring.auth.dto.JwtDto;
import com.spring.auth.dto.SigninDto;
import com.spring.auth.dto.SignupDto;
import com.spring.auth.entity.User;
import com.spring.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/authenticate")
@RequiredArgsConstructor
public class AuthController {
        private final AuthenticationManager authenticationManager;
        private final AuthService service;
        private final TokenProvider tokenService;

        @PostMapping("/signup")
        public ResponseEntity<?> signUp(@RequestBody @Valid SignupDto data) {
            service.signUp(data);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @PostMapping("/signin")
        public ResponseEntity<JwtDto> signIn(@Valid @RequestBody SigninDto data) {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
            var authUser = authenticationManager.authenticate(usernamePassword);
            var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
            return ResponseEntity.ok(new JwtDto(accessToken));
        }
    }

