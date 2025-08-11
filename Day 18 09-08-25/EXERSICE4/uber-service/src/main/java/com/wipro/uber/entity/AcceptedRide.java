package com.wipro.uber.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "accepted_ride")
@Data
public class AcceptedRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String riderName;
    private String pickup;
    @Column(name = "drop_location")
    private String dropLocation;
    private String status;
    private LocalDateTime timestamp;
}
