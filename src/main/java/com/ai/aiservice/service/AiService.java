package com.ai.aiservice.service;

import org.springframework.stereotype.Service;

import com.ai.aiservice.dto.AiRequest;
import com.ai.aiservice.dto.AiResponse;
import com.ai.aiservice.provider.AiProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiService {

	private final ProviderRouterService routerService;

	public AiResponse ask(AiRequest request) {

		validateRequest(request);

		AiProvider provider = routerService.getProvider(request.getProvider());

		return provider.generate(request);
	}

	private void validateRequest(AiRequest request) {

		if (request == null) {
			throw new RuntimeException("AI request is required");
		}

		if (isBlank(request.getProvider())) {
			throw new RuntimeException("Provider is required");
		}

		if (isBlank(request.getModel())) {
			throw new RuntimeException("Model is required");
		}

		if (isBlank(request.getPrompt())) {
			throw new RuntimeException("Prompt is required");
		}
	}

	private boolean isBlank(String value) {

		return value == null || value.isBlank();
	}
}