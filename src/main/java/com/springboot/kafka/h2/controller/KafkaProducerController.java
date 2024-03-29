package com.springboot.kafka.h2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.kafka.h2.service.MessagePublisher;
import com.springboot.kafka.h2.service.MessageService;
import com.springboot.kafka.h2.service.MessageUserProducer;
import com.springboot.kafka.h2.domain.*;

@RestController
@RequestMapping(path="api/v1/kafka/h2")
public class KafkaProducerController {

	@Autowired
	private MessagePublisher publisher;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageUserProducer messageUserProducer;
	
	@RequestMapping(path="/publish")
	public ResponseEntity publish( @RequestParam("message") String message) {	
		publisher.sendMessage(message);	
		return ResponseEntity.ok("Sent Message ="+ message);
	}
	
	 @GetMapping(path="getAllMessages")
	    public ResponseEntity<List<MessageEntity>> getAllMessages() {
	        List<MessageEntity> messages = messageService.findAll();
	        return new ResponseEntity<>(messages, HttpStatus.OK);
	    }
	 
	
}
