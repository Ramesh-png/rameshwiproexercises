package com.wipro.orderms.service.impl;

import java.util.List;

import com.wipro.orderms.entity.CartItem;
import com.wipro.orderms.entity.Order;
import com.wipro.orderms.entity.OrderItem;
import com.wipro.orderms.repo.CartItemRepo;
import com.wipro.orderms.repo.OrderItemRepo;
import com.wipro.orderms.repo.OrderRepo;
import com.wipro.orderms.service.OrderService;
import com.wipro.orderms.exception.ResourceNotFoundException;
import com.wipro.orderms.dto.OrderEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	 @Autowired
	    private OrderRepo orderRepository;
	    
	    @Autowired
	    private OrderItemRepo orderItemRepository;
	    
	    @Autowired
	    private CartItemRepo cartItemRepository;
	    
	    @Autowired
	    private KafkaTemplate<String, Object> kafkaTemplate;
	    
	    @Autowired
	    private WebClient.Builder webClientBuilder;
	    
	    @Value("${product.service.url}")
	    private String productServiceUrl;
	    
	    @Override
	    public Order createOrder(Long userId) {
	        // Get cart items for user
	        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
	        if (cartItems.isEmpty()) {
	            throw new RuntimeException("Cart is empty");
	        }
	        
	        checkProductAvailability(cartItems);
	        
	        // Calculate total amount
	        Double totalAmount = cartItems.stream()
	                .mapToDouble(item -> item.getPrice() * item.getQuantity())
	                .sum();
	        
	        // Create order
	        Order order = new Order();
	        order.setUserId(userId);
	        order.setOrderDate(LocalDateTime.now());
	        order.setStatus(Order.OrderStatus.PENDING);
	        order.setTotalAmount(totalAmount);
	        
	        Order savedOrder = orderRepository.save(order);
	        
	        // Create order items and send Kafka events
	        for (CartItem cartItem : cartItems) {
	            OrderItem orderItem = new OrderItem();
	            orderItem.setOrder(savedOrder);
	            orderItem.setProductId(cartItem.getProductId());
	            orderItem.setQuantity(cartItem.getQuantity());
	            orderItem.setPrice(cartItem.getPrice());
	            orderItem.setProductName(cartItem.getProductName());
	            
	            orderItemRepository.save(orderItem);
	            
	            // Send Kafka event for product quantity update
	            OrderEvent event = new OrderEvent();
	            event.setType("ORDER_CREATED");
	            event.setProductId(cartItem.getProductId());
	            event.setQuantity(cartItem.getQuantity());
	            event.setOrderId(savedOrder.getId());
	            
	            kafkaTemplate.send("order-events", event);
	        }
	        
	        // Clear cart
	        cartItemRepository.deleteByUserId(userId);
	        
	        // Update order status to confirmed
	        savedOrder.setStatus(Order.OrderStatus.CONFIRMED);
	        return orderRepository.save(savedOrder);
	    }
	    
	    @Override
	    public Order cancelOrder(Long orderId) {
	        Order order = orderRepository.findById(orderId)
	                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
	        
	        order.setStatus(Order.OrderStatus.CANCELLED);
	        Order cancelledOrder = orderRepository.save(order);
	        
	        // Send Kafka events to restore product quantities
	        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
	        for (OrderItem item : orderItems) {
	            OrderEvent event = new OrderEvent();
	            event.setType("ORDER_CANCELLED");
	            event.setProductId(item.getProductId());
	            event.setQuantity(item.getQuantity());
	            event.setOrderId(orderId);
	            
	            kafkaTemplate.send("order-events", event);
	        }
	        
	        return cancelledOrder;
	    }
	    
	    @Override
	    public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }
	    
	    @Override
	    public List<Order> getOrdersByUserId(Long userId) {
	        return orderRepository.findByUserId(userId);
	    }
	    
	    @Override
	    public Order getOrderById(Long orderId) {
	        return orderRepository.findById(orderId)
	                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
	    }
	    
	    private void checkProductAvailability(List<CartItem> cartItems) {
	        for (CartItem cartItem : cartItems) {
	            try {
	                // Call Product Service to check availability
	                Integer availableQuantity = webClientBuilder.build()
	                        .get()
	                        .uri("http://productms/product/{id}/quantity", cartItem.getProductId())
	                        .retrieve()
	                        .bodyToMono(Integer.class)
	                        .block();
	                
	                if (availableQuantity == null || availableQuantity < cartItem.getQuantity()) {
	                    throw new RuntimeException("Insufficient quantity for product ID: " + cartItem.getProductId() + 
	                                             ". Available: " + availableQuantity + ", Requested: " + cartItem.getQuantity());
	                }
	                
	            } catch (WebClientResponseException e) {
	                if (e.getStatusCode().value() == 404) {
	                    throw new RuntimeException("Product not found with ID: " + cartItem.getProductId());
	                }
	                throw new RuntimeException("Error checking product availability: " + e.getMessage());
	            } catch (Exception e) {
	                throw new RuntimeException("Error checking product availability: " + e.getMessage());
	            }
	        }
	    }

}
