//package com.ai.aiservice.whatsapp.template.util;
//
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.ai.aiservice.whatsapp.template.dto.TemplateGenerateResponse;
//
//@Component
//public class TemplateResponseValidator {
//
//	private static final List<String> VALID_TYPES = List.of("MARKETING", "UTILITY", "AUTHENTICATION");
//
//	public void validate(TemplateGenerateResponse response) {
//
//		if (response == null) {
//			throw new RuntimeException("Template response is null");
//		}
//
//		if (isBlank(response.getTemplateName())) {
//			throw new RuntimeException("Template name is missing");
//		}
//
//		if (isBlank(response.getBody())) {
//			throw new RuntimeException("Template body is missing");
//		}
//
//		if (isBlank(response.getTemplateType())) {
//			throw new RuntimeException("Template type is missing");
//		}
//
//		if (!VALID_TYPES.contains(response.getTemplateType().toUpperCase())) {
//
//			throw new RuntimeException("Invalid template type");
//		}
//	}
//
//	private boolean isBlank(String value) {
//
//		return value == null || value.isBlank();
//	}
//}

package com.ai.aiservice.whatsapp.template.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.aiservice.whatsapp.template.dto.TemplateGenerateResponse;

@Component
public class TemplateResponseValidator {

	public void validate(TemplateGenerateResponse response) {

		if (response == null) {
			return;
		}

		if (isBlank(response.getTemplateName())) {
			response.setTemplateName("generated_template");
		}

		if (isBlank(response.getBody())) {
			response.setBody("Hello {{1}} 👋\\n\\nThank you for connecting with us.");
		}

		if (isBlank(response.getLanguage())) {
			response.setLanguage("en");
		}

		if (isBlank(response.getTemplateType())) {
			response.setTemplateType("MARKETING");
		}

		if (response.getFooter() == null) {
			response.setFooter("");
		}

		if (response.getButtons() == null) {
			response.setButtons(List.of());
		}
	}

	private boolean isBlank(String value) {

		return value == null || value.isBlank();
	}
}