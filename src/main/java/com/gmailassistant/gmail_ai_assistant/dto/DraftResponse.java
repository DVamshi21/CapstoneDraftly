package com.gmailassistant.gmail_ai_assistant.dto;

import java.time.LocalDateTime;

import com.gmailassistant.gmail_ai_assistant.entity.EmailDraft;

public class DraftResponse {
    private Long id;
    private String subject;
    private String generatedDraft;
    private String finalDraft;
    private EmailDraft.DraftStatus status;
    private EmailDraft.EmailTone tone;
    private LocalDateTime createdAt;
    
    // Static factory method - following DRY
    public static DraftResponse fromEntity(EmailDraft draft) {
        DraftResponse response = new DraftResponse();
        response.id = draft.getId();
        response.subject = draft.getSubject();
        response.generatedDraft = draft.getGeneratedDraft();
        response.finalDraft = draft.getFinalDraft();
        response.status = draft.getStatus();
        response.tone = draft.getTone();
        response.createdAt = draft.getCreatedAt();
        return response;
    }
    
    // Getters only - immutable response
    public Long getId() { return id; }
    public String getSubject() { return subject; }
    public String getGeneratedDraft() { return generatedDraft; }
    public String getFinalDraft() { return finalDraft; }
    public EmailDraft.DraftStatus getStatus() { return status; }
    public EmailDraft.EmailTone getTone() { return tone; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
