package com.example.jwtDemo.controller;


import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.time.LocalDateTime;


@RestController
public class TimeController {

	@GetMapping("/getCurrentTime")
	public Map<String, String> getCurrentTime() {
	    return Collections.singletonMap("currentTime", LocalDateTime.now().toString());
	}
}
