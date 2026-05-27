package com.ai.aiservice.dto;

import lombok.Data;

@Data
public class AiRequest {

    private String provider;

    private String model;

    private String prompt;

    private Double temperature = 0.7;

    private Integer maxTokens = 1000;
}