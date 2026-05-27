package com.ai.aiservice.whatsapp.template.util;

import org.springframework.stereotype.Component;

@Component
public class JsonExtractor {

	public String extract(String response) {

		if (response == null || response.isBlank()) {
			throw new RuntimeException("Empty AI response");
		}

		int start = response.indexOf("{");

		int end = response.lastIndexOf("}");

		if (start < 0 || end < 0 || start >= end) {
			throw new RuntimeException("Invalid JSON response");
		}

		return response.substring(start, end + 1);
	}
}