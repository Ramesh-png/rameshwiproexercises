package com.wipro.orderms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.orderms.entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByOrderId(Long orderId);
}
