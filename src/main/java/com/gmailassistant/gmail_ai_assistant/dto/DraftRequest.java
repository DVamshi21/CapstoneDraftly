package com.gmailassistant.gmail_ai_assistant.dto;

import com.gmailassistant.gmail_ai_assistant.entity.EmailDraft;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class DraftRequest {
    
    @NotBlank(message = "Message ID is required")
    @Schema(description = "Gmail message ID", example = "12345abcde")
    private String messageId;
    
    @NotNull(message = "Tone is required")
    @Schema(description = "Desired tone for the reply", example = "FORMAL")
    private EmailDraft.EmailTone tone;
    
    // Simple constructor for required fields
    public DraftRequest(String messageId, EmailDraft.EmailTone tone) {
        this.messageId = messageId;
        this.tone = tone;
    }
    
    // Default constructor for JSON deserialization
    public DraftRequest() {}
    
    // Getters and Setters
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
    
    public EmailDraft.EmailTone getTone() { return tone; }
    public void setTone(EmailDraft.EmailTone tone) { this.tone = tone; }
}

