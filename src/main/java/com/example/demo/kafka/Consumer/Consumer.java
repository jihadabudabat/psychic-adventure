package com.example.demo.kafka.Consumer;

import com.example.demo.kafka.Event.Simple;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Slf4j
public class Consumer {

    @KafkaListener(groupId = "demo", topics = "simpleTopic")
    public void listen(ConsumerRecord<String, Simple> simple) {
        log.info(simple.value().toString());
    }
}
