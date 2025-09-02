package com.wipro.userservice.web;

import com.wipro.userservice.model.User;
import com.wipro.userservice.repo.UserRepository;
import com.wipro.userservice.service.UserService;
import com.wipro.userservice.dto.AuthDto;
import com.wipro.common.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/user")
@Tag(name="User Management")
public class UserController {
  private final UserService service; private final UserRepository repo; private final PasswordEncoder enc; private final JwtUtil jwt;
  public UserController(UserService service, UserRepository repo, PasswordEncoder enc,
                        @Value("${security.jwt.secret}") String secret,
                        @Value("${security.jwt.expiration}") long exp){
    this.service=service; this.repo=repo; this.enc=enc; this.jwt=new JwtUtil(secret, exp);
  }

  @Operation(summary="Create user (registration)")
  @PostMapping public User create(@RequestBody User u){ return service.create(u); }

  @Operation(summary="Update user profile")
  @PutMapping public User update(@RequestBody User u){ return service.update(u); }

  @Operation(summary="Delete user")
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }

  @GetMapping public List<User> list(){ return service.all(); }
  @GetMapping("/{id}") public User get(@PathVariable Long id){ return service.get(id); }

  @GetMapping("/menu/{id}") public Map<String,Object> menu(@PathVariable Long id){ var u=service.get(id); return Map.of("items", u.getRole().equals("ADMIN")? List.of("Dashboard","Products","Orders"):List.of("Dashboard","Catalog","My Orders")); }

  @PostMapping("/login")
  public ResponseEntity<AuthDto.LoginResponse> login(@RequestBody AuthDto.LoginRequest req){
    var u = repo.findByUsername(req.username).orElseThrow();
    if(!enc.matches(req.password, u.getPassword())) return ResponseEntity.status(401).build();
    String token = jwt.generateToken(u.getUsername(), u.getRole(), u.getId());
    var res = new AuthDto.LoginResponse(); res.token=token; res.role=u.getRole(); res.userId=u.getId();
    return ResponseEntity.ok(res);
  }

  @PostMapping("/logout") public Map<String,String> logout(){ return Map.of("status","ok"); }
}
