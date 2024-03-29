package com.springboot.kafka.h2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.springboot.kafka.h2.domain.User;

@Service
public class MessagePublisher {
	private static final String TOPIC_NAME = "test-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }
   
}
