package com.wipro.productservice.config;

import com.wipro.common.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain chain(HttpSecurity http,
      @Value("${security.jwt.secret}") String secret,
      @Value("${security.jwt.expiration}") long exp) throws Exception {
    var jwt = new JwtUtil(secret, exp);
    var filter = new JwtAuthFilter(jwt);
    http.csrf(csrf->csrf.disable())
      .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(auth->auth
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/product/**").permitAll()
        .anyRequest().authenticated())
      .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
