package com.ai.aiservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class InternalApiProperties {

	@Value("${internal.api.key}")
	private String apiKey;
}