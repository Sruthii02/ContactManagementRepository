package com.example.contactapp.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.contactapp.model.ChatMessage;
import com.example.contactapp.repository.ChatMessageRepository;

public class ChatHandler extends TextWebSocketHandler {
    private static final Set<WebSocketSession> sessions = new HashSet<>();
    private final ChatMessageRepository messageRepository;

    public ChatHandler(ChatMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(message.getPayload());
        messageRepository.save(chatMessage);

        for (WebSocketSession s : sessions) {
            s.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }
}
