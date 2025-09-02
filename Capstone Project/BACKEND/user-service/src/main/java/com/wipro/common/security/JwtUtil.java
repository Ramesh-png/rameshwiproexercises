package com.wipro.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
  private final Key key;
  private final long expirationMs;

  public JwtUtil(String secret, long expirationMs){
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
    this.expirationMs = expirationMs;
  }

  public String generateToken(String username, String role, Long userId){
    Date now = new Date();
    Date exp = new Date(now.getTime()+expirationMs);
    return Jwts.builder()
      .setSubject(username)
      .addClaims(Map.of("role", role, "userId", userId))
      .setIssuedAt(now)
      .setExpiration(exp)
      .signWith(key, SignatureAlgorithm.HS256)
      .compact();
  }

  public Jws<Claims> parse(String token){
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }
}
