
package com.gmailassistant.gmail_ai_assistant.exception;

public class GmailAssistantException extends RuntimeException {
    private final String errorCode;
    
    public GmailAssistantException(String message) {
        super(message);
        this.errorCode = "GENERIC_ERROR";
    }
    
    public GmailAssistantException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}