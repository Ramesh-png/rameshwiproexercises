package com.wipro.orderms.controller;

import com.wipro.orderms.entity.OrderEntity;
import com.wipro.orderms.entity.OrderItem;
import com.wipro.orderms.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    // GET all orders
    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(orderRepo.findAll());
    }
    
    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                item.setOrder(order); // make sure items know their parent
            }
        }
        OrderEntity saved = orderRepo.save(order);
        return ResponseEntity.ok(saved);
    }


   
}
