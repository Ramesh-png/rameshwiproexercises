package com.example.letsgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.letsgo.entity.VehicleMovement;

public interface VehicleRepository extends JpaRepository<VehicleMovement, Long> {
}
