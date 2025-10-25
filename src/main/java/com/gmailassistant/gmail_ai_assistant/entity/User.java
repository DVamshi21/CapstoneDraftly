package com.gmailassistant.gmail_ai_assistant.entity;


import jakarta.persistence.*;
import java.util.List;


import java.util.ArrayList;
import java.time.LocalDateTime ;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;


    @Column(name = "gmail_access_token", length = 2000)
    private String gmailAccessToken;

    @Column(name="gmail_refresh_token")
    private String gmailRefreshToken;

    @Column(name = "token_expiry")
    private LocalDateTime tokenExpiry;

    @Column(length = 2000)
    private String preferences;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name="updatedAt")
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<EmailDraft> drafts = new ArrayList<>();

    //constructors
    public User() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public User(String email){
        this();
        this.email = email;
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();   
    }

    // Getters and Setters
    public Long getId() {
        return id;      
    }
    public void setId(Long id) {
        this.id = id;
    }   
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }   
    public String getGmailAccessToken() {
        return gmailAccessToken;
    }
    public void setGmailAccessToken(String gmailAccessToken) {
        this.gmailAccessToken = gmailAccessToken;
    }
    public String getGmailRefreshToken() {
        return gmailRefreshToken;
    }
    public void setGmailRefreshToken(String gmailRefreshToken) {
        this.gmailRefreshToken = gmailRefreshToken; 
    }   
    public LocalDateTime getTokenExpiry() {
        return tokenExpiry;
    }
    public void setTokenExpiry(LocalDateTime tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }
    public String getPreferences() {
        return preferences;
    }
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }   
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public List<EmailDraft> getDrafts() {
        return drafts;
    }
    public void setDrafts(List<EmailDraft> drafts) {
        this.drafts = drafts;
    }




    
}
