package com.example.spring.security.demo.spring.security.service;

import com.example.spring.security.demo.spring.security.dto.JwtAuthenticationResponse;
import com.example.spring.security.demo.spring.security.dto.SignInRequest;
import com.example.spring.security.demo.spring.security.dto.SignUpRequest;
import com.example.spring.security.demo.spring.security.entity.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
}
