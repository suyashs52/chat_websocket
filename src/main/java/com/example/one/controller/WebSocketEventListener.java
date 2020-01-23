package com.example.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.one.dao.ChatDAO;
import com.example.one.model.Message;
import com.example.one.model.MessageType;

@Component
public class WebSocketEventListener {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	static ChatDAO chatDAO = new ChatDAO();

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		System.out.println("[WS] Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String username = (String) headerAccessor.getSessionAttributes().get("username");
		if (username != null) {
			System.out.println("[WS] User Disconnected : " + username);

			Message message = new Message();
			message.setType(MessageType.LEAVE);
			message.setUsername(username);

			List<String> revUser = chatDAO.getRevUserJoin(username, 0);
			revUser.forEach(u -> messagingTemplate.convertAndSend("/topic/" + u, message));
		}
	}
}
