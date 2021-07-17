package com.example.demo.kafka.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "kafka")
@Component
@Getter
@Setter
public class Config {
    private String topic;
}
