package com.example.letsgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.letsgo.entity.VehicleMovement;
import com.example.letsgo.repository.VehicleRepository;

@RestController
public class VehicleController {

    @Autowired
    private VehicleRepository repository;

    @PostMapping("/move")
    public String move(@RequestBody VehicleMovement movement) {
        repository.save(movement);
        return "Movement saved successfully";
    }
}
