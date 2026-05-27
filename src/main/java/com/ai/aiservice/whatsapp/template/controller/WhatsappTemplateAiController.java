package com.ai.aiservice.whatsapp.template.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ai.aiservice.whatsapp.template.dto.TemplateGenerateRequest;
import com.ai.aiservice.whatsapp.template.dto.TemplateGenerateResponse;
import com.ai.aiservice.whatsapp.template.service.WhatsappTemplateAiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ai/whatsapp/template")
@RequiredArgsConstructor
public class WhatsappTemplateAiController {

    private final WhatsappTemplateAiService service;

    @PostMapping("/generate")
    public ResponseEntity<TemplateGenerateResponse> generate(
            @RequestBody
            TemplateGenerateRequest request) {

        return ResponseEntity.ok(
                service.generate(request)
        );
    }
}