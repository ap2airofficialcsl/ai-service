//package com.ai.aiservice.whatsapp.template.util;
//
//import org.springframework.stereotype.Component;
//
//import com.ai.aiservice.whatsapp.template.dto.TemplateGenerateRequest;
//
//@Component
//public class TemplatePromptBuilder {
//
//	public String build(TemplateGenerateRequest request) {
//
//		return """
//				You are an expert WhatsApp template writer.
//
//				Return ONLY valid JSON.
//
//				Rules:
//				- Meta compatible
//				- templateName required
//				- Variables only {{1}}, {{2}}
//				- Detect language automatically
//				- Use requested template type
//				- Keep message professional and concise
//
//				Format:
//				{
//				 "templateName":"",
//				 "templateType":"",
//				 "language":"",
//				 "body":"",
//				 "footer":"",
//				 "buttons":[]
//				}
//
//				Template Type: %s
//				User Input: %s
//								""".formatted(request.getTemplateType(), request.getUserPrompt());
//	}
//}
package com.ai.aiservice.whatsapp.template.util;

import org.springframework.stereotype.Component;

import com.ai.aiservice.whatsapp.template.dto.TemplateGenerateRequest;

@Component
public class TemplatePromptBuilder {

	public String build(TemplateGenerateRequest request) {

		return """
						You are an elite WhatsApp Business template writer and marketing copywriter.

						Return ONLY valid JSON.

						RULES:
						- Valid JSON only
						- No markdown
						- No explanations
						- No text outside JSON
						- Meta WhatsApp compatible
						- templateName is required
						- Use requested templateType
						- Detect language automatically
						- Use variables only as {{1}}, {{2}}, {{3}}
						- Generate unique content every time
						- Never sound robotic
						- Never generate boring corporate text

						STYLE:
						- Attractive and engaging
						- Premium and professional
						- Use emojis naturally
						- Proper spacing using \\n
						- Mobile friendly formatting
						- Strong opening line
						- Clear call-to-action
						- Marketing templates should feel exciting
						- Utility templates should feel clear and informative
						- Authentication templates should be short and direct
				CRITICAL INSTRUCTIONS:

				You must return ONLY a valid JSON object.

				Do not think aloud.
				Do not explain your reasoning.
				Do not describe your process.
				Do not write notes.
				Do not write analysis.
				Do not write examples.
				Do not write markdown.
				Do not write ```json.

				Your response must start with {
				Your response must end with }

				If you return anything except JSON, the response will be rejected.
						RETURN FORMAT:

						{
						  "templateName":"",
						  "templateType":"",
						  "language":"",
						  "body":"",
						  "footer":"",
						  "buttons":[]
						}

						TEMPLATE TYPE:
						%s

						USER REQUEST:
						%s
						""".formatted(request.getTemplateType(), request.getUserPrompt());
	}

}
