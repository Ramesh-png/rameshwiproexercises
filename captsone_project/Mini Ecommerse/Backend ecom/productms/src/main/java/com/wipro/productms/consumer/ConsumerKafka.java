package com.wipro.productms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.wipro.productms.dto.OrderEvent;
import com.wipro.productms.service.ProductService;

@Service
public class ConsumerKafka {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerKafka.class);

    @Autowired
    private ProductService productService;

    @KafkaListener(
        topics = "order-events", 
        groupId = "product-service-group",
        containerFactory = "orderEventKafkaListenerContainerFactory"
    )
    public void consumeOrderEvent(
        @Payload OrderEvent event,
        @Header(KafkaHeaders.RECEIVED_KEY) String key,
        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
        @Header(KafkaHeaders.OFFSET) long offset) {

        LOGGER.info("Received message: {} from partition: {} with offset: {} and key: {}", 
                    event, partition, offset, key);

        try {
            if ("ORDER_CREATED".equals(event.getType())) {
                LOGGER.info("Processing ORDER_CREATED event for product: {}", event.getProductId());
                productService.updateProductQuantity(event.getProductId(), event.getQuantity());
            } else if ("ORDER_CANCELLED".equals(event.getType())) {
                LOGGER.info("Processing ORDER_CANCELLED event for product: {}", event.getProductId());
                productService.updateProductQuantity(event.getProductId(), -event.getQuantity());
            } else {
                LOGGER.warn("Unknown event type: {}", event.getType());
            }
        } catch (Exception e) {
            LOGGER.error("Error processing order event", e);
        }
    }
}