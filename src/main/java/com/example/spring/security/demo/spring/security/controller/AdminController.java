package com.example.spring.security.demo.spring.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", exposedHeaders = "**")
public class AdminController {

    @PostMapping("/data")
    public String testAdmin(@RequestHeader (value = "Authorization") String authheader ){
        System.out.println("authheader  === "+authheader);
        return "welcome";
    }
}
