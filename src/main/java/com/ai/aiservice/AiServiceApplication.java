package com.ai.aiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.ai.aiservice.whatsapp.template.config.YamlPropertySourceFactory;

@SpringBootApplication
@PropertySource(
        value = "classpath:whatsapp-template-ai.yml",
        factory = YamlPropertySourceFactory.class
)
public class AiServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(AiServiceApplication.class, args);
    }
}