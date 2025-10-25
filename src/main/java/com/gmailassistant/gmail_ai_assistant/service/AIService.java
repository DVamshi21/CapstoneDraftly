package com.gmailassistant.gmail_ai_assistant.service;

import com.gmailassistant.gmail_ai_assistant.entity.EmailDraft;

public interface AIService {
    String generateReplyDraft(String originalEmail, EmailDraft.EmailTone tone);
    void learnFromUserStyle(String userEmail);
    
}
