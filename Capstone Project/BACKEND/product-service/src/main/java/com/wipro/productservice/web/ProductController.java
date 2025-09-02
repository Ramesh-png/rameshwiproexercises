package com.wipro.productservice.web;

import com.wipro.productservice.model.Product;
import com.wipro.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name="Product Management")
public class ProductController {
  private final ProductService svc;
  public ProductController(ProductService svc){ this.svc=svc; }

  @GetMapping public List<Product> list(){ return svc.all(); }
  @GetMapping("/{id}") public Product get(@PathVariable Long id){ return svc.get(id); }

  @PostMapping @PreAuthorize("hasRole('ADMIN')") public Product create(@RequestBody Product p){ return svc.create(p); }
  @PutMapping @PreAuthorize("hasRole('ADMIN')") public Product update(@RequestBody Product p){ return svc.update(p); }
  @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public void delete(@PathVariable Long id){ svc.delete(id); }

  @PutMapping("/{id}/adjust-qty") @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
  public Product adjust(@PathVariable Long id, @RequestParam int delta){ return svc.adjustQty(id, delta); }
}
