package com.wipro.ecom.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.wipro.ecom.product.entity.Product;
import com.wipro.ecom.product.repository.ProductRepository;
import com.wipro.ecom.product.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        kafkaTemplate.send("product-events", "Product created: " + savedProduct.getName());
        return savedProduct;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setCategory(productDetails.getCategory());
            product.setManufacturer(productDetails.getManufacturer());
            product.setPrice(productDetails.getPrice());
            product.setQuantity(productDetails.getQuantity());
            product.setUom(productDetails.getUom());
            product.setRating(productDetails.getRating());
            product.setImageUrl(productDetails.getImageUrl());
            product.setManufactureDate(productDetails.getManufactureDate());
            
            Product updated = productRepository.save(product);
            kafkaTemplate.send("product-events", "Product updated: " + updated.getName());
            return updated;
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            kafkaTemplate.send("product-events", "Product deleted: " + productOptional.get().getName());
        }
    }

    @Override
    public Product updateProductQuantity(Long id, Integer quantity) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setQuantity(quantity);
            Product updatedProduct = productRepository.save(product);
            kafkaTemplate.send("product-events", "Product quantity updated: " + product.getName() + " to " + quantity);
            return updatedProduct;
        }
        return null;
    }
}