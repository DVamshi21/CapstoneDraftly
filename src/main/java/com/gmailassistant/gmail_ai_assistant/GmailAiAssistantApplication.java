// src/main/java/com/gmailassistant/GmailAiAssistantApplication.java
package com.gmailassistant.gmail_ai_assistant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Gmail AI Assistant API", version = "1.0", description = "AI-powered Gmail Assistant"))
public class GmailAiAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailAiAssistantApplication.class, args);
    }
}