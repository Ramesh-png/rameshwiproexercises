package com.example.carregistration.entity;


import jakarta.persistence.*;
import java.time.LocalDate;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@Entity
@SpringBootApplication
public class CarRegistrationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;

    private LocalDate dateOfRegistration;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public LocalDate getDateOfRegistration() { return dateOfRegistration; }
    public void setDateOfRegistration(LocalDate dateOfRegistration) { this.dateOfRegistration = dateOfRegistration; }
}
