package com.springboot.kafka.h2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.kafka.h2.dao.UserRepository;
import com.springboot.kafka.h2.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	
	public List<User> findAll() {
	return userRepository.findAll();
	}
	
}
