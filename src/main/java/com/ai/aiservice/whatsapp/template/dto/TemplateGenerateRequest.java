package com.ai.aiservice.whatsapp.template.dto;

import lombok.Data;

@Data
public class TemplateGenerateRequest {

	/*
	 * Example: "give discount offer for shoes"
	 */
	private String userPrompt;

	/*
	 * MARKETING UTILITY AUTHENTICATION
	 */
	private String templateType;
}