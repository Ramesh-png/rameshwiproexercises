package com.wipro.orderservice.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="orders")
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; private Long userId; private Instant createdAt; private String status; private double total;
  public Order(){}
  public Long getId(){ return id; } public void setId(Long id){ this.id=id; }
  public Long getUserId(){ return userId; } public void setUserId(Long userId){ this.userId=userId; }
  public Instant getCreatedAt(){ return createdAt; } public void setCreatedAt(Instant createdAt){ this.createdAt=createdAt; }
  public String getStatus(){ return status; } public void setStatus(String status){ this.status=status; }
  public double getTotal(){ return total; } public void setTotal(double total){ this.total=total; }
}
