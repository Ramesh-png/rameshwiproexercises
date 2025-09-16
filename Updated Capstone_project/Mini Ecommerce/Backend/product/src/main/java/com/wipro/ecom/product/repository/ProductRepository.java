package com.wipro.ecom.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wipro.ecom.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
