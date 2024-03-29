package com.springboot.kafka.h2.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.kafka.h2.domain.User;


@Service
public class MessageUserProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired //not required here
	public MessageUserProducer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(User user) {
		
		Message<User> message=MessageBuilder.withPayload(user)
				.setHeader(KafkaHeaders.TOPIC, "topic-json")
				.build();
		
	     // Create JSON object to send
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser;
        try {
            jsonUser = objectMapper.writeValueAsString(user); // Convert User object to JSON string
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
		System.out.println("User  = "+ message);

        // Send JSON object to Kafka topic
        ProducerRecord<String, String> record = new ProducerRecord<>("topic-json", jsonUser);
		kafkaTemplate.send("topic-json", jsonUser);
		
	}
}
