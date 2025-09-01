package com.wipro.orderms.service;

import java.util.List;

import com.wipro.orderms.entity.Order;

public interface OrderService {
	Order createOrder(Long userId);
    Order cancelOrder(Long orderId);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(Long userId);
    Order getOrderById(Long orderId);
}
