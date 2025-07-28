package dev.nathanrbrown.kafka.test.kafka.service;

import dev.nathanrbrown.kafka.test.kafka.config.TopicConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaLoggerService {
    @KafkaListener(topics = TopicConfig.TOPIC, groupId = "1")
    public void logTopicChange(final String newMessage){
        System.out.println("New Message received: " + newMessage);
    }
}
