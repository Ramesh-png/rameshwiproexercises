package com.wipro.orderms.dto;

import lombok.Data;

@Data
public class CartItemDTO {
	private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double price;
    private String productName;
}
