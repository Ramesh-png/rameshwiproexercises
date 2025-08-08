package com.wipro.notificationservice.notificationdto;

import com.wipro.notificationservice.user.User;

public class NotificationDTO {
    private User user;
    private String action;

    public NotificationDTO(User user, String action) {
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
