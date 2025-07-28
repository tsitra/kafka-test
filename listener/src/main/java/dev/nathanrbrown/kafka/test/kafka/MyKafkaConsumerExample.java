package dev.nathanrbrown.kafka.test.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController("/")
public class MyKafkaConsumerExample {

    @GetMapping
    public String index(){
        return "Hello, world.";
    }

    @KafkaListener(topics = "test-topic", groupId = "1")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
