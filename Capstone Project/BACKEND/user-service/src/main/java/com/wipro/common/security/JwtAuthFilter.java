package com.wipro.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  public JwtAuthFilter(JwtUtil jwtUtil){ this.jwtUtil = jwtUtil; }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
    if(auth != null && auth.startsWith("Bearer ")){
      try{
        var jws = jwtUtil.parse(auth.substring(7));
        String username = jws.getBody().getSubject();
        String role = jws.getBody().get("role", String.class);
        var authToken = new UsernamePasswordAuthenticationToken(username, null,
          List.of(new SimpleGrantedAuthority("ROLE_"+role)));
        // attach userId for downstream usage
        request.setAttribute("jwt.userId", jws.getBody().get("userId", Integer.class));
        request.setAttribute("jwt.role", role);
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }catch(Exception ex){ /* ignore */ }
    }
    chain.doFilter(request, response);
  }
}
