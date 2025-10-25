package com.gmailassistant.gmail_ai_assistant.service.impl;

import org.springframework.stereotype.Service;

import com.gmailassistant.gmail_ai_assistant.entity.EmailDraft;
import com.gmailassistant.gmail_ai_assistant.service.AIService;

@Service
public class AIServiceImpl implements AIService {
    private static final String BASE_REPLY = "Thank you for your email. I appreciate your reaching out to me. I will get back to you shortly with a detailed response."; 

    @Override
    public String generateReplyDraft(String originalEmail, EmailDraft.EmailTone tone) {
        // Use tone-specific phrase to avoid unused private method and produce better drafts
        String tonePhrase = getToneSpecificPhrase(tone);
        return BASE_REPLY + " " + tonePhrase + " (Tone: " + tone.name() + ")";
    }
    @Override
    public void learnFromUserStyle(String userEmail){
        // Mock implementation: In a real scenario, this would involve training a model on the user's email style
        System.out.println("Learning from user email style: " + userEmail);
    }
    private String getToneSpecificPhrase(EmailDraft.EmailTone tone) {
        switch (tone) {
            case FORMAL:
                return "I hope this message finds you well.";
            case CONCISE:
                return "Hey there! Hope you're doing great.";
            case FRIENDLY:
                return "It's always a pleasure to hear from you!";
            default:
                return "I'll respond your message shortly";
        }
    }
    
}
