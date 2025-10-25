package com.gmailassistant.gmail_ai_assistant.service;

import java.util.List;
import com.gmailassistant.gmail_ai_assistant.entity.EmailDraft;
import com.gmailassistant.gmail_ai_assistant.dto.DraftRequest;




public interface DraftService {
    EmailDraft generateDraft(DraftRequest request);
    EmailDraft approveDraft(Long draftId);
    void rejectDraft(Long draftId);
    List<EmailDraft> getUserDrafts(Long userId);
    EmailDraft updateDraftContent(Long draftId, String content);
}
