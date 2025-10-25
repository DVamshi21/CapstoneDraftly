package com.gmailassistant.gmail_ai_assistant.service;


import java.util.List;
import com.google.api.services.gmail.model.Message;


public interface GmailService {
    List<Message> fetchUnreadEmails(String accessToken);
    Message getEmailDetails(String accessToken, String messageId);
    void sendReply(String accessToken, String threadId, String to, String subject, String body);
    boolean validateToken(String accessToken);
}
