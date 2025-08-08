package com.spring.entity.SpringDepartment;

 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.spring.entity") // 👈 Add this line!
public class SpringDepartmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDepartmentApplication.class, args);
    }
}
