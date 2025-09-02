package com.wipro.userservice.dto;

public class AuthDto {
  public static class LoginRequest { public String username; public String password; }
  public static class LoginResponse { public String token; public String role; public Long userId; }
}
