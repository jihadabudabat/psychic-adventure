package com.example.demo.kafka;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.example.demo.kafka.Consumer.Consumer;
import com.example.demo.kafka.Event.Simple;
import com.example.demo.kafka.Producer.Producer;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import ch.qos.logback.classic.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@EmbeddedKafka(topics = "simpleTopic" , brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class DemoApplicationTests {

    @Autowired
    private Consumer consumer;
    @Autowired
    private Producer producer;

    private final static String EVENT_AS_STRING = "Simple(name=Simple Name, id=Simple ID)";
    @Test
    void contextLoads() throws InterruptedException {
        // Given
        Simple event = Simple.builder()
                             .id("Simple ID")
                             .name("Simple Name")
                             .build();
        // When
        producer.send(event);
        Logger logger = (Logger) LoggerFactory.getLogger(Consumer.class);
        ListAppender<ILoggingEvent> loggerAdapter = new ListAppender<>();
        logger.addAppender(loggerAdapter);
        loggerAdapter.start();
        Thread.sleep(5000);

        // Then
        assertEquals(Level.INFO,loggerAdapter.list.get(0).getLevel());
        assertEquals(EVENT_AS_STRING,loggerAdapter.list.get(0).getMessage());
    }

}
