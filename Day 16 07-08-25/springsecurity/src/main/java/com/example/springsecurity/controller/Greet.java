package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greet {

	 @GetMapping("/hello")
	    public String hello() {
	        return "Hello - No authentication needed!";
	    }

	    @GetMapping("/secure")
	    public String secure() {
	        return "This is a secure endpoint!";
	    }

	    @GetMapping("/admin")
	    public String admin() {
	        return "Only authenticated users can see this!";
	    }
}
