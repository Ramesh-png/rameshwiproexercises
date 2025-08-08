package com.spring.product.SpringProduct;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo repository;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        Product existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setProductName(updatedProduct.getProductName());
            existing.setProductMake(updatedProduct.getProductMake());
            existing.setProductPrice(updatedProduct.getProductPrice());
            return repository.save(existing);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        repository.deleteById(id);
        return "Product deleted successfully";
    }
}
