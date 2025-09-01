package com.wipro.orderms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private String type; 
    private Long productId;
    private Integer quantity;
    private Long orderId;
    private LocalDateTime timestamp;
    private Long userId;

    public OrderEvent(String type, Long productId, Integer quantity, Long orderId) {
        this.type = type;
        this.productId = productId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.timestamp = LocalDateTime.now();
    }
}
