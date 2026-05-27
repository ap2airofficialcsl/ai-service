package com.ai.aiservice.whatsapp.template.util;

import org.springframework.stereotype.Component;

import com.ai.aiservice.whatsapp.template.dto.TemplateGenerateRequest;

@Component
public class TemplatePromptBuilder {

	public String build(TemplateGenerateRequest request) {

//		return """
//												You are a WhatsApp Business template generator.
//				Generate ONLY valid JSON. Do not write explanations. Do not write markdown. Do not write notes. Do not wrap JSON in ```.
//												STRICT RULES:
//											1. Return ONLY valid JSON
//											2. No markdown
//											3. No explanation
//								4. Generate Meta-compatible templates
//								5. Keep messages natural and professional
//								6. Detect language automatically
//								7. Add buttons only if needed
//								8. templateName is REQUIRED and must NEVER be empty
//								9. templateName must be lowercase_with_underscores
//								10. category is REQUIRED
//								11. body is REQUIRED
//								12. language is REQUIRED
//								13. If variables are used, they must be inside double curly braces only, like {{1}}, {{2}}, {{3}}. Never use any other variable format
//
//									ALLOWED category VALUES: - MARKETING - UTILITY
//												TEMPLATE TYPE:
//												%s
//
//												USER INPUT:
//												%s
//
//												RETURN FORMAT:
//
//												{
//												  "templateName":"",
//												  "category":"",
//												  "templateType":"",
//												  "language":"",
//												  "body":"",
//												  "footer":"",
//												  "buttons":[]
//												}
//												"""
//				.formatted(request.getTemplateType(), request.getUserPrompt());
//	}

		return """
				You are an expert WhatsApp Business template writer.

				Return ONLY valid JSON.

				STRICT RULES:
				1. Return ONLY valid JSON
				2. No markdown
				3. No explanations
				4. Generate Meta-compatible WhatsApp templates
				5.  Keep message short, professional, attractive, and WhatsApp friendly
				6. Detect language automatically from user input
				7. Add buttons only if genuinely useful
				8. templateName is REQUIRED and must NEVER be empty
				9. Variables must ONLY use Meta format like {{1}}, {{2}}
				10. Make messages visually beautiful and easy to read
				11. Use proper line breaks where needed
				12. Avoid robotic or generic wording
				13. Create emotionally engaging and premium wording
				14. Use emojis only if they improve readability naturally
				15. The body must start with a professional opening line or greeting and use proper paragraph spacing, line breaks, and formatting to make the template visually clean and premium on WhatsApp
				

				TEMPLATE TYPE RULES:
				Allowed values:
				- MARKETING
				- UTILITY
			

				REQUESTED TEMPLATE TYPE:
				%s
			
				USER INPUT:
				%s

				RETURN FORMAT:

				{
				  "templateName":"",
				  "templateType":"",
				  "language":"",
				  "body":"",
				  "footer":"",
				  "buttons":[]
				}
				""".formatted(request.getTemplateType(), request.getUserPrompt());
	}
}
