package com.example.jwtDemo.controller;



import com.example.jwtDemo.security.JwtUtil;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
import java.util.Collections;
import java.util.Map;


@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");

        if ("admin".equals(username) && "password".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return Collections.singletonMap("token", token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

}
