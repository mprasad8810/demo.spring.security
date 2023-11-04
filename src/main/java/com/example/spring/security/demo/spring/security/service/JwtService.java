package com.example.spring.security.demo.spring.security.service;

import com.example.spring.security.demo.spring.security.entity.User;
import com.example.spring.security.demo.spring.security.service.impl.JwtServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JwtService {

    String username(String jwt);
    String generatedJwt(UserDetails userDetails);

    boolean isTokenValid(String jwt, UserDetails userDetails);

    String generatedRefreshJwt(Map<String, Object> extractclaims, UserDetails userDetails);
}
