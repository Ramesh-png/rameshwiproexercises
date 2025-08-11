package com.wipro.rider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.rider.entity.RideRequest;

public interface RideRequestRepo extends JpaRepository<RideRequest, Integer> {
}
