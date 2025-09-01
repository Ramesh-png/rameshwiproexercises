package com.wipro.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotBlank(message = "Username is required")
	    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	    @Column(nullable = false, unique = true)
	    private String username;
	    
	    @NotBlank(message = "Email is required")
	    @Email(message = "Email should be valid")
	    @Column(nullable = false, unique = true)
	    private String email;
	    
	    @NotBlank(message = "Password is required")
	    @Size(min = 6, message = "Password must be at least 6 characters")
	    @Column(nullable = false)
	    private String password;
	    
	    private String address;
	    
	    @Enumerated(EnumType.STRING)
	    private Role role;
	    
	    public enum Role {
	        ADMIN, CUSTOMER
	    }

		public User() {
			super();
		}

		public User(Long id,
				@NotBlank(message = "Username is required") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username,
				@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email,
				@NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters") String password,
				String address, Role role) {
			super();
			this.id = id;
			this.username = username;
			this.email = email;
			this.password = password;
			this.address = address;
			this.role = role;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
					+ ", address=" + address + ", role=" + role + "]";
		}
	    
	    

}
