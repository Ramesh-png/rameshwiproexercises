package com.wipro.orderms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.orderms.dto.CartItemDTO;
import com.wipro.orderms.entity.CartItem;
import com.wipro.orderms.exception.ResourceNotFoundException;
import com.wipro.orderms.repo.CartItemRepo;
import com.wipro.orderms.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepo cartItemRepository;

    @Override
    public CartItem addToCart(CartItemDTO cartItemDTO) {
        // Check if item already exists in cart
        CartItem existingItem = cartItemRepository.findByUserIdAndProductId(
            cartItemDTO.getUserId(), cartItemDTO.getProductId());

        if (existingItem != null) {
            // Update quantity if item exists
            existingItem.setQuantity(existingItem.getQuantity() + cartItemDTO.getQuantity());
            return cartItemRepository.save(existingItem);
        } else {
            // Add new item to cart
            CartItem cartItem = new CartItem();
            cartItem.setUserId(cartItemDTO.getUserId());
            cartItem.setProductId(cartItemDTO.getProductId());
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItem.setPrice(cartItemDTO.getPrice());
            cartItem.setProductName(cartItemDTO.getProductName());

            return cartItemRepository.save(cartItem);
        }
    }

    @Override
    public void removeFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart item not found with id: " + cartItemId));
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart item not found with id: " + cartItemId));

        if (quantity <= 0) {
            cartItemRepository.delete(cartItem);
            return null;
        }

        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getCartByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
