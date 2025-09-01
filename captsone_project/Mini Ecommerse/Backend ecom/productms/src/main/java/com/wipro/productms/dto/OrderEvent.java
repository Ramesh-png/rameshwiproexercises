package com.wipro.productms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String type;
    private Long productId;
    private Integer quantity;
    private Long orderId;
}
