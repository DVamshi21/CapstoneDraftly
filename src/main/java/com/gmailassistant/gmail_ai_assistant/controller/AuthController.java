package com.gmailassistant.gmail_ai_assistant.controller;

// src/main/java/com/gmailassistant/controller/AuthController.java


import com.gmailassistant.gmail_ai_assistant.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "APIs for Gmail authentication")
public class AuthController {
    
    @GetMapping("/gmail")
    @Operation(summary = "Initiate Gmail OAuth", description = "Get the URL to initiate Gmail OAuth flow")
    public ResponseEntity<ApiResponse<String>> initiateGmailAuth() {
        // Return Gmail OAuth URL
        String authUrl = "https://accounts.google.com/o/oauth2/auth?client_id=YOUR_CLIENT_ID&redirect_uri=YOUR_REDIRECT_URI&scope=YOUR_SCOPES&response_type=code";
        return ResponseEntity.ok(ApiResponse.success("OAuth URL generated", authUrl));
    }
    
    @GetMapping("/callback")
    @Operation(summary = "OAuth Callback", description = "Handle OAuth callback from Gmail")
    public ResponseEntity<ApiResponse<String>> handleCallback(
            @RequestParam String code,
            @RequestParam(required = false) String state) {
        try {
            // Handle OAuth callback and store tokens
            return ResponseEntity.ok(ApiResponse.success("Authentication successful!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Authentication failed: " + e.getMessage()));
        }
    }
    
    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Revoke tokens and logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        return ResponseEntity.ok(ApiResponse.success("Logged out successfully"));
    }
}
