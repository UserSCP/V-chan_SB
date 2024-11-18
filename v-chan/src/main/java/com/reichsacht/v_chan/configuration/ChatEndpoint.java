package com.reichsacht.v_chan.configuration;

import java.io.IOException;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
@ServerEndpoint("/ws/chat")
public class ChatEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("New WebSocket connection: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session)  {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Authenticated user: " + username);
        String fullMessage = username + ": " + message;
        for (Session s : session.getOpenSessions()) {
            try {
                s.getBasicRemote().sendText(fullMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket closed: " + session.getId());
    }
}

