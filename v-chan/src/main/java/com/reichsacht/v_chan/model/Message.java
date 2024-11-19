package com.reichsacht.v_chan.model;

public class Message {
    private String type;    // system, sender, receiver
    private String sender;  // Nombre del remitente
    private String content; // Contenido del mensaje

    // Constructor
    public Message(String type, String sender, String content) {
        this.type = type;
        this.sender = sender;
        this.content = content;
    }

    // Getters y setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}