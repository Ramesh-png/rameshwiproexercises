package com.wipro.userservice.request;

import com.wipro.userservice.user.User;



public class NotificationRequest {
    private User user;
    private String action; // CREATE, UPDATE, DELETE

    public NotificationRequest(User user, String action) {
        this.user = user;
        this.action = action;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
