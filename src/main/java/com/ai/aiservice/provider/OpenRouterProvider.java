package com.ai.aiservice.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ai.aiservice.dto.AiRequest;
import com.ai.aiservice.dto.AiResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OpenRouterProvider implements AiProvider {

	private final RestTemplate restTemplate;

	@Value("${openrouter.api.key}")
	private String apiKey;

	@Override
	public String getProviderName() {

		return "openrouter";
	}

	@Override
	public AiResponse generate(AiRequest request) {

		try {

			String url = "https://openrouter.ai/api/v1/chat/completions";

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);

			headers.setBearerAuth(apiKey);

			headers.add("HTTP-Referer", "http://localhost:8004");

			headers.add("X-Title", "ai-service");

			Map<String, Object> body = new HashMap<>();

			body.put("model", request.getModel());

			body.put("messages", List.of(Map.of("role", "user", "content", request.getPrompt())));

			body.put("max_tokens", request.getMaxTokens());

			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

			ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.POST, entity,
					new ParameterizedTypeReference<>() {
					});

			Object responseBodyObj = response.getBody();

			if (!(responseBodyObj instanceof Map<?, ?> responseBody)) {

				throw new RuntimeException("Invalid response body");
			}

			Object choicesObj = responseBody.get("choices");

			if (!(choicesObj instanceof List<?> choices) || choices.isEmpty()) {

				throw new RuntimeException("No choices found");
			}

			Object firstChoiceObj = choices.get(0);

			if (!(firstChoiceObj instanceof Map<?, ?> firstChoice)) {

				throw new RuntimeException("Invalid choice object");
			}

			Object messageObj = firstChoice.get("message");

			if (!(messageObj instanceof Map<?, ?> message)) {

				throw new RuntimeException("Invalid message object");
			}

			String content = String.valueOf(message.get("content"));

			AiResponse aiResponse = new AiResponse();

			aiResponse.setSuccess(true);

			aiResponse.setProvider(getProviderName());

			aiResponse.setModel(request.getModel());

			aiResponse.setContent(content);

			return aiResponse;

		} catch (Exception e) {

			e.printStackTrace();

			AiResponse aiResponse = new AiResponse();

			aiResponse.setSuccess(false);

			aiResponse.setProvider(getProviderName());

			aiResponse.setError(e.getMessage());

			return aiResponse;
		}
	}
}