package com.wipro.ecom.user.service;

import com.wipro.ecom.user.entity.User;
import com.wipro.ecom.user.model.JwtRequest;
import com.wipro.ecom.user.model.JwtResponse;

public interface AuthService {
    JwtResponse authenticate(JwtRequest jwtRequest);
    User register(User user);
}