package com.springboot.kafka.h2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.kafka.h2.domain.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
//JpaRepository interface from the Spring framework, 
//which provides a default implementation for the basic CRUD operations.
}
