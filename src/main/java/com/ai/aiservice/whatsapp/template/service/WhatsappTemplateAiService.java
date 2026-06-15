package com.ai.aiservice.whatsapp.template.service;

import org.springframework.stereotype.Service;

import com.ai.aiservice.dto.AiRequest;
import com.ai.aiservice.dto.AiResponse;
import com.ai.aiservice.service.AiService;
import com.ai.aiservice.whatsapp.template.config.WhatsappTemplateAiProperties;
import com.ai.aiservice.whatsapp.template.dto.TemplateGenerateRequest;
import com.ai.aiservice.whatsapp.template.dto.TemplateGenerateResponse;
import com.ai.aiservice.whatsapp.template.util.JsonExtractor;
import com.ai.aiservice.whatsapp.template.util.TemplatePromptBuilder;
import com.ai.aiservice.whatsapp.template.util.TemplateResponseValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WhatsappTemplateAiService {

	private final AiService aiService;

	private final TemplatePromptBuilder promptBuilder;

	private final JsonExtractor jsonExtractor;

	private final TemplateResponseValidator validator;

	private final ObjectMapper objectMapper;

	private final WhatsappTemplateAiProperties properties;

	public TemplateGenerateResponse generate(TemplateGenerateRequest request) {

		String prompt = promptBuilder.build(request);

		AiResponse aiResponse = tryPrimaryProvider(prompt);

		if (!isValid(aiResponse)) {

			aiResponse = tryFallbackProvider(prompt);
		}

		if (!isValid(aiResponse)) {

			throw new RuntimeException("Template generation failed");
		}

		try {

			String cleanJson = jsonExtractor.extract(aiResponse.getContent());

			TemplateGenerateResponse response = objectMapper.readValue(cleanJson, TemplateGenerateResponse.class);

			response.setRawJson(cleanJson);

			validator.validate(response);

			return response;

		} catch (Exception e) {

			throw new RuntimeException("Failed to parse AI response", e);
		}
	}

//	private AiResponse tryPrimaryProvider(String prompt) {
//
//		try {
//
//			AiRequest request = new AiRequest();
//
//			request.setProvider(properties.getPrimaryProvider());
//
//			request.setModel(properties.getGroqModel());
//
//			request.setPrompt(prompt);
//
//			return aiService.ask(request);
//
//		}catch (Exception e) {
//
//		    e.printStackTrace();
//
//		    throw new RuntimeException("Primary provider failed", e);
//		}
//	}
	
	
	private AiResponse tryPrimaryProvider(String prompt) {

	    AiRequest request = new AiRequest();

	    request.setProvider(properties.getPrimaryProvider());
	    request.setModel(properties.getOpenrouterModel());
	    request.setPrompt(prompt);

	    return aiService.ask(request);
	}

	private AiResponse tryFallbackProvider(String prompt) {

	    AiRequest request = new AiRequest();

	    request.setProvider(properties.getFallbackProvider());
	    request.setModel(properties.getGroqModel());
	    request.setPrompt(prompt);

	    return aiService.ask(request);
	}

	private boolean isValid(AiResponse response) {

		return response != null && response.isSuccess() && response.getContent() != null
				&& !response.getContent().isBlank();
	}
}