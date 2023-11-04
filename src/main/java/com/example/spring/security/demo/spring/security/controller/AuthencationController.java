package com.example.spring.security.demo.spring.security.controller;

import com.example.spring.security.demo.spring.security.dto.JwtAuthenticationResponse;
import com.example.spring.security.demo.spring.security.dto.SignInRequest;
import com.example.spring.security.demo.spring.security.dto.SignUpRequest;
import com.example.spring.security.demo.spring.security.entity.User;
import com.example.spring.security.demo.spring.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthencationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
      return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }
}
