package com.wipro.orderservice.service;

import com.wipro.orderservice.client.ProductClient;
import com.wipro.orderservice.model.CartItem;
import com.wipro.orderservice.model.Order;
import com.wipro.orderservice.model.OrderItem;
import com.wipro.orderservice.repo.CartItemRepository;
import com.wipro.orderservice.repo.OrderItemRepository;
import com.wipro.orderservice.repo.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class CartOrderService {

  private final CartItemRepository cartRepo;
  private final OrderRepository orderRepo;
  private final OrderItemRepository itemRepo;
  private final ProductClient productClient;

  public CartOrderService(CartItemRepository c, OrderRepository o, OrderItemRepository i, ProductClient p) {
    this.cartRepo = c;
    this.orderRepo = o;
    this.itemRepo = i;
    this.productClient = p;
  }

  public CartItem addToCart(Long userId, Long productId, int qty) {
    CartItem c = new CartItem();
    c.setUserId(userId);
    c.setProductId(productId);
    c.setQuantity(qty);
    return cartRepo.save(c);
  }

  public void deleteFromCart(Long itemId) {
    cartRepo.deleteById(itemId);
  }

  public CartItem updateQty(Long itemId, int qty) {
    CartItem c = cartRepo.findById(itemId)
        .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
    c.setQuantity(qty);
    return cartRepo.save(c);
  }

  public List<CartItem> viewCart(Long userId) {
    return cartRepo.findByUserId(userId);
  }

  @Transactional
  public Order createOrder(Long userId) {
    List<CartItem> items = cartRepo.findByUserId(userId);
    if (items.isEmpty()) {
      throw new IllegalStateException("Cart empty");
    }

    double total = 0;
    for (CartItem it : items) {
      Map<String, Object> p = productClient.get(it.getProductId());
      int stock = ((Number) p.get("quantity")).intValue();
      double price = ((Number) p.get("price")).doubleValue();
      if (it.getQuantity() > stock) {
        throw new IllegalArgumentException("Quantity not available for " + p.get("name"));
      }
      total += price * it.getQuantity();
    }

    // adjust stock
    for (CartItem it : items) {
      productClient.adjust(it.getProductId(), -it.getQuantity());
    }

    Order order = new Order();
    order.setUserId(userId);
    order.setCreatedAt(Instant.now());
    order.setStatus("CREATED");
    order.setTotal(total);
    order = orderRepo.save(order);

    for (CartItem it : items) {
      Map<String, Object> p = productClient.get(it.getProductId());
      double price = ((Number) p.get("price")).doubleValue();

      OrderItem oi = new OrderItem();
      oi.setOrderId(order.getId());
      oi.setProductId(it.getProductId());
      oi.setQuantity(it.getQuantity());
      oi.setPrice(price);
      itemRepo.save(oi);
    }

    // clear cart
    cartRepo.deleteByUserId(userId);
    return order;
  }

  @Transactional
  public Order cancelOrder(Long orderId) {
    Order o = orderRepo.findById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    if (!"CREATED".equals(o.getStatus())) {
      return o;
    }

    var items = itemRepo.findByOrderId(orderId);
    for (OrderItem it : items) {
      productClient.adjust(it.getProductId(), it.getQuantity());
    }

    o.setStatus("CANCELLED");
    return orderRepo.save(o);
  }

  public List<Order> all() {
    return orderRepo.findAll();
  }

  public List<Order> byUser(Long userId) {
    return orderRepo.findByUserId(userId);
  }

  public Map<String, Object> details(Long orderId) {
    Order o = orderRepo.findById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    var items = itemRepo.findByOrderId(orderId);
    return Map.of("order", o, "items", items);
  }
}
