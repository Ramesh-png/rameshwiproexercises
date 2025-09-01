package com.wipro.orderms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrdermsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdermsApplication.class, args);
	}

}
