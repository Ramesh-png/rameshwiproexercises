package com.example.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setOrderStatus("I"); // Initial status

        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setPaymentStatus(true);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                "http://PAYMENT-MS/payment", payment, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                order.setOrderStatus("P"); // Payment successful
            } else {
                order.setOrderStatus("C"); // Payment failed
            }
        } catch (Exception e) {
            order.setOrderStatus("C"); // Exception in call
        }

        return ResponseEntity.ok(order);
    }
}
