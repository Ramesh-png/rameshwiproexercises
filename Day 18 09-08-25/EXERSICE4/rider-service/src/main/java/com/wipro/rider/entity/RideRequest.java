package com.wipro.rider.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ride_request")
@Data
public class RideRequest {
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
