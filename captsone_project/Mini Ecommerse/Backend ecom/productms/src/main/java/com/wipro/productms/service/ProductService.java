package com.wipro.productms.service;

import com.wipro.productms.entity.Product;
import com.wipro.productms.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product create(Product product) {
        return repo.save(product);
    }

    public Product update(Product product) {
        return repo.save(product);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Optional<Product> getById(Long id) {
        return repo.findById(id);
    }

    public List<Product> getByCategory(String category) {
        return repo.findByCategory(category);
    }

	public void updateProductQuantity(Long productId, Integer quantity) {
		// TODO Auto-generated method stub
		
	}

	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product updateProduct(Long id, Product productDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}
}
