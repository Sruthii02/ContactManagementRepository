package com.example.contactapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.contactapp.model.ChatMessage;
import com.example.contactapp.repository.ChatMessageRepository;

@Service
public class ChatService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void saveMessage(ChatMessage message) {
        chatMessageRepository.save(message);
    }

    public List<ChatMessage> getChatHistory() {
        return chatMessageRepository.findAll();
    }
}
