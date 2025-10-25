// src/main/java/com/gmailassistant/service/impl/GmailServiceImpl.java
package com.gmailassistant.gmail_ai_assistant.service.impl;

import com.google.auth.oauth2.AccessToken;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import org.springframework.stereotype.Service;
import com.gmailassistant.gmail_ai_assistant.service.GmailService;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class GmailServiceImpl implements GmailService {
    
    private static final String APPLICATION_NAME = "Gmail AI Assistant";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    
    @Override
    public List<Message> fetchUnreadEmails(String accessToken) {
        try {
            Gmail service = createGmailService(accessToken);
            ListMessagesResponse response = service.users().messages()
                    .list("me")
                    .setQ("is:unread")
                    .execute();
            
            return response.getMessages() != null ? response.getMessages() : Collections.emptyList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch unread emails", e);
        }
    }
    
    @Override
    public Message getEmailDetails(String accessToken, String messageId) {
        try {
            Gmail service = createGmailService(accessToken);
            return service.users().messages().get("me", messageId).setFormat("full").execute();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get email details: " + messageId, e);
        }
    }
    
    @Override
    public void sendReply(String accessToken, String threadId, String to, String subject, String body) {
        try {
            Gmail service = createGmailService(accessToken);
            // DRY: Reuse message creation logic
            Message message = createReplyMessage(threadId, to, subject, body);
            service.users().messages().send("me", message).execute();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send reply", e);
        }
    }
    
    @Override
    public boolean validateToken(String accessToken) {
        // KISS: Simple validation - in production, use proper token validation
        return accessToken != null && !accessToken.trim().isEmpty();
    }
    
private Gmail createGmailService(String accessToken) throws GeneralSecurityException, java.io.IOException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

    // Create a GoogleCredentials instance from the raw access token and adapt it for the client
    AccessToken token = new AccessToken(accessToken, null);
    GoogleCredentials credentials = GoogleCredentials.create(token);
    HttpCredentialsAdapter adapter = new HttpCredentialsAdapter(credentials);

    return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, adapter)
            .setApplicationName(APPLICATION_NAME)
            .build();
}
    
    private Message createReplyMessage(String threadId, String to, String subject, String body) {
        // Simplified message creation - focus on core functionality first
        Message message = new Message();
        message.setThreadId(threadId);
        // In production, implement proper MIME encoding
        return message;
    }
}