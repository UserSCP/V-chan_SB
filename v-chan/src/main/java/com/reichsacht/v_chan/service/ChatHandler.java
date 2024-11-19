package com.reichsacht.v_chan.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reichsacht.v_chan.model.Message;

@Service
public class ChatHandler extends TextWebSocketHandler {

	private static final List<WebSocketSession> sessions = new ArrayList<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	    sessions.add(session);
	    String username = session.getPrincipal().getName();
	    String welcomeMessage = username + " se ha unido.";
	    sendAllMessages(welcomeMessage);
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	    sessions.remove(session);
	    String username = session.getPrincipal().getName();
	    String goodbyeMessage = username + " ha salido.";
	    sendAllMessages(goodbyeMessage);
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    String username = session.getPrincipal().getName();
	    String payload = message.getPayload();
	    Message msg;
	    if (payload.startsWith("SYSTEM:")) {
	        String content = payload.substring(7).trim();
	        msg = new Message("system", "System", content);
	    } else {
	        msg = new Message("sender", username, payload);
	    }

	    for (WebSocketSession wsSession : sessions) {
	        if (wsSession.isOpen()) {
	            if (!"system".equals(msg.getType())) {
	                String type = wsSession == session ? "sender" : "receiver";
	                msg.setType(type);
	            }
	            wsSession.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(msg)));
	        }
	    }
	}
	public void sendAllMessages(String content) throws IOException {
	    Message systemMessage = new Message("system", "System", content);
	    String systemMessageJson = new ObjectMapper().writeValueAsString(systemMessage);

	    for (WebSocketSession wsSession : sessions) {
	        if (wsSession.isOpen()) {
	            wsSession.sendMessage(new TextMessage(systemMessageJson));
	        }
	    }
	}

}