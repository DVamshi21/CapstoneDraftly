// src/main/java/com/gmailassistant/service/impl/DraftServiceImpl.java
package com.gmailassistant.gmail_ai_assistant.service.impl;

import com.gmailassistant.gmail_ai_assistant.dto.DraftRequest;
import com.gmailassistant.gmail_ai_assistant.entity.EmailDraft;
import com.gmailassistant.gmail_ai_assistant.entity.User;
import com.gmailassistant.gmail_ai_assistant.repository.DraftRepository;
import com.gmailassistant.gmail_ai_assistant.repository.UserRepository;
import com.gmailassistant.gmail_ai_assistant.service.AIService;
import com.gmailassistant.gmail_ai_assistant.service.DraftService;
import com.gmailassistant.gmail_ai_assistant.service.GmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DraftServiceImpl implements DraftService {
    
    private final DraftRepository draftRepository;
    private final UserRepository userRepository;
    private final GmailService gmailService;
    private final AIService aiService;
    
    @Autowired
    public DraftServiceImpl(DraftRepository draftRepository, 
                          UserRepository userRepository,
                          GmailService gmailService,
                          AIService aiService) {
        // Dependency Injection - following DIP
        this.draftRepository = draftRepository;
        this.userRepository = userRepository;
        this.gmailService = gmailService;
        this.aiService = aiService;
    }
    
    @Override
    public EmailDraft generateDraft(DraftRequest request) {
        // DRY: Extract user validation
        User user = getUserById(1L); // Simplified - use security context in production
        
        validateGmailAccess(user);
        
        var message = gmailService.getEmailDetails(user.getGmailAccessToken(), request.getMessageId());
        String emailBody = extractEmailBody(message);
        
        String aiReply = aiService.generateReplyDraft(emailBody, request.getTone());
        
        EmailDraft draft = createDraftEntity(request, emailBody, aiReply, user);
        return draftRepository.save(draft);
    }
    
    @Override
    public EmailDraft approveDraft(Long draftId) {
        EmailDraft draft = getDraftById(draftId);
        User user = draft.getUser();
        
        gmailService.sendReply(
            user.getGmailAccessToken(),
            draft.getThreadId(),
            draft.getSender(),
            draft.getSubject(),
            getFinalDraftContent(draft)
        );
        
        draft.markAsSent();
        return draftRepository.save(draft);
    }
    
    @Override
    public void rejectDraft(Long draftId) {
        EmailDraft draft = getDraftById(draftId);
        draft.markAsRejected();
        draftRepository.save(draft);
    }
    
    @Override
    public List<EmailDraft> getUserDrafts(Long userId) {
        return draftRepository.findByUserId(userId);
    }
    
    @Override
    public EmailDraft updateDraftContent(Long draftId, String content) {
        EmailDraft draft = getDraftById(draftId);
        draft.updateContent(content);
        return draftRepository.save(draft);
    }
    
    // DRY: Extract common validation logic
    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
    }
    
    private EmailDraft getDraftById(Long draftId) {
        return draftRepository.findById(draftId)
                .orElseThrow(() -> new RuntimeException("Draft not found: " + draftId));
    }
    
    private void validateGmailAccess(User user) {
        if (!gmailService.validateToken(user.getGmailAccessToken())) {
            throw new RuntimeException("Invalid Gmail access token for user: " + user.getEmail());
        }
    }
    
    private String getFinalDraftContent(EmailDraft draft) {
        return Optional.ofNullable(draft.getFinalDraft())
                .orElse(draft.getGeneratedDraft());
    }
    
    private EmailDraft createDraftEntity(DraftRequest request, String emailBody, String aiReply, User user) {
        EmailDraft draft = new EmailDraft();
        draft.setOriginalMessageId(request.getMessageId());
        draft.setOriginalBody(emailBody);
        draft.setGeneratedDraft(aiReply);
        draft.setTone(request.getTone());
        draft.setUser(user);
        return draft;
    }
    
    private String extractEmailBody(com.google.api.services.gmail.model.Message message) {
        // KISS: Simplified extraction
        return "Email content from: " + message.getId();
    }
}