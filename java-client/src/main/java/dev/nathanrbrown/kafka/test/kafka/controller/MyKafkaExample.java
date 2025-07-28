package dev.nathanrbrown.kafka.test.kafka.controller;

import dev.nathanrbrown.kafka.test.kafka.config.TopicConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController(MyKafkaExample.PATH)
public class MyKafkaExample {
    public static final String PATH = "/message";
    private String message;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory;

    @GetMapping(PATH + "/")
    public String index(){
        return message;
    }

    @GetMapping(PATH + "/{newMessage}")
    public void newMessage(@PathVariable final String newMessage){
        System.out.println("Received request for new message: " + newMessage);
        kafkaTemplate.send(TopicConfig.TOPIC,newMessage);
    }

    @KafkaListener(topics = TopicConfig.TOPIC, groupId = "1")
    public void kafkaTriggeredMessageChange(final String message) {
        this.message = message;
    }

    @PostConstruct
    public void init(){
        concurrentKafkaListenerContainerFactory.getConsumerFactory().createConsumer().
    }
}
