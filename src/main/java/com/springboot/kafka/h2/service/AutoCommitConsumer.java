package com.springboot.kafka.h2.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.annotation.KafkaListener;

import com.springboot.kafka.h2.domain.User;

public class AutoCommitConsumer extends KafkaConsumer<Long, User> {


	public AutoCommitConsumer(Map<String, Object> configs, Deserializer<Long> keyDeserializer,
			Deserializer<User> valueDeserializer) {
		super(configs, keyDeserializer, valueDeserializer);
		
	}

	//@KafkaListener(topics = "test-topic, ", groupId = "group-1")
	public void consume() {
		Properties props = new Properties();
	     props.setProperty("bootstrap.servers", "localhost:9092");
	     props.setProperty("group.id", "test");
	     props.setProperty("enable.auto.commit", "true");
	     props.setProperty("auto.commit.interval.ms", "1000");
	     props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.setProperty("value.deserializer", "org.springframework.kafka.support.serializer.JsonDeserializer");
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	     consumer.subscribe(Arrays.asList("test-topic", "bar"));
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
	         for (ConsumerRecord<String, String> record : records)
	             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	     }
	}
	

}
