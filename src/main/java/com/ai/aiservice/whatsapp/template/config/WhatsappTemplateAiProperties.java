package com.ai.aiservice.whatsapp.template.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "whatsapp.template.ai")
public class WhatsappTemplateAiProperties {

	private String primaryProvider;

	private String fallbackProvider;

	private String groqModel;

	private String openrouterModel;
}