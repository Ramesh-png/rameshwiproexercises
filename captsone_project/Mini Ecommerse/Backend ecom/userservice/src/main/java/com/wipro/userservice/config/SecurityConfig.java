package com.wipro.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class SecurityConfig {

    private static final String SECRET_KEY = "mySecretKey"; // same as used in JWT generation

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/login/**", "/user/register/**").permitAll()
                .requestMatchers("/user/**").authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt()); // Enable JWT validation
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 👇 This fixes the error
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(
            new javax.crypto.spec.SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256")
        ).build();
    }
}
