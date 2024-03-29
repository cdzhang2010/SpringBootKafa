package com.springboot.kafka.h2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.springboot.kafka.h2.domain.MessageEntity;
import com.springboot.kafka.h2.util.AppConstants;

@Service
public class MessageConsumer {

	Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);

	@Autowired
	private MessageService messageService;

	@KafkaListener(topics = AppConstants.TOPIC_NAME, groupId =AppConstants.GROUP_ID)
	public void consumeMessage(String message) {
		System.out.println("Received message: " + message);
		// Here you can persist the message to H2 database using Spring Data JPA
		messageService.saveMessage(message);
		List<MessageEntity> list = messageService.findAll();
		list.stream().forEach(e -> System.out.println(e.getId() + " \t" + e.getMessage()));
	}
	
}
