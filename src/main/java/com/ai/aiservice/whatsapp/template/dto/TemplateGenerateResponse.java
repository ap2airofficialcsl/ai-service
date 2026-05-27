package com.ai.aiservice.whatsapp.template.dto;

import java.util.List;

import lombok.Data;

@Data
public class TemplateGenerateResponse {

	private String templateName;

	private String category;

	private String templateType;

	private String language;

	private String body;

	private String footer;

	private List<Object> buttons;

	private String rawJson;
}