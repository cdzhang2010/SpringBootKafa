package com.springboot.kafka.h2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.springboot.kafka.h2.domain.User;
import com.springboot.kafka.h2.util.AppConstants;

@Service
public class MessageUserConsumer {
	Logger LOG = LoggerFactory.getLogger(MessageUserConsumer.class);

	@Autowired
	private UserService userService;

	@KafkaListener(topics =AppConstants.JSON_TOPIC_NAME, groupId = AppConstants.GROUP_ID)
	public void consumeMessage(User user) {
		System.out.println("Received message: " + user.toString());
		// Here you can persist the message to H2 database using Spring Data JPA
		userService.save(user);
		List<User> list = userService.findAll();
		list.stream().forEach(e -> System.out.println(e.toString()));
	}
}
