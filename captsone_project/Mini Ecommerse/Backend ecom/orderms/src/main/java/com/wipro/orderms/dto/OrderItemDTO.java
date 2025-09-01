package com.wipro.orderms.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
	private Long productId;
    private Integer quantity;
    private Double price;
    private String productName;
}
