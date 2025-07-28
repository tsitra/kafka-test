package dev.nathanrbrown.kafka.test.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group 1: " + message);
        this.message = message;
    }
}
