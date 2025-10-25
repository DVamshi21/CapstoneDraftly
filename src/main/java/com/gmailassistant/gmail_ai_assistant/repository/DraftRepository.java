// src/main/java/com/gmailassistant/repository/DraftRepository.java
package com.gmailassistant.gmail_ai_assistant.repository;

import com.gmailassistant.gmail_ai_assistant.entity.EmailDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DraftRepository extends JpaRepository<EmailDraft, Long> {
    List<EmailDraft> findByStatus(String status);
    List<EmailDraft> findByUserId(Long userId);
}