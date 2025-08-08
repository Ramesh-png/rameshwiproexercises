package com.wipro.userservice.usercontroller;

import com.wipro.userservice.user.User;
import com.wipro.userservice.notification.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    private final String notificationServiceUrl = "http://localhost:8081/notify";  // Notification service URL

    // Create User
    @PostMapping
    public String createUser(@RequestBody User user) {
        // Notify Notification service about user creation
        restTemplate.postForObject(notificationServiceUrl, new NotificationDTO(user, "Created"), String.class);
        return "User created successfully!";
    }

    // Update User
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        // Ensure the ID is correct for update operation
        user.setId(id);  // Set the ID to the path variable ID (make sure we are updating the correct user)
        // Notify Notification service about user update
        restTemplate.postForObject(notificationServiceUrl, new NotificationDTO(user, "Updated"), String.class);
        return "User updated successfully!";
    }

    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        // Create user object with the ID for deletion notification
        User user = new User();
        user.setId(id);
        restTemplate.postForObject(notificationServiceUrl, new NotificationDTO(user, "Deleted"), String.class);
        return "User deleted successfully!";
    }
}
