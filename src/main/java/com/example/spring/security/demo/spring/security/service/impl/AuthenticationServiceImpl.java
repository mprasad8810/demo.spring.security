package com.example.spring.security.demo.spring.security.service.impl;


import com.example.spring.security.demo.spring.security.dto.JwtAuthenticationResponse;
import com.example.spring.security.demo.spring.security.dto.SignInRequest;
import com.example.spring.security.demo.spring.security.dto.SignUpRequest;
import com.example.spring.security.demo.spring.security.entity.Role;
import com.example.spring.security.demo.spring.security.entity.User;
import com.example.spring.security.demo.spring.security.repository.UserRepository;
import com.example.spring.security.demo.spring.security.service.AuthenticationService;
import com.example.spring.security.demo.spring.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public User signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getLastname());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

//                .builder()
//                .firstname(signUpRequest.getFirstname())
//                .lastname(signUpRequest.getLastname())
//                .email(signUpRequest.getEmail())
//                .role(Role.USER)
//                .password(passwordEncoder.encode(signUpRequest.getPassword()))
//                .build();

        return repository.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
        var user = repository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("user name or password incorrect"));
        var jwt = jwtService.generatedJwt(user);
        var refreshjwt = jwtService.generatedRefreshJwt(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = JwtAuthenticationResponse.builder()
                .token(jwt)
                .refreshToken(refreshjwt)
                .build();
        return jwtAuthenticationResponse;
    }
}
