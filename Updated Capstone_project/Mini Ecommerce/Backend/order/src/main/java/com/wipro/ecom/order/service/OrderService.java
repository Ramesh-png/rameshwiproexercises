package com.wipro.ecom.order.service;

import java.util.List;
import java.util.Optional;
import com.wipro.ecom.order.entity.Order;

public interface OrderService {
    Order createOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(Long userId);
    Order updateOrderStatus(Long id, String status);
    void deleteOrder(Long id);
}
