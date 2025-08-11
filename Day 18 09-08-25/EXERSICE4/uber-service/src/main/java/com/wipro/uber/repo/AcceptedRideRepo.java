package com.wipro.uber.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.uber.entity.AcceptedRide;

public interface AcceptedRideRepo extends JpaRepository<AcceptedRide, Integer> {
}
