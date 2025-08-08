package com.example.mobilecurd.repository;

import com.example.mobilecurd.model.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface MobileRepository extends JpaRepository<Mobile, Integer> {
}