package com.wipro.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@FeignClient(name="PRODUCT-SERVICE", path="/product")
public interface ProductClient {
  @GetMapping("/{id}") Map<String,Object> get(@PathVariable("id") Long id);
  @PutMapping("/{id}/adjust-qty") Map<String,Object> adjust(@PathVariable("id") Long id, @RequestParam int delta);
}
