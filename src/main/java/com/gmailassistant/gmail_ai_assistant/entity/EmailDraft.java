package com.gmailassistant.gmail_ai_assistant.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "email_drafts")
public class EmailDraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_message_id")
    private String originalMessageId;

    @Column(name = "thread_id")
    private String threadId;

    private String sender;
    private String subject;

    @Column(name = "original_body", length = 10000)
    private String originalBody;

    @Column(name = "generated_Draft", length = 10000)
    private String generatedDraft;

    @Column(name = "final_draft", length = 10000)
    private String finalDraft;



    @Enumerated(EnumType.STRING)
    private DraftStatus status;

    @Enumerated(EnumType.STRING)
    private EmailTone tone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name="updated_at")
    public LocalDateTime updatedAt;

    @Column(name="sent_at")
    private LocalDateTime sentAt;

    public enum DraftStatus {
        PENDING, APPROVED, REJECTED, SENT
    }

    public enum EmailTone {
        FORMAL, CONCISE, FRIENDLY 
    }
    // Constructors, Getters, and Setters   
    public EmailDraft() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = DraftStatus.PENDING;
        this.tone = EmailTone.FORMAL;
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;   
    }
    public String getOriginalMessageId() {
        return originalMessageId;
    }
    public void setOriginalMessageId(String originalMessageId) {
        this.originalMessageId = originalMessageId;
    }
    public String getThreadId() {
        return threadId;
    }
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getOriginalBody() {
        return originalBody;
    }
    public void setOriginalBody(String originalBody) {
        this.originalBody = originalBody;
    }
    public String getGeneratedDraft() {
        return generatedDraft;
    }
    public void setGeneratedDraft(String generatedDraft) {
        this.generatedDraft = generatedDraft;
    }
    public String getFinalDraft() {
        return finalDraft;
    }       
    public void setFinalDraft(String finalDraft){
        this.finalDraft = finalDraft;
    }
    public DraftStatus getStatus(){
        return status;
    }
   
    public EmailTone getTone(){
        return tone;
    }
    public void setTone(EmailTone tone){
        this.tone = tone;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }
    // private LocalDateTime getSentAt(){
    //     return sentAt;
    // }
    public void setSentAt(LocalDateTime sentAt){
        this.sentAt = sentAt;
    }
    public void markAsSent() {
        this.status = DraftStatus.SENT;
        this.sentAt = LocalDateTime.now();
    }
    
    public void markAsRejected() {
        this.status = DraftStatus.REJECTED;
    }
    
    public void updateContent(String content) {
        this.finalDraft = content;
        this.status = DraftStatus.APPROVED;
    }
    
    public boolean isSent() {
        return this.status == DraftStatus.SENT;
    }
    
    public boolean canBeEdited() {
        return this.status == DraftStatus.PENDING || this.status == DraftStatus.APPROVED;
    }
    
    // Remove setStatus to control state transitions
    public void setStatus(DraftStatus status) {
        // Only allow controlled state transitions
        this.status = status;
    }

}
