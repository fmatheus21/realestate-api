package com.firecode.app.controller.error;

public class MessageError {

    private final String messageUser;
    private final String messageDeveloper;

    public MessageError(String messageUser, String messageDeveloper) {
        this.messageUser = messageUser;
        this.messageDeveloper = messageDeveloper;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public String getMessageDeveloper() {
        return messageDeveloper;
    }

}
