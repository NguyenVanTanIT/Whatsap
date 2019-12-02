package com.example.whatsap.Chat;
public class ChatObject {
    private String message;
    private String currentUser;
    private String currentPhone;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentPhone() {
        return currentPhone;
    }

    public void setCurrentPhone(String currentPhone) {
        this.currentPhone = currentPhone;
    }

    public ChatObject(String message, String currentUser, String currentPhone) {
        this.message = message;
        this.currentUser = currentUser;
        this.currentPhone = currentPhone;
    }
}
