package com.example.spring.security.demo.spring.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
}
