package com.wipro.ecom.user.model;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private Long userId;
    private String username;
    private String role;
    
    public JwtResponse(String token, Long userId, String username, String role) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
}
