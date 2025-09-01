package com.wipro.orderms.dto;

import com.wipro.orderms.entity.Order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
	
	private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Double totalAmount;
    private List<OrderItemDTO> items;

}
