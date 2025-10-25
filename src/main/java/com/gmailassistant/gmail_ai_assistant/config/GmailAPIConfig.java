// src/main/java/com/gmailassistant/config/GmailApiConfig.java
package com.gmailassistant.gmail_ai_assistant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GmailAPIConfig {
    
    @Value("${gmail.api.client-id}")
    private String clientId;
    
    @Value("${gmail.api.client-secret}")
    private String clientSecret;
    
    @Value("${gmail.api.redirect-uri}")
    private String redirectUri;
    
    @Value("${gmail.api.scopes}")
    private String scopes;
    
    @Value("${ai.api.key}")
    private String aiApiKey;
    
    @Value("${ai.api.url}")
    private String aiApiUrl;
    
    // Getters
    public String getClientId() { return clientId; }
    public String getClientSecret() { return clientSecret; }
    public String getRedirectUri() { return redirectUri; }
    public String getScopes() { return scopes; }
    public String getAiApiKey() { return aiApiKey; }
    public String getAiApiUrl() { return aiApiUrl; }
}