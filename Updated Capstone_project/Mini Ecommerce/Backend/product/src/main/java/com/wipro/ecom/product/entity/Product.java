package com.wipro.ecom.product.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "category", nullable = false)
    private String category; // electronics, apparel, kitchen, books, etc

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "uom") // litre, kg, pc
    private String uom;

    @Column(name = "rating")
    private Double rating;
    
    
    @Column(name = "image_url",  columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;
}