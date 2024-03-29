package com.springboot.kafka.h2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.kafka.h2.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
