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
				You are an expert WhatsApp Business template writer.

				Return ONLY valid JSON.

				RULES:
				- Output must be valid JSON only
				- No markdown
				- No explanations
				- No extra text outside JSON
				- Meta WhatsApp compatible
				- templateName is mandatory
				- templateType must match requested type
				- Detect language automatically
				- Use only Meta variables like {{1}}, {{2}}, {{3}}
				- Keep content concise and engaging
				- Use proper line breaks (\\n)
				- Use emojis naturally where appropriate
				- Make the message visually attractive on WhatsApp
				- Use a professional and premium tone
				- Add a strong call-to-action when suitable
				- Footer may be empty
				- Buttons should be added only when useful
				- Generate unique content every time
				- Avoid generic AI wording

				MESSAGE STYLE:
				- Professional greeting
				- Proper spacing between sections
				- Easy to read on mobile
				- WhatsApp friendly formatting
				- Marketing templates should feel promotional
				- Utility templates should feel clear and informative

				RETURN JSON FORMAT:

				{
				  "templateName": "",
				  "templateType": "",
				  "language": "",
				  "body": "",
				  "footer": "",
				  "buttons": []
				}

				TEMPLATE TYPE:
				%s

				USER REQUEST:
				%s
				""".formatted(request.getTemplateType(), request.getUserPrompt());
	}
}