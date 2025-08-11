package com.wipro.uber.service;

import com.wipro.uber.dto.Ride;
import com.wipro.uber.entity.AcceptedRide;

public interface UberService {
    void processRide(Ride ride);
}
