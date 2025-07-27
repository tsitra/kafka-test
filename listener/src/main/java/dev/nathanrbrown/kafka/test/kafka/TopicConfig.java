package dev.nathanrbrown.kafka.test.kafka;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    
    // @Value(value = "${spring.kafka.bootstrap-servers}")
    // private String bootstrapAddress;

    // @Bean
    // public KafkaAdmin kafkaAdmin() {
        // Map<String, Object> configs = new HashMap<>();
        // configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        // return new KafkaAdmin(configs);
    // }
}
