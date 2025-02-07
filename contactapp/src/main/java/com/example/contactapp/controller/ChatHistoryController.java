package com.example.contactapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.contactapp.model.ChatMessage;
import com.example.contactapp.repository.ChatMessageRepository;
import com.example.contactapp.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatHistoryController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/history")
    public List<ChatMessage> getChatHistory() {
        return chatService.getChatHistory();
    }
}