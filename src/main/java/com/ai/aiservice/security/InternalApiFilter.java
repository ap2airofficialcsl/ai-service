package com.ai.aiservice.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InternalApiFilter extends OncePerRequestFilter {

	private final InternalApiProperties properties;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String apiKey = request.getHeader("X-API-KEY");

		if (apiKey == null || !apiKey.equals(properties.getApiKey())) {

			response.setStatus(HttpStatus.UNAUTHORIZED.value());

			response.getWriter().write("Invalid API Key");

			return;
		}

		filterChain.doFilter(request, response);
	}
}