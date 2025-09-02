package com.wipro.productservice.service;

import com.wipro.productservice.model.Product;
import com.wipro.productservice.repo.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
  private final ProductRepository repo;

  public ProductService(ProductRepository repo) {
    this.repo = repo;
  }

  public Product create(Product p) {
    return repo.save(p);
  }

  public Product update(Product p) {
    return repo.save(p);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<Product> all() {
    return repo.findAll();
  }

  public Product get(Long id) {
    return repo.findById(id).orElseThrow();
  }

  public synchronized Product adjustQty(Long id, int delta) {
    var p = repo.findById(id).orElseThrow();
    int q = p.getQuantity() + delta;
    if (q < 0)
      throw new IllegalArgumentException("Insufficient quantity");
    p.setQuantity(q);
    return repo.save(p);
  }
}
