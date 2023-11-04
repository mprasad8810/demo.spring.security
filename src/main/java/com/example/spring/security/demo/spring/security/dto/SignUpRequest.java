package com.example.spring.security.demo.spring.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
