package com.ai.aiservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.aiservice.provider.AiProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderRouterService {

	private final List<AiProvider> providers;

	public AiProvider getProvider(String providerName) {

		return providers.stream().filter(provider -> provider.getProviderName().equalsIgnoreCase(providerName))
				.findFirst().orElseThrow(() -> new RuntimeException("Provider not found : " + providerName));
	}
}