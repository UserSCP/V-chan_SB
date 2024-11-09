package com.example.spring_boot_web_socket.service;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class ChatHandler extends TextWebSocketHandler {
	
	
	private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	private ArrayList<TextMessage> listMessage = new ArrayList<TextMessage>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        for(TextMessage t:listMessage) {
        	broadCastMessage(t);
        }
        
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String mensageReecivido = message.getPayload();
		broadCastMessage(mensageReecivido);
		String keyBoard = "websockect";
		if(mensageReecivido.toLowerCase().contains(keyBoard)) {
			broadCastMessage("Web Socket corre en el puerto 80 y 443");
			broadCastMessage("Es un protocolo de comunicacion que agiliza la comunicacio entre des socket o mas, donde no es necesario mandarle un request al servidor cada vez que se envia un 'mensage'");
		}
		listMessage.add(message);
	}
	public void broadCastMessage(String message)throws Exception {
		for(WebSocketSession s:sessions) {
			if(s.isOpen()) {
				s.sendMessage(new TextMessage(message));
			}
		}
	}
	public void broadCastMessage(TextMessage message)throws Exception {
		for(WebSocketSession s:sessions) {
			if(s.isOpen()) {
				s.sendMessage(message);
			}
		}
	}


}
