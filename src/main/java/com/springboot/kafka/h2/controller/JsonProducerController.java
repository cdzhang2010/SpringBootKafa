package com.springboot.kafka.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.kafka.h2.domain.User;
import com.springboot.kafka.h2.service.MessageUserProducer;

@RestController
@RequestMapping(path="api/v1/kafka/h2")
public class JsonProducerController {
		
	@Autowired
	private MessageUserProducer messageUserProducer;
	
	
	@PostMapping(path="/publishUser")
	public ResponseEntity publish( @RequestBody User user) {	
		System.out.println(user.toString());
		messageUserProducer.sendMessage(user);	
		return ResponseEntity.ok("Sent Message ="+ user.toString());
	}


}
