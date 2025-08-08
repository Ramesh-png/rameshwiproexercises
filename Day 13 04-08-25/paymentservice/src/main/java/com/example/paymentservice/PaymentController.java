package com.example.paymentservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestBody Payment payment) {
        if (payment.isPaymentStatus()) {
            return ResponseEntity.ok("Payment Successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment Failed");
        }
    }
}
