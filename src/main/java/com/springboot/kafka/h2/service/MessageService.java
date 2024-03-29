package com.springboot.kafka.h2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.kafka.h2.dao.MessageRepository;
import com.springboot.kafka.h2.domain.MessageEntity;

@Service
public class MessageService {
	
	 @Autowired
	    private MessageRepository messageRepository;

	    public void saveMessage(String messageContent) {
	        MessageEntity messageEntity = new MessageEntity();
	        messageEntity.setMessage(messageContent);
	        messageRepository.save(messageEntity);
	    }

	    public List<MessageEntity> findAll() {   
	      return  messageRepository.findAll();
	    }
	    
}
