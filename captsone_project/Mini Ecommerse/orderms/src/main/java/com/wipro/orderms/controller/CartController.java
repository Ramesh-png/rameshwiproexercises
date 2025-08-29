package com.wipro.orderms.controller;

import com.wipro.orderms.entity.CartItem;
import com.wipro.orderms.repository.CartItemRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartItemRepository cartRepo;

    public CartController(CartItemRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem item) {
        return ResponseEntity.ok(cartRepo.save(item));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartRepo.findByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
