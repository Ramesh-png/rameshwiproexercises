package com.wipro.apigateway.filter;

import com.wipro.apigateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    private static final List<String> openApiEndpoints = List.of(
            "/user/login",
            "/user/register"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        
        if (openApiEndpoints.stream().anyMatch(path::endsWith)) {
            return chain.filter(exchange);
        }

        
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String token = (authHeader != null && authHeader.startsWith("Bearer "))
                ? authHeader.substring(7) : null;

        if (token == null || !jwtUtil.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

       
        Claims claims = jwtUtil.extractAllClaims(token);
        String username = claims.getSubject();

       
        ServerHttpRequest modifiedRequest = request.mutate()
                .header("X-Auth-User", username)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .build();

        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }

    @Override
    public int getOrder() {
        return -1; 
    }
}