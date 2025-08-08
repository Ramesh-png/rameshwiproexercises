package com.wipro.notificationservice.controller;

 

import org.springframework.web.bind.annotation.*;

import com.wipro.notificationservice.notificationdto.NotificationDTO;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @PostMapping
    public String notifyUser(@RequestBody NotificationDTO notificationDTO) {
        String userAction = notificationDTO.getAction();
        String username = notificationDTO.getUser().getUsername();
        System.out.println("User " + username + " information is " + userAction);
        return "Notification sent : " + userAction;
    }
}
	