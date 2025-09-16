package com.wipro.ecom.product.service;

import java.util.List;
import java.util.Optional;
import com.wipro.ecom.product.entity.Product;

public interface ProductService {
    Product createProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
    Product updateProductQuantity(Long id, Integer quantity);
}