package com.example.kafka_consumer.listener;

import com.example.kafka_common.model.Subject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {

    @KafkaListener(topics = "learn-subject", groupId = "spring-consumer-group")
    public void consume(Subject subject) {
        System.out.println("Consumer received: " + subject);
    }
}
