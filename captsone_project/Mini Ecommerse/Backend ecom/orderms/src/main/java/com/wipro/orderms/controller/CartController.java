package com.wipro.orderms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.orderms.dto.CartItemDTO;
import com.wipro.orderms.entity.CartItem;
import com.wipro.orderms.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart Management", description = "APIs for managing shopping cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    @Operation(summary = "Add product to cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product added to cart successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItemDTO cartItemDTO) {
        CartItem cartItem = cartService.addToCart(cartItemDTO);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/{cartItemId}")
    @Operation(summary = "Remove item from cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item removed from cart successfully"),
        @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{cartItemId}")
    @Operation(summary = "Update cart item quantity")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart item updated successfully"),
        @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    public ResponseEntity<CartItem> updateCartItemQuantity(@PathVariable Long cartItemId,
                                                           @RequestParam Integer quantity) {
        CartItem cartItem = cartService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get cart by user ID")
    public ResponseEntity<List<CartItem>> getCartByUserId(@PathVariable Long userId) {
        List<CartItem> cartItems = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Clear user's cart")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
