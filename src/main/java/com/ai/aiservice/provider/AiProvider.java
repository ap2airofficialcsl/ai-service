package com.ai.aiservice.provider;

import com.ai.aiservice.dto.AiRequest;
import com.ai.aiservice.dto.AiResponse;

public interface AiProvider {

	String getProviderName();

	AiResponse generate(AiRequest request);
}