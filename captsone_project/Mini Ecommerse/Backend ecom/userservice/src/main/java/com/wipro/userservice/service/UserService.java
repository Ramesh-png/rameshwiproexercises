package com.wipro.userservice.service;

import java.util.List;

import com.wipro.userservice.dto.Token;
import com.wipro.userservice.dto.UserDTO;
import com.wipro.userservice.entity.User;

public interface UserService {
	 	User registerUser(UserDTO userDTO);
	    
	 	User updateUser(Long id, UserDTO userDTO);
	    
	 	void deleteUser(Long id);
	    
	 	List<User> getAllUsers();
	    
	 	User getUserById(Long id);
	    
	 	User getUserByUsername(String username);
	    
	 	Token login(UserDTO userDTO);

}
