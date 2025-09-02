package com.wipro.userservice.service;

import com.wipro.userservice.model.User;
import com.wipro.userservice.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
  private final UserRepository repo; private final PasswordEncoder enc;
  public UserService(UserRepository repo, PasswordEncoder enc){ this.repo=repo; this.enc=enc; }
  public User create(User u){ u.setPassword(enc.encode(u.getPassword())); return repo.save(u); }
  public User update(User u){ var e=repo.findById(u.getId()).orElseThrow(); e.setEmail(u.getEmail()); e.setName(u.getName()); e.setAddress(u.getAddress()); if(u.getPassword()!=null && !u.getPassword().isBlank()) e.setPassword(enc.encode(u.getPassword())); return repo.save(e); }
  public void delete(Long id){ repo.deleteById(id); }
  public List<User> all(){ return repo.findAll(); }
  public User get(Long id){ return repo.findById(id).orElseThrow(); }
}
