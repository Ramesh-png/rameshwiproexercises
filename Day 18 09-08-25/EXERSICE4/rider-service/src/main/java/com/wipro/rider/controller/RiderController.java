package com.wipro.rider.controller;

import com.wipro.rider.entity.RideRequest;
import com.wipro.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
public class RiderController {

    @Autowired
    RiderService riderService;

    @PostMapping
    public ResponseEntity<RideRequest> save(@RequestBody RideRequest r) {
        RideRequest saved = riderService.save(r);
        return ResponseEntity.ok(saved);
    }
}
