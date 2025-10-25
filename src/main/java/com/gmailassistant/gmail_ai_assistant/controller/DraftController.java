// src/main/java/com/gmailassistant/controller/DraftController.java
package com.gmailassistant.gmail_ai_assistant.controller;

import com.gmailassistant.gmail_ai_assistant.dto.ApiResponse;
import com.gmailassistant.gmail_ai_assistant.dto.DraftRequest;
import com.gmailassistant.gmail_ai_assistant.dto.DraftResponse;
import com.gmailassistant.gmail_ai_assistant.service.DraftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/drafts")
@Tag(name = "Draft Management", description = "APIs for managing email drafts")
public class DraftController {
    
    private final DraftService draftService;
    
    @Autowired
    public DraftController(DraftService draftService) {
        this.draftService = draftService;
    }
    
    @PostMapping
    @Operation(summary = "Generate a draft", description = "Generate an AI-powered email draft")
    public ResponseEntity<ApiResponse<DraftResponse>> generateDraft(
            @Valid @RequestBody DraftRequest request) {
        
        var draft = draftService.generateDraft(request);
        return ResponseEntity.ok(ApiResponse.success("Draft generated successfully", 
                DraftResponse.fromEntity(draft)));
    }
    
    @PutMapping("/{draftId}/approve")
    @Operation(summary = "Approve a draft", description = "Approve and send an email draft")
    public ResponseEntity<ApiResponse<DraftResponse>> approveDraft(@PathVariable Long draftId) {
        var draft = draftService.approveDraft(draftId);
        return ResponseEntity.ok(ApiResponse.success("Draft approved and sent successfully", 
                DraftResponse.fromEntity(draft)));
    }
    
    // Other endpoints follow same pattern...
}