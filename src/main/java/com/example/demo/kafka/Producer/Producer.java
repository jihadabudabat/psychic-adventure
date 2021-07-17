package com.example.demo.kafka.Producer;

import com.example.demo.kafka.Config.Config;
import com.example.demo.kafka.Event.Simple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, Simple> simpleKafkaTemplate;
    @Autowired
    private Config config;

    public void send(Simple event) {
        simpleKafkaTemplate.send(config.getTopic(),event);
    }
}
