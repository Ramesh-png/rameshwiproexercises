package com.example.carregistration.repository;



import com.example.carregistration.entity.CarRegistrationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDetailRepo extends JpaRepository<CarDetailRepo, Long> {
}
