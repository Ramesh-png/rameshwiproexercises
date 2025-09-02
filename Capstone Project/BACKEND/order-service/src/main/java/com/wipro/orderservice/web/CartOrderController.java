package com.wipro.orderservice.web;

import com.wipro.orderservice.model.CartItem;
import com.wipro.orderservice.model.Order;
import com.wipro.orderservice.service.CartOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Cart & Order Management")
public class CartOrderController {

  private final CartOrderService svc;

  public CartOrderController(CartOrderService svc) {
    this.svc = svc;
  }

  private boolean isOwnerOrAdmin(HttpServletRequest req, Long userId) {
    Object rid = req.getAttribute("jwt.userId");
    Object rrole = req.getAttribute("jwt.role");
    if (rrole != null && "ADMIN".equals(rrole.toString())) {
      return true;
    }
    return rid != null && Long.valueOf(String.valueOf(rid)).equals(userId);
  }

  // -------- CART --------
  @Operation(summary = "Add product to cart")
  @PostMapping("/cart/addProd")
  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
  public CartItem add(@RequestParam Long userId,
      @RequestParam Long productId,
      @RequestParam int qty,
      HttpServletRequest req) {
    if (!isOwnerOrAdmin(req, userId))
      throw new RuntimeException("Forbidden");
    return svc.addToCart(userId, productId, qty);
  }

  @Operation(summary = "Delete product from cart")
  @DeleteMapping("/cart/deleteProd/{itemId}")
  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
  public void delete(@PathVariable Long itemId) {
    svc.deleteFromCart(itemId);
  }

  @Operation(summary = "Change quantity")
  @PutMapping("/cart/update")
  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
  public CartItem update(@RequestParam Long itemId,
      @RequestParam int qty) {
    return svc.updateQty(itemId, qty);
  }

  @Operation(summary = "View cart by user")
  @GetMapping("/cart/{userId}")
  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
  public List<CartItem> view(@PathVariable Long userId,
      HttpServletRequest req) {
    if (!isOwnerOrAdmin(req, userId))
      throw new RuntimeException("Forbidden");
    return svc.viewCart(userId);
  }

  // -------- ORDER --------
  @Operation(summary = "Create order from cart")
  @PostMapping("/order")
  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
  public Order create(@RequestParam Long userId,
      HttpServletRequest req) {
    if (!isOwnerOrAdmin(req, userId))
      throw new RuntimeException("Forbidden");
    return svc.createOrder(userId);
  }

  @Operation(summary = "Cancel order")
  @PutMapping("/order/{orderId}")
  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
  public Order cancel(@PathVariable Long orderId) {
    return svc.cancelOrder(orderId);
  }

  @Operation(summary = "View all orders (admin)")
  @GetMapping("/order")
  @PreAuthorize("hasRole('ADMIN')")
  public List<Order> all() {
    return svc.all();
  }

  @Operation(summary = "List orders by user")
  @GetMapping("/order/{userId}")
  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
  public List<Order> byUser(@PathVariable Long userId,
      HttpServletRequest req) {
    if (!isOwnerOrAdmin(req, userId))
      throw new RuntimeException("Forbidden");
    return svc.byUser(userId);
  }

  @Operation(summary = "Order details")
  @GetMapping("/order/details/{orderId}")
  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
  public Map<String, Object> details(@PathVariable Long orderId) {
    return svc.details(orderId);
  }
}
