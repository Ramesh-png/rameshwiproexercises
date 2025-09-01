package com.wipro.orderms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.orderms.entity.Order;
import com.wipro.orderms.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order Management", description = "APIs for managing orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}")
    @Operation(summary = "Create a new order from user's cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Order> createOrder(@PathVariable Long userId) {
        Order order = orderService.createOrder(userId);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/cancel")
    @Operation(summary = "Cancel an order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order cancelled successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        Order order = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    @Operation(summary = "Get all orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get orders by user ID")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get order by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order found"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}
